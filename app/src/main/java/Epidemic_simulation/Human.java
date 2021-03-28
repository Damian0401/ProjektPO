package Epidemic_simulation;

public abstract class Human extends SimulationObject{
    Human(int x, int y, int mapSize){
        super(x, y);
        this.mapSize = mapSize;
    }

    private int mapSize;

    abstract public void Move();

}
