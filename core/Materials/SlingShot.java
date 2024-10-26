package com.game.angrybird.Materials;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SlingShot {

    private Image slingShot;
    private Viewport viewport;
    private Stage stage;

    public SlingShot(Viewport viewport, Stage stage) {
        this.viewport = viewport;
        this.stage = stage;
    }

    public void create(float x,float y,float width,float height) {

        slingShot = new Image(new Texture(Gdx.files.internal("Slingshot/Slingshot.png")));
        slingShot.setSize(width, height);
        slingShot.setPosition(x,y);

    }

    public void draw() {
        stage.addActor(slingShot);
    }

    public void destroy(){
        slingShot.remove();
    }

}
