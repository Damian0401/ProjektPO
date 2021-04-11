package Epidemic_simulation;

import java.util.Random;

public class InfectedHuman extends Human{

    public InfectedHuman(int x, int y, int mapSize, int infectChance) {
        super(x, y, mapSize, 1);
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
