package Epidemic_simulation;

import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String[] args) {
        System.out.println(args.length);
        Epidemic epidemic = new Epidemic(50, 50, 100, 1, 10, 50, 200, 5, 20, 20, 10);
        try {
            epidemic.startSimulation();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
