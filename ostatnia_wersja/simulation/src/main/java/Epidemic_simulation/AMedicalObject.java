package Epidemic_simulation;

import Epidemic_simulation.IMove;

public abstract class AMedicalObject extends ASimulationObject implements IMove {
    public abstract boolean cureSuccessful(int xPosition, int yPosition);
}
