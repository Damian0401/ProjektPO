package Epidemic_simulation;

import java.util.Random;

public class InfectedHuman extends Human{

    InfectedHuman(int x, int y, int mapSize, int infectChance) {
        super(x, y, mapSize, 1);
        this.infectChance = infectChance;
    }

    private final Random random = new Random();

    public boolean isCured(Cure cure){
        return true;
    }

    private final int infectChance;

    public int getInfectChance() {
        return infectChance;
    }
}
