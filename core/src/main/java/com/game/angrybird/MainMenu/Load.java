package com.game.angrybird.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.angrybird.Handler;

public class Load {

    private MainMenuScreen mainMenuScreen;

    private Texture loadScreen;

    private Image close;


    public Load(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        create();
    }

    public void create() {

        loadScreen = new Texture(Gdx.files.internal("Load Screen/Background.png"));

        close = new Image(new Texture(Gdx.files.internal("Load Screen/CloseBtn.png")));
        close.setSize(mainMenuScreen.getViewport().getWorldWidth() / 22.0f, mainMenuScreen.getViewport().getWorldHeight() / 20.0f);
        close.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2f + 100, mainMenuScreen.getViewport().getWorldHeight() / 2f + 150);

        // Event Listener
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Close clicked.");
                mainMenuScreen.setLoadOpen(false);
            }
        });

        Handler.hoverEffect(close);

    }

    public void draw() {

        mainMenuScreen.getBatch().begin();

        mainMenuScreen.getStage().addActor(close);

        mainMenuScreen.getBatch().draw(loadScreen, 0, 0, mainMenuScreen.getViewport().getWorldWidth(), mainMenuScreen.getViewport().getWorldHeight());

        mainMenuScreen.getBatch().end();
    }

    public void destroy() {
        close.remove();
    }

}
