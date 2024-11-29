package com.game.angrybird.Levels.Pause;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Birds.*;
import com.game.angrybird.FileHandler;
import com.game.angrybird.Handler;
import com.game.angrybird.Levels.Level1;
import com.game.angrybird.Levels.Level2;
import com.game.angrybird.Levels.Level3;
import com.game.angrybird.Levels.LevelContainers.Level1Container;
import com.game.angrybird.Levels.LevelContainers.LevelContainer;
import com.game.angrybird.Levels.LevelMenuScreen;
import com.game.angrybird.Materials.Glass;
import com.game.angrybird.Materials.Stone;
import com.game.angrybird.Materials.Wood;
import com.game.angrybird.Pigs.Pig;
import com.game.angrybird.Pigs.Pig1;
import com.game.angrybird.Pigs.Pig2;
import com.game.angrybird.Pigs.Pig3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.TreeMap;

public class PauseMenu {

    private PauseScreen pauseScreen;

    private Texture background;

    private Image resume, restart, save, menu;

    public PauseMenu(PauseScreen pauseScreen) {
        this.pauseScreen = pauseScreen;
        create();

    }

    public float getRadius(Body body) {
        float radius = 0f;

        for (Fixture fixture : body.getFixtureList()) {
            Shape shape = fixture.getShape();

            if (shape instanceof CircleShape) {
                CircleShape circleShape = (CircleShape) shape;

                radius = circleShape.getRadius();
            }
        }
        return radius;
    }

    public void saveGame() {
        ArrayList<Wood> woods = pauseScreen.getLevel().getWoods();
        ArrayList<Glass> glasses = pauseScreen.getLevel().getGlasses();
        ArrayList<Stone> stones = pauseScreen.getLevel().getStones();
        ArrayList<Pig> pigs = pauseScreen.getLevel().getPigs();
        ArrayList<Bird> birds = pauseScreen.getLevel().getBirds();
        ArrayList<Bird> projectileBirds = pauseScreen.getLevel().getProjectileBirds();

        LevelContainer levelContainer = pauseScreen.getLevel().getLevelContainer();

        levelContainer.getMaterials().clear();
        for (Wood wood : woods) {
            ArrayList<Object> o = new ArrayList<>();
            o.add(0, wood.getType());
            o.add(1, wood.getBody().getPosition().x);
            o.add(2, wood.getBody().getPosition().y);
            o.add(3, wood.getSize().x);
            o.add(4, wood.getSize().y);
            o.add(5, wood.getBody().getAngle());
            o.add(6, wood.getBody().getLinearVelocity().x);
            o.add(7, wood.getBody().getLinearVelocity().y);
            o.add(8, wood.getHealth());

            levelContainer.getMaterials().add(o);
        }

        for (Glass glass : glasses) {
            ArrayList<Object> o = new ArrayList<>();
            o.add(0, glass.getType());
            o.add(1, glass.getBody().getPosition().x);
            o.add(2, glass.getBody().getPosition().y);
            o.add(3, glass.getSize().x);
            o.add(4, glass.getSize().y);
            o.add(5, glass.getBody().getAngle());
            o.add(6, glass.getBody().getLinearVelocity().x);
            o.add(7, glass.getBody().getLinearVelocity().y);
            o.add(8, glass.getHealth());

            levelContainer.getMaterials().add(o);
        }

        for (Stone stone : stones) {
            ArrayList<Object> o = new ArrayList<>();
            o.add(0, stone.getType());
            o.add(1, stone.getBody().getPosition().x);
            o.add(2, stone.getBody().getPosition().y);
            o.add(3, stone.getSize().x);
            o.add(4, stone.getSize().y);
            o.add(5, stone.getBody().getAngle());
            o.add(6, stone.getBody().getLinearVelocity().x);
            o.add(7, stone.getBody().getLinearVelocity().y);
            o.add(8, stone.getHealth());

            levelContainer.getMaterials().add(o);
        }

        levelContainer.getPigs().clear();
        for (Pig pig : pigs) {
            ArrayList<Object> o = new ArrayList<>();

            if (pig instanceof Pig1) {
                o.add(0, "pig1");
            } else if (pig instanceof Pig2) {
                o.add(0, "pig2");
            } else if (pig instanceof Pig3) {
                o.add(0, "pig3");
            }

            o.add(1, pig.getBody().getPosition().x);
            o.add(2, pig.getBody().getPosition().y);
            o.add(3, getRadius(pig.getBody()));
            o.add(4, pig.getBody().getAngle());
            o.add(5, pig.getBody().getLinearVelocity().x);
            o.add(6, pig.getBody().getLinearVelocity().y);
            o.add(7, pig.getHealth());

            levelContainer.getPigs().add(o);
        }

        levelContainer.getBirds().clear();
        for (Bird bird : birds) {
            ArrayList<Object> o = new ArrayList<>();

            if (bird instanceof RedBird) {
                o.add(0, "redBird");
            } else if (bird instanceof BlueBird) {
                o.add(0, "blueBird");
            } else if (bird instanceof BlackBird) {
                o.add(0, "blackBird");
            } else if (bird instanceof YellowBird) {
                o.add(0, "yellowBird");
            }

            o.add(1, bird.getBody().getPosition().x);
            o.add(2, bird.getBody().getPosition().y);
            o.add(3, getRadius(bird.getBody()));
            o.add(4, bird.getBody().getAngle());
            o.add(5, bird.getBody().getLinearVelocity().x);
            o.add(6, bird.getBody().getLinearVelocity().y);

            levelContainer.getBirds().add(o);
        }

        levelContainer.getProjectileBirds().clear();
        for (Bird projectileBird : projectileBirds) {

            ArrayList<Object> o = new ArrayList<>();

            if (projectileBird instanceof RedBird) {
                o.add(0, "redBird");
            } else if (projectileBird instanceof BlackBird) {
                o.add(0, "blackBird");
            } else if (projectileBird instanceof YellowBird) {
                o.add(0, "yellowBird");
            } else if (projectileBird instanceof BlueBird) {
                o.add(0, "blueBird");
            }

            o.add(1, projectileBird.getBody().getPosition().x);
            o.add(2, projectileBird.getBody().getPosition().y);
            o.add(3, getRadius(projectileBird.getBody()));
            o.add(4, projectileBird.getBody().getAngle());
            o.add(5, projectileBird.getBody().getLinearVelocity().x);
            o.add(6, projectileBird.getBody().getLinearVelocity().y);

            levelContainer.getProjectileBirds().add(o);
        }

        levelContainer.setScore(pauseScreen.getLevel().getScore());
        levelContainer.setBestScore(pauseScreen.getLevel().getBestScore());
        levelContainer.setBirds_left(pauseScreen.getLevel().getBirds_left());

        TreeMap<String, LevelContainer> map = FileHandler.getSavedGames();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String curr = sdf.format(new Date());

        map.put(curr, levelContainer);

        FileHandler.addtoSavedGames(map);


    }

