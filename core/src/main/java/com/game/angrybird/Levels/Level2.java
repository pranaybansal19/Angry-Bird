package com.game.angrybird.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Birds.*;
import com.game.angrybird.Levels.Complete_Fail.LevelCompleteScreen;
import com.game.angrybird.Levels.Complete_Fail.LevelFailScreen;
import com.game.angrybird.Levels.Pause.PauseScreen;
import com.game.angrybird.Materials.*;
import com.game.angrybird.Pigs.Pig1;
import com.game.angrybird.Pigs.Pig2;
import com.game.angrybird.Pigs.Pig3;

import java.util.Objects;

public class Level2 implements Level, Screen {

    private AngryBird game;
    private PauseScreen pauseScreen;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Stage stage;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private ShapeRenderer shapeRenderer;

    private MouseJoint mouseJoint;
    private MouseJointDef mouseJointDef;

    private Texture background;
    private Image pause;

    private Bird redBird, yellowBird, blueBird, blackBird;
    private Array<Bird> birds = new Array<>();
    private Array<Body> birdsBody = new Array<>();

    private Material wood, glass;
    private Array<Body> woodHorizontalPlank = new Array<>();
    private Array<Body> woodVerticalPlank = new Array<>();
    private Array<Body> woodBox = new Array<>();
    private Array<Body> glassHorizontalPlank = new Array<>();
    private Array<Body> glassVerticalPlank = new Array<>();
    private Array<Body> glassBox = new Array<>();

    private Pig1 pig1;
    private Array<Body> pig1List = new Array<>();

    private Pig2 pig2;
    private Array<Body> pig2List = new Array<>();

    private Pig3 pig3;
    private Array<Body> pig3List = new Array<>();

    int birds_left = 4;
    private boolean birdsLeftCheckStarted = false;


    private Vector2 initialTouch;

    private SlingShot slingShot;

    private Ground ground;

    private Body groundBody;

    GestureDetector gestureDetector;
    InputMultiplexer multiplexer;

    private Array<Body> projectileBodies = new Array<>();
    private Array<String> projectileBirdNames = new Array<>();

    BodyDef dynamicBodyDef = new BodyDef();
    BodyDef staticBodyDef = new BodyDef();

    private boolean isDragging = false;

    private CollisionListener collisionListener;


