package com.game.angrybird.Levels.LevelContainers;

import java.util.ArrayList;

public interface LevelContainer{
    //Getters
    ArrayList<ArrayList<Object>> getMaterials();

    ArrayList<ArrayList<Object>> getPigs();

    ArrayList<ArrayList<Object>> getBirds();

    ArrayList<ArrayList<Object>> getProjectileBirds();

    int getBestScore();

    int getScore();

    int getBirds_left();

    //Setters
    void setMaterials(ArrayList<ArrayList<Object>> materials);

    void setPigs(ArrayList<ArrayList<Object>> pigs);

    void setBirds(ArrayList<ArrayList<Object>> birds);

    void setProjectileBirds(ArrayList<ArrayList<Object>> projectileBirds);

    void setBestScore(int bestScore);

    void setScore(int score);


    void setBirds_left(int birds_left);

    void addMaterials();

    void addPigs();

    void addBirds();
}
