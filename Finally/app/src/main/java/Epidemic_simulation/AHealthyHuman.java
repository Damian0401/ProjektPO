package Epidemic_simulation;

public abstract class AHealthyHuman extends ASimulationObject implements IMove{

    public abstract boolean isInfected(AInfectedHuman infectedHuman);
}