    public Level2(AngryBird game) {
        this.game = game;
        pauseScreen = new PauseScreen(this);

        world = new World(new Vector2(0f, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        viewport = new StretchViewport(50, 50, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);

        ground = new Ground(world);

        redBird = new RedBird(world, batch);
        yellowBird = new YellowBird(world, batch);
        blueBird = new BlueBird(world, batch);
        blackBird = new BlackBird(world, batch);

        pig1 = new Pig1(world, batch);
        pig2 = new Pig2(world, batch);
        pig3 = new Pig3(world, batch);

        wood = new Wood(world, batch);
        glass = new Glass(world, batch);

        slingShot = new SlingShot(viewport, stage);

        dynamicBodyDef.type = BodyDef.BodyType.DynamicBody;
        staticBodyDef.type = BodyDef.BodyType.StaticBody;

        collisionListener = new CollisionListener(world);
        world.setContactListener(collisionListener);

        createGesture();
        createLevel();

    }

    @Override
    public AngryBird getGame() {
        return game;
    }

    private void createProjectile(Vector2 position) {
        BodyDef projectileBodyDef = new BodyDef();
        projectileBodyDef.type = BodyDef.BodyType.DynamicBody;
        projectileBodyDef.position.set(position);

        Body body = birdsBody.get(0);
        Bird bird = birds.get(0);

        if (bird instanceof RedBird) {
            Body b = redBird.create(projectileBodyDef, position.x, position.y, 1.1f);
            b.setUserData("projectile_bird");

            projectileBodies.add(b);
            projectileBirdNames.add("red");
        } else if (bird instanceof BlueBird) {
            Body b = blueBird.create(projectileBodyDef, position.x, position.y, 0.94f);
            b.setUserData("projectile_bird");

            projectileBodies.add(b);
            projectileBirdNames.add("blue");
        } else if (bird instanceof BlackBird) {
            Body b = blackBird.create(projectileBodyDef, position.x, position.y, 1.5f);
            b.setUserData("projectile_bird");

            projectileBodies.add(b);
            projectileBirdNames.add("black");
        } else if (bird instanceof YellowBird) {
            Body b = yellowBird.create(projectileBodyDef, position.x, position.y, 0.97f);
            b.setUserData("projectile_bird");

            projectileBodies.add(b);
            projectileBirdNames.add("yellow");
        }

        birdsBody.removeIndex(0);
        birds.removeIndex(0);

        world.destroyBody(body);

    }

    private void createMouseJoint(Vector2 target) {
        mouseJointDef = new MouseJointDef();
        mouseJointDef.bodyA = groundBody;
        mouseJointDef.bodyB = projectileBodies.get(projectileBodies.size - 1);
        mouseJointDef.collideConnected = true;
        mouseJointDef.target.set(target);
        mouseJointDef.maxForce = 10000.0f * projectileBodies.get(projectileBodies.size - 1).getMass();
        mouseJoint = (MouseJoint) world.createJoint(mouseJointDef);
    }

    private void createGesture() {
        gestureDetector = new GestureDetector(new GestureDetector.GestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                if (birds.isEmpty()) {
                    return false;
                }

                Vector3 worldCoordinates = camera.unproject(new Vector3(x, y, 0));
                initialTouch = new Vector2(worldCoordinates.x, worldCoordinates.y);
                isDragging = false;

                System.out.println(initialTouch.x + " " + initialTouch.y);

//                createProjectile(initialTouch);
//                createMouseJoint(initialTouch);
                return true;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                return false;
            }

            @Override
            public boolean longPress(float x, float y) {
                return false;
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                return false;
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {

                if (birds_left == 0) {
                    return false;
                }

                if (mouseJoint != null) {
                    Vector3 worldCoordinates = camera.unproject(new Vector3(x, y, 0));
                    Vector2 currentTouch = new Vector2(worldCoordinates.x, worldCoordinates.y);

                    float distance = initialTouch.dst(currentTouch);

                    if (distance > 4) {
                        Vector2 direction = currentTouch.cpy().sub(initialTouch).nor();
                        currentTouch = initialTouch.cpy().add(direction.scl(4));
                    }

                    mouseJoint.setTarget(currentTouch);

                } else {
                    Vector3 worldCoordinates = camera.unproject(new Vector3(x, y, 0));
                    Vector2 currentTouch = new Vector2(worldCoordinates.x, worldCoordinates.y);

                    isDragging = true;

                    if (checkTouched()) {
                        createProjectile(initialTouch);
                        createMouseJoint(initialTouch);
                    }

                }
                return true;
            }

            @Override
            public boolean panStop(float x, float y, int pointer, int button) {
                if (mouseJoint != null) {
                    Vector3 worldCoordinates = camera.unproject(new Vector3(x, y, 0));
                    Vector2 releasePoint = new Vector2(worldCoordinates.x, worldCoordinates.y);

                    if (initialTouch.dst(releasePoint) < 1f) {
                        Body projectileBody = projectileBodies.get(projectileBodies.size - 1);
                        if (projectileBody != null) {
                            world.destroyJoint(mouseJoint);
                            mouseJoint = null;
                            world.destroyBody(projectileBody);

                            projectileBodies.pop();
                            String name = projectileBirdNames.pop();

                            if (Objects.equals(name, "red")) {
                                birdsBody.insert(0, redBird.create(staticBodyDef, 9f, 3.5f, 1.1f));
                                birds.insert(0, redBird);
                            } else if (Objects.equals(name, "blue")) {
                                birdsBody.insert(0, blueBird.create(staticBodyDef, 7f, 3.5f, 0.94f));
                                birds.insert(0, blueBird);
                            } else if (Objects.equals(name, "black")) {
                                birdsBody.insert(0, blackBird.create(staticBodyDef, 4.6f, 4f, 1.5f));
                                birds.insert(0, blackBird);

                            } else if (Objects.equals(name, "yellow")) {
                                birdsBody.insert(0, yellowBird.create(staticBodyDef, 2, 3.5f, 0.97f));
                                birds.insert(0, yellowBird);

                            }

                        }
                    } else {
                        slingShot.launchProjectile(releasePoint, initialTouch, projectileBodies.get(projectileBodies.size - 1));
                        birds_left--;
                    }

                    if (mouseJoint != null) {
                        world.destroyJoint(mouseJoint);
                        mouseJoint = null;
                    }
                }
                return true;
            }


            @Override
            public boolean zoom(float initialDistance, float distance) {
                return false;
            }

            @Override
            public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
                return false;
            }

            @Override
            public void pinchStop() {
            }
        });

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(gestureDetector);

        Gdx.input.setInputProcessor(multiplexer);
    }

