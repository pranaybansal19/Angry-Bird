package com.game.angrybird.Levels.LevelContainers;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Level1Container implements LevelContainer, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArrayList<ArrayList<Object>> materials = new ArrayList<>();
    public ArrayList<ArrayList<Object>> pigs = new ArrayList<>();
    public ArrayList<ArrayList<Object>> birds = new ArrayList<>();
    public ArrayList<ArrayList<Object>> projectileBirds = new ArrayList<>();
    public int bestScore = 0;
    public int score = 0;
    public int birds_left = 4;



    public Level1Container() {
        addMaterials();
        addPigs();
        addBirds();
    }

    //Getters
    @Override
    public ArrayList<ArrayList<Object>> getMaterials() {
        return materials;
    }

    @Override
    public ArrayList<ArrayList<Object>> getPigs() {
        return pigs;
    }

    @Override
    public ArrayList<ArrayList<Object>> getBirds() {
        return birds;
    }

    @Override
    public ArrayList<ArrayList<Object>> getProjectileBirds() {
        return projectileBirds;
    }

    @Override
    public int getBestScore() {
        return bestScore;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getBirds_left() {
        return birds_left;
    }

    //Setters
    @Override
    public void setMaterials(ArrayList<ArrayList<Object>> materials) {
        this.materials = materials;
    }

    @Override
    public void setPigs(ArrayList<ArrayList<Object>> pigs) {
        this.pigs = pigs;
    }

    @Override
    public void setBirds(ArrayList<ArrayList<Object>> birds) {
        this.birds = birds;
    }

    @Override
    public void setProjectileBirds(ArrayList<ArrayList<Object>> projectileBirds) {
        this.projectileBirds = projectileBirds;
    }

    @Override
    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void setBirds_left(int birds_left) {
        this.birds_left = birds_left;
    }

    @Override
    public void addMaterials() {

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

    @Override
    public void addPigs() {
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

    @Override
    public void addBirds(){
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


}
