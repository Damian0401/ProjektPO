package Epidemic_simulation;

public class InfectedHuman extends Human{

    InfectedHuman(int x, int y, int mapSize, int infectChance) {
        super(x, y, mapSize, 1);
        this.infectChance = infectChance;
    }

    public boolean isCured(Cure cure){
        return true;
    }

    private int infectChance;

    public int getInfectChance() {
        return infectChance;
    }
}
