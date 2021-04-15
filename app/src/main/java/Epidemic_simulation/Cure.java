package Epidemic_simulation;

public class Cure extends SimulationObject{
    public Cure(int x, int y, int recoveryChance){
        super(x, y);
        this.recoveryChance = recoveryChance;
    }
    private final int recoveryChance;

    public int getRecoveryChance(){ return this.recoveryChance; }

}
