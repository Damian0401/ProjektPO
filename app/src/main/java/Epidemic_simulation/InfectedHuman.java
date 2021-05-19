package Epidemic_simulation;

public class InfectedHuman extends SimulationObject{

    public InfectedHuman(int x, int y, int infectChance) {
        super(x, y);
        this.infectChance = infectChance;
    }

    public boolean isCured(Cure cure){
        if(Math.abs(cure.getXPosition() - this.getXPosition()) > 2 || Math.abs(cure.getYPosition() - this.getYPosition()) > 2 ) return false;

        return cure.getRecoveryChance()>=RandomGenerator.getChance();
    }

    private final int infectChance;

    public int getInfectChance() {
        return infectChance;
    }
}
