package com.game.angrybird.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Birds.*;
import com.game.angrybird.FileHandler;
import com.game.angrybird.Levels.Complete_Fail.LevelCompleteScreen;
import com.game.angrybird.Levels.Complete_Fail.LevelFailScreen;
import com.game.angrybird.Levels.LevelContainers.Level1Container;
import com.game.angrybird.Levels.LevelContainers.Level2Container;
import com.game.angrybird.Levels.LevelContainers.Level3Container;
import com.game.angrybird.Levels.LevelContainers.LevelContainer;
import com.game.angrybird.Levels.Pause.PauseScreen;
import com.game.angrybird.Materials.*;
import com.game.angrybird.Pigs.Pig;
import com.game.angrybird.Pigs.Pig1;
import com.game.angrybird.Pigs.Pig2;
import com.game.angrybird.Pigs.Pig3;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Level {

    private AngryBird game;

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

    private SlingShot slingShot;

    private Ground ground;
    private Body groundBody;

    private Vector2 initialTouch;

    private GestureDetector gestureDetector;
    private InputMultiplexer multiplexer;
    private CollisionListener collisionListener;

    private BodyDef dynamicBodyDef = new BodyDef();
    private BodyDef staticBodyDef = new BodyDef();

    private int birds_left = 4;
    private boolean birdsLeft = false;
    private boolean levelCompleted = false;
    private boolean isDragging = false;

    private ArrayList<Wood> woods = new ArrayList<>();
    private ArrayList<Glass> glasses = new ArrayList<>();
    private ArrayList<Stone> stones = new ArrayList<>();
    private ArrayList<Pig> pigs = new ArrayList<>();
    private ArrayList<Bird> birds = new ArrayList<>();
    private ArrayList<Bird> projectileBirds = new ArrayList<>();

    private int score = 0;
    private int bestScore;
    private Label label1, label2;
    private Label.LabelStyle labelStyle;

    private LevelContainer levelContainer;
    private int level;

    private float redBirdHeight = 2.5f;
    private float redBirdWidth = 2.5f;

    private float blueBirdWidth = 2f;
    private float blueBirdHeight = 2f;

    //Constructor
    public Level(){}

    public Level(AngryBird game, LevelContainer levelContainer, int level) {

        this.game = game;
        this.level = level;

        world = new World(new Vector2(0f, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        viewport = new StretchViewport(50, 50, camera);
        batch = new SpriteBatch();
        stage = new Stage(getViewport(), getBatch());

        Gdx.input.setInputProcessor(getStage());

        ground = new Ground(getWorld());

        slingShot = new SlingShot(getViewport(), getStage());

        dynamicBodyDef.type = BodyDef.BodyType.DynamicBody;
        staticBodyDef.type = BodyDef.BodyType.StaticBody;

        collisionListener = new CollisionListener(getWorld(), getGame(), getLevel());
        getWorld().setContactListener(collisionListener);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);

        Skin skin = new Skin();
        skin.add("default-font", font);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default-font");

        if (level == 1) {
            bestScore = game.getPlayer().getLevel1BestScore();
        } else if (level == 2) {
            game.getPlayer().setLevel1Locked(false);
            game.getPlayer().setLevel2Locked(false);
            bestScore = game.getPlayer().getLevel2BestScore();
        } else if (level == 3) {
            game.getPlayer().setLevel1Locked(false);
            game.getPlayer().setLevel2Locked(false);
            game.getPlayer().setLevel3Locked(false);
            bestScore = game.getPlayer().getLevel3BestScore();
        }

        if (levelContainer == null) {

            if (level == 1) {
                this.levelContainer = new Level1Container();
            } else if (level == 2) {
                this.levelContainer = new Level2Container();
            } else if (level == 3) {
                this.levelContainer = new Level3Container();
            }
            this.birds_left = 4;
            this.levelContainer.setBestScore(bestScore);
        } else {
            this.levelContainer = levelContainer;
            this.bestScore = getLevelContainer().getBestScore();
            this.score = getLevelContainer().getScore();
            this.birds_left = levelContainer.getBirds_left();
        }

    }


    // Getters
    public AngryBird getGame() {
        return game;
    }

    public Level getLevel() {
        return null;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public World getWorld() {
        return world;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Stage getStage() {
        return stage;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public CollisionListener getCollisionListener() {
        return collisionListener;
    }

    public ArrayList<Wood> getWoods() {
        return woods;
    }

    public ArrayList<Glass> getGlasses() {
        return glasses;
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    public ArrayList<Pig> getPigs() {
        return pigs;
    }

    public ArrayList<Bird> getProjectileBirds() {
        return projectileBirds;
    }

    public ArrayList<Bird> getBirds() {
        return birds;
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return bestScore;
    }

    public int getBirds_left() {
        return birds_left;
    }

    public boolean isBirdsLeft() {
        return birdsLeft;
    }

    public boolean isLevelCompleted() {
        return levelCompleted;
    }

    public LevelContainer getLevelContainer() {
        return levelContainer;
    }


    //Setters
    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevelCompleted(boolean levelCompleted) {
        this.levelCompleted = levelCompleted;
    }

    public void setBirds_left(int birds_left) {
        this.birds_left = birds_left;
    }

    public void setBirdsLeft(boolean birdsLeft) {
        this.birdsLeft = birdsLeft;
    }

    public void updateScore(int points) {
        score += points;
        if (score > bestScore) {
            bestScore += points;
        }

        label1.setText("Score :  " + String.format("%05d", score));
        label2.setText("Best Score :  " + String.format("%05d", bestScore));
    }

    public boolean checkTouched() {
        if (initialTouch.x > 12.2 && initialTouch.x < 14.2 && initialTouch.y < 11.5 && initialTouch.y > 10) {
            initialTouch.x = 13.15f;
            initialTouch.y = 10.75f;
            return true;
        }
        return false;
    }

    public boolean allPigsDied() {
        for (Pig pig : pigs) {
            if (pig.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }

    public void createGesture() {
        gestureDetector = new GestureDetector(new GestureDetector.GestureListener() {

            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {

                if (!projectileBirds.isEmpty() && !projectileBirds.get(projectileBirds.size() - 1).isAbilityUsed()) {
                    projectileBirds.get(projectileBirds.size() - 1).setAbilityUsed(true);

                    if (projectileBirds.get(projectileBirds.size() - 1) instanceof YellowBird) {
                        Vector2 velocity = projectileBirds.get(projectileBirds.size() - 1).getBody().getLinearVelocity();
                        projectileBirds.get(projectileBirds.size() - 1).getBody().setLinearVelocity(velocity.x + 45, velocity.y);
                    } else if (projectileBirds.get(projectileBirds.size() - 1) instanceof RedBird) {
                        Body body = projectileBirds.get(projectileBirds.size() - 1).getBody();

                        Vector2 position = body.getPosition();
                        Vector2 velocity = body.getLinearVelocity();
                        float angle = body.getAngle();

                        world.destroyBody(body);

                        BodyDef bodyDef = new BodyDef();
                        bodyDef.type = BodyDef.BodyType.DynamicBody;
                        bodyDef.position.set(position);
                        bodyDef.angle = angle;

                        Body newBody = world.createBody(bodyDef);

                        CircleShape shape = new CircleShape();
                        shape.setRadius(2f);

                        FixtureDef fixtureDef = new FixtureDef();
                        fixtureDef.shape = shape;
                        fixtureDef.density = 1.0f;
                        fixtureDef.friction = 0.2f;
                        fixtureDef.restitution = 0.5f;

                        newBody.createFixture(fixtureDef);
                        shape.dispose();

                        newBody.setLinearVelocity(velocity);

                        projectileBirds.get(projectileBirds.size() - 1).setBody(newBody);

                        redBirdWidth = 4f;
                        redBirdHeight = 4f;
                    } else if (projectileBirds.get(projectileBirds.size() - 1) instanceof BlueBird) {
                        ((BlueBird) projectileBirds.get(projectileBirds.size() - 1)).useAbility();
                        blueBirdHeight = 1.3f;
                        blueBirdWidth = 1.3f;
                    } else if (projectileBirds.get(projectileBirds.size() - 1) instanceof BlackBird) {
                        ((BlackBird) projectileBirds.get(projectileBirds.size() - 1)).setBombExploded(true);
                        ((BlackBird) projectileBirds.get(projectileBirds.size() - 1)).useAbility();

                        if (game.getMainMenuScreen().getSettings().isSound()) {
                            game.bomb.play();
                        }
                    }


                }

                if (birds.isEmpty()) {
                    return false;
                }

                Vector3 worldCoordinates = camera.unproject(new Vector3(x, y, 0));
                initialTouch = new Vector2(worldCoordinates.x, worldCoordinates.y);
                isDragging = false;

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
                        if (game.getMainMenuScreen().getSettings().isSound()) {
                            game.sling.play();
                        }
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
                        Body projectileBody = getProjectileBirds().get(getProjectileBirds().size() - 1).getBody();

                        if (projectileBody != null) {

                            world.destroyJoint(mouseJoint);
                            mouseJoint = null;
                            world.destroyBody(projectileBody);

                            Bird bird = getProjectileBirds().get(getProjectileBirds().size() - 1);
                            getProjectileBirds().remove(getProjectileBirds().size() - 1);

                            if (bird instanceof RedBird) {
                                birds.add(0, new RedBird(world, batch));
                                birds.get(0).create(staticBodyDef, 9f, 3.5f, 1.1f);
                            } else if (bird instanceof BlueBird) {
                                birds.add(0, new BlueBird(world, batch));
                                birds.get(0).create(staticBodyDef, 7f, 3.5f, 0.94f);
                            } else if (bird instanceof BlackBird) {
                                birds.add(0, new BlackBird(world, batch));
                                birds.get(0).create(staticBodyDef, 4.6f, 4f, 1.5f);
                            } else if (bird instanceof YellowBird) {
                                birds.add(0, new YellowBird(world, batch));
                                birds.get(0).create(staticBodyDef, 2, 3.5f, 0.97f);
                            }
                        }
                    } else {
                        slingShot.launchProjectile(releasePoint, initialTouch, getProjectileBirds().get(getProjectileBirds().size() - 1).getBody());

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

    public void createProjectile(Vector2 position) {

        BodyDef projectileBodyDef = new BodyDef();
        projectileBodyDef.type = BodyDef.BodyType.DynamicBody;
        projectileBodyDef.position.set(position);

        Bird bird = birds.get(0);

        if (bird instanceof RedBird) {

            getProjectileBirds().add(new RedBird(world, batch));
            getProjectileBirds().get(getProjectileBirds().size() - 1).create(projectileBodyDef, position.x, position.y, 1.1f);
            getProjectileBirds().get(getProjectileBirds().size() - 1).getBody().setUserData("projectile_bird");

        } else if (bird instanceof BlueBird) {

            getProjectileBirds().add(new BlueBird(world, batch));
            getProjectileBirds().get(getProjectileBirds().size() - 1).create(projectileBodyDef, position.x, position.y, 0.94f);
            getProjectileBirds().get(getProjectileBirds().size() - 1).getBody().setUserData("projectile_bird");

        } else if (bird instanceof BlackBird) {

            getProjectileBirds().add(new BlackBird(world, batch));
            getProjectileBirds().get(getProjectileBirds().size() - 1).create(projectileBodyDef, position.x, position.y, 1.5f);
            getProjectileBirds().get(getProjectileBirds().size() - 1).getBody().setUserData("projectile_bird");

        } else if (bird instanceof YellowBird) {

            getProjectileBirds().add(new YellowBird(world, batch));
            getProjectileBirds().get(getProjectileBirds().size() - 1).create(projectileBodyDef, position.x, position.y, 0.97f);
            getProjectileBirds().get(getProjectileBirds().size() - 1).getBody().setUserData("projectile_bird");

        }

        world.destroyBody(birds.get(0).getBody());
        birds.remove(0);
    }

    public void createMouseJoint(Vector2 target) {
        mouseJointDef = new MouseJointDef();
        mouseJointDef.bodyA = groundBody;
        mouseJointDef.bodyB = getProjectileBirds().get(getProjectileBirds().size() - 1).getBody();
        mouseJointDef.collideConnected = true;
        mouseJointDef.target.set(target);
        mouseJointDef.maxForce = 10000.0f * getProjectileBirds().get(getProjectileBirds().size() - 1).getBody().getMass();
        mouseJoint = (MouseJoint) world.createJoint(mouseJointDef);
    }

    public void drawTrajectory(Vector2 start, Vector2 velocity) {

        shapeRenderer.setProjectionMatrix(camera.combined);
        Vector2 position = new Vector2(start);
        Vector2 vel = new Vector2(velocity);
        float timeStep = 1 / 60f;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < 300; i++) {
            vel.add(0, -9.8f * timeStep);
            position.add(vel.x * timeStep, vel.y * timeStep);
            shapeRenderer.circle(position.x, position.y, 0.1f);
        }
        shapeRenderer.end();

    }


    public void createLevel() {

        label1 = new Label("Score :  " + String.format("%05d", score), labelStyle);
        label1.setFontScale(0.12f);
        label1.setColor(Color.WHITE);
        label1.setPosition(38, 38);

        label2 = new Label("Best Score :  " + String.format("%05d", bestScore), labelStyle);
        label2.setFontScale(0.12f);
        label2.setColor(Color.WHITE);
        label2.setPosition(20, 38);

        String backgroundImageAddress = "Level " + level + "/Background.png";
        String pauseBtnImageAddress = "Level " + level + "/PauseBtn.png";

        background = AngryBird.loadTextureSafely(backgroundImageAddress);
        pause = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely(pauseBtnImageAddress)));

        slingShot.create(9, 2.9f, 7, 9);

        pause.setSize(2, 3);
        pause.setPosition(2, 44);

        groundBody = ground.create(staticBodyDef, 0, -7.5f, getViewport().getWorldWidth(), 10);

        if (level == 3) {
            ground.create(staticBodyDef, 46, 18, 4, 2);
        }

        ground.create(staticBodyDef, 51, 25, 1, getViewport().getWorldHeight());

        loadLevel();

        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause Clicked.");
                game.setScreen(new PauseScreen(getLevel()));
            }
        });

    }

    public void loadLevel() {

        for (int i = 0; i < getLevelContainer().getMaterials().size(); ++i) {

            if ((float) getLevelContainer().getMaterials().get(i).get(8) <= 0) {
                continue;
            }

            String type = (String) getLevelContainer().getMaterials().get(i).get(0);
            if (Objects.equals(type, "woodBox") || Objects.equals(type, "woodPlankHorizontal") || Objects.equals(type, "woodPlankVertical")) {

                getWoods().add(new Wood(getWorld(), getBatch()));

                getWoods().get(getWoods().size() - 1).createQuad(dynamicBodyDef, (float) getLevelContainer().getMaterials().get(i).get(1), (float) getLevelContainer().getMaterials().get(i).get(2), (float) getLevelContainer().getMaterials().get(i).get(3), (float) getLevelContainer().getMaterials().get(i).get(4), (String) getLevelContainer().getMaterials().get(i).get(0));

                getWoods().get(getWoods().size() - 1).getBody().setLinearVelocity((float) getLevelContainer().getMaterials().get(i).get(6), (float) getLevelContainer().getMaterials().get(i).get(7));

                Vector2 position = getWoods().get(getWoods().size() - 1).getBody().getPosition();
                float angle = (float) getLevelContainer().getMaterials().get(i).get(5);
                getWoods().get(getWoods().size() - 1).getBody().setTransform(position.x, position.y, angle);
                getWoods().get(getWoods().size() - 1).setHealth((float) getLevelContainer().getMaterials().get(i).get(8));

            } else if (Objects.equals(type, "stoneBox") || Objects.equals(type, "stonePlankHorizontal") || Objects.equals(type, "stonePlankVertical")) {

                getStones().add(new Stone(getWorld(), getBatch()));

                getStones().get(getStones().size() - 1).createQuad(dynamicBodyDef, (float) getLevelContainer().getMaterials().get(i).get(1), (float) getLevelContainer().getMaterials().get(i).get(2), (float) getLevelContainer().getMaterials().get(i).get(3), (float) getLevelContainer().getMaterials().get(i).get(4), (String) getLevelContainer().getMaterials().get(i).get(0));

                getStones().get(getStones().size() - 1).getBody().setLinearVelocity((float) getLevelContainer().getMaterials().get(i).get(6), (float) getLevelContainer().getMaterials().get(i).get(7));

                Vector2 position = getStones().get(getStones().size() - 1).getBody().getPosition();
                float angle = (float) getLevelContainer().getMaterials().get(i).get(5);
                getStones().get(getStones().size() - 1).getBody().setTransform(position.x, position.y, angle);
                getStones().get(getStones().size() - 1).setHealth((float) getLevelContainer().getMaterials().get(i).get(8));

            } else if (Objects.equals(type, "glassBox") || Objects.equals(type, "glassPlankHorizontal") || Objects.equals(type, "glassPlankVertical")) {

                getGlasses().add(new Glass(getWorld(), getBatch()));

                getGlasses().get(getGlasses().size() - 1).createQuad(dynamicBodyDef, (float) getLevelContainer().getMaterials().get(i).get(1), (float) getLevelContainer().getMaterials().get(i).get(2), (float) getLevelContainer().getMaterials().get(i).get(3), (float) getLevelContainer().getMaterials().get(i).get(4), (String) getLevelContainer().getMaterials().get(i).get(0));

                getGlasses().get(getGlasses().size() - 1).getBody().setLinearVelocity((float) getLevelContainer().getMaterials().get(i).get(6), (float) getLevelContainer().getMaterials().get(i).get(7));

                Vector2 position = getGlasses().get(getGlasses().size() - 1).getBody().getPosition();
                float angle = (float) getLevelContainer().getMaterials().get(i).get(5);
                getGlasses().get(getGlasses().size() - 1).getBody().setTransform(position.x, position.y, angle);
                getGlasses().get(getGlasses().size() - 1).setHealth((float) getLevelContainer().getMaterials().get(i).get(8));

            }

        }

        for (int i = 0; i < getLevelContainer().getPigs().size(); ++i) {

            if ((float) getLevelContainer().getPigs().get(i).get(7) <= 0) {
                continue;
            }

            if (Objects.equals((String) getLevelContainer().getPigs().get(i).get(0), "pig1")) {
                getPigs().add(new Pig1(getWorld(), getBatch()));
            }
            else if (Objects.equals((String) getLevelContainer().getPigs().get(i).get(0), "pig2")) {
                getPigs().add(new Pig2(getWorld(), getBatch()));
            }
            else if (Objects.equals((String) getLevelContainer().getPigs().get(i).get(0), "pig3")) {
                getPigs().add(new Pig3(getWorld(), getBatch()));
            }

            getPigs().get(getPigs().size() - 1).create(dynamicBodyDef, (float) getLevelContainer().getPigs().get(i).get(1), (float) getLevelContainer().getPigs().get(i).get(2), (float) getLevelContainer().getPigs().get(i).get(3));
            getPigs().get(getPigs().size() - 1).getBody().setLinearVelocity((float) getLevelContainer().getPigs().get(i).get(5), (float) getLevelContainer().getPigs().get(i).get(6));

            Vector2 position = getPigs().get(getPigs().size() - 1).getBody().getPosition();
            float angle = (float) getLevelContainer().getPigs().get(i).get(4);

            getPigs().get(getPigs().size() - 1).getBody().setTransform(position.x, position.y, angle);
            getPigs().get(getPigs().size() - 1).setHealth((float) getLevelContainer().getPigs().get(i).get(7));
        }

        for (int i = 0; i < getLevelContainer().getBirds().size(); ++i) {
            if (Objects.equals((String) getLevelContainer().getBirds().get(i).get(0), "redBird")) {
                getBirds().add(new RedBird(getWorld(), getBatch()));
            } else if (Objects.equals((String) getLevelContainer().getBirds().get(i).get(0), "blueBird")) {
                getBirds().add(new BlueBird(getWorld(), getBatch()));
            } else if (Objects.equals((String) getLevelContainer().getBirds().get(i).get(0), "blackBird")) {
                getBirds().add(new BlackBird(getWorld(), getBatch()));
            } else if (Objects.equals((String) getLevelContainer().getBirds().get(i).get(0), "yellowBird")) {
                getBirds().add(new YellowBird(getWorld(), getBatch()));
            }

            getBirds().get(getBirds().size() - 1).create(staticBodyDef, (float) getLevelContainer().getBirds().get(i).get(1), (float) getLevelContainer().getBirds().get(i).get(2), (float) getLevelContainer().getBirds().get(i).get(3));
            getBirds().get(getBirds().size() - 1).getBody().setLinearVelocity((float) getLevelContainer().getBirds().get(i).get(5), (float) getLevelContainer().getBirds().get(i).get(6));

            Vector2 position = getBirds().get(getBirds().size() - 1).getBody().getPosition();
            float angle = (float) getLevelContainer().getBirds().get(i).get(4);

            getBirds().get(getBirds().size() - 1).getBody().setTransform(position.x, position.y, angle);
        }

        for (int i = 0; i < getLevelContainer().getProjectileBirds().size(); ++i) {

            if (Objects.equals((String) getLevelContainer().getProjectileBirds().get(i).get(0), "redBird")) {
                getProjectileBirds().add(new RedBird(getWorld(), getBatch()));
            } else if (Objects.equals((String) getLevelContainer().getProjectileBirds().get(i).get(0), "blueBird")) {
                getProjectileBirds().add(new BlueBird(getWorld(), getBatch()));
            } else if (Objects.equals((String) getLevelContainer().getProjectileBirds().get(i).get(0), "blackBird")) {
                getProjectileBirds().add(new BlackBird(getWorld(), getBatch()));
            } else if (Objects.equals((String) getLevelContainer().getProjectileBirds().get(i).get(0), "yellowBird")) {
                getProjectileBirds().add(new YellowBird(getWorld(), getBatch()));
            }

            getProjectileBirds().get(getProjectileBirds().size() - 1).create(dynamicBodyDef, (float) getLevelContainer().getProjectileBirds().get(i).get(1), (float) getLevelContainer().getProjectileBirds().get(i).get(2), (float) getLevelContainer().getProjectileBirds().get(i).get(3));
            getProjectileBirds().get(getProjectileBirds().size() - 1).getBody().setLinearVelocity((float) getLevelContainer().getProjectileBirds().get(i).get(5), (float) getLevelContainer().getProjectileBirds().get(i).get(6));

            Vector2 position = getProjectileBirds().get(getProjectileBirds().size() - 1).getBody().getPosition();
            float angle = (float) getLevelContainer().getProjectileBirds().get(i).get(4);

            getProjectileBirds().get(getProjectileBirds().size() - 1).getBody().setTransform(position.x, position.y, angle);
        }

    }

    public void drawLevel() {

        ScreenUtils.clear(Color.BLACK);

        getWorld().step(1 / 60f, 6, 2);

        getBatch().setProjectionMatrix(getCamera().combined);

        getViewport().apply();
        getBatch().begin();

        if (!isLevelCompleted() && allPigsDied()) {
            setLevelCompleted(true);

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {

                    if (level == 1) {
                        if (getBestScore() > getGame().getPlayer().getLevel1BestScore()) {
                            getGame().getPlayer().setLevel1BestScore(getBestScore());
                        }

                        getGame().getPlayer().setLevel2Locked(false);
                    } else if (level == 2) {
                        if (getBestScore() > getGame().getPlayer().getLevel2BestScore()) {
                            getGame().getPlayer().setLevel2BestScore(getBestScore());
                        }

                        getGame().getPlayer().setLevel3Locked(false);
                    }

                    FileHandler.updatePlayer(getGame().getPlayer());
                    getGame().setScreen(new LevelCompleteScreen(getLevel(), getGame()));
                }
            }, 3);
        } else if (getBirds_left() == 0 && !isBirdsLeft()) {

            setBirdsLeft(true);

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if (getBirds_left() == 0) {
                        if (allPigsDied()) {

                            if (level == 1) {
                                if (getBestScore() > getGame().getPlayer().getLevel1BestScore()) {
                                    getGame().getPlayer().setLevel1BestScore(getBestScore());
                                }

                                getGame().getPlayer().setLevel2Locked(false);
                            } else if (level == 2) {
                                if (getBestScore() > getGame().getPlayer().getLevel2BestScore()) {
                                    getGame().getPlayer().setLevel2BestScore(getBestScore());
                                }

                                getGame().getPlayer().setLevel3Locked(false);
                            }

                            FileHandler.updatePlayer(getGame().getPlayer());
                            getGame().setScreen(new LevelCompleteScreen(getLevel(), getGame()));
                        } else {
                            getGame().setScreen(new LevelFailScreen(getLevel()));
                        }
                    }
                }
            }, 5);

        } else if (getBirds_left() > 0) {
            setBirdsLeft(false);
        }

        getBatch().draw(background, 0, 0, getViewport().getWorldWidth(), getViewport().getWorldHeight());
        getStage().addActor(pause);
        getStage().addActor(label1);
        getStage().addActor(label2);

        slingShot.draw();

        drawBirds();
        drawPigs();
        drawMaterial();

        getBatch().end();
//        debugRenderer.render(getWorld(), getCamera().combined);

        stage.act();
        stage.draw();

        shapeRenderer.setProjectionMatrix(getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        if (mouseJoint == null) {
            shapeRenderer.line(12.249999f, 10.666667f, 14.249999f, 10.666667f);
        }

        if (mouseJoint != null) {
            Vector3 target = new Vector3(mouseJoint.getTarget(), 0);
            shapeRenderer.line(initialTouch.x - 1, initialTouch.y, target.x, target.y);
            shapeRenderer.line(initialTouch.x + 1, initialTouch.y, target.x, target.y);

            Vector2 launchVector = initialTouch.cpy().sub(new Vector2(target.x, target.y)).scl(10);
            shapeRenderer.end();

            if (initialTouch.dst(new Vector2(target.x, target.y)) > 1f) {
                drawTrajectory(initialTouch, launchVector);
            }
        } else {
            shapeRenderer.end();
        }

        collisionListener.update();

    }

    public void drawBirds() {
        for (Bird bird : getProjectileBirds()) {

            if (bird instanceof RedBird) {
                bird.draw(redBirdWidth, redBirdHeight);
            } else if (bird instanceof BlueBird) {
                bird.draw(blueBirdWidth, blueBirdHeight);
            } else if (bird instanceof YellowBird) {
                bird.draw(2f, 2.5f);
            } else if (bird instanceof BlackBird) {
                bird.draw(3f, 3f);
            }
        }

        for (Bird bird : getBirds()) {

            if (bird instanceof RedBird) {
                bird.draw(2.5f, 2.5f);
            } else if (bird instanceof BlueBird) {
                bird.draw(2f, 2f);
            } else if (bird instanceof BlackBird) {
                bird.draw(3f, 3f);
            } else if (bird instanceof YellowBird) {
                bird.draw(2f, 2.5f);
            }

        }
    }

    public void drawPigs() {
        for (Pig pig : pigs) {
            if (pig instanceof Pig1) {
                pig.draw(3.5f, 3.5f);
            } else if (pig instanceof Pig2) {
                pig.draw(4f, 4f);
            } else if (pig instanceof Pig3) {
                pig.draw(4f, 4f);
            }
        }

    }

    public void drawMaterial() {
        for (Wood wood : getWoods()) {
            wood.draw();
        }

        for (Glass glass : getGlasses()) {
            glass.draw();
        }

        for (Stone stone : getStones()) {
            stone.draw();
        }
    }


}
