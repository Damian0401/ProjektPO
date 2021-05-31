package Epidemic_simulation;

import Epidemic_simulation.IMove;

public abstract class AInfectedObject extends ASimulationObject implements IMove {

    public abstract int getInfectChance();

    public abstract boolean isCured(ACureObject cure);
}
