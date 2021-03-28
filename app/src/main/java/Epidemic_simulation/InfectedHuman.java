package Epidemic_simulation;

public class InfectedHuman extends Human{

    InfectedHuman(int x, int y, int mapSize, int infectChance) {
        super(x, y, mapSize);
        this.infectChance = infectChance;
    }

    @Override
    public void Move() {

    }

    public boolean isCured(Cure cure){
        return true;
    }

    public int infectChance;

}
