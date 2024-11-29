package com.game.angrybird;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    AngryBird game = AngryBird.getInstance();


    @Test
    public void checkLevelLocked() {
        assertFalse(game.getPlayer().isLevel1Locked());
        assertTrue(game.getPlayer().isLevel2Locked());
        assertTrue(game.getPlayer().isLevel3Locked());
    }

    @Test
    public void checkBestScores(){
        assertEquals(0,game.getPlayer().getLevel1BestScore());
        assertEquals(0,game.getPlayer().getLevel2BestScore());
        assertEquals(0,game.getPlayer().getLevel3BestScore());
    }

    @Test
    public void checkUsername(){
        assertEquals("Player",game.getPlayer().getUsername());
    }
}
