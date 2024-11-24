package com.game.angrybird.Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import com.game.angrybird.Birds.Bird;
import com.game.angrybird.Materials.Ground;
import com.game.angrybird.Materials.Wood;
import com.game.angrybird.Pigs.Pig1;
import com.game.angrybird.AngryBird;
import com.game.angrybird.Pigs.Pig2;
import com.game.angrybird.Pigs.Pig3;

public interface Level{

    AngryBird getGame();
//    Bird getBlackBird();
//    Bird getBlueBird();
//    Bird getRedBird();
//    Bird getYellowBird();
//    Ground getGround();
//    Pig1 getPig1();
//    Pig2 getPig2();
//    Pig3 getPig3();
//
//
//    void setBlackBird(Bird blackBird);
//    void setRedBird(Bird redBird);
//    void setBlueBird(Bird blueBird);
//    void setYellowBird(Bird yellowBird);
//    void setGround(Ground ground);
//    void setGroundBody(Body groundBody);
//    void setPig1(Pig1 pig1);
//    void setPig2(Pig2 pig2);
//    void setPig3(Pig3 pig3);


    void createLevel();

    void drawLevel();
}
