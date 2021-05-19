package Epidemic_simulation;

import java.util.Objects;

public abstract class SimulationObject {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return false;
        if (!(o instanceof SimulationObject)) return false;
        SimulationObject that = (SimulationObject) o;
        return xPosition == that.xPosition && yPosition == that.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }

    @Override
    public String toString() {
        return "(" + xPosition + ", " + yPosition + ")";
    }
}
