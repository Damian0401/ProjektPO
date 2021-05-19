package Epidemic_simulation;

public class HealthyHuman extends SimulationObject{
    public HealthyHuman(int x, int y) {
        super(x, y);
    }

    public boolean isInfected(InfectedHuman infectedHuman){ return true; }

}
