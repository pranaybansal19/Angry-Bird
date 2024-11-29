package com.game.angrybird.Levels;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Materials.Glass;
import com.game.angrybird.Materials.Material;
import com.game.angrybird.Materials.Stone;
import com.game.angrybird.Materials.Wood;
import com.game.angrybird.Pigs.Pig;

import java.util.Objects;

public class CollisionListener implements ContactListener {
    private World world;
    private AngryBird game;
    private Level level;
    private Array<Body> bodiesToDestroy = new Array<>();

    public CollisionListener(World world, AngryBird game, Level level) {
        this.world = world;
        this.game = game;
        this.level = level;
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

        if (maxImpulse > 40) {

            if (!Objects.equals("projectile_bird", bodyA.getUserData()) && !bodiesToDestroy.contains(bodyA, true) && bodyA.getType() != BodyDef.BodyType.StaticBody) {

                if (bodyA.getUserData() instanceof Material) {
                    Material m = (Material) bodyA.getUserData();
                    m.setHealth(m.getHealth() - maxImpulse);

                    if (m.getHealth() <= 0) {
                        bodiesToDestroy.add(bodyA);
                    }

                }

                if (bodyA.getUserData() instanceof Pig) {
                    Pig p = (Pig) bodyA.getUserData();
                    p.setHealth(p.getHealth() - maxImpulse);

                    if (p.getHealth() <= 0) {
                        bodiesToDestroy.add(bodyA);
                    }

                }
            }
            if (!Objects.equals("projectile_bird", bodyB.getUserData()) && !bodiesToDestroy.contains(bodyB, true) && bodyB.getType() != BodyDef.BodyType.StaticBody) {

                if (bodyB.getUserData() instanceof Material) {
                    Material m = (Material) bodyB.getUserData();
                    m.setHealth(m.getHealth() - maxImpulse);

                    if (m.getHealth() <= 0) {
                        bodiesToDestroy.add(bodyB);
                    }
                }

                if (bodyB.getUserData() instanceof Pig) {
                    Pig p = (Pig) bodyB.getUserData();
                    p.setHealth(p.getHealth() - maxImpulse);

                    if (p.getHealth() <= 0) {
                        bodiesToDestroy.add(bodyB);
                    }
                }
            }
        }
    }

    public void update() {
        for (Body body : bodiesToDestroy) {

            if (body.getUserData() instanceof Material) {
                level.updateScore(100);

                if (game.getMainMenuScreen().getSettings().isSound()) {

                    if (body.getUserData() instanceof Wood) {
                        game.destroyed.play();
                    } else if (body.getUserData() instanceof Glass) {
                        game.glassDestroyed.play();
                    } else if (body.getUserData() instanceof Stone) {
                        game.stoneDestroyed.play();
                    }
                }

            } else if (body.getUserData() instanceof Pig) {
                level.updateScore(1000);
            }

            world.destroyBody(body);
        }

        bodiesToDestroy.clear();
    }
}
