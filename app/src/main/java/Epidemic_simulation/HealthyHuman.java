package Epidemic_simulation;

public class HealthyHuman extends Human{
    HealthyHuman(int x, int y, int mapSize) {
        super(x, y, mapSize, 3);
    }

    boolean isInfected(InfectedHuman infectedHuman){ return true; }

}
