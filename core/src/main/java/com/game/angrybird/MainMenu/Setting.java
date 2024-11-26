package com.game.angrybird.MainMenu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.angrybird.Handler;

public class Setting {

    private MainMenuScreen mainMenuScreen;

    private Texture settingScreen;

    private Image close, musicON, musicOFF, soundON, soundOFF, changeProfile;


    boolean music, sound;

    public Setting(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        music = true;
        sound = true;
        create();
    }

    public boolean isMusic() {
        return sound;
    }

    public boolean isSound() {
        return music;
    }

    public void create() {

        settingScreen = new Texture(Gdx.files.internal("Setting Screen/Background.png"));

        close = new Image(new Texture(Gdx.files.internal("Setting Screen/CloseBtn.png")));
        close.setSize(mainMenuScreen.getViewport().getWorldWidth() / 16.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        close.setPosition(mainMenuScreen.getViewport().getWorldWidth() - 150, mainMenuScreen.getViewport().getWorldHeight() - 80);

        musicON = new Image(new Texture(Gdx.files.internal("Setting Screen/SoundON.png")));
        musicON.setSize(mainMenuScreen.getViewport().getWorldWidth() / 7.5f, mainMenuScreen.getViewport().getWorldHeight() / 5.5f);
        musicON.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - musicON.getWidth() / 2 - 220, mainMenuScreen.getViewport().getWorldHeight() / 2 - musicON.getHeight() / 2 + 60);

        musicOFF = new Image(new Texture(Gdx.files.internal("Setting Screen/SoundOFF.png")));
        musicOFF.setSize(mainMenuScreen.getViewport().getWorldWidth() / 7.5f, mainMenuScreen.getViewport().getWorldHeight() / 5.5f);
        musicOFF.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - musicOFF.getWidth() / 2 - 220, mainMenuScreen.getViewport().getWorldHeight() / 2 - musicOFF.getHeight() / 2 + 60);

        soundON = new Image(new Texture(Gdx.files.internal("Setting Screen/MusicON.png")));
        soundON.setSize(mainMenuScreen.getViewport().getWorldWidth() / 7.5f, mainMenuScreen.getViewport().getWorldHeight() / 5.5f);
        soundON.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - soundON.getWidth() / 2 + 220, mainMenuScreen.getViewport().getWorldHeight() / 2 - soundON.getHeight() / 2 + 60);

        soundOFF = new Image(new Texture(Gdx.files.internal("Setting Screen/MusicOFF.png")));
        soundOFF.setSize(mainMenuScreen.getViewport().getWorldWidth() / 7.5f, mainMenuScreen.getViewport().getWorldHeight() / 5.5f);
        soundOFF.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - soundOFF.getWidth() / 2 + 220, mainMenuScreen.getViewport().getWorldHeight() / 2 - soundOFF.getHeight() / 2 + 60);

        changeProfile = new Image(new Texture(Gdx.files.internal("Setting Screen/ChangeProfileBtn.png")));
        changeProfile.setSize(mainMenuScreen.getViewport().getWorldWidth() / 6f, mainMenuScreen.getViewport().getWorldHeight() / 4f);
        changeProfile.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - changeProfile.getWidth() / 2 + 10, mainMenuScreen.getViewport().getWorldHeight() / 2 - changeProfile.getHeight() / 2 + 70);


        // Event Listener

        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setSettingOpen(false);
                System.out.println("Close Clicked.");
            }
        });

        musicON.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music = !music;
                System.out.println("Sound off.");
            }
        });

        musicOFF.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music = !music;
                System.out.println("Sound on.");
            }
        });

        soundON.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound = !sound;
                System.out.println("Music off.");
                mainMenuScreen.getGame().backgroundMusic.pause();
            }
        });

        soundOFF.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound = !sound;
                System.out.println("Music on.");
                mainMenuScreen.getGame().backgroundMusic.play();
            }
        });

        changeProfile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setProfileOpen(true);
                mainMenuScreen.setSettingOpen(false);
                System.out.println("Profile Clicked.");
            }
        });

        Handler.hoverEffect(close);
        Handler.hoverEffect(musicON);
        Handler.hoverEffect(musicOFF);
        Handler.hoverEffect(soundON);
        Handler.hoverEffect(soundOFF);
        Handler.hoverEffect(changeProfile);

    }

    public void draw() {

        mainMenuScreen.getBatch().begin();

        mainMenuScreen.getStage().addActor(close);
        mainMenuScreen.getStage().addActor(changeProfile);

        if (music) {
            musicOFF.remove();
            mainMenuScreen.getStage().addActor(musicON);
        } else {
            musicON.remove();
            mainMenuScreen.getStage().addActor(musicOFF);
        }

        if (sound) {
            soundOFF.remove();
            mainMenuScreen.getStage().addActor(soundON);
        } else {
            soundON.remove();
            mainMenuScreen.getStage().addActor(soundOFF);
        }

        mainMenuScreen.getBatch().draw(settingScreen, 0, 0, mainMenuScreen.getViewport().getWorldWidth(), mainMenuScreen.getViewport().getWorldHeight());

        mainMenuScreen.getBatch().end();
    }

    public void destroy() {
        close.remove();
        musicON.remove();
        musicOFF.remove();
        soundON.remove();
        soundOFF.remove();
        changeProfile.remove();
    }

}
