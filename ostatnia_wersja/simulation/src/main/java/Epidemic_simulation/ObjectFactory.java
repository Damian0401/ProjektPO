package Epidemic_simulation;

public class ObjectFactory implements IObjectFactory {

    private final int moveRange;
    private final int infectChance;
    private final int recoveryChance;
    private int medicalNumber = 0;
    private boolean firstInfectedExist;

    public ObjectFactory(int moveRange, int infectChance, int recoveryChance){
        this.moveRange = moveRange;
        this.infectChance = infectChance;
        this.recoveryChance = recoveryChance;
    }

    @Override
    public AInfectedObject createInfectedObject(int xPosition, int yPosition) {
        firstInfectedExist = true;
        if(RandomGenerator.getChance()<6) return new MutatedInfectedHuman(xPosition, yPosition, this.moveRange, this.infectChance);
        return new InfectedHuman(xPosition, yPosition, this.moveRange, this.infectChance);
    }

    @Override
    public AHealthyObject createHealthyObject(int xPosition, int yPosition) {
        if(firstInfectedExist && RandomGenerator.getChance()<51) return new CuredHealthyHuman(xPosition, yPosition, this.moveRange);
        return new HealthyHuman(xPosition, yPosition, this.moveRange);
    }

    @Override
    public AMedicalObject createMedicalObject(int xPosition, int yPosition) {
        if(medicalNumber>4) {
            medicalNumber=0;
            return new InexperiencedMedicalHuman(xPosition, yPosition, this.moveRange);
        }
        medicalNumber++;
        return new MedicalHuman(xPosition, yPosition, this.moveRange);
    }

    @Override
    public ACureObject createCureObject(int xPosition, int yPosition) {
        if(RandomGenerator.getChance()<11) return new DefectedCure(xPosition, yPosition, recoveryChance);
        return new Cure(xPosition, yPosition, recoveryChance);
    }
}
