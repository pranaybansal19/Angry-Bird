package com.game.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.game.angrybird.Loading.LoadingScreen;
import com.game.angrybird.MainMenu.MainMenuScreen;
import com.game.angrybird.Levels.*;

public class AngryBird extends Game {

    private MainMenuScreen mainMenuScreen;
    private LevelMenuScreen levelMenuScreen;
    private Level level1;
    private Level level2;

    public Level getLevel1() {
        return level1;
    }

    public Level getLevel2() {
        return level2;
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

    public void setLevelMenuScreen(LevelMenuScreen levelMenuScreen) {
        this.levelMenuScreen = levelMenuScreen;
    }

    public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
    }

    @Override
    public void create() {
        mainMenuScreen = new MainMenuScreen(this);
        levelMenuScreen = new LevelMenuScreen(this);
        level1 = new Level1(this);
//        this.setScreen(new LoadingScreen(this, mainMenuScreen));
        this.setScreen((Screen) level1);
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
