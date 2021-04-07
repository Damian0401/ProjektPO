package Epidemic_simulation;

import java.util.Random;

public class Human extends SimulationObject{
    Human(int x, int y, int mapSize, int moveRange){
        super(x, y);
        this.mapSize = mapSize;
        this.moveRange = moveRange;
    }

    private final Random random = new Random();

    private final int mapSize;

    private final int moveRange;

    public void Move(){}

    public int getMapSize() {
        return mapSize;
    }

    public int getMoveRange() {
        return moveRange;
    }
}
