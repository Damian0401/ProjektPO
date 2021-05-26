package Epidemic_simulation;

public abstract class AMedicalHuman extends ASimulationObject implements IMove{
    public abstract boolean cureSuccessful(int xPosition, int yPosition);
}
