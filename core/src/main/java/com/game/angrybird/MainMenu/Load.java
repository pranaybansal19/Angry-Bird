package com.game.angrybird.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.game.angrybird.AngryBird;
import com.game.angrybird.FileHandler;
import com.game.angrybird.Handler;
import com.game.angrybird.Levels.Level1;
import com.game.angrybird.Levels.Level2;
import com.game.angrybird.Levels.Level3;
import com.game.angrybird.Levels.LevelContainers.Level1Container;
import com.game.angrybird.Levels.LevelContainers.Level2Container;
import com.game.angrybird.Levels.LevelContainers.Level3Container;
import com.game.angrybird.Levels.LevelContainers.LevelContainer;

import java.util.Objects;
import java.util.TreeMap;

public class Load {

    private MainMenuScreen mainMenuScreen;

    private Texture loadScreen;

    private Image close;

    Table table;
    ScrollPane scrollPane;
    private BitmapFont font;

    boolean temp = false;

    public Load(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
        create();
    }

    public void create() {

        loadScreen = AngryBird.loadTextureSafely("Load Screen/Background.png");

        close = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Load Screen/CloseBtn.png")));
        close.setSize(mainMenuScreen.getViewport().getWorldWidth() / 22.0f, mainMenuScreen.getViewport().getWorldHeight() / 20.0f);
        close.setPosition(mainMenuScreen.getViewport().getWorldWidth() / 2f + 100, mainMenuScreen.getViewport().getWorldHeight() / 2f + 150);

        font = new BitmapFont();
        font.getData().setScale(1.5f);

        table = new Table();
        table.top();

        scrollPane = new ScrollPane(table);
        scrollPane.setBounds(350, 120, Gdx.graphics.getWidth() - 700, Gdx.graphics.getHeight() - 280);

        scrollPane.setScrollingDisabled(true, false);

        // Event Listener
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Close clicked.");
                mainMenuScreen.setLoadOpen(false);
            }
        });

        Handler.hoverEffect(close, mainMenuScreen.getGame());

    }

    public void draw() {

        if (!temp) {

            TreeMap<String, LevelContainer> map = FileHandler.getSavedGames();

            for (String s : map.keySet()) {

                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font;
                labelStyle.fontColor = Color.BLACK;

                Label label = new Label("Level " + getLevel(map.get(s)) + "  " + s, labelStyle);

                label.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (getLevel(map.get(s)) == 1) {
                            mainMenuScreen.getGame().setLevel1(new Level1(mainMenuScreen.getGame(), (Level1Container) map.get(s)));
                            mainMenuScreen.getGame().setScreen((Screen) mainMenuScreen.getGame().getLevel1());

                            mainMenuScreen.getGame().backgroundMusic.stop();

                            if (mainMenuScreen.getGame().getMainMenuScreen().getSettings().isMusic()) {
                                mainMenuScreen.getGame().levelMusic.setLooping(true);
                                mainMenuScreen.getGame().levelMusic.play();
                            }
                        }
                        else if (getLevel(map.get(s)) == 2) {
                            mainMenuScreen.getGame().setLevel2(new Level2(mainMenuScreen.getGame(), (Level2Container) map.get(s)));
                            mainMenuScreen.getGame().setScreen((Screen) mainMenuScreen.getGame().getLevel2());

                            mainMenuScreen.getGame().backgroundMusic.stop();

                            if (mainMenuScreen.getGame().getMainMenuScreen().getSettings().isMusic()) {
                                mainMenuScreen.getGame().levelMusic.setLooping(true);
                                mainMenuScreen.getGame().levelMusic.play();
                            }
                        }
                        else if (getLevel(map.get(s)) == 3) {
                            mainMenuScreen.getGame().setLevel3(new Level3(mainMenuScreen.getGame(), (Level3Container) map.get(s)));
                            mainMenuScreen.getGame().setScreen((Screen) mainMenuScreen.getGame().getLevel3());

                            mainMenuScreen.getGame().backgroundMusic.stop();

                            if (mainMenuScreen.getGame().getMainMenuScreen().getSettings().isMusic()) {
                                mainMenuScreen.getGame().levelMusic.setLooping(true);
                                mainMenuScreen.getGame().levelMusic.play();
                            }
                        }
                        mainMenuScreen.setLoadOpen(false);
                    }
                });

                label.setDebug(true);

                label.setTouchable(Touchable.enabled);

                table.add(label).pad(10f).expandX().left();
                table.row();
            }

            temp = true;
        }

        mainMenuScreen.getBatch().begin();

        mainMenuScreen.getStage().addActor(close);

        mainMenuScreen.getStage().addActor(scrollPane);

        mainMenuScreen.getBatch().draw(loadScreen, 0, 0, mainMenuScreen.getViewport().getWorldWidth(), mainMenuScreen.getViewport().getWorldHeight());

        mainMenuScreen.getBatch().end();

    }

    public void destroy() {

        scrollPane.remove();
        close.remove();
        table.clear();
        temp=false;
    }

    public int getLevel(LevelContainer level) {
        if (level instanceof Level1Container) {
            return 1;
        } else if (level instanceof Level2Container) {
            return 2;
        } else if (level instanceof Level3Container) {
            return 3;
        }
        return 0;
    }

}
