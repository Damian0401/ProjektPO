package Epidemic_simulation;

abstract public class SimulationObject {
    private int xPosition;
    private int yPosition;

    SimulationObject(int x, int y){
        xPosition = x;
        yPosition = y;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