    public void create() {

        background = AngryBird.loadTextureSafely("Pause Screen/Background.png");

        resume = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Pause Screen/ResumeBtn.png")));
        resume.setSize(pauseScreen.getViewport().getWorldWidth() / 4.0f, pauseScreen.getViewport().getWorldHeight() / 10.0f);
        resume.setPosition(pauseScreen.getViewport().getWorldWidth() / 2 - resume.getWidth() / 2 + 14, pauseScreen.getViewport().getWorldHeight() / 2 - resume.getHeight() / 2 + 75 + 25);

        save = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Pause Screen/SaveBtn.png")));
        save.setSize(pauseScreen.getViewport().getWorldWidth() / 6.0f, pauseScreen.getViewport().getWorldHeight() / 12.0f);
        save.setPosition(pauseScreen.getViewport().getWorldWidth() / 2 - save.getWidth() / 2 + 15, pauseScreen.getViewport().getWorldHeight() / 2 - save.getHeight() / 2 + 20);

        restart = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Pause Screen/RestartBtn.png")));
        restart.setSize(pauseScreen.getViewport().getWorldWidth() / 6.0f, pauseScreen.getViewport().getWorldHeight() / 14.0f);
        restart.setPosition(pauseScreen.getViewport().getWorldWidth() / 2 - restart.getWidth() / 2 + 15, pauseScreen.getViewport().getWorldHeight() / 2 - restart.getHeight() / 2 - 75 + 25);

        menu = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Pause Screen/MenuBtn.png")));
        menu.setSize(pauseScreen.getViewport().getWorldWidth() / 6.0f, pauseScreen.getViewport().getWorldHeight() / 12.0f);
        menu.setPosition(pauseScreen.getViewport().getWorldWidth() / 2 - menu.getWidth() / 2 + 15, pauseScreen.getViewport().getWorldHeight() / 2 - menu.getHeight() / 2 - 150 + 30);

        // Event Listeners
        resume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Resume Clicked.");
                pauseScreen.getLevel().getGame().setScreen((Screen) pauseScreen.getLevel());

                if (pauseScreen.getLevel().getGame().getMainMenuScreen().getSettings().isMusic()) {
                    pauseScreen.getLevel().getGame().levelMusic.setLooping(true);
                    pauseScreen.getLevel().getGame().levelMusic.play();
                }
            }
        });

        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Save clicked.");
                saveGame();
                pauseScreen.getLevel().getGame().setScreen(pauseScreen.getLevel().getGame().getMainMenuScreen());
                pauseScreen.getLevel().getGame().getMainMenuScreen().setSaveProgressOpen(true);
                pauseScreen.getLevel().getGame().getMainMenuScreen().setProgress(0f);
                pauseScreen.getLevel().getGame().getMainMenuScreen().setStartTime(TimeUtils.nanoTime());

                pauseScreen.getLevel().getGame().levelMusic.stop();

                if (pauseScreen.getLevel().getGame().getMainMenuScreen().getSettings().isMusic()) {
                    pauseScreen.getLevel().getGame().backgroundMusic.setLooping(true);
                    pauseScreen.getLevel().getGame().backgroundMusic.play();
                }

            }
        });

        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart Clicked.");

                if (pauseScreen.getLevel() instanceof Level1) {
                    pauseScreen.getLevel().getGame().setLevel1(new Level1(pauseScreen.getLevel().getGame(), null));
                    pauseScreen.getLevel().getGame().setScreen((Screen) pauseScreen.getLevel().getGame().getLevel1());
                }
                if (pauseScreen.getLevel() instanceof Level2) {
                    pauseScreen.getLevel().getGame().setLevel2(new Level2(pauseScreen.getLevel().getGame(), null));
                    pauseScreen.getLevel().getGame().setScreen((Screen) pauseScreen.getLevel().getGame().getLevel2());
                }
                if (pauseScreen.getLevel() instanceof Level3) {
                    pauseScreen.getLevel().getGame().setLevel3(new Level3(pauseScreen.getLevel().getGame(), null));
                    pauseScreen.getLevel().getGame().setScreen((Screen) pauseScreen.getLevel().getGame().getLevel3());
                }

                pauseScreen.getLevel().getGame().levelMusic.stop();

                if (pauseScreen.getLevel().getGame().getMainMenuScreen().getSettings().isMusic()) {
                    pauseScreen.getLevel().getGame().levelMusic.setLooping(true);
                    pauseScreen.getLevel().getGame().levelMusic.play();
                }
            }
        });

        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu Clicked.");
                pauseScreen.getLevel().getGame().setLevelMenuScreen(new LevelMenuScreen(pauseScreen.getLevel().getGame()));
                pauseScreen.getLevel().getGame().setScreen(pauseScreen.getLevel().getGame().getLevelMenuScreen());

                pauseScreen.getLevel().getGame().levelMusic.stop();

                if (pauseScreen.getLevel().getGame().getMainMenuScreen().getSettings().isMusic()) {
                    pauseScreen.getLevel().getGame().backgroundMusic.setLooping(true);
                    pauseScreen.getLevel().getGame().backgroundMusic.play();
                }
            }
        });

        Handler.hoverEffect(resume, pauseScreen.getLevel().getGame());
        Handler.hoverEffect(save, pauseScreen.getLevel().getGame());
        Handler.hoverEffect(restart, pauseScreen.getLevel().getGame());
        Handler.hoverEffect(menu, pauseScreen.getLevel().getGame());

    }

    public void draw() {

        pauseScreen.getBatch().begin();

        pauseScreen.getBatch().draw(background, 0, 0, pauseScreen.getViewport().getWorldWidth(), pauseScreen.getViewport().getWorldHeight());

        pauseScreen.getStage().addActor(resume);
        pauseScreen.getStage().addActor(save);
        pauseScreen.getStage().addActor(restart);
        pauseScreen.getStage().addActor(menu);

        pauseScreen.getBatch().end();

    }

    public void destroy() {
        resume.remove();
        save.remove();
        restart.remove();
        menu.remove();
    }

}
