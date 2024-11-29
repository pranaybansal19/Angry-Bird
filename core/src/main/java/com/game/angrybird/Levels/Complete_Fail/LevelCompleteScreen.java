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
import com.game.angrybird.Handler;
import com.game.angrybird.Levels.*;

import java.util.Objects;

public class LevelCompleteScreen implements Screen {

    private Level level;

    private Viewport viewport;
    private Camera camera;

    private Texture background;
    private Image nextLevel, restart, menu, star3;

    private Stage stage;
    private SpriteBatch batch;

    AngryBird game;

    // Constructor
    public LevelCompleteScreen(Level level, AngryBird game) {

        level.getGame().levelMusic.stop();

        if (level.getGame().getMainMenuScreen().getSettings().isMusic()) {
            level.getGame().backgroundMusic.setLooping(true);
            level.getGame().backgroundMusic.setVolume(0.3f);
            level.getGame().backgroundMusic.play();
        }

        this.level = level;
        this.game = game;

        // initialization
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1000, 600, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        createScreen();
    }

    public void createScreen() {

        background = AngryBird.loadTextureSafely("Level Complete Screen/Background.png");

        nextLevel = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Level Complete Screen/NextLevelBtn.png")));
        restart = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Level Complete Screen/RestartBtn.png")));
        menu = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Level Complete Screen/MenuBtn.png")));
        star3 = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Level Complete Screen/Star(3).png")));

        nextLevel.setSize(viewport.getWorldWidth() / 6f, viewport.getWorldHeight() / 20.0f);
        nextLevel.setPosition(viewport.getWorldWidth() / 2 - nextLevel.getWidth() / 2 + 20, viewport.getWorldHeight() / 2 - nextLevel.getHeight() / 2 - 33 + 4);

        restart.setSize(viewport.getWorldWidth() / 8f, viewport.getWorldHeight() / 20.0f);
        restart.setPosition(viewport.getWorldWidth() / 2 - restart.getWidth() / 2 + 10, viewport.getWorldHeight() / 2 - restart.getHeight() / 2 - 120 + 6);

        menu.setSize(viewport.getWorldWidth() / 10f, viewport.getWorldHeight() / 20.0f);
        menu.setPosition(viewport.getWorldWidth() / 2 - menu.getWidth() / 2 + 10, viewport.getWorldHeight() / 2 - restart.getHeight() / 2 - 200 + 4);

        star3.setSize(viewport.getWorldWidth() / 5f, viewport.getWorldHeight() / 8.9f);
        star3.setPosition(viewport.getWorldWidth() / 2 - star3.getWidth() / 2, viewport.getWorldHeight() / 2 - star3.getHeight() / 2 + 45);

        nextLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Next Level Clicked.");

                if (level instanceof Level1) {
                    System.out.println("Level 1 Completed.");
                    game.setLevel2(new Level2(game,null));
                    game.setScreen((Screen) game.getLevel2());
                }
                if (level instanceof Level2) {
                    System.out.println("Level 2 Completed.");
                    game.setLevel3(new Level3(game,null));
                    game.setScreen((Screen) game.getLevel3());
                }
                if (level instanceof Level3) {
                    System.out.println("Level 3 Completed.");
                    game.setLevelMenuScreen(new LevelMenuScreen(game));
                    game.setScreen((Screen) game.getLevelMenuScreen());
                }

                level.getGame().backgroundMusic.stop();

                if (level.getGame().getMainMenuScreen().getSettings().isMusic()) {
                    level.getGame().levelMusic.setLooping(true);
                    level.getGame().levelMusic.play();
                }

            }
        });

        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart Clicked.");

                if (level instanceof Level1) {
                    System.out.println("Level 1 Restarted.");
                    game.setLevel1(new Level1(game,null));
                    game.setScreen((Screen) game.getLevel1());
                }
                if (level instanceof Level2) {
                    System.out.println("Level 2 Restarted.");
                    game.setLevel2(new Level2(game,null));
                    game.setScreen((Screen) game.getLevel2());
                }
                if (level instanceof Level3) {
                    System.out.println("Level 3 Restarted.");
                    game.setLevel3(new Level3(game,null));
                    game.setScreen((Screen) game.getLevel3());
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
                game.setScreen((Screen) game.getLevelMenuScreen());

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

        stage.addActor(nextLevel);
        stage.addActor(restart);
        stage.addActor(menu);
        stage.addActor(star3);

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
