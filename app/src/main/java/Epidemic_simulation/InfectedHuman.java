package Epidemic_simulation;

public class InfectedHuman extends Human{

    public InfectedHuman(int x, int y, int mapHeight, int mapWidth, int infectChance) {
        super(x, y, mapHeight, mapWidth, 1);
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
