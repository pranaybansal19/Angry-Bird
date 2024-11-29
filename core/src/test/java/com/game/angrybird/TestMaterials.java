package com.game.angrybird;

import com.game.angrybird.Materials.Glass;
import com.game.angrybird.Materials.Stone;
import com.game.angrybird.Materials.Wood;
import com.game.angrybird.Pigs.Pig1;
import com.game.angrybird.Pigs.Pig2;
import com.game.angrybird.Pigs.Pig3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMaterials {

    Wood wood = new Wood();
    Stone stone = new Stone();
    Glass glass = new Glass();

    @Test
    public void checkHealth() {
        assertEquals(350,wood.getHealth(),2);
        assertEquals(350,stone.getHealth(),2);
        assertEquals(350,glass.getHealth(),2);
    }

    @Test
    public void checkHealthAfterDamage() {
        float damage = 100;
        wood.setHealth(wood.getHealth()-damage);
        glass.setHealth(glass.getHealth()-damage);
        stone.setHealth(stone.getHealth()-damage);

        assertEquals(250,wood.getHealth(),2);
        assertEquals(250,glass.getHealth(),2);
        assertEquals(250,stone.getHealth(),2);
    }

}
