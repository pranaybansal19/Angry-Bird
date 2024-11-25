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
    private Level level3;

    private boolean level1Locked = false;
    private boolean level2Locked = true;
    private boolean level3Locked = true;

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

}
