package Epidemic_simulation;

public class MedicalHuman extends Human{
    MedicalHuman(int x, int y, int mapSize) {
        super(x, y, mapSize, 2);
    }

    public boolean cure(InfectedHuman infectedHuman){ return true; }
}
