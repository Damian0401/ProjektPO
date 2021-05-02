package Epidemic_simulation;

import java.util.ArrayList;
import java.util.List;

public class Epidemic {
    public Epidemic(int mapHeight, int mapWidth, int infectChance, int recoveryChance, int healthyNumber,
                    int infectedNumber, int medicalNumber, int cureNumber){
        for(int i = 0; i < infectedNumber; i++){
            infectedHumanList.add(new InfectedHuman(RandomGenerator.getPosition(mapWidth),RandomGenerator.getPosition(mapHeight),mapHeight,mapWidth,infectChance));
        }
    }

    private final List<Cure> cureList = new ArrayList<>();

    private final List<HealthyHuman> healthyHumanList = new ArrayList<>();

    private final List<InfectedHuman> infectedHumanList = new ArrayList<>();

    private final List<MedicalHuman> medicalHumanList = new ArrayList<>();

    private int stageNumber = 0;

    private final int mapHeight = 0;

    private final int mapWidth = 0;

    public void nextStage(){
        stageNumber++;
    }

    public void saveStats(){}

    public void drawMap(){}
}
