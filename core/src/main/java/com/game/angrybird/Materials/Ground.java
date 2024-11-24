package com.game.angrybird.Materials;

import com.badlogic.gdx.physics.box2d.*;

public class Ground {

    private World world;

    public Ground(World world) {
        this.world = world;
    }

    public Body create(BodyDef bodyDef, float x, float y, float width, float height) {

        bodyDef.position.set(x, y);

        Body groundBody = world.createBody(bodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        groundBody.createFixture(fixtureDef);
        groundBody.setUserData("ground");

        groundBox.dispose();

        return groundBody;
    }

}
