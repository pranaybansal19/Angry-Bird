package com.game.angrybird;

import com.game.angrybird.Birds.Bird;
import com.game.angrybird.Levels.Level;
import com.game.angrybird.Levels.Level1;
import com.game.angrybird.Levels.LevelContainers.Level1Container;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class TestLevel {

    Level1 level1 = new Level1();
    Level1Container level1Container = new Level1Container();
    ArrayList<ArrayList<Object>> pigs = new ArrayList<>();
    ArrayList<ArrayList<Object>> birds = new ArrayList<>();
    ArrayList<ArrayList<Object>> materials = new ArrayList<>();

    @Before
    public void setUp() {
        initializePigs();
        initializeBirds();
        initializeMaterial();
    }

    public void initializePigs(){
        Object[][] data = {
            // type x y radius angle vx vy health
            {"pig3",38f, 12f, 2f, 0f, 0f, 0f, 350f},
            {"pig2",38f, 25f, 2f, 0f, 0f, 0f, 350f},
            {"pig1",33f, 10f, 1.5f, 0f, 0f, 0f, 350f},
            {"pig1",43f, 10f, 1.5f, 0f, 0f, 0f, 350f},
        };

        for (Object[] entry : data) {
            ArrayList<Object> pig = new ArrayList<>();
            for (Object value : entry) {
                pig.add(value);
            }
            pigs.add(pig);
        }
    }

    public void initializeBirds(){
        Object[][] data = {
            // type x y radius angle vx vy
            {"redBird",9f, 3.5f, 1.1f, 0f, 0f, 0f},
            {"blueBird",7f, 3.5f, 0.94f, 0f, 0f, 0f},
            {"blackBird",4.6f, 4f, 1.5f, 0f, 0f, 0f},
            {"yellowBird",2f, 3.5f, 0.97f, 0f, 0f, 0f},
        };

        for (Object[] entry : data) {
            ArrayList<Object> bird = new ArrayList<>();
            for (Object value : entry) {
                bird.add(value);
            }
            birds.add(bird);
        }
    }

    public void initializeMaterial(){
        Object[][] data = {
            // type x y width height angle vx vy health
            {"woodBox", 33f, 4f, 3.5f, 4.5f, 0f, 0f, 0f, 350f},
            {"woodBox", 43f, 4f, 3.5f, 4.5f, 0f, 0f, 0f, 350f},
            {"woodPlankHorizontal", 33f, 8f, 9f, 2f, 0f, 0f, 0f, 350f},
            {"woodPlankHorizontal", 43f, 8f, 9f, 2f, 0f, 0f, 0f, 350f},
            {"woodPlankHorizontal", 38f, 10f, 9f, 2f, 0f, 0f, 0f, 350f},
            {"woodPlankVertical", 34.5f, 15.5f, 1.5f, 11f, 0f, 0f, 0f, 350f},
            {"woodPlankVertical", 41.5f, 15.5f, 1.5f, 11f, 0f, 0f, 0f, 350f},
            {"woodPlankVertical", 29.5f, 13.5f, 1.5f, 11f, 0f, 0f, 0f, 350f},
            {"woodPlankVertical", 46.5f, 13.5f, 1.5f, 11f, 0f, 0f, 0f, 350f},
            {"woodPlankHorizontal", 38f, 22f, 9f, 2f, 0f, 0f, 0f, 350f},
            {"woodPlankVertical", 34.5f, 29.5f, 1.5f, 11f, 0f, 0f, 0f, 350f},
            {"woodPlankVertical", 41.5f, 29.5f, 1.5f, 11f, 0f, 0f, 0f, 350f},
            {"woodPlankHorizontal", 38f, 35f, 9f, 2f, 0f, 0f, 0f, 350f},
            {"woodPlankHorizontal", 29f, 20f, 7f, 2f, 0f, 0f, 0f, 350f},
            {"woodPlankHorizontal", 47f, 20f, 7f, 2f, 0f, 0f, 0f, 350f}
        };


        for (Object[] entry : data) {
            ArrayList<Object> material = new ArrayList<>();
            for (Object value : entry) {
                material.add(value);
            }
            materials.add(material);
        }
    }

    @Test
    public void checkBirdsCount() {
       assertEquals(4,level1.getBirds_left());
    }

    @Test
    public void checkScore() {
        assertEquals(0,level1.getScore());
        level1.setScore(level1.getScore()+100);
        assertEquals(100,level1.getScore());
    }

    @Test
    public void checkBirds(){
        assertEquals(4,level1Container.getBirds().size());

        int i=0;
        for (ArrayList<Object> bird : birds) {
            assertEquals(bird,level1Container.getBirds().get(i++));
        }
    }

    @Test
    public void checkPigs(){
        assertEquals(4,level1Container.getPigs().size());

        int i=0;
        for (ArrayList<Object> pig : pigs) {
            assertEquals(pig,level1Container.getPigs().get(i++));
        }
    }

    @Test
    public void checkProjectiles(){
        assertEquals(0,level1Container.getProjectileBirds().size());
    }

    @Test
    public void checkMaterials(){
        assertEquals(15,level1Container.getMaterials().size());

        int i=0;
        for (ArrayList<Object> wood : materials) {
            assertEquals(wood,level1Container.getMaterials().get(i++));
        }
    }


}
