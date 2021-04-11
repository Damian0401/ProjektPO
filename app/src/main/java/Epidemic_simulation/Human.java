package Epidemic_simulation;

import java.util.Random;

public class Human extends SimulationObject{
    public Human(int x, int y, int mapSize, int moveRange){
        super(x, y);
        this.mapSize = mapSize;
        this.moveRange = moveRange;
    }

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
