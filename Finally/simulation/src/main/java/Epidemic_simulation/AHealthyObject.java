package Epidemic_simulation;

public abstract class AHealthyObject extends ASimulationObject implements IMove {

    public abstract boolean isInfected(AInfectedObject infectedHuman);
}
