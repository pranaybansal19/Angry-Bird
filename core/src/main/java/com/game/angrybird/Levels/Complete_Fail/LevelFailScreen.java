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
import com.game.angrybird.Levels.Level;
import com.game.angrybird.Levels.Level1;

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

        background = new Texture(Gdx.files.internal("Level Fail Screen/Background.png"));

        retry = new Image(new Texture(Gdx.files.internal("Level Fail Screen/Button.png")));
        menu = new Image(new Texture(Gdx.files.internal("Level Fail Screen/Button.png")));

        retry.setSize(viewport.getWorldWidth() / 5f, viewport.getWorldHeight() / 8f);
        retry.setPosition(viewport.getWorldWidth() / 2 - retry.getWidth() / 2 + 5, viewport.getWorldHeight() / 2 - retry.getHeight() / 2 - 76);

        menu.setSize(viewport.getWorldWidth() / 5f, viewport.getWorldHeight() / 8f);
        menu.setPosition(viewport.getWorldWidth() / 2 - menu.getWidth() / 2 + 10, viewport.getWorldHeight() / 2 - menu.getHeight() / 2 - 200);


        retry.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Retry Clicked.");
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
