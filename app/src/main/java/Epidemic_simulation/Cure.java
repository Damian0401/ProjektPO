package Epidemic_simulation;

public class Cure extends SimulationObject{
    public Cure(int x, int y, int recoveryChance){
        super(x, y);
        this.recoveryChance = recoveryChance;
        amount++;
    }
    private static int amount;
    private int recoveryChance;

    public static int getAmount(){
        return amount;
    }

    public static void decreaseAmount(){
        amount--;
    }

    public int getRecoveryChance(){ return this.recoveryChance; }

}