    private void drawTrajectory(Vector2 start, Vector2 velocity) {

        shapeRenderer.setProjectionMatrix(camera.combined);
        Vector2 position = new Vector2(start);
        Vector2 vel = new Vector2(velocity);
        float timeStep = 1 / 60f;

        shapeRenderer.setColor(0.6f, 0.3f, 0.1f, 1f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < 6; i++) {
            vel.add(0, -9.8f * timeStep);
            position.add(vel.x * timeStep, vel.y * timeStep);
            shapeRenderer.circle(position.x, position.y, 0.1f);
        }
        shapeRenderer.end();

    }

    public boolean checkTouched() {
        if (initialTouch.x > 12.2 && initialTouch.x < 14.2 && initialTouch.y < 11.5 && initialTouch.y > 10) {
            initialTouch.x = 13.15f;
            initialTouch.y = 10.75f;
            return true;
        }
        return false;
    }


    public void createTower() {

//        woodBox.add(wood.createQuad(dynamicBodyDef, 33, 4f, 3.5f, 4.5f));
//        woodBox.add(wood.createQuad(dynamicBodyDef, 43, 4f, 3.5f, 4.5f));
//
//        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, 33f, 8f, 9f, 2f));
//        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, 43f, 8f, 9f, 2f));
//
//        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, 38f, 10f, 9f, 2f));
//
//        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, 34.5f, 15.5f, 1.5f, 11f));
//        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, 41.5f, 15.5f, 1.5f, 11f));
//
//        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, 29.5f, 13.5f, 1.5f, 11f));
//        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, 46.5f, 13.5f, 1.5f, 11f));
//
//        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, 38f, 22f, 9f, 2f));
//
//        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, 34.5f, 29.5f, 1.5f, 11f));
//        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, 41.5f, 29.5f, 1.5f, 11f));
//
//        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, 38f, 35f, 9f, 2f));
//
//        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, 29f, 20f, 7f, 2f));
//        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, 47f, 20f, 7f, 2f));
//
//
//        pig3List.add(pig3.create(dynamicBodyDef, 38f, 12f, 2f));
//        pig2List.add(pig2.create(dynamicBodyDef, 38f, 25f, 2f));
//
//        pig1List.add(pig1.create(dynamicBodyDef, 33f, 10f, 1.5f));
//
//        pig1List.add(pig1.create(dynamicBodyDef, 43f, 10f, 1.5f));
    }

    public void createBirds() {
        birdsBody.add(redBird.create(staticBodyDef, 9f, 3.5f, 1.1f));
        birdsBody.add(blueBird.create(staticBodyDef, 7f, 3.5f, 0.94f));
        birdsBody.add(blackBird.create(staticBodyDef, 4.6f, 4f, 1.5f));
        birdsBody.add(yellowBird.create(staticBodyDef, 2, 3.5f, 0.97f));

        birds.add(redBird);
        birds.add(blueBird);
        birds.add(blackBird);
        birds.add(yellowBird);
    }

