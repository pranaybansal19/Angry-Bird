package com.game.angrybird.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.angrybird.AngryBird;

public class MainMenuScreen implements Screen {

    private AngryBird game;

    private Setting settings;
    private Profile profile;
    private Quit quit;
    private SaveProgress saveProgress;
    private Load load;
    private StartMenu startMenu;

    private Viewport viewport;
    private Camera camera;

    private Stage stage;
    private SpriteBatch batch;

    private float progress;
    private long startTime;

    private boolean settingOpen, exitOpen, profileOpen, saveProgressOpen, loadOpen;

    // Getters
    public AngryBird getGame() {
        return game;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Stage getStage() {
        return stage;
    }

    public Batch getBatch() {
        return batch;
    }

    public float getProgress() {
        return progress;
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean isExitOpen() {
        return exitOpen;
    }

    public boolean isProfileOpen() {
        return profileOpen;
    }

    public boolean isSettingOpen() {
        return settingOpen;
    }

    public boolean isSaveProgressOpen() {
        return saveProgressOpen;
    }

    public boolean isLoadOpen() {
        return loadOpen;
    }

    // Setters
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setSettingOpen(boolean settingOpen) {
        this.settingOpen = settingOpen;
    }

    public void setExitOpen(boolean exitOpen) {
        this.exitOpen = exitOpen;
    }

    public void setSaveProgressOpen(boolean saveProgressOpen) {
        this.saveProgressOpen = saveProgressOpen;
    }

    public void setProfileOpen(boolean profileOpen) {
        this.profileOpen = profileOpen;
    }

    public void setLoadOpen(boolean loadOpen) {
        this.loadOpen = loadOpen;
    }

    public void setGame(AngryBird game) {
        this.game = game;
    }

    // Constructor
    public MainMenuScreen(AngryBird game) {

        this.game = game;

        settingOpen = false;
        exitOpen = false;
        profileOpen = false;
        saveProgressOpen = false;
        loadOpen = false;

        // initialization
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1000, 600, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        settings = new Setting(this);
        profile = new Profile(this);
        saveProgress = new SaveProgress(this);
        startMenu = new StartMenu(this);
        quit = new Quit(this);
        load = new Load(this);
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);

        viewport.apply();

        if (settingOpen) {

            quit.destroy();
            profile.destroy();
            saveProgress.destroy();
            startMenu.destroy();
            load.destroy();

            settings.draw();
        } else if (exitOpen) {

            profile.destroy();
            saveProgress.destroy();
            startMenu.destroy();
            settings.destroy();
            load.destroy();

            quit.draw();
        } else if (profileOpen) {

            saveProgress.destroy();
            startMenu.destroy();
            settings.destroy();
            quit.destroy();
            load.destroy();

            profile.draw();
        } else if (saveProgressOpen) {

            profile.destroy();
            startMenu.destroy();
            settings.destroy();
            quit.destroy();
            load.destroy();

            saveProgress.draw();
        } else if (loadOpen) {

            profile.destroy();
            startMenu.destroy();
            settings.destroy();
            quit.destroy();
            saveProgress.destroy();

            load.draw();
        } else {
            profile.destroy();
            saveProgress.destroy();
            settings.destroy();
            quit.destroy();
            load.destroy();

            startMenu.draw();
        }

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
