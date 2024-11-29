package com.game.angrybird.Levels.LevelContainers;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Level3Container implements LevelContainer, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArrayList<ArrayList<Object>> materials = new ArrayList<>();
    public ArrayList<ArrayList<Object>> pigs = new ArrayList<>();
    public ArrayList<ArrayList<Object>> birds = new ArrayList<>();
    public ArrayList<ArrayList<Object>> projectileBirds = new ArrayList<>();
    public int bestScore = 0;
    public int score = 0;
    public int birds_left = 4;

    public Level3Container() {
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
            {"stonePlankVertical", 32f, 5.5f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"stonePlankVertical", 36f, 7f, 1f, 4.5f, 0f, 0f, 0f, 350f},
            {"stonePlankHorizontal", 33.8f, 11.5f, 4.5f, 1.5f, 0f, 0f, 0f, 350f},
            {"stonePlankHorizontal", 38.5f, 11.5f, 5f, 1.5f, 0f, 0f, 0f, 350f},
            {"stonePlankVertical", 40.5f, 8.5f, 1f, 4.5f, 0f, 0f, 0f, 350f},
            {"stonePlankVertical", 36f, 16.5f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"stonePlankVertical", 40.5f, 16.5f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"stoneBox", 37f, 4f, 3f, 3.5f, 0f, 0f, 0f, 350f},
            {"stoneBox", 40f, 4f, 3f, 3.5f, 0f, 0f, 0f, 350f},
            {"stonePlankHorizontal", 38.5f, 22f, 6f, 1.5f, 0f, 0f, 0f, 350f},
            {"stonePlankHorizontal", 45f, 22f, 6f, 1.5f, 0f, 0f, 0f, 350f},
            {"stonePlankHorizontal", 42f, 24f, 5f, 1.5f, 0f, 0f, 0f, 350f},
            {"stonePlankVertical", 38.5f, 27.5f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"stonePlankHorizontal", 38.5f, 32f, 6f, 1.5f, 0f, 0f, 0f, 350f},
            {"stonePlankVertical", 45f, 27.5f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"stonePlankHorizontal", 45f, 32f, 6f, 1.5f, 0f, 0f, 0f, 350f},
            {"stoneBox", 38.5f, 33f, 3f, 3.5f, 0f, 0f, 0f, 350f},
            {"stoneBox", 45f, 33f, 3f, 3.5f, 0f, 0f, 0f, 350f}
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
            {"pig1", 33.5f, 5f, 1.3f, 0f, 0f, 0f, 350f},
            {"pig3", 38f, 6f, 1.3f, 0f, 0f, 0f, 350f},
            {"pig3", 38f, 12.5f, 1.3f, 0f, 0f, 0f, 350f},
            {"pig1", 41.8f, 26f, 1.3f, 0f, 0f, 0f, 350f},
            {"pig2", 42f, 35f, 1.3f, 0f, 0f, 0f, 350f},
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
    public void addBirds() {
        Object[][] data = {
            // type x y radius angle vx vy
            {"redBird", 9f, 3.5f, 1.1f, 0f, 0f, 0f},
            {"blueBird", 7f, 3.5f, 0.94f, 0f, 0f, 0f},
            {"blackBird", 4.6f, 4f, 1.5f, 0f, 0f, 0f},
            {"yellowBird", 2f, 3.5f, 0.97f, 0f, 0f, 0f},
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
