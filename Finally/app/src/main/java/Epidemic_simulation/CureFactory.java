package Epidemic_simulation;

public class CureFactory implements ICureFactory{
    private final int recoveryChance;

    public CureFactory(int recoveryChance){
        this.recoveryChance = recoveryChance;
    }

    @Override
    public Cure createCure(int xPosition, int yPosition) {
        return new Cure(xPosition, yPosition, this.recoveryChance);
    }
}
