package com.game.angrybird.Levels.Pause;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.angrybird.Handler;
import com.game.angrybird.Levels.Level1;

public class PauseMenu {

    private PauseScreen pauseScreen;

    private Texture background;

    private Image resume, restart, save, menu;

    public PauseMenu(PauseScreen pauseScreen) {
        this.pauseScreen = pauseScreen;
        create();
    }

    public void create() {

        background = new Texture(Gdx.files.internal("Pause Screen/Background.png"));

        resume = new Image(new Texture(Gdx.files.internal("Pause Screen/ResumeBtn.png")));
        resume.setSize(pauseScreen.getViewport().getWorldWidth() / 4.0f, pauseScreen.getViewport().getWorldHeight() / 10.0f);
        resume.setPosition(pauseScreen.getViewport().getWorldWidth() / 2 - resume.getWidth() / 2 + 14, pauseScreen.getViewport().getWorldHeight() / 2 - resume.getHeight() / 2 + 75 + 25);

        save = new Image(new Texture(Gdx.files.internal("Pause Screen/SaveBtn.png")));
        save.setSize(pauseScreen.getViewport().getWorldWidth() / 6.0f, pauseScreen.getViewport().getWorldHeight() / 12.0f);
        save.setPosition(pauseScreen.getViewport().getWorldWidth() / 2 - save.getWidth() / 2 + 15, pauseScreen.getViewport().getWorldHeight() / 2 - save.getHeight() / 2 + 20);

        restart = new Image(new Texture(Gdx.files.internal("Pause Screen/RestartBtn.png")));
        restart.setSize(pauseScreen.getViewport().getWorldWidth() / 6.0f, pauseScreen.getViewport().getWorldHeight() / 14.0f);
        restart.setPosition(pauseScreen.getViewport().getWorldWidth() / 2 - restart.getWidth() / 2 + 15, pauseScreen.getViewport().getWorldHeight() / 2 - restart.getHeight() / 2 - 75 + 25);

        menu = new Image(new Texture(Gdx.files.internal("Pause Screen/MenuBtn.png")));
        menu.setSize(pauseScreen.getViewport().getWorldWidth() / 6.0f, pauseScreen.getViewport().getWorldHeight() / 12.0f);
        menu.setPosition(pauseScreen.getViewport().getWorldWidth() / 2 - menu.getWidth() / 2 + 15, pauseScreen.getViewport().getWorldHeight() / 2 - menu.getHeight() / 2 - 150 + 30);

        // Event Listeners
        resume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Resume Clicked.");
                pauseScreen.getLevel().getGame().setScreen((Screen) pauseScreen.getLevel());
            }
        });

        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Save clicked.");
            }
        });

        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart Clicked.");
                pauseScreen.getLevel().getGame().setLevel1(new Level1(pauseScreen.getLevel().getGame()));
                pauseScreen.getLevel().getGame().setScreen((Screen) pauseScreen.getLevel().getGame().getLevel1());
            }
        });

        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu Clicked.");
                pauseScreen.getLevel().getGame().setScreen(pauseScreen.getLevel().getGame().getLevelMenuScreen());
            }
        });

        Handler.hoverEffect(resume);
        Handler.hoverEffect(save);
        Handler.hoverEffect(restart);
        Handler.hoverEffect(menu);

    }

    public void draw() {

        pauseScreen.getBatch().begin();

        pauseScreen.getBatch().draw(background, 0, 0, pauseScreen.getViewport().getWorldWidth(), pauseScreen.getViewport().getWorldHeight());

        pauseScreen.getStage().addActor(resume);
        pauseScreen.getStage().addActor(save);
        pauseScreen.getStage().addActor(restart);
        pauseScreen.getStage().addActor(menu);

        pauseScreen.getBatch().end();

    }

    public void destroy() {
        resume.remove();
        save.remove();
        restart.remove();
        menu.remove();
    }

}
