package Epidemic_simulation;

import java.util.Random;

public abstract class Human extends SimulationObject{
    public Human(int x, int y, int mapHeight, int mapWidth, int moveRange){
        super(x, y);
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.moveRange = moveRange;
    }

    private final int mapHeight;

    private final int mapWidth;

    private final int moveRange;

    public void Move(){}
}
