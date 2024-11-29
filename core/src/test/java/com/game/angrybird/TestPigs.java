package com.game.angrybird;

import com.game.angrybird.Pigs.Pig1;
import com.game.angrybird.Pigs.Pig2;
import com.game.angrybird.Pigs.Pig3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPigs {

    Pig1 pig1 = new Pig1();
    Pig2 pig2 = new Pig2();
    Pig3 pig3 = new Pig3();

    @Test
    public void CheckHealth() {
        assertEquals(350,pig1.getHealth(),2);
        assertEquals(350,pig2.getHealth(),2);
        assertEquals(350,pig3.getHealth(),2);
    }

    @Test
    public void CheckHealthAfterDamage() {
        float damage = 100;
        pig1.setHealth(pig1.getHealth()-damage);
        pig2.setHealth(pig2.getHealth()-damage);
        pig3.setHealth(pig3.getHealth()-damage);

        assertEquals(250,pig1.getHealth(),2);
        assertEquals(250,pig2.getHealth(),2);
        assertEquals(250,pig3.getHealth(),2);
    }

}
