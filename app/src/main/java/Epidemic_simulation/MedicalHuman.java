package Epidemic_simulation;

public class MedicalHuman extends SimulationObject{
    MedicalHuman(int x, int y) {
        super(x, y);
    }

    public boolean cure(InfectedHuman infectedHuman){
        return Math.abs(infectedHuman.getXPosition() - this.getXPosition()) < 2 && Math.abs(infectedHuman.getYPosition() - this.getYPosition()) < 2 ;
    }
}
