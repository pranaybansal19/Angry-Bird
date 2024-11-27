package com.game.angrybird.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
import com.game.angrybird.Pair;
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

    private Material wood1, wood2, wood3, wood4, wood5, wood6, wood7, wood8, wood9, wood10, wood11, wood12;
    private Array<Pair<Body, Material>> woodHorizontalPlank = new Array<>();
    private Array<Pair<Body, Material>> woodVerticalPlank = new Array<>();
    private Array<Pair<Body, Material>> woodBox = new Array<>();

    private Pig1 pig1_1,pig1_2;
    private Array<Pair<Body, Pig1>> pig1List = new Array<>();

    private Pig2 pig2_1,pig2_2;
    private Array<Pair<Body, Pig2>> pig2List = new Array<>();

    private Array<Pair<Body, Pig3>> pig3List = new Array<>();

    int birds_left = 4;
    private boolean birdsLeft = false;
    private boolean levelCompleted = false;


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

    private int score = 0;
    private int bestScore;
    private Label label1, label2;
    private Label.LabelStyle labelStyle;

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

        pig1_1 = new Pig1(world, batch);
        pig1_2 = new Pig1(world, batch);
        pig2_1 = new Pig2(world, batch);
        pig2_2 = new Pig2(world, batch);

        wood1 = new Wood(world, batch);
        wood2 = new Wood(world, batch);
        wood3 = new Wood(world, batch);
        wood4 = new Wood(world, batch);
        wood5 = new Wood(world, batch);
        wood6 = new Wood(world, batch);
        wood7 = new Wood(world, batch);
        wood8 = new Wood(world, batch);
        wood9 = new Wood(world, batch);
        wood10 = new Wood(world, batch);
        wood11 = new Wood(world, batch);
        wood12 = new Wood(world, batch);

        slingShot = new SlingShot(viewport, stage);

        dynamicBodyDef.type = BodyDef.BodyType.DynamicBody;
        staticBodyDef.type = BodyDef.BodyType.StaticBody;

        collisionListener = new CollisionListener(world,game,this);
        world.setContactListener(collisionListener);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        bestScore = game.getLevel2BestScore();

        createGesture();
        createLevel();

    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void updateScore(int points){
        score+=points;
        if (score>bestScore){
            bestScore+=points;
        }

        label1.setText("Score :  " + String.format("%05d", score));
        label2.setText("Best Score :  " + String.format("%05d", bestScore));
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
                        if (game.getMainMenuScreen().getSettings().isSound()) {
                            game.launchBird.play();
                        }
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

        Body b1 = wood1.createQuad(dynamicBodyDef, 32f, 5.5f, 1f, 8f);
        Body b2 = wood2.createQuad(dynamicBodyDef, 36f, 7f, 1f, 8f);
        Body b3 = wood3.createQuad(dynamicBodyDef, 34f, 12.5f, 5.5f, 1.5f);
        Body b4 = wood4.createQuad(dynamicBodyDef, 36.3f, 13.5f, 5f, 1.5f);
        Body b5 = wood5.createQuad(dynamicBodyDef, 36f, 19f, 1f, 8f);
        Body b6 = wood6.createQuad(dynamicBodyDef, 36f, 24f, 6f, 1.5f);
        Body b7 = wood7.createQuad(dynamicBodyDef, 42.5f, 2f, 6f, 1.5f);
        Body b8 = wood8.createQuad(dynamicBodyDef, 40f, 7f, 1f, 7f);
        Body b9 = wood9.createQuad(dynamicBodyDef, 40, 13f, 3f, 3.5f);
        Body b10 = wood10.createQuad(dynamicBodyDef, 44.5f, 9f, 1f, 8f);
        Body b11 = wood11.createQuad(dynamicBodyDef, 44.5f, 12f, 5f, 1.5f);
        Body b12 = wood12.createQuad(dynamicBodyDef, 36f, 27f, 3f, 3.5f);

        Body b13 = pig1_1.create(dynamicBodyDef, 33.8f, 5f, 1.5f);
        Body b14 = pig1_2.create(dynamicBodyDef, 39.8f, 16f, 1.5f);
//        Body b15 = pig2_1.create(dynamicBodyDef, 36f, 28f, 1.5f);
        Body b16 = pig2_2.create(dynamicBodyDef, 44.4f, 14f, 1.5f);

        woodVerticalPlank.add(new Pair<>(b1,wood1));
        woodVerticalPlank.add(new Pair<>(b2,wood2));


        woodHorizontalPlank.add(new Pair<>(b3,wood3));
        woodHorizontalPlank.add(new Pair<>(b4,wood4));

        woodVerticalPlank.add(new Pair<>(b5,wood5));
        woodHorizontalPlank.add(new Pair<>(b6,wood6));

        woodHorizontalPlank.add(new Pair<>(b7,wood7));
        woodVerticalPlank.add(new Pair<>(b8,wood8));
        woodBox.add(new Pair<>(b9,wood9));

        woodVerticalPlank.add(new Pair<>(b10,wood10));
        woodHorizontalPlank.add(new Pair<>(b11,wood11));

        woodBox.add(new Pair<>(b12,wood12));

        pig1List.add(new Pair<>(b13,pig1_1));
        pig1List.add(new Pair<>(b14,pig1_2));


//        pig2List.add(new Pair<>(b15,pig2_1));
        pig2List.add(new Pair<>(b16,pig2_2));

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

        label1 = new Label("Score :  " + String.format("%05d", score), labelStyle);
        label1.setFontScale(0.12f);
        label1.setColor(Color.WHITE);
        label1.setPosition(38, 38);

        label2 = new Label("Best Score :  " + String.format("%05d", bestScore), labelStyle);
        label2.setFontScale(0.12f);
        label2.setColor(Color.WHITE);
        label2.setPosition(20, 38);

        background = new Texture(Gdx.files.internal("Level 2/Background.png"));
        pause = new Image(new Texture(Gdx.files.internal("Level 2/PauseBtn.png")));

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
        stage.addActor(label1);
        stage.addActor(label2);

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

        for (int i = 0; i < woodBox.size; ++i) {
            woodBox.get(i).getSecond().drawBox(woodBox.get(i).getFirst());
        }
        for (int i = 0; i < woodVerticalPlank.size; ++i) {
            woodVerticalPlank.get(i).getSecond().drawPlankVertical(woodVerticalPlank.get(i).getFirst());
        }
        for (int i = 0; i < woodHorizontalPlank.size; ++i) {
            woodHorizontalPlank.get(i).getSecond().drawPlankHorizontal(woodHorizontalPlank.get(i).getFirst());
        }

        for (int i = 0; i < pig1List.size; ++i) {
            pig1List.get(i).getSecond().draw(pig1List.get(i).getFirst(),3.5f,4f);
        }
        for (int i = 0; i < pig2List.size; ++i) {
            pig2List.get(i).getSecond().draw(pig2List.get(i).getFirst(),3.5f,4f);
        }
        for (int i = 0; i < pig3List.size; ++i) {
            pig3List.get(i).getSecond().draw(pig3List.get(i).getFirst(),3.5f,4f);
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

        if (!levelCompleted && pig1List.isEmpty() && pig2List.isEmpty() && pig3List.isEmpty()) {
            levelCompleted = true;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {

                    if (bestScore > game.getLevel2BestScore()) {
                        game.setLevel2BestScore(bestScore);
                    }

                    game.setLevel3Locked(false);
                    game.setScreen(new LevelCompleteScreen(game.getLevel2(),game));
                }
            }, 3);
        } else if (birds_left == 0 && !birdsLeft) {
            birdsLeft = true;

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if (birds_left == 0) {
                        if (pig1List.isEmpty() && pig2List.isEmpty() && pig3List.isEmpty()) {

                            if (bestScore > game.getLevel2BestScore()) {
                                game.setLevel2BestScore(bestScore);
                            }

                            game.setLevel3Locked(false);
                            game.setScreen(new LevelCompleteScreen(game.getLevel2(),game));
                        } else {
                            game.setScreen(new LevelFailScreen(game.getLevel2()));
                        }
                    }
                }
            }, 5);
        } else if (birds_left > 0) {
            birdsLeft = false;
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
            shapeRenderer.end();

            if (initialTouch.dst(new Vector2(target.x, target.y)) > 1f) {
                drawTrajectory(initialTouch, launchVector);
            }
        } else {
            shapeRenderer.end();
        }

        collisionListener.update(woodBox,woodHorizontalPlank,woodVerticalPlank,new Array<>(),new Array<>(),new Array<>(),new Array<>(),new Array<>(),new Array<>(),pig1List,pig2List,pig3List);
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
