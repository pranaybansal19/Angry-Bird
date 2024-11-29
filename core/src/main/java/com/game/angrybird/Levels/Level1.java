package com.game.angrybird.Levels;

import com.badlogic.gdx.Screen;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Levels.LevelContainers.Level1Container;

public class Level1 extends Level implements Screen {

    public Level1(AngryBird game, Level1Container level1Container) {
        super(game,level1Container,1);
        createGesture();
        createLevel();
    }

    public Level1(){
        super();
    }

    @Override
    public Level getLevel(){
        return this;
    }

    public void render(float delta) {
        drawLevel();
    }

    @Override
    public void show() {
        getWorld().setContactListener(getCollisionListener());
        createGesture();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);

        getCamera().viewportWidth = getViewport().getWorldWidth();
        getCamera().viewportHeight = getViewport().getWorldHeight();
        getCamera().position.set(getCamera().viewportWidth / 2, getCamera().viewportHeight / 2, 0);
        getCamera().update();

        getBatch().setProjectionMatrix(getCamera().combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        if (getBatch() != null) getBatch().dispose();
        if (getStage() != null) getStage().dispose();

        if (getShapeRenderer() != null) getShapeRenderer().dispose();

        if (getWorld() != null) getWorld().dispose();
        if (getDebugRenderer() != null) getDebugRenderer().dispose();
    }
}
