package Epidemic_simulation;

public class HealthyHuman extends Human{
    public HealthyHuman(int x, int y, int mapHeight, int mapWidth) {
        super(x, y, mapHeight, mapWidth, 3);
    }

    public boolean isInfected(InfectedHuman infectedHuman){ return true; }

}
