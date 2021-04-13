package Epidemic_simulation;

public class MedicalHuman extends Human{
    MedicalHuman(int x, int y, int mapHeight, int mapWidth) {
        super(x, y, mapHeight, mapWidth, 2);
    }

    public boolean cure(InfectedHuman infectedHuman){ return true; }
}
