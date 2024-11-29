package com.game.angrybird.Pigs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public interface Pig {

    float getHealth();

    void setHealth(float health);

    TextureRegion getPig();

    TextureRegion getPigDamaged();

    void create(BodyDef bodyDef, float x, float y, float radius);

    void draw(float width, float height);

    void destroy();

    Body getBody();
}
