package com.game.angrybird;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Handler {

    public static void hoverEffect(Image image){

        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);

        image.addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                image.setScale(1.1f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                image.setScale(1f);
            }
        });

    }
}
