package com.game.angrybird.Levels.LevelContainers;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Level2Container implements LevelContainer, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArrayList<ArrayList<Object>> materials = new ArrayList<>();
    public ArrayList<ArrayList<Object>> pigs = new ArrayList<>();
    public ArrayList<ArrayList<Object>> birds = new ArrayList<>();
    public ArrayList<ArrayList<Object>> projectileBirds = new ArrayList<>();
    public int bestScore = 0;
    public int score = 0;
    public int birds_left = 4;

    public Level2Container() {
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
            {"glassPlankVertical", 32f, 5.5f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"glassPlankVertical", 36f, 7f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"glassPlankHorizontal", 34f, 12.5f, 5.5f, 1.5f, 0f, 0f, 0f, 350f},
            {"glassPlankHorizontal", 36.3f, 13.5f, 5f, 1.5f, 0f, 0f, 0f, 350f},
            {"glassPlankVertical", 36f, 19f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"glassPlankHorizontal", 36f, 24f, 6f, 1.5f, 0f, 0f, 0f, 350f},
            {"glassPlankHorizontal", 42.5f, 2f, 6f, 1.5f, 0f, 0f, 0f, 350f},
            {"glassPlankVertical", 40f, 7f, 1f, 7f, 0f, 0f, 0f, 350f},
            {"glassBox", 40f, 13f, 3f, 3.5f, 0f, 0f, 0f, 350f},
            {"glassPlankVertical", 44.5f, 9f, 1f, 8f, 0f, 0f, 0f, 350f},
            {"glassPlankHorizontal", 44.5f, 12f, 5f, 1.5f, 0f, 0f, 0f, 350f},
            {"glassBox", 36f, 27f, 3f, 3.5f, 0f, 0f, 0f, 350f},
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
            {"pig1", 33.8f, 5f, 1.5f, 0f, 0f, 0f, 350f},
            {"pig1", 39.8f, 16f, 1.5f, 0f, 0f, 0f, 350f},
            {"pig2", 44.4f, 14f, 1.5f, 0f, 0f, 0f, 350f},
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
