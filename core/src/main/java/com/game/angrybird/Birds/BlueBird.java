package com.game.angrybird.Birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BlueBird implements Bird{

    private World world;
    private Batch batch;
    private float speed;

    private TextureRegion bird;

    public BlueBird(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        bird = new TextureRegion(new Texture(Gdx.files.internal("Birds/BlueBird.png")));
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
    public void useSpecialAbility(){}

    @Override
    public Body create(BodyDef bodyDef, float x, float y, float radius) {
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.5f;

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

        batch.draw(bird,
            position.x - radius, position.y - radius,
            radius, radius,
            width, height,
            1f, 1f,
            (float) Math.toDegrees(angle)
        );

    }

    public boolean isClicked(Body body, float mouseX, float mouseY) {
        Vector2 position = body.getPosition();
        CircleShape shape = (CircleShape) body.getFixtureList().get(0).getShape();
        float radius = shape.getRadius();

        float distance = position.dst(mouseX, mouseY);
        return distance <= radius;
    }

    @Override
    public void destroy() {
        batch.dispose();
        world.dispose();
    }
}
