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
import com.game.angrybird.Handler;
import com.game.angrybird.Levels.Level;
import com.game.angrybird.Levels.Level1;

public class LevelCompleteScreen implements Screen {

    private Level level;

    private Viewport viewport;
    private Camera camera;

    private Texture background;
    private Image nextLevel, restart, menu, star3;

    private Stage stage;
    private SpriteBatch batch;

    // Constructor
    public LevelCompleteScreen(Level level) {

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

        background = new Texture(Gdx.files.internal("Level Complete Screen/Background.png"));

        nextLevel = new Image(new Texture(Gdx.files.internal("Level Complete Screen/NextLevelBtn.png")));
        restart = new Image(new Texture(Gdx.files.internal("Level Complete Screen/RestartBtn.png")));
        menu = new Image(new Texture(Gdx.files.internal("Level Complete Screen/MenuBtn.png")));
        star3 = new Image(new Texture(Gdx.files.internal("Level Complete Screen/Star(3).png")));

        nextLevel.setSize(viewport.getWorldWidth() / 6f, viewport.getWorldHeight() / 20.0f);
        nextLevel.setPosition(viewport.getWorldWidth() / 2 - nextLevel.getWidth() / 2 + 20, viewport.getWorldHeight() / 2 - nextLevel.getHeight() / 2 - 33 + 4);

        restart.setSize(viewport.getWorldWidth() / 8f, viewport.getWorldHeight() / 20.0f);
        restart.setPosition(viewport.getWorldWidth() / 2 - restart.getWidth() / 2 + 10, viewport.getWorldHeight() / 2 - restart.getHeight() / 2 - 120 + 6);

        menu.setSize(viewport.getWorldWidth() / 10f, viewport.getWorldHeight() / 20.0f);
        menu.setPosition(viewport.getWorldWidth() / 2 - menu.getWidth() / 2 + 10, viewport.getWorldHeight() / 2 - restart.getHeight() / 2 - 200 + 4);

        star3.setSize(viewport.getWorldWidth() / 5f, viewport.getWorldHeight() / 8.9f);
        star3.setPosition(viewport.getWorldWidth() / 2 - star3.getWidth() / 2, viewport.getWorldHeight() / 2 - star3.getHeight() / 2 + 45);

        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart Clicked.");
                level.getGame().setLevel1(new Level1(level.getGame()));
                level.getGame().setScreen((Screen) level.getGame().getLevel1());

            }
        });

        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu Clicked.");
                level.getGame().setScreen((Screen) level.getGame().getLevelMenuScreen());
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
