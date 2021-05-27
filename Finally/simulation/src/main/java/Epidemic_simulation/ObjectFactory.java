package Epidemic_simulation;

import Epidemic_simulation.*;

public class ObjectFactory implements IObjectFactory {

    private final int moveRange;
    private final int infectChance;
    private final int recoveryChance;


    public ObjectFactory(int moveRange, int infectChance, int recoveryChance){
        this.moveRange = moveRange;
        this.infectChance = infectChance;
        this.recoveryChance = recoveryChance;
    }

    @Override
    public AInfectedObject createInfectedHuman(int xPosition, int yPosition) {
        return new InfectedHuman(xPosition, yPosition, this.moveRange, this.infectChance);
    }

    @Override
    public AHealthyObject createHealthyHuman(int xPosition, int yPosition) {
        return new HealthyHuman(xPosition, yPosition, this.moveRange);
    }

    @Override
    public AMedicalObject createMedicalHuman(int xPosition, int yPosition) {
        return new MedicalHuman(xPosition, yPosition, this.moveRange);
    }

    @Override
    public ACureObject createCure(int xPosition, int yPosition) {
        return new Cure(xPosition, yPosition, recoveryChance);
    }
}
