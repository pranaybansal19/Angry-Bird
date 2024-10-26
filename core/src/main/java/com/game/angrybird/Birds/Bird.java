package com.game.angrybird.Birds;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public interface Bird {

    // Setters
    void setWorld(World world);
    void setBatch(Batch batch);
    void setBird(TextureRegion bird);

    // Getters
    World getWorld();
    Batch getBatch();
    TextureRegion getBird();

    Body create(BodyDef bodyDef, float x, float y, float radius);
    void draw(Body body, float width, float height);
    void destroy();
    void useSpecialAbility();

    boolean isClicked(Body body, float x, float y);
}
