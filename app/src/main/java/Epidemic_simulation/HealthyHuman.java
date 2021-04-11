package Epidemic_simulation;

import java.util.Random;

public class HealthyHuman extends Human{
    public HealthyHuman(int x, int y, int mapSize) {
        super(x, y, mapSize, 3);
    }

    public boolean isInfected(InfectedHuman infectedHuman){ return true; }

}
