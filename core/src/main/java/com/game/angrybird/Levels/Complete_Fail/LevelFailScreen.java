package com.game.angrybird.Levels.Complete_Fail;

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
import com.game.angrybird.Levels.*;

import java.util.Objects;

public class LevelFailScreen implements Screen {

    private Level level;

    private Viewport viewport;
    private Camera camera;

    private Texture background;
    private Image retry, menu;

    private Stage stage;
    private SpriteBatch batch;

    // Constructor
    public LevelFailScreen(Level level) {

        level.getGame().levelMusic.stop();

        if (level.getGame().getMainMenuScreen().getSettings().isMusic()) {
            level.getGame().backgroundMusic.setLooping(true);
            level.getGame().backgroundMusic.setVolume(0.3f);
            level.getGame().backgroundMusic.play();
        }

        this.level = level;

        // initialization
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1000, 600, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        createScreen();
    }

    public void createScreen() {

        background = AngryBird.loadTextureSafely("Level Fail Screen/Background.png");

        retry = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Level Fail Screen/Button.png")));
        menu = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Level Fail Screen/Button.png")));

        retry.setSize(viewport.getWorldWidth() / 5f, viewport.getWorldHeight() / 8f);
        retry.setPosition(viewport.getWorldWidth() / 2 - retry.getWidth() / 2 + 5, viewport.getWorldHeight() / 2 - retry.getHeight() / 2 - 76);

        menu.setSize(viewport.getWorldWidth() / 5f, viewport.getWorldHeight() / 8f);
        menu.setPosition(viewport.getWorldWidth() / 2 - menu.getWidth() / 2 + 10, viewport.getWorldHeight() / 2 - menu.getHeight() / 2 - 200);


        retry.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Retry Clicked.");

                if (level instanceof Level1) {
                    System.out.println("Level 1 Restarted.");
                    level.getGame().setLevel1(new Level1(level.getGame(),null));
                    level.getGame().setScreen((Screen) level.getGame().getLevel1());
                }
                if (level instanceof Level2) {
                    System.out.println("Level 2 Restarted.");
                    level.getGame().setLevel2(new Level2(level.getGame(),null));
                    level.getGame().setScreen((Screen) level.getGame().getLevel2());
                }
                if (level instanceof Level3) {
                    System.out.println("Level 3 Restarted.");
                    level.getGame().setLevel3(new Level3(level.getGame(),null));
                    level.getGame().setScreen((Screen) level.getGame().getLevel3());
                }

                level.getGame().backgroundMusic.stop();

                if (level.getGame().getMainMenuScreen().getSettings().isMusic()) {
                    level.getGame().levelMusic.setLooping(true);
                    level.getGame().levelMusic.play();
                }
            }
        });

        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu Clicked.");
                level.getGame().setLevelMenuScreen(new LevelMenuScreen(level.getGame()));
                level.getGame().setScreen((Screen) level.getGame().getLevelMenuScreen());

                level.getGame().backgroundMusic.stop();

                if (level.getGame().getMainMenuScreen().getSettings().isMusic()) {
                    level.getGame().backgroundMusic.setLooping(true);
                    level.getGame().backgroundMusic.setVolume(0.5f);
                    level.getGame().backgroundMusic.play();
                }
            }
        });

    }

    public void draw() {

        batch.begin();

        stage.addActor(retry);
        stage.addActor(menu);

        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        batch.end();

    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);

        viewport.apply();

        draw();

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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

}