    @Override
    public void createLevel() {

        background = new Texture(Gdx.files.internal("Level 1/Background.png"));
        pause = new Image(new Texture(Gdx.files.internal("Level 1/PauseBtn.png")));

        slingShot.create(9, 2.9f, 7, 9);

        pause.setSize(2, 3);
        pause.setPosition(2, 44);


        groundBody = ground.create(staticBodyDef, 0, -7.5f, viewport.getWorldWidth(), 10);
        ground.create(staticBodyDef, 51, 25, 1, viewport.getWorldHeight());

        createTower();
        createBirds();

        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause Clicked.");
                game.setScreen(pauseScreen);
            }
        });

    }


    @Override
    public void drawLevel() {

        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        stage.addActor(pause);

        slingShot.draw();

        if (!projectileBodies.isEmpty()) {

            for (int i = 0; i < projectileBodies.size; ++i) {
                Body body = projectileBodies.get(i);
                String name = projectileBirdNames.get(i);

                if (Objects.equals(name, "red")) {
                    redBird.draw(body, 2.5f, 2.5f);
                } else if (Objects.equals(name, "blue")) {
                    blueBird.draw(body, 2f, 2f);
                } else if (Objects.equals(name, "yellow")) {
                    yellowBird.draw(body, 2f, 2.5f);
                } else if (Objects.equals(name, "black")) {
                    blackBird.draw(body, 3f, 3f);
                }
            }

        }

        for (Body body : woodHorizontalPlank) {
            wood.drawPlankHorizontal(body);
        }
        for (Body body : woodVerticalPlank) {
            wood.drawPlankVertical(body);
        }
        for (Body body : woodBox) {
            wood.drawBox(body);
        }

        for (Body body : pig1List) {
            pig1.draw(body, 3f, 3f);
        }
        for (Body body : pig2List) {
            pig2.draw(body, 4f, 4f);
        }
        for (Body body : pig3List) {
            pig3.draw(body, 4f, 4f);
        }

        for (Body body : glassHorizontalPlank) {
            glass.drawPlankHorizontal(body);
        }
        for (Body body : glassVerticalPlank) {
            glass.drawPlankVertical(body);
        }
        for (Body body : glassBox) {
            glass.drawBox(body);
        }

        for (int i = 0; i < birds.size; ++i) {
            if (birds.get(i) instanceof RedBird) {
                birds.get(i).draw(birdsBody.get(i), 2.5f, 2.5f);
            } else if (birds.get(i) instanceof BlueBird) {
                birds.get(i).draw(birdsBody.get(i), 2f, 2f);
            } else if (birds.get(i) instanceof BlackBird) {
                birds.get(i).draw(birdsBody.get(i), 3f, 3f);
            } else if (birds.get(i) instanceof YellowBird) {
                birds.get(i).draw(birdsBody.get(i), 2f, 2.5f);
            }
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        world.step(1 / 60f, 6, 2);

        batch.setProjectionMatrix(camera.combined);

        viewport.apply();
        batch.begin();

        if (pig1List.isEmpty() && pig2List.isEmpty() && pig3List.isEmpty()) {

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new LevelCompleteScreen(game.getLevel1()));
                }
            }, 3);
        } else if (birds_left == 0 && !birdsLeftCheckStarted) {
            birdsLeftCheckStarted = true;

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if (birds_left == 0) {
                        if (pig1List.isEmpty() && pig2List.isEmpty() && pig3List.isEmpty()) {
                            game.setScreen(new LevelCompleteScreen(game.getLevel1()));
                        } else {
                            game.setScreen(new LevelFailScreen(game.getLevel1()));

                        }
                    }
                }
            }, 3); // Delay in seconds
        } else if (birds_left > 0) {
            birdsLeftCheckStarted = false; // Reset if the condition is no longer true
        }

        drawLevel();

        batch.end();
//        debugRenderer.render(world, camera.combined);

        stage.act();
        stage.draw();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        if (mouseJoint == null) {
            shapeRenderer.line(12.249999f, 10.666667f, 14.249999f, 10.666667f);
        }

        if (mouseJoint != null) {
            Vector3 target = new Vector3(mouseJoint.getTarget(), 0);
            shapeRenderer.line(initialTouch.x - 1, initialTouch.y, target.x, target.y);
            shapeRenderer.line(initialTouch.x + 1, initialTouch.y, target.x, target.y);

            System.out.println(initialTouch.x + " " + initialTouch.y);
            Vector2 launchVector = initialTouch.cpy().sub(new Vector2(target.x, target.y)).scl(10);
            shapeRenderer.end(); // End the current shape batch before drawing the trajectory

            if (initialTouch.dst(new Vector2(target.x, target.y)) > 1f) {
                drawTrajectory(initialTouch, launchVector);
            }
        } else {
            shapeRenderer.end();
        }

        collisionListener.update(woodBox, woodHorizontalPlank, woodVerticalPlank, glassBox, glassHorizontalPlank, glassVerticalPlank, birdsBody, pig1List, pig2List, pig3List);
    }


    @Override
    public void show() {
        world.setContactListener(collisionListener);
        createGesture();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

        camera.viewportWidth = viewport.getWorldWidth();
        camera.viewportHeight = viewport.getWorldHeight();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (background != null) background.dispose();

        if (batch != null) batch.dispose();
        if (stage != null) stage.dispose();

        if (shapeRenderer != null) shapeRenderer.dispose();

        if (world != null) world.dispose();
        if (debugRenderer != null) debugRenderer.dispose();

        if (pauseScreen != null) pauseScreen.dispose();
    }


}
