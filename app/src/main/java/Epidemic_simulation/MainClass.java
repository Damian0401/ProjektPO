package Epidemic_simulation;

import java.util.ArrayList;
import java.util.List;

public class MainClass{
    public static void main(String[] args) {

        simulation(20, 50, 50, 20, 50, 200, 20, 10, 20);

    }

    public static void simulation(int stageNumber, int mapHeight, int mapWidth, int infectChance, int recoveryChance,
                                  int healthyNumber, int infectedNumber, int medicalNumber, int cureNumber){

        Epidemic epidemic = new Epidemic(mapHeight,mapWidth,infectChance,recoveryChance,healthyNumber,infectedNumber,medicalNumber,cureNumber);

        epidemic.drawMap();
        epidemic.saveStats();

        for(int i = 0; i < stageNumber; i++){
            epidemic.nextStage();
            epidemic.drawMap();
            epidemic.saveStats();
        }
    }
}