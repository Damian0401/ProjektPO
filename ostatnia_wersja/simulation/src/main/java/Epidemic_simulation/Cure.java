package Epidemic_simulation;

import Epidemic_simulation.ACureObject;

public class Cure extends ACureObject {
    private final int recoveryChance;
    private int xPosition;
    private int yPosition;
    public Cure(int xPosition, int yPosition, int recoveryChance) {
        this.recoveryChance = recoveryChance;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    @Override
    public int getRecoveryChance(){
        return this.recoveryChance;
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
}
