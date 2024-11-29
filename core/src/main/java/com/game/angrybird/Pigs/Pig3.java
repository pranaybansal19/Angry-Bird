package com.game.angrybird.Pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.game.angrybird.AngryBird;

import java.util.Objects;

public class Pig3 implements Pig {

    private World world;
    private Batch batch;

    private float health = 350;

    private Body body;
    private TextureRegion pig, pigDamaged;

    public Pig3() {}


    public Pig3(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        pig = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Pigs/Pig3.png")));
        pigDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Pigs/Pig3Damaged.png")));
    }

    @Override
    public TextureRegion getPig() {
        return pig;
    }

    @Override
    public TextureRegion getPigDamaged() {
        return pigDamaged;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public void create(BodyDef bodyDef, float x, float y, float radius) {
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.6f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.3f;

        body.setUserData(this);
        body.createFixture(fixtureDef);
        circle.dispose();

    }

    @Override
    public void draw(float width, float height) {

        if (health <= 0) {
            return;
        }

        Vector2 position = body.getPosition();
        float angle = body.getAngle();

        CircleShape shape = (CircleShape) body.getFixtureList().get(0).getShape();
        float radius = shape.getRadius();

        if (health <= 200) {
            batch.draw(pigDamaged,
                position.x - radius, position.y - radius,
                radius, radius,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(pig,
                position.x - radius, position.y - radius,
                radius, radius,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        }
    }

    @Override
    public void destroy() {
    }

}
