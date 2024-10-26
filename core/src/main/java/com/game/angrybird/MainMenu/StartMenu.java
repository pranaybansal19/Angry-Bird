package com.game.angrybird.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.game.angrybird.Handler;


public class StartMenu {
    private MainMenuScreen mainMenuScreen;

    private Texture background;

    private Image play, setting, quit, save, load;

    public StartMenu(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        create();
    }

    public void create() {

        background = new Texture(Gdx.files.internal("Start Menu/Background.png"));

        play = new Image(new Texture(Gdx.files.internal("Start Menu/PlayBtn.png")));
        play.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        play.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - play.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - play.getHeight() / 2 + 75);

        load = new Image(new Texture(Gdx.files.internal("Start Menu/LoadBtn.png")));
        load.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        load.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - load.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - load.getHeight() / 2);

        save = new Image(new Texture(Gdx.files.internal("Start Menu/SaveBtn.png")));
        save.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        save.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - save.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - save.getHeight() / 2 - 75);

        setting = new Image(new Texture(Gdx.files.internal("Start Menu/SettingBtn.png")));
        setting.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        setting.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - setting.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - setting.getHeight() / 2 - 150);

        quit = new Image(new Texture(Gdx.files.internal("Start Menu/QuitBtn.png")));
        quit.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        quit.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - quit.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - quit.getHeight() / 2 - 225);

        // Event Listeners
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.getGame().setScreen(mainMenuScreen.getGame().getLevelMenuScreen());
                System.out.println("Play Clicked.");
            }

        });

        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setSaveProgressOpen(true);
                mainMenuScreen.setProgress(0f);
                mainMenuScreen.setStartTime(TimeUtils.nanoTime());
                System.out.println("Save Clicked.");
            }
        });

        load.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setLoadOpen(true);
                System.out.println("Load Clicked.");
            }
        });

        setting.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setSettingOpen(true);
                System.out.println("Menu Clicked.");
            }
        });

        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setExitOpen(!mainMenuScreen.isExitOpen());
                System.out.println("Quit Clicked.");
            }
        });

        Handler.hoverEffect(play);
        Handler.hoverEffect(load);
        Handler.hoverEffect(save);
        Handler.hoverEffect(setting);
        Handler.hoverEffect(quit);

    }

    public void draw() {

        mainMenuScreen.getBatch().begin();

        mainMenuScreen.getBatch().draw(background, 0, 0, mainMenuScreen.getViewport().getWorldWidth(), mainMenuScreen.getViewport().getWorldHeight());

        mainMenuScreen.getStage().addActor(play);
        mainMenuScreen.getStage().addActor(load);
        mainMenuScreen.getStage().addActor(save);
        mainMenuScreen.getStage().addActor(setting);
        mainMenuScreen.getStage().addActor(quit);

        mainMenuScreen.getBatch().end();

    }

    public void destroy() {
        play.remove();
        load.remove();
        save.remove();
        setting.remove();
        quit.remove();
    }

}
