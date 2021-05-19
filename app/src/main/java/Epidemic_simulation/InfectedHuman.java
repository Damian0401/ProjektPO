package Epidemic_simulation;

public class InfectedHuman extends SimulationObject{

    public InfectedHuman(int x, int y, int infectChance) {
        super(x, y);
        this.infectChance = infectChance;
    }

    public boolean isCured(Cure cure){
        return true;
    }

    private final int infectChance;

    public int getInfectChance() {
        return infectChance;
    }
}
