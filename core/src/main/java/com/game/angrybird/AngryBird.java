package com.game.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.game.angrybird.Loading.LoadingScreen;
import com.game.angrybird.MainMenu.MainMenuScreen;
import com.game.angrybird.Levels.*;

public class AngryBird extends Game {

    private MainMenuScreen mainMenuScreen;
    private LevelMenuScreen levelMenuScreen;
    private Level level1;
    private Level level2;
    private Level level3;

    private boolean level1Locked = false;
    private boolean level2Locked = true;
    private boolean level3Locked = true;

    private int level1BestScore = 0;
    private int level2BestScore = 0;
    private int level3BestScore = 0;

    private String username = "Player";

    public Music backgroundMusic;
    public Music levelMusic;
    public Music launchBird;
    public Music destroyed ;
    public Music click;


    public void setLevel1BestScore(int level1BestScore) {
        this.level1BestScore = level1BestScore;
    }

    public void setLevel2BestScore(int level2BestScore) {
        this.level2BestScore = level2BestScore;
    }

    public void setLevel3BestScore(int level3BestScore) {
        this.level3BestScore = level3BestScore;
    }

    public int getLevel1BestScore() {
        return level1BestScore;
    }

    public int getLevel2BestScore() {
        return level2BestScore;
    }

    public int getLevel3BestScore() {
        return level3BestScore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Level getLevel1() {
        return level1;
    }

    public Level getLevel2() {
        return level2;
    }

    public Level getLevel3() {
        return level3;
    }

    public void setLevel1Locked(boolean level1Locked) {
        this.level1Locked = level1Locked;
    }

    public void setLevel2Locked(boolean level2Locked) {
        this.level2Locked = level2Locked;
    }

    public void setLevel3Locked(boolean level3Locked) {
        this.level3Locked = level3Locked;
    }

    public boolean isLevel1Locked() {
        return level1Locked;
    }

    public boolean isLevel2Locked() {
        return level2Locked;
    }

    public boolean isLevel3Locked() {
        return level3Locked;
    }

    public LevelMenuScreen getLevelMenuScreen() {
        return levelMenuScreen;
    }

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public void setLevel1(Level level1) {
        this.level1 = level1;
    }

    public void setLevel2(Level level2) {
        this.level2 = level2;
    }

    public void setLevel3(Level level3) {
        this.level3 = level3;
    }

    public void setLevelMenuScreen(LevelMenuScreen levelMenuScreen) {
        this.levelMenuScreen = levelMenuScreen;
    }

    public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
    }

    @Override
    public void create() {

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/backgroundMusic.ogg"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);

        launchBird = Gdx.audio.newMusic(Gdx.files.internal("Audio/launch.mp3"));
        destroyed = Gdx.audio.newMusic(Gdx.files.internal("Audio/destroyed.mp3"));
        click = Gdx.audio.newMusic(Gdx.files.internal("Audio/button_click.mp3"));
        levelMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/levelMusic.mp3"));

        mainMenuScreen = new MainMenuScreen(this);
        levelMenuScreen = new LevelMenuScreen(this);

//        this.setScreen(new LoadingScreen(this, mainMenuScreen));
        backgroundMusic.stop();
        this.setScreen(new Level3(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        mainMenuScreen.dispose();
        levelMenuScreen.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

}
