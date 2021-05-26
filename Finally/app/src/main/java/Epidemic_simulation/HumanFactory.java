package Epidemic_simulation;

public class HumanFactory implements IHumanFactory {

    private final int moveRange;
    private final int infectChance;


    public HumanFactory(int moveRange, int infectChance){
        this.moveRange = moveRange;
        this.infectChance = infectChance;
    }

    @Override
    public AInfectedHuman createInfectedHuman(int xPosition, int yPosition) {
        return new InfectedHuman(xPosition, yPosition, this.moveRange, this.infectChance);
    }

    @Override
    public AHealthyHuman createHealthyHuman(int xPosition, int yPosition) {
        return new HealthyHuman(xPosition, yPosition, this.moveRange);
    }

    @Override
    public AMedicalHuman createMedicalHuman(int xPosition, int yPosition) {
        return new MedicalHuman(xPosition, yPosition, this.moveRange);
    }
}
