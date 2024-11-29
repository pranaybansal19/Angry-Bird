package com.game.angrybird.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Handler;
import com.game.angrybird.Levels.Level1;
import com.game.angrybird.Levels.Level2;
import com.game.angrybird.Levels.Level3;
import com.game.angrybird.Levels.LevelContainers.Level1Container;
import com.game.angrybird.Levels.LevelContainers.Level2Container;
import com.game.angrybird.Levels.LevelContainers.Level3Container;

import java.util.Objects;


public class StartMenu {
    private MainMenuScreen mainMenuScreen;

    private Texture background;

    private Image play, setting, quit, load;
    private Label label;
    private Label.LabelStyle labelStyle;

    public Image getPlay() {
        return play;
    }

    public StartMenu(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        create();
    }

    public void create() {

        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);

        Skin skin = new Skin();
        skin.add("default-font", font);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default-font");

        label = new Label("Welcome, \n " + mainMenuScreen.getGame().getPlayer().getUsername() + "!", labelStyle);
        label.setFontScale(2.0f);
        label.setColor(Color.WHITE);
        label.setPosition(mainMenuScreen.getViewport().getWorldWidth() - 150, mainMenuScreen.getViewport().getWorldHeight() - 80);

        background = AngryBird.loadTextureSafely("Start Menu/Background.png");

        play = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Start Menu/PlayBtn.png")));
        play.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        play.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - play.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - play.getHeight() / 2 + 75);

        load = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Start Menu/LoadBtn.png")));
        load.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        load.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - load.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - load.getHeight() / 2);

        setting = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Start Menu/SettingBtn.png")));
        setting.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        setting.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - setting.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - setting.getHeight() / 2 - 75);

        quit = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Start Menu/QuitBtn.png")));
        quit.setSize(mainMenuScreen.getViewport().getWorldWidth() / 4.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        quit.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - quit.getWidth() / 2, mainMenuScreen.getViewport().getWorldHeight() / 2 - quit.getHeight() / 2 - 150);

        // Event Listeners
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.getGame().setScreen(mainMenuScreen.getGame().getLevelMenuScreen());
                System.out.println("Play Clicked.");
            }

        });


        load.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Load Clicked.");
                mainMenuScreen.setLoadOpen(true);
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

        Handler.hoverEffect(play, mainMenuScreen.getGame());
        Handler.hoverEffect(load, mainMenuScreen.getGame());
        Handler.hoverEffect(setting, mainMenuScreen.getGame());
        Handler.hoverEffect(quit, mainMenuScreen.getGame());

    }

    public void updateUsername() {
        label.setText("Welcome, \n " + mainMenuScreen.getGame().getPlayer().getUsername() + "!");
    }

    public void draw() {

        mainMenuScreen.getBatch().begin();

        mainMenuScreen.getBatch().draw(background, 0, 0, mainMenuScreen.getViewport().getWorldWidth(), mainMenuScreen.getViewport().getWorldHeight());

        mainMenuScreen.getStage().addActor(play);
        mainMenuScreen.getStage().addActor(load);
        mainMenuScreen.getStage().addActor(setting);
        mainMenuScreen.getStage().addActor(quit);
        mainMenuScreen.getStage().addActor(label);

        mainMenuScreen.getBatch().end();

    }

    public void destroy() {
        play.remove();
        load.remove();
        setting.remove();
        quit.remove();
        label.remove();
    }

}
