package com.game.angrybird;

import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username = "Player";

    private boolean level1Locked = false;
    private boolean level2Locked = true;
    private boolean level3Locked = true;

    private int level1BestScore = 0;
    private int level2BestScore = 0;
    private int level3BestScore = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLevel1Locked() {
        return level1Locked;
    }

    public void setLevel1Locked(boolean level1Locked) {
        this.level1Locked = level1Locked;
    }

    public boolean isLevel2Locked() {
        return level2Locked;
    }

    public void setLevel2Locked(boolean level2Locked) {
        this.level2Locked = level2Locked;
    }

    public boolean isLevel3Locked() {
        return level3Locked;
    }

    public void setLevel3Locked(boolean level3Locked) {
        this.level3Locked = level3Locked;
    }

    public int getLevel1BestScore() {
        return level1BestScore;
    }

    public void setLevel1BestScore(int level1BestScore) {
        this.level1BestScore = level1BestScore;
    }

    public int getLevel2BestScore() {
        return level2BestScore;
    }

    public void setLevel2BestScore(int level2BestScore) {
        this.level2BestScore = level2BestScore;
    }

    public int getLevel3BestScore() {
        return level3BestScore;
    }

    public void setLevel3BestScore(int level3BestScore) {
        this.level3BestScore = level3BestScore;
    }
}
