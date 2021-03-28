package Epidemic_simulation;

public class Cure extends SimulationObject{
    Cure(int x, int y){
        super(x, y);
        amount++;
    }
    public static int amount;
    public int recoveryChance;
}
