package com.game.angrybird.Materials;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.game.angrybird.AngryBird;

import java.util.Objects;

public class Stone implements Material {

    private World world;
    private Batch batch;

    private Body body;
    private TextureRegion stonePlankHorizontal, stonePlankVertical, stoneBox;
    private TextureRegion stonePlankHorizontalDamaged, stonePlankVerticalDamaged, stoneBoxDamaged;

    private String type;

    float health = 350;

    Vector2 size;

    public Stone() {}

    public Stone(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        stonePlankHorizontal = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Stone/StonePlankHorizontal.png")));
        stonePlankVertical = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Stone/StonePlankVertical.png")));
        stoneBox = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Stone/StoneBox.png")));

        stonePlankHorizontalDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Stone/StonePlankHorizontalDamaged.png")));
        stonePlankVerticalDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Stone/StonePlankVerticalDamaged.png")));
        stoneBoxDamaged = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Stone/StoneBoxDamaged.png")));

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
    public void createQuad(BodyDef bodyDef, float x, float y, float width, float height,String type) {

        this.type=type;

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
        if (Objects.equals(type, "stoneBox")) {
            drawBox();
        } else if (Objects.equals(type, "stonePlankHorizontal")) {
            drawPlankHorizontal();
        } else if (Objects.equals(type, "stonePlankVertical")) {
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
            batch.draw(stonePlankHorizontalDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(stonePlankHorizontal,
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
            batch.draw(stonePlankVerticalDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(stonePlankVertical,
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
            batch.draw(stoneBoxDamaged,
                position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                (float) Math.toDegrees(angle)
            );
        } else {
            batch.draw(stoneBox,
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
