package com.game.angrybird.Materials;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public interface Material {
    void createQuad(BodyDef bodyDef, float x, float y, float width, float height, String type);

    void draw();

    void drawPlankHorizontal();

    void drawPlankVertical();

    void drawBox();

    void destroy();

    String getType();

    Vector2 getSize();

    float getHealth();

    void setHealth(float v);

    Body getBody();
}
