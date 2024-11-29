package com.game.angrybird.Birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.game.angrybird.AngryBird;

import java.util.Objects;

public class BlueBird implements Bird {

    private World world;
    private Batch batch;

    private Body body = null;
    private Body body1 = null;
    private Body body2 = null;
    private TextureRegion bird;

    private boolean abilityUsed = false;

    public BlueBird(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        bird = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Birds/BlueBird.png")));
    }

    // Setters
    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    @Override
    public void setBird(TextureRegion bird) {
        this.bird = bird;
    }

    @Override
    public void setAbilityUsed(boolean abilityUsed) {
        this.abilityUsed = abilityUsed;
    }

    @Override
    public void setBody(Body body) {
        this.body = body;
    }

    // Getters
    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Batch getBatch() {
        return batch;
    }

    @Override
    public TextureRegion getBird() {
        return bird;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public boolean isAbilityUsed() {
        return abilityUsed;
    }

    public void useAbility() {

        Vector2 position = body.getPosition();
        Vector2 velocity = body.getLinearVelocity();
        float angle = body.getAngle();

        world.destroyBody(body);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        bodyDef.angle = angle;

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.8f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        shape.dispose();

        body.setLinearVelocity(velocity);


        body1 = world.createBody(bodyDef);

        CircleShape shape1 = new CircleShape();
        shape1.setRadius(0.8f);

        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape;
        fixtureDef1.density = 1.0f;
        fixtureDef1.friction = 0.2f;
        fixtureDef1.restitution = 0.5f;

        body1.createFixture(fixtureDef1);
        shape1.dispose();

        body1.setLinearVelocity(velocity.x, velocity.y + 6);


        body2 = world.createBody(bodyDef);

        CircleShape shape2 = new CircleShape();
        shape2.setRadius(0.8f);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape;
        fixtureDef2.density = 1.0f;
        fixtureDef2.friction = 0.2f;
        fixtureDef2.restitution = 0.5f;

        body2.createFixture(fixtureDef2);
        shape2.dispose();

        body2.setLinearVelocity(velocity.x, velocity.y - 6);


    }

    @Override
    public void create(BodyDef bodyDef, float x, float y, float radius) {
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        circle.dispose();

    }

    @Override
    public void draw(float width, float height) {

        Vector2 position = body.getPosition();
        float angle = body.getAngle();

        CircleShape shape = (CircleShape) body.getFixtureList().get(0).getShape();
        float radius = shape.getRadius();

        batch.draw(bird,
            position.x - radius, position.y - radius,
            radius, radius,
            width, height,
            1f, 1f,
            (float) Math.toDegrees(angle)
        );

        if (body1 != null) {
            position = body1.getPosition();
            angle = body1.getAngle();

            shape = (CircleShape) body1.getFixtureList().get(0).getShape();
            radius = shape.getRadius();

            batch.draw(bird,
                position.x - radius, position.y - radius,
                radius, radius,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        }

        if (body2 != null) {
            position = body2.getPosition();
            angle = body2.getAngle();

            shape = (CircleShape) body2.getFixtureList().get(0).getShape();
            radius = shape.getRadius();

            batch.draw(bird,
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
        batch.dispose();
        world.dispose();
    }
}
