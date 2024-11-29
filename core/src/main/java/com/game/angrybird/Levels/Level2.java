package com.game.angrybird.Levels;

import com.badlogic.gdx.Screen;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Levels.LevelContainers.Level2Container;

public class Level2 extends Level implements Screen {

    public Level2(AngryBird game, Level2Container level2Container) {
        super(game,level2Container,2);
        createGesture();
        createLevel();
    }

    @Override
    public Level getLevel() {
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

