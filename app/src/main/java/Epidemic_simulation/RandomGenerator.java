package Epidemic_simulation;

import java.util.Random;

public class RandomGenerator{

    private static final Random random = new Random();

    private RandomGenerator() {}

    public static int getMove(int range){
        return random.nextInt(2 * range + 1) - range;
    }

    public static int getChance(){
        return random.nextInt(101);
    }

    public static int getPosition(int range){
        return random.nextInt(range);
    }
}
