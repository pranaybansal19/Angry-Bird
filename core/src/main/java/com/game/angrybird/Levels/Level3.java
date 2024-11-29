package com.game.angrybird.Levels;

import com.badlogic.gdx.Screen;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Levels.LevelContainers.Level3Container;
import com.game.angrybird.Pigs.Pig;
import com.game.angrybird.Pigs.Pig1;
import com.game.angrybird.Pigs.Pig2;
import com.game.angrybird.Pigs.Pig3;

public class Level3 extends Level implements Screen {

    public Level3(AngryBird game, Level3Container level3Container) {
        super(game,level3Container,3);
        createGesture();
        createLevel();
    }

    @Override
    public Level getLevel() {
        return this;
    }

    @Override
    public void drawPigs() {
        for (Pig pig : getPigs()) {
            if (pig instanceof Pig1) {
                pig.draw(3f, 3.5f);
            } else if (pig instanceof Pig2) {
                pig.draw(3f, 3.5f);
            } else if (pig instanceof Pig3) {
                pig.draw(3f, 3.5f);
            }
        }
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
