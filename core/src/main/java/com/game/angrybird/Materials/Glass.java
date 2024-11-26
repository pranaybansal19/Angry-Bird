package com.game.angrybird.Materials;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Glass implements Material {

    private World world;
    private Batch batch;

    private TextureRegion glassPlankHorizontal, glassPlankVertical, glassBox;

    public Glass(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        glassPlankHorizontal = new TextureRegion(new Texture(Gdx.files.internal("Glass/GlassPlankHorizontal.png")));
        glassPlankVertical = new TextureRegion(new Texture(Gdx.files.internal("Glass/GlassPlankVertical.png")));
        glassBox = new TextureRegion(new Texture(Gdx.files.internal("Glass/GlassBox.png")));
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

        body.setUserData(new Vector2(width, height));

        return body;
    }

    @Override
    public void drawPlankHorizontal(Body body) {
        Vector2 position = body.getPosition();
        float angle = body.getAngle();

        Vector2 size = (Vector2) body.getUserData();
        float width = size.x;
        float height = size.y;

        batch.draw(glassPlankHorizontal,
            position.x - width / 2, position.y - height / 2,
            width / 2, height / 2,
            width, height,
            1f, 1f,
            (float) Math.toDegrees(angle)
        );
    }

    @Override
    public void drawPlankVertical(Body body) {
        Vector2 position = body.getPosition();
        float angle = body.getAngle();

        Vector2 size = (Vector2) body.getUserData();
        float width = size.x;
        float height = size.y;

        batch.draw(glassPlankVertical,
            position.x - width / 2, position.y - height / 2,
            width / 2, height / 2,
            width, height,
            1f, 1f,
            (float) Math.toDegrees(angle)
        );
    }

    @Override
    public void drawBox(Body body) {
        Vector2 position = body.getPosition();
        float angle = body.getAngle();

        Vector2 size = (Vector2) body.getUserData();
        float width = size.x;
        float height = size.y;

        batch.draw(glassBox,
            position.x - width / 2, position.y - height / 2,
            width / 2, height / 2,
            width, height,
            1f, 1f,
            (float) Math.toDegrees(angle)
        );
    }

    @Override
    public void destroy() {
        world.dispose();
        batch.dispose();
    }

    @Override
    public float getHealth() {
        return 0;
    }

    @Override
    public void setHealth(float v) {

    }

}
