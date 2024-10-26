package com.game.angrybird.Loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.angrybird.AngryBird;

public class LoadingScreen implements Screen {

    private AngryBird game;
    private Screen nextScreen;

    private Viewport viewport;
    private Camera camera;

    private Texture background, loadingBarEmpty, loadingBar;

    private Stage stage;
    private SpriteBatch batch;

    private float progress;
    private long startTime;

    private boolean transition = false;

    public LoadingScreen(AngryBird game, Screen nextScreen) {

        // initialization
        this.game = game;
        this.nextScreen = nextScreen;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(1000, 600, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);

        progress = 0f;
        startTime = TimeUtils.nanoTime();

        Gdx.input.setInputProcessor(stage);

        createLoadingScreen();

    }

    public void createLoadingScreen() {

        background = new Texture("Loading Screen/Background.png");
        loadingBarEmpty = new Texture("Loading Screen/LoadingBarEmpty.png");
        loadingBar = new Texture("Loading Screen/LoadingBarFull.png");

    }

    public void drawLoadingScreen() {
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        batch.draw(loadingBarEmpty, viewport.getWorldWidth() / 2 - 160, viewport.getWorldHeight() / 2.0f - 110, viewport.getWorldHeight() / 2 + 30, 20);

        float elapsedTime = (TimeUtils.nanoTime() - startTime) / 1000000000.0f;

        progress = elapsedTime / 5f;

        if (progress >= 1) {
            progress = 1;
            transition = true;
        }

        batch.draw(loadingBar, viewport.getWorldWidth() / 2 - 159, viewport.getWorldHeight() / 2.0f - 107, 327 * progress, 14);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        viewport.apply();
        batch.begin();

        drawLoadingScreen();

        batch.end();

        if (transition) {
            System.out.println("Transitioning to MainScreen...");
            game.setScreen(nextScreen);
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
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

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        background.dispose();
        loadingBarEmpty.dispose();
        loadingBar.dispose();
    }
}
