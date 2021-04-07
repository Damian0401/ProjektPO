package Epidemic_simulation;

import java.util.Random;

public class HealthyHuman extends Human{
    HealthyHuman(int x, int y, int mapSize) {
        super(x, y, mapSize, 3);
    }

    private final Random random = new Random();

    public boolean isInfected(InfectedHuman infectedHuman){ return true; }

}
