package com.game.angrybird.MainMenu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.angrybird.Handler;

public class Profile {

    private MainMenuScreen mainMenuScreen;

    private Texture profileScreen;

    private Image textField, save, close;

    public Profile(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        create();
    }

    public void create() {

        profileScreen = new Texture(Gdx.files.internal("Profile Screen/Background.png"));

        textField = new Image(new Texture(Gdx.files.internal("Profile Screen/TextField.png")));
        textField.setSize(mainMenuScreen.getViewport().getWorldWidth() / 2.3f, mainMenuScreen.getViewport().getWorldHeight() / 12f);
        textField.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - textField.getWidth() / 2 + 10, mainMenuScreen.getViewport().getWorldHeight() / 2 - textField.getHeight() / 2 - 30);

        save = new Image(new Texture(Gdx.files.internal("Profile Screen/SaveBtn.png")));
        save.setSize(mainMenuScreen.getViewport().getWorldWidth() / 6.9f, mainMenuScreen.getViewport().getWorldHeight() / 10f);
        save.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - save.getWidth() / 2 + 10, mainMenuScreen.getViewport().getWorldHeight() / 2 - textField.getHeight() / 2 - 110);

        close = new Image(new Texture(Gdx.files.internal("Profile Screen/closeBtn.png")));
        close.setSize(mainMenuScreen.getViewport().getWorldWidth() / 16.0f, mainMenuScreen.getViewport().getWorldHeight() / 10.0f);
        close.setPosition(mainMenuScreen.getViewport().getWorldWidth() - 195, mainMenuScreen.getViewport().getWorldHeight() - 115);

        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setSettingOpen(true);
                mainMenuScreen.setProfileOpen(false);
                System.out.println("Close Clicked.");
            }
        });

        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setSettingOpen(true);
                mainMenuScreen.setProfileOpen(false);
                System.out.println("Save Clicked.");
            }
        });

        Handler.hoverEffect(close);
        Handler.hoverEffect(save);

    }

    public void draw() {

        mainMenuScreen.getBatch().begin();

        mainMenuScreen.getBatch().draw(profileScreen, 0, 0, mainMenuScreen.getViewport().getWorldWidth(), mainMenuScreen.getViewport().getWorldHeight());
        mainMenuScreen.getStage().addActor(textField);
        mainMenuScreen.getStage().addActor(save);
        mainMenuScreen.getStage().addActor(close);

        mainMenuScreen.getBatch().end();
    }

    public void destroy() {
        textField.remove();
        save.remove();
        close.remove();
    }

}
