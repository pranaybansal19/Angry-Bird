package com.game.angrybird.Materials;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public interface Material {
    Body createQuad(BodyDef bodyDef, float x, float y, float width, float height);

    void drawPlankHorizontal(Body body);

    void drawPlankVertical(Body body);

    void drawBox(Body body);

    boolean isClicked(Body body, float mouseX, float mouseY);

    void destroy();
}
