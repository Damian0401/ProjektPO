package Epidemic_simulation;

import Epidemic_simulation.AMedicalObject;

public class MedicalHuman extends AMedicalObject {


    private int xPosition;
    private int yPosition;
    private final int moveRange;

    public MedicalHuman(int xPosition, int yPosition, int moveRange) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveRange = moveRange;
    }

    @Override
    public boolean cureSuccessful(int xPosition, int yPosition) {
        // Sprawdzenie czy współrzędne obiektu InfectedHuman są zbyt odległe, aby został uleczony i na tej postawie wzrócenie true lub false
        return Math.abs(xPosition - this.getXPosition()) < 2 && Math.abs(yPosition - this.getYPosition()) < 2 ;
    }

    @Override
    public int getXPosition() {
        return this.xPosition;
    }

    @Override
    public int getYPosition() {
        return this.yPosition;
    }

    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public int getMoveRange() {
        return this.moveRange;
    }
}
