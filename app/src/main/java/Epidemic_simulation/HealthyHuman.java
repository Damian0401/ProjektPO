package Epidemic_simulation;

public class HealthyHuman extends SimulationObject{
    public HealthyHuman(int x, int y) {
        super(x, y);
    }

    public boolean isInfected(InfectedHuman infectedHuman) {

        if(Math.abs(infectedHuman.getXPosition() - this.getXPosition()) > 2 || Math.abs(infectedHuman.getYPosition() - this.getYPosition()) > 2 ) return false;

        return infectedHuman.getInfectChance()>=RandomGenerator.getChance();
    }
}
