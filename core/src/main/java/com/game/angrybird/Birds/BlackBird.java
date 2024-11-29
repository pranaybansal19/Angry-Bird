package com.game.angrybird.Birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.game.angrybird.AngryBird;

import java.util.Objects;

public class BlackBird implements Bird {

    private World world;
    private Batch batch;

    private Body body;
    private TextureRegion bird;
    private Texture explosionTexture;

    private boolean bombExploded = false;
    private float explosionTimer = 0f;
    private final float EXPLOSION_DURATION = 0.5f;
    private Vector2 explosionPosition;

    private boolean abilityUsed = false;

    public BlackBird(World world, Batch batch) {
        this.world = world;
        this.batch = batch;

        bird = new TextureRegion(Objects.requireNonNull(AngryBird.loadTextureSafely("Birds/BlackBird.png")));
        explosionTexture = new Texture("Birds/explode.png");

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

    public void setBombExploded(boolean bombExploded) {
        this.bombExploded = bombExploded;
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
            float explosionRadius = 7f;
            Vector2 explosionCenter = body.getPosition();
            explosionPosition=body.getPosition().cpy();

            world.destroyBody(body);
            body=null;

            world.QueryAABB(fixture -> {
                    Body body = fixture.getBody();

                    if (body.getType() == BodyDef.BodyType.StaticBody) return true;

                    Vector2 bodyPosition = body.getPosition();
                    float distance = explosionCenter.dst(bodyPosition);

                    if (distance <= explosionRadius) {
                        Vector2 forceDirection = bodyPosition.cpy().sub(explosionCenter).nor();
                        float forceMagnitude = (explosionRadius - distance) * 100f;
                        body.applyLinearImpulse(forceDirection.scl(forceMagnitude), bodyPosition, true);
                    }

                    return true;
                },
                explosionCenter.x - explosionRadius,
                explosionCenter.y - explosionRadius,
                explosionCenter.x + explosionRadius,
                explosionCenter.y + explosionRadius);



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

        if (body==null) {
            if (bombExploded) {
                explosionTimer += Gdx.graphics.getDeltaTime();
                if (explosionTimer < EXPLOSION_DURATION) {
                    batch.draw(
                        explosionTexture,
                        explosionPosition.x - (float) 3 / 2,
                        explosionPosition.y - (float) 3 / 2,
                        3,
                        3
                    );
                } else {
                    bombExploded = false;
                    explosionTimer = 0f;
                }
            }
            return;
        }

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
