package com.game.angrybird.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Handler;

public class LevelMenuScreen implements Screen {

    private AngryBird game;

    private Viewport viewport;
    private Camera camera;

    private Texture background;
    private Image level1, level2, level3, back;

    private Stage stage;
    private SpriteBatch batch;


    public LevelMenuScreen(AngryBird game) {
        this.game = game;

        // initialization
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1000, 600, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        createLevelMenuScreen();
    }

    public void createLevelMenuScreen() {
        background = new Texture(Gdx.files.internal("Level Menu Screen/Background.png"));

        level1 = new Image(new Texture(Gdx.files.internal("Level Menu Screen/Level1Btn.png")));
        level1.setSize(viewport.getWorldWidth() / 13f, viewport.getWorldHeight() / 9f);
        level1.setPosition(viewport.getWorldWidth() / 2 - level1.getWidth() / 2 - 90 + 3, viewport.getWorldHeight() / 2 - level1.getHeight() / 2 + 120);

        level2 = new Image(new Texture(Gdx.files.internal("Level Menu Screen/Level2Btn.png")));
        level2.setSize(viewport.getWorldWidth() / 13f, viewport.getWorldHeight() / 9f);
        level2.setPosition(viewport.getWorldWidth() / 2 - level2.getWidth() / 2 - 90 + 110 - 5, viewport.getWorldHeight() / 2 - level2.getHeight() / 2 + 120);

        level3 = new Image(new Texture(Gdx.files.internal("Level Menu Screen/Level3Btn.png")));
        level3.setSize(viewport.getWorldWidth() / 13f, viewport.getWorldHeight() / 9f);
        level3.setPosition(viewport.getWorldWidth() / 2 - level3.getWidth() / 2 - 90 + 220 - 10, viewport.getWorldHeight() / 2 - level3.getHeight() / 2 + 120);

        back = new Image(new Texture(Gdx.files.internal("Level Menu Screen/BackBtn.png")));
        back.setSize(viewport.getWorldWidth() / 16f, viewport.getWorldHeight() / 12f);
        back.setPosition(viewport.getWorldWidth() / 2 - back.getWidth() / 2 - 290, viewport.getWorldHeight() / 2 - back.getHeight() / 2 +  120 + 100);

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back Clicked.");
                game.setScreen(game.getMainMenuScreen());
            }
        });

        level1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 1 Clicked.");
                game.setLevel1(new Level1(game));
                game.setScreen((Screen) game.getLevel1());
            }
        });

        level2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level 2 Clicked.");
                game.setLevel2(new Level2(game));
                game.setScreen((Screen) game.getLevel2());
            }
        });

        Handler.hoverEffect(back);
        Handler.hoverEffect(level1);
        Handler.hoverEffect(level2);
        Handler.hoverEffect(level3);
    }

    public void drawLevelMenuScreen() {
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        stage.addActor(level1);
        stage.addActor(level2);
        stage.addActor(level3);
        stage.addActor(back);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        viewport.apply();
        batch.begin();

        drawLevelMenuScreen();

        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
