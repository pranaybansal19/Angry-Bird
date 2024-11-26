package com.game.angrybird.Materials;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Stone implements Material {

    private World world;
    private Batch batch;

    private TextureRegion stonePlankHorizontal, stonePlankVertical, stoneBox;
    private TextureRegion stonePlankHorizontalDamaged, stonePlankVerticalDamaged, stoneBoxDamaged;

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

    public Stone(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        stonePlankHorizontal = new TextureRegion(new Texture(Gdx.files.internal("Stone/StonePlankHorizontal.png")));
        stonePlankVertical = new TextureRegion(new Texture(Gdx.files.internal("Stone/StonePlankVertical.png")));
        stoneBox = new TextureRegion(new Texture(Gdx.files.internal("Stone/StoneBox.png")));

        stonePlankHorizontalDamaged = new TextureRegion(new Texture(Gdx.files.internal("Stone/StonePlankHorizontalDamaged.png")));
        stonePlankVerticalDamaged = new TextureRegion(new Texture(Gdx.files.internal("Stone/StonePlankVerticalDamaged.png")));
        stoneBoxDamaged = new TextureRegion(new Texture(Gdx.files.internal("Stone/StoneBoxDamaged.png")));

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
    public void drawPlankVertical(Body body) {
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
    public void drawBox(Body body) {
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
