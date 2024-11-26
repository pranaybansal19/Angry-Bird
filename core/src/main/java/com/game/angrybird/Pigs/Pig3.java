package com.game.angrybird.Pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Pig3 implements Pig {

    private World world;
    private Batch batch;

    private float health = 350;

    private TextureRegion pig,pigDamaged;


    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }


    public Pig3(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        pig = new TextureRegion(new Texture(Gdx.files.internal("Pigs/Pig3.png")));
        pigDamaged = new TextureRegion(new Texture(Gdx.files.internal("Pigs/Pig3Damaged.png")));
    }

    @Override
    public Body create(BodyDef bodyDef, float x, float y, float radius) {
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

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

        return body;
    }

    @Override
    public void draw(Body body, float width, float height) {

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
