package com.game.angrybird.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Handler;

import java.util.Objects;

public class Quit {

    private MainMenuScreen mainMenuScreen;

    private Texture exitScreen;

    private Image cross, tick;


    public Quit(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        create();
    }

    public void create() {

        exitScreen = AngryBird.loadTextureSafely("Quit Screen/Background.png");

        cross = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Quit Screen/CrossBtn.png")));
        cross.setSize(mainMenuScreen.getViewport().getWorldWidth() / 6.0f, mainMenuScreen.getViewport().getWorldHeight() / 6.0f);
        cross.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2f - 90, mainMenuScreen.getViewport().getWorldHeight() / 2f - 120);

        tick = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Quit Screen/TickBtn.png")));
        tick.setSize(mainMenuScreen.getViewport().getWorldWidth() / 6.0f, mainMenuScreen.getViewport().getWorldHeight() / 6.0f);
        tick.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2f - 90, mainMenuScreen.getViewport().getWorldHeight() / 2f - 20);

        // Event Listener
        cross.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setExitOpen(false);
                System.out.println("Close Clicked.");
            }
        });

        tick.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Handler.hoverEffect(cross, mainMenuScreen.getGame());
        Handler.hoverEffect(tick, mainMenuScreen.getGame());

    }

    public void draw() {

        mainMenuScreen.getBatch().begin();

        mainMenuScreen.getStage().addActor(cross);
        mainMenuScreen.getStage().addActor(tick);

        mainMenuScreen.getBatch().draw(exitScreen, 0, 0, mainMenuScreen.getViewport().getWorldWidth(), mainMenuScreen.getViewport().getWorldHeight());

        mainMenuScreen.getBatch().end();
    }

    public void destroy() {
        cross.remove();
        tick.remove();

    }

}
