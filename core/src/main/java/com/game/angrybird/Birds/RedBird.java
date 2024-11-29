package com.game.angrybird.Birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.game.angrybird.AngryBird;

import java.util.Objects;

public class RedBird implements Bird {

    private World world;
    private Batch batch;

    private Body body;
    private TextureRegion bird;

    private boolean abilityUsed = false;

    public RedBird(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        bird = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Birds/RedBird.png")));
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

    }

    @Override
    public void destroy() {
        batch.dispose();
        world.dispose();
    }
}
