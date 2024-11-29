package com.game.angrybird.Materials;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.game.angrybird.AngryBird;

import java.util.Objects;

public class Glass implements Material {

    private World world;
    private Batch batch;

    private Body body;
    private TextureRegion glassPlankHorizontal, glassPlankVertical, glassBox;
    private TextureRegion glassPlankHorizontalDamaged, glassPlankVerticalDamaged, glassBoxDamaged;

    private String type;

    float health = 350;

    Vector2 size;

    public Glass() {}

    public Glass(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        glassPlankHorizontal = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Glass/GlassPlankHorizontal.png")));
        glassPlankVertical = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Glass/GlassPlankVertical.png")));
        glassBox = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Glass/GlassBox.png")));

        glassPlankHorizontalDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Glass/GlassPlankHorizontalDamaged.png")));
        glassPlankVerticalDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Glass/GlassPlankVerticalDamaged.png")));
        glassBoxDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Glass/GlassBoxDamaged.png")));
    }

    //Getters
    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    @Override
    public float getHealth() {
        return health;
    }


    //Setters
    @Override
    public void setHealth(float health) {
        this.health = health;
    }


    @Override
    public void createQuad(BodyDef bodyDef, float x, float y, float width, float height, String type) {

        this.type = type;

        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        box.dispose();

        size = new Vector2(width, height);

        body.setUserData(this);

    }

    @Override
    public void draw() {
        if (Objects.equals(type, "glassBox")) {
            drawBox();
        } else if (Objects.equals(type, "glassPlankHorizontal")) {
            drawPlankHorizontal();
        } else if (Objects.equals(type, "glassPlankVertical")) {
            drawPlankVertical();
        }
    }

    @Override
    public void drawPlankHorizontal() {

        if (health <= 0) {
            return;
        }

        Vector2 position = body.getPosition();
        float angle = body.getAngle();

        float width = size.x;
        float height = size.y;

        if (health <= 200) {
            batch.draw(glassPlankHorizontalDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(glassPlankHorizontal,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        }
    }

    @Override
    public void drawPlankVertical() {

        if (health <= 0) {
            return;
        }

        Vector2 position = body.getPosition();
        float angle = body.getAngle();

        float width = size.x;
        float height = size.y;

        if (health <= 200) {
            batch.draw(glassPlankVerticalDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(glassPlankVertical,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        }
    }

    @Override
    public void drawBox() {

        if (health <= 0) {
            return;
        }

        Vector2 position = body.getPosition();
        float angle = body.getAngle();

        float width = size.x;
        float height = size.y;

        if (health <= 200) {
            batch.draw(glassBoxDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(glassBox,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        }
    }

    @Override
    public void destroy() {
        world.dispose();
        batch.dispose();
    }


}
