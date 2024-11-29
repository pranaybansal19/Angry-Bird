package com.game.angrybird.Materials;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.game.angrybird.AngryBird;

import java.util.Objects;

public class Wood implements Material {

    private World world;
    private Batch batch;

    private Body body;
    private TextureRegion woodPlankHorizontal, woodPlankVertical, woodBox;
    private TextureRegion woodPlankHorizontalDamaged, woodPlankVerticalDamaged, woodBoxDamaged;

    private String type;

    float health = 350;

    Vector2 size;

    public Wood(){}

    public Wood(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        woodPlankHorizontal = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Wood/WoodPlankHorizontal.png")));
        woodPlankVertical = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Wood/WoodPlankVertical.png")));
        woodBox = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Wood/WoodBox.png")));

        woodBoxDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Wood/WoodBoxDamaged.png")));
        woodPlankHorizontalDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Wood/WoodPlankHorizontalDamaged.png")));
        woodPlankVerticalDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Wood/WoodPlankVerticalDamaged.png")));

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
        if (Objects.equals(type, "woodBox")) {
            drawBox();
        } else if (Objects.equals(type, "woodPlankHorizontal")) {
            drawPlankHorizontal();
        } else if (Objects.equals(type, "woodPlankVertical")) {
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
            batch.draw(woodPlankHorizontalDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(woodPlankHorizontal,
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
            batch.draw(woodPlankVerticalDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(woodPlankVertical,
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
            batch.draw(woodBoxDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(woodBox,
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
