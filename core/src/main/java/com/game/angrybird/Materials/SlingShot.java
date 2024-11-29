package com.game.angrybird.Materials;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Birds.Bird;

import java.util.Objects;

public class SlingShot {

    private Image slingShot;

    private Viewport viewport;
    private Stage stage;

    private Vector2 slingshotPosition;

    public SlingShot(Viewport viewport, Stage stage) {
        this.viewport = viewport;
        this.stage = stage;
    }


    public void create(float x, float y, float width, float height) {

        slingshotPosition = new Vector2(x, y);
        slingShot = new Image(Objects.requireNonNull(AngryBird.loadTextureSafely("Slingshot/Slingshot.png")));
        slingShot.setSize(width, height);
        slingShot.setPosition(x, y);

    }

    public void launchProjectile(Vector2 releasePoint, Vector2 initialTouch, Body projectileBody) {
        Vector2 launchVector = initialTouch.cpy().sub(releasePoint);
        projectileBody.applyLinearImpulse(launchVector.scl(33), projectileBody.getWorldCenter(), true);
    }

    private boolean isInsideSlingshotTop(Vector2 point) {
        return true;
    }

    public void draw() {
        stage.addActor(slingShot);

    }

    public void destroy() {
        slingShot.remove();
    }

}
