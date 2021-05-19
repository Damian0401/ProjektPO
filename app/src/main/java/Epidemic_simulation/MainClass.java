package Epidemic_simulation;

import java.util.ArrayList;
import java.util.List;

public class MainClass{
    public static void main(String[] args) {

        Epidemic epidemic = new Epidemic(50, 50, 50, 50, 200, 10, 15, 20);

        epidemic.saveStats();
        epidemic.nextStage();
        epidemic.nextStage();
        epidemic.nextStage();
        epidemic.saveStats();


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