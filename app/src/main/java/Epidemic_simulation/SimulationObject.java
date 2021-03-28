package Epidemic_simulation;

abstract public class SimulationObject {
    public int xPosition;
    public int yPosition;

    SimulationObject(int x, int y){
        xPosition = x;
        yPosition = y;
    }
}
