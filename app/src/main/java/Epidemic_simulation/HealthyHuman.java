package Epidemic_simulation;

public class HealthyHuman extends Human{
    HealthyHuman(int x, int y, int mapSize) {
        super(x, y, mapSize);
    }

    @Override
    public void Move() {

    }

    boolean isInfected(InfectedHuman infectedHuman){ return true; }

}
