package com.game.angrybird.Pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Pig2 implements Pig {

    private World world;
    private Batch batch;

    private TextureRegion pig;

    public Pig2(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        pig = new TextureRegion(new Texture(Gdx.files.internal("Pigs/Pig2.png")));
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

        batch.draw(pig,
            position.x - radius, position.y - radius,
            radius, radius,
            width, height,
            1f, 1f,
            (float) Math.toDegrees(angle)
        );

    }

    @Override
    public boolean isClicked(Body body, float mouseX, float mouseY) {
        Vector2 position = body.getPosition();
        CircleShape shape = (CircleShape) body.getFixtureList().get(0).getShape();
        float radius = shape.getRadius();

        float distance = position.dst(mouseX, mouseY);
        return distance <= radius;
    }

    @Override
    public void destroy() {
    }
}
