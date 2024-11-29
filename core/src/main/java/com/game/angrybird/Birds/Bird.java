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

    void setAbilityUsed(boolean abilityUsed);

    void setBody(Body body);

    // Getters
    World getWorld();
    Batch getBatch();
    TextureRegion getBird();
    Body getBody();

    boolean isAbilityUsed();

    void create(BodyDef bodyDef, float x, float y, float radius);
    void draw(float width, float height);
    void destroy();

}
