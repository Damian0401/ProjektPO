package Epidemic_simulation;

public class MainClass{
    public static void main(String[] args) {

        simulation(5,5,5,5,5,5,5,5,5);

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