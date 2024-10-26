package com.game.angrybird.Materials;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class TNT {

    private World world;
    private Batch batch;

    private TextureRegion TNTimage;

    public TNT(World world, Batch batch) {
        this.world = world;
        this.batch = batch;
    }

    public Body create(BodyDef bodyDef, float x, float y, float width, float height) {
        return null;
    }

    public void draw(Body body) {

    }

    public boolean isClicked(Body body, float mouseX, float mouseY) {
        return false;
    }

    public void destroy() {

    }
}
