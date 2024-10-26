package com.game.angrybird.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
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


public class Level1 implements Level, Screen {

    private AngryBird game;
    private PauseScreen pauseScreen;

    private Viewport viewport;
    private OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    private Texture background;
    private Image pause;

    private Stage stage;
    private SpriteBatch batch;

    private SlingShot slingShot;

    private Bird redBird, yellowBird, blueBird, blackBird;
    private Array<Bird> birds = new Array<>();
    private Array<Body> birdsBody = new Array<>();
    private Material wood;
    private Array<Body> woodHorizontalPlank = new Array<>();
    private Array<Body> woodVerticalPlank = new Array<>();
    private Array<Body> woodBox = new Array<>();

    private Pig1 pig1;
    private Array<Body> pig1List = new Array<>();

    private Pig2 pig2;
    private Array<Body> pig2List = new Array<>();

    private Pig3 pig3;
    private Array<Body> pig3List = new Array<>();

    private Ground ground;
    private Body groundBody;


    public Level1(AngryBird game) {
        this.game = game;
        pauseScreen = new PauseScreen(this);

        // initialization
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        viewport = new StretchViewport(1000, 600, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);

        world = new World(new Vector2((float) 0, (float) -9.8), true);
        debugRenderer = new Box2DDebugRenderer();

        Gdx.input.setInputProcessor(stage);

        slingShot = new SlingShot(viewport, stage);

        redBird = new RedBird(world, batch);
        yellowBird = new YellowBird(world, batch);
        blueBird = new BlueBird(world, batch);
        blackBird = new BlackBird(world, batch);

        wood = new Glass(world, batch);

        ground = new Ground(world);

        pig1 = new Pig1(world, batch);
        pig2 = new Pig2(world, batch);
        pig3 = new Pig3(world, batch);

        createLevel();
    }

    // Getters
    @Override
    public AngryBird getGame() {
        return game;
    }

    @Override
    public Bird getBlackBird() {
        return blackBird;
    }

    @Override
    public Bird getBlueBird() {
        return blueBird;
    }

    @Override
    public Ground getGround() {
        return ground;
    }

    @Override
    public Pig1 getPig1() {
        return pig1;
    }

    @Override
    public Pig2 getPig2() {
        return pig2;
    }

    @Override
    public Pig3 getPig3() {
        return pig3;
    }

    @Override
    public Bird getRedBird() {
        return redBird;
    }

    @Override
    public Bird getYellowBird() {
        return yellowBird;
    }


    // Setters
    @Override
    public void setBlackBird(Bird blackBird) {
        this.blackBird = blackBird;
    }

    @Override
    public void setBlueBird(Bird blueBird) {
        this.blueBird = blueBird;
    }

    @Override
    public void setGround(Ground ground) {
        this.ground = ground;
    }

    @Override
    public void setGroundBody(Body groundBody) {
        this.groundBody = groundBody;
    }

    @Override
    public void setPig1(Pig1 pig1) {
        this.pig1 = pig1;
    }

    @Override
    public void setPig2(Pig2 pig2) {
        this.pig2 = pig2;
    }

    @Override
    public void setPig3(Pig3 pig3) {
        this.pig3 = pig3;
    }

    @Override
    public void setRedBird(Bird redBird) {
        this.redBird = redBird;
    }

    @Override
    public void setYellowBird(Bird yellowBird) {
        this.yellowBird = yellowBird;
    }


