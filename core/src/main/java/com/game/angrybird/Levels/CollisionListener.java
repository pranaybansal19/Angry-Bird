package com.game.angrybird.Levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Materials.Material;
import com.game.angrybird.Pair;
import com.game.angrybird.Pigs.Pig;
import com.game.angrybird.Pigs.Pig1;
import com.game.angrybird.Pigs.Pig2;
import com.game.angrybird.Pigs.Pig3;

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

        if (maxImpulse > 40) {

            if (!"ground".equals(bodyA.getUserData()) && !Objects.equals("projectile_bird", bodyA.getUserData()) && !bodiesToDestroy.contains(bodyA, true) && bodyA.getType() != BodyDef.BodyType.StaticBody) {
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
            if (!"ground".equals(bodyB.getUserData()) && !Objects.equals("projectile_bird", bodyB.getUserData()) && !bodiesToDestroy.contains(bodyB, true) && bodyB.getType() != BodyDef.BodyType.StaticBody) {
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

    public boolean isCircle(Body body) {
        for (Fixture fixture : body.getFixtureList()) {
            if (fixture.getShape() instanceof CircleShape) {
                return true;
            }
        }
        return false;
    }

    public void update(Array<Pair<Body, Material>> woodBox, Array<Pair<Body, Material>> woodHorizontalPlank, Array<Pair<Body, Material>> woodVerticalPlank,
                       Array<Pair<Body, Material>> glassBox, Array<Pair<Body, Material>> glassHorizontalPlank, Array<Pair<Body, Material>> glassVerticalPlank,
                       Array<Pair<Body, Material>> stoneBox, Array<Pair<Body, Material>> stoneHorizontalPlank, Array<Pair<Body, Material>> stoneVerticalPlank,
                       Array<Pair<Body, Pig1>> pig1List, Array<Pair<Body, Pig2>> pig2List, Array<Pair<Body, Pig3>> pig3List) {
        for (Body body : bodiesToDestroy) {

            for (int i = 0; i < woodBox.size; ++i) {
                if (woodBox.get(i).getFirst() == body) {
                    woodBox.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < woodVerticalPlank.size; ++i) {
                if (woodVerticalPlank.get(i).getFirst() == body) {
                    woodVerticalPlank.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < woodHorizontalPlank.size; ++i) {
                if (woodHorizontalPlank.get(i).getFirst() == body) {
                    woodHorizontalPlank.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < glassBox.size; ++i) {
                if (glassBox.get(i).getFirst() == body) {
                    glassBox.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < glassVerticalPlank.size; ++i) {
                if (glassVerticalPlank.get(i).getFirst() == body) {
                    glassVerticalPlank.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < glassHorizontalPlank.size; ++i) {
                if (glassHorizontalPlank.get(i).getFirst() == body) {
                    glassHorizontalPlank.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < stoneBox.size; ++i) {
                if (stoneBox.get(i).getFirst() == body) {
                    stoneBox.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < stoneVerticalPlank.size; ++i) {
                if (stoneVerticalPlank.get(i).getFirst() == body) {
                    stoneVerticalPlank.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < stoneHorizontalPlank.size; ++i) {
                if (stoneHorizontalPlank.get(i).getFirst() == body) {
                    stoneHorizontalPlank.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < pig1List.size; ++i) {
                if (pig1List.get(i).getFirst() == body) {
                    pig1List.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < pig2List.size; ++i) {
                if (pig2List.get(i).getFirst() == body) {
                    pig2List.removeIndex(i);
                    break;
                }
            }

            for (int i = 0; i < pig3List.size; ++i) {
                if (pig3List.get(i).getFirst() == body) {
                    pig3List.removeIndex(i);
                    break;
                }
            }

            if (!isCircle(body)) {
                if (game.getMainMenuScreen().getSettings().isSound()) {
                    game.destroyed.play();
                }
                level.updateScore(100);
            } else {
                level.updateScore(1000);
            }
            world.destroyBody(body);
            body.setUserData("destroyed");
        }
        bodiesToDestroy.clear();
    }
}
