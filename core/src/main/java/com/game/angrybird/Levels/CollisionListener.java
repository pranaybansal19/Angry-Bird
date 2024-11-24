package com.game.angrybird.Levels;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.Objects;

public class CollisionListener implements ContactListener {
    private World world;
    private Array<Body> bodiesToDestroy = new Array<>();

    public CollisionListener(World world) {
        this.world = world;
    }

    public void addBodies(Array<Body> bodies) {
        bodiesToDestroy.addAll(bodies);
    }

    @Override
    public void beginContact(Contact contact) {
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        float maxImpulse = 0;
        for (float normalImpulse : impulse.getNormalImpulses()) {
            maxImpulse = Math.max(maxImpulse, normalImpulse);
        }

        float destructionThreshold = 70f;

        if (maxImpulse > destructionThreshold) {

            if (!"ground".equals(bodyA.getUserData()) && !Objects.equals("projectile_bird", bodyA.getUserData()) && !bodiesToDestroy.contains(bodyA, true) && bodyA.getType() != BodyDef.BodyType.StaticBody) {
                bodiesToDestroy.add(bodyA);
            }
            if (!"ground".equals(bodyB.getUserData()) && !Objects.equals("projectile_bird", bodyB.getUserData()) && !bodiesToDestroy.contains(bodyB, true) && bodyB.getType() != BodyDef.BodyType.StaticBody) {
                bodiesToDestroy.add(bodyB);
            }
        }
    }

    public void update(Array<Body> woodBox, Array<Body> woodHorizontalPlank, Array<Body> woodVerticalPlank, Array<Body> glassBox, Array<Body> glassHorizontalPlank, Array<Body> glassVerticalPlank, Array<Body> birdsBody, Array<Body> pigList1, Array<Body> pigList2,Array<Body> pigList3) {
        for (Body body : bodiesToDestroy) {
            woodHorizontalPlank.removeValue(body, true);
            woodVerticalPlank.removeValue(body, true);
            glassHorizontalPlank.removeValue(body, true);
            glassVerticalPlank.removeValue(body, true);
            birdsBody.removeValue(body, true);
            woodBox.removeValue(body, true);
            glassBox.removeValue(body, true);
            pigList1.removeValue(body, true);
            pigList2.removeValue(body, true);
            pigList3.removeValue(body, true);
            world.destroyBody(body);
            body.setUserData("destroyed");

        }
        bodiesToDestroy.clear();
    }
}