    @Override
    public void createLevel() {
        background = new Texture(Gdx.files.internal("Level 1/Background.png"));
        pause = new Image(new Texture(Gdx.files.internal("Level 1/PauseBtn.png")));

        pause.setSize(viewport.getWorldWidth() / 25, viewport.getWorldHeight() / 18);
        pause.setPosition(40, viewport.getWorldHeight() - 70);

        slingShot.create(130, 40, viewport.getWorldWidth() / 8.0f, viewport.getWorldHeight() / 6f);

        BodyDef dynamicBodyDef = new BodyDef();
        dynamicBodyDef.type = BodyDef.BodyType.DynamicBody;

        birdsBody.add(redBird.create(dynamicBodyDef, 160, 55, 20f));
        birdsBody.add(blueBird.create(dynamicBodyDef, 125, 55, 20f));
        birdsBody.add(blackBird.create(dynamicBodyDef, 80, 55, 20f));
        birdsBody.add(yellowBird.create(dynamicBodyDef, 30, 55, 20f));

        birds.add(redBird);
        birds.add(blueBird);
        birds.add(blackBird);
        birds.add(yellowBird);

        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 100, 90, 100, 20)); // right
        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 250, 90, 100, 20)); // left

        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 175, 110, 100, 20)); // middle

        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 100 + 35, 210, 100, 20)); // right
        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 250 - 38, 210, 100, 20)); // left

        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 175, 250, 100, 20));

        woodHorizontalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 175, 390, 100, 20));

        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 100 + 40, 150, 20, 100)); // right
        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 250 - 40, 150, 20, 100)); // left

        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 175 - 40, 180, 20, 120));
        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 175 + 40, 180, 20, 120));

        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 175 - 40, 320, 20, 120));
        woodVerticalPlank.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 175 + 40, 320, 20, 120));

        woodBox.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 100, 55, 50, 50));
        woodBox.add(wood.createQuad(dynamicBodyDef, viewport.getWorldWidth() - 250, 55, 50, 50));


        pig1List.add(pig1.create(dynamicBodyDef, viewport.getWorldWidth() - 100, 130f, 20f));
        pig1List.add(pig1.create(dynamicBodyDef, viewport.getWorldWidth() - 250, 130f, 20f));
        pig1List.add(pig1.create(dynamicBodyDef, viewport.getWorldWidth() - 175, 290f, 20f));

        pig2List.add(pig2.create(dynamicBodyDef, viewport.getWorldWidth() - 175, 430f, 20f));

        pig3List.add(pig3.create(dynamicBodyDef, viewport.getWorldWidth() - 100 + 40, 250f, 20f));
        pig3List.add(pig3.create(dynamicBodyDef, viewport.getWorldWidth() - 250 - 40, 250f, 20f));
        pig3List.add(pig3.create(dynamicBodyDef, viewport.getWorldWidth() - 175, 150f, 20f));


        BodyDef staticBodyDef = new BodyDef();
        staticBodyDef.type = BodyDef.BodyType.StaticBody;

        groundBody = ground.create(staticBodyDef, 0, 20, viewport.getWorldWidth(), 10);

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

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();

        Vector3 mousePos = new Vector3(mouseX, mouseY, 0);
        camera.unproject(mousePos);


        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

            for (int i = 0; i < pig1List.size; ++i) {
                if (pig1.isClicked(pig1List.get(i), mousePos.x, mousePos.y)) {
                    world.destroyBody(pig1List.get(i));
                    pig1List.removeIndex(i);
                }
            }

            for (int i = 0; i < pig2List.size; ++i) {
                if (pig2.isClicked(pig2List.get(i), mousePos.x, mousePos.y)) {
                    world.destroyBody(pig2List.get(i));
                    pig2List.removeIndex(i);
                }
            }

            for (int i = 0; i < pig3List.size; ++i) {
                if (pig3.isClicked(pig3List.get(i), mousePos.x, mousePos.y)) {
                    world.destroyBody(pig3List.get(i));
                    pig3List.removeIndex(i);
                }
            }

            for (int i = 0; i < woodBox.size; ++i) {
                if (wood.isClicked(woodBox.get(i), mousePos.x, mousePos.y)) {
                    world.destroyBody(woodBox.get(i));
                    woodBox.removeIndex(i);
                }
            }

            for (int i = 0; i < woodVerticalPlank.size; ++i) {
                if (wood.isClicked(woodVerticalPlank.get(i), mousePos.x, mousePos.y)) {
                    world.destroyBody(woodVerticalPlank.get(i));
                    woodVerticalPlank.removeIndex(i);
                }
            }

            for (int i = 0; i < woodHorizontalPlank.size; ++i) {
                if (wood.isClicked(woodHorizontalPlank.get(i), mousePos.x, mousePos.y)) {
                    world.destroyBody(woodHorizontalPlank.get(i));
                    woodHorizontalPlank.removeIndex(i);
                }
            }

            for (int i = 0; i < birds.size; ++i) {
                if (birds.get(i).isClicked(birdsBody.get(i), mousePos.x, mousePos.y)) {
                    world.destroyBody(birdsBody.get(i));
                    birdsBody.removeIndex(i);
                    birds.removeIndex(i);
                }
            }
        }

        for (int i = 0; i < birds.size; ++i) {
            if (birds.get(i) instanceof RedBird) {
                birds.get(i).draw(birdsBody.get(i), viewport.getWorldWidth() / 24.0f, viewport.getWorldHeight() / 18f);
            } else if (birds.get(i) instanceof BlueBird) {
                birds.get(i).draw(birdsBody.get(i), viewport.getWorldWidth() / 28.0f, viewport.getWorldHeight() / 28f);
            } else if (birds.get(i) instanceof BlackBird) {
                birds.get(i).draw(birdsBody.get(i), viewport.getWorldWidth() / 22.0f, viewport.getWorldHeight() / 15f);
            } else if (birds.get(i) instanceof YellowBird) {
                birds.get(i).draw(birdsBody.get(i), viewport.getWorldWidth() / 22.0f, viewport.getWorldHeight() / 15f);
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
            pig1.draw(body, 40, 40);
        }
        for (Body body : pig2List) {
            pig2.draw(body, 40, 40);
        }
        for (Body body : pig3List) {
            pig3.draw(body, 40, 40);
        }

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Gdx.graphics.setVSync(true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        float timeStep = 1 / 60f;
        int velocityIterations = 6;
        int positionIterations = 2;
        world.step(timeStep, velocityIterations, positionIterations);

        batch.setProjectionMatrix(camera.combined);

        viewport.apply();
        batch.begin();

        if (pig1List.isEmpty() && pig2List.isEmpty() && pig3List.isEmpty()) {
            game.setScreen(new LevelCompleteScreen(this));
        } else if (birds.isEmpty()) {
            game.setScreen(new LevelFailScreen(this));
        }

        drawLevel();

        batch.end();

        stage.act(delta);
        stage.draw();
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
        batch.dispose();
        stage.dispose();
    }

}
