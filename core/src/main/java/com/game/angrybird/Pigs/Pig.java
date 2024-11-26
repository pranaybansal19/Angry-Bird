package com.game.angrybird.Pigs;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public interface Pig {

    float getHealth();

    void setHealth(float health);

    Body create(BodyDef bodyDef, float x, float y, float radius);

    void draw(Body body, float width, float height);

    void destroy();
}
