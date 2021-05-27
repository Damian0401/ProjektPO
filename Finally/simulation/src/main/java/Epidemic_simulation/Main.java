package Epidemic_simulation;

import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String[] args) throws InterruptedException {

        simulation(100, 50, 50,1, 10, 50, 200, 10, 50, 50 ,15);

    }

    public static void simulation(int stageNumber, int mapHeight, int mapWidth, int moveRange, int infectChance, int recoveryChance,
                                  int healthyNumber, int infectedNumber, int medicalNumber, int cureNumber, int scale) throws InterruptedException {

        Epidemic epidemic = new Epidemic(mapHeight, mapWidth, moveRange, infectChance, recoveryChance, healthyNumber, infectedNumber, medicalNumber, cureNumber, scale);

        epidemic.drawMap();
        epidemic.saveStats();

        for(int i = 0; i < stageNumber; i++){
            TimeUnit.MILLISECONDS.sleep(500);
            epidemic.nextStage();
            epidemic.drawMap();
            epidemic.saveStats();
        }
    }
}
