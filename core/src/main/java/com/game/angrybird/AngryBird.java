package com.game.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.game.angrybird.Loading.LoadingScreen;
import com.game.angrybird.MainMenu.MainMenuScreen;
import com.game.angrybird.Levels.*;


public class AngryBird extends Game {

    private MainMenuScreen mainMenuScreen;
    private LevelMenuScreen levelMenuScreen;
    private Level level1;
    private Level level2;
    private Level level3;

    public Music backgroundMusic;
    public Music levelMusic;
    public Music launchBird;
    public Music destroyed;
    public Music glassDestroyed;
    public Music stoneDestroyed;
    public Music click;
    public Music sling;
    public Music bomb;

    private Player player = new Player();

    private static AngryBird instance = null;

    public static AngryBird getInstance() {
        if (instance == null) {
            instance = new AngryBird();
        }
        return instance;
    }

    private AngryBird(){}

    public Player getPlayer() {
        return player;
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

    public LevelMenuScreen getLevelMenuScreen() {
        return levelMenuScreen;
    }

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public void setLevel1(Level1 level1) {
        this.level1 = level1;
    }

    public void setLevel2(Level2 level2) {
        this.level2 = level2;
    }

    public void setLevel3(Level3 level3) {
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

        FileHandler.initialize();

        player = FileHandler.getPlayer();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/backgroundMusic.ogg"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);

        launchBird = Gdx.audio.newMusic(Gdx.files.internal("Audio/launch.mp3"));
        destroyed = Gdx.audio.newMusic(Gdx.files.internal("Audio/destroyed.mp3"));
        glassDestroyed = Gdx.audio.newMusic(Gdx.files.internal("Audio/GlassDestroyed.mp3"));
        stoneDestroyed = Gdx.audio.newMusic(Gdx.files.internal("Audio/StoneDestroyed.mp3"));
        click = Gdx.audio.newMusic(Gdx.files.internal("Audio/button_click.mp3"));
        levelMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/levelMusic.mp3"));
        sling = Gdx.audio.newMusic(Gdx.files.internal("Audio/Slingshot.mp3"));
        bomb = Gdx.audio.newMusic(Gdx.files.internal("Audio/bomb.mp3"));

        mainMenuScreen = new MainMenuScreen(this);
        levelMenuScreen = new LevelMenuScreen(this);

        this.setScreen(new LoadingScreen(this, mainMenuScreen));
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

    public static Texture loadTextureSafely(String filePath) {
        FileHandle file = Gdx.files.internal(filePath);
        if (!file.exists()) {
            System.err.println("Missing asset: " + filePath);
            return null;
        }
        return new Texture(file);
    }

}
