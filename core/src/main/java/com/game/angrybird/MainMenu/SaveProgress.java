package com.game.angrybird.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.game.angrybird.AngryBird;
import com.game.angrybird.FileHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;


public class SaveProgress {

    private MainMenuScreen mainMenuScreen;

    private Texture saveProgressScreen, saveProgress;

    private Image saved;

    private Instant t;

    public SaveProgress(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        create();
    }

    public void create() {

        saveProgressScreen = AngryBird.loadTextureSafely("Save Progress Screen/Background.png");

        saveProgress = AngryBird.loadTextureSafely("Save Progress Screen/SavingBar.png");

        saved = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Save Progress Screen/SaveBtn.png")));
        saved.setSize(mainMenuScreen.getViewport().getWorldWidth() / 6.0f, mainMenuScreen.getViewport().getWorldHeight() / 9f);
        saved.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - saved.getWidth() / 2 + 10, mainMenuScreen.getViewport().getWorldHeight() / 2 - saved.getHeight() / 2 - 120);

    }

    public void draw() {

        mainMenuScreen.getBatch().begin();

        mainMenuScreen.getBatch().draw(saveProgressScreen, 0, 0, mainMenuScreen.getViewport().getWorldWidth(), mainMenuScreen.getViewport().getWorldHeight());

        float elapsedTime = (TimeUtils.nanoTime() - mainMenuScreen.getStartTime()) / 1000000000.0f;

        mainMenuScreen.setProgress(elapsedTime / 3f);

        if (mainMenuScreen.getProgress() >= 1) {
            mainMenuScreen.getStage().addActor(saved);
            if (mainMenuScreen.getProgress() >= 1.5) {
                mainMenuScreen.setSaveProgressOpen(false);
            }

        } else {
            mainMenuScreen.getBatch().draw(saveProgress, mainMenuScreen.getViewport().getWorldWidth() / 2 - 90, mainMenuScreen.getViewport().getWorldHeight() / 2 - 140, 200 * mainMenuScreen.getProgress(), 50);
            t = Instant.now();
        }

        mainMenuScreen.getBatch().end();

//        ArrayList<AngryBird> arr = FileHandler.getSavedGames();
//        arr.add(mainMenuScreen.getGame());
//        FileHandler.addtoSavedGames(arr);

    }

    public void destroy(){
        saved.remove();
    }
}
