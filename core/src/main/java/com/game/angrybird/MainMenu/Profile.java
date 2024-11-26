package com.game.angrybird.MainMenu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.angrybird.Handler;

public class Profile {

    private MainMenuScreen mainMenuScreen;

    private Texture profileScreen;

    private Image textField, save, close;
    private BitmapFont font;
    private TextField inputField;

    public Profile(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        Gdx.input.setInputProcessor(mainMenuScreen.getStage());
        create();
    }

    private Drawable createCursorDrawable() {
        Pixmap pixmap = new Pixmap(2, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(texture);
    }

    public void create() {

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2.0f);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;
        Drawable cursorDrawable = createCursorDrawable();
        textFieldStyle.cursor = cursorDrawable;

        inputField = new TextField("", textFieldStyle);
        inputField.setText(mainMenuScreen.getGame().getUsername());

        inputField.setSize(mainMenuScreen.getViewport().getWorldWidth() / 2.3f, mainMenuScreen.getViewport().getWorldHeight() / 12f);
        inputField.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2 - inputField.getWidth() / 2 + 30, mainMenuScreen.getViewport().getWorldHeight() / 2 - inputField.getHeight() / 2 - 30);

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
                inputField.setText(mainMenuScreen.getGame().getUsername());
            }
        });

        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setSettingOpen(true);
                mainMenuScreen.setProfileOpen(false);
                System.out.println("Save Clicked.");

                String text = inputField.getText();
                if (!text.isEmpty()) {
                    mainMenuScreen.getGame().setUsername(text);
                    inputField.setText(mainMenuScreen.getGame().getUsername());
                }
                else{
                    inputField.setText(mainMenuScreen.getGame().getUsername());
                }
                mainMenuScreen.startMenu.updateUsername();
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
        mainMenuScreen.getStage().addActor(inputField);

        mainMenuScreen.getBatch().end();
    }

    public void destroy() {
        textField.remove();
        save.remove();
        close.remove();
        inputField.remove();
    }

}
