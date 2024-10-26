package com.game.angrybird.Levels.Pause;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.angrybird.Levels.Level;

public class PauseScreen implements Screen {

    private Level level;

    private PauseMenu pauseMenu;

    private Viewport viewport;
    private Camera camera;

    private Stage stage;
    private SpriteBatch batch;

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public SpriteBatch getBatch() {
        return batch;
    }
    public Stage getStage() {
        return stage;
    }
    public Viewport getViewport() {
        return viewport;
    }
    public Level getLevel() {
        return level;
    }

    // Constructor
    public PauseScreen(Level level) {

        this.level = level;

        // initialization
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1000, 600, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        pauseMenu = new PauseMenu(this);
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);

        viewport.apply();

        pauseMenu.draw();

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
