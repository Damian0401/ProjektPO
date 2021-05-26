package Epidemic_simulation;

public abstract class AInfectedHuman extends ASimulationObject implements IMove{

    public abstract int getInfectChance();

    public abstract boolean isCured(ACure cure);
}
