package com.game.angrybird.Materials;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Wood implements Material {

    private World world;
    private Batch batch;

    private TextureRegion woodPlankHorizontal, woodPlankVertical, woodBox;
    private TextureRegion woodPlankHorizontalDamaged, woodPlankVerticalDamaged, woodBoxDamaged;

    float health = 350;

    Vector2 size;

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    public Wood(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        woodPlankHorizontal = new TextureRegion(new Texture(Gdx.files.internal("Wood/WoodPlankHorizontal.png")));
        woodPlankVertical = new TextureRegion(new Texture(Gdx.files.internal("Wood/WoodPlankVertical.png")));
        woodBox = new TextureRegion(new Texture(Gdx.files.internal("Wood/WoodBox.png")));

        woodBoxDamaged = new TextureRegion(new Texture(Gdx.files.internal("Wood/WoodBoxDamaged.png")));
        woodPlankHorizontalDamaged = new TextureRegion(new Texture(Gdx.files.internal("Wood/WoodPlankHorizontalDamaged.png")));
        woodPlankVerticalDamaged = new TextureRegion(new Texture(Gdx.files.internal("Wood/WoodPlankVerticalDamaged.png")));

    }

    @Override
    public Body createQuad(BodyDef bodyDef, float x, float y, float width, float height) {
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

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

        return body;
    }

    @Override
    public void drawPlankHorizontal(Body body) {
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
    public void drawPlankVertical(Body body) {
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
    public void drawBox(Body body) {
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
