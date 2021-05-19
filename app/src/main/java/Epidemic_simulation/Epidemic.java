package Epidemic_simulation;

import java.util.ArrayList;
import java.util.List;

public class Epidemic {
    public Epidemic(int mapHeight, int mapWidth, int infectChance, int recoveryChance, int healthyNumber,
                    int infectedNumber, int medicalNumber, int cureNumber){

        for (int i = 0; i < medicalNumber; i++){
            medicalHumanList.add(new MedicalHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight)));
        }

        for (int i = 0; i < healthyNumber; i++){
            healthyHumanList.add(new HealthyHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight)));
        }

        for (int i = 0; i < infectedNumber; i++){
            infectedHumanList.add(new InfectedHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight), infectChance));
        }

        for (int i = 0; i < cureNumber; i++){
            cureList.add(new Cure(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight), recoveryChance));
        }

        this.mapHeight = mapHeight;

        this.mapWidth = mapWidth;

    }

    private final List<Cure> cureList = new ArrayList<>();

    private final List<HealthyHuman> healthyHumanList = new ArrayList<>();

    private final List<InfectedHuman> infectedHumanList = new ArrayList<>();

    private final List<MedicalHuman> medicalHumanList = new ArrayList<>();

    private int recovered = 0;

    private int infected = 0;

    private int stageNumber = 0;

    private final int mapHeight;

    private final int mapWidth;

    public void nextStage(){

        for (MedicalHuman medicalHuman : medicalHumanList) {
            for (int i = 0; i < infectedHumanList.size(); i++) {
                if (medicalHuman.cure(infectedHumanList.get(i))) {
                    healthyHumanList.add(new HealthyHuman(infectedHumanList.get(i).getXPosition(), infectedHumanList.get(i).getYPosition()));
                    infectedHumanList.remove(i);
                    this.recovered++;
                    i--;
                }
            }
        }

        for (int i = 0; i < infectedHumanList.size(); i++){
            InfectedHuman infectedHuman = infectedHumanList.get(i);
            for (int j = 0; j < cureList.size(); j++){
                if (infectedHuman.isCured(cureList.get(j))){
                    healthyHumanList.add(new HealthyHuman(infectedHuman.getXPosition(), infectedHuman.getYPosition()));
                    infectedHumanList.remove(infectedHuman);
                    cureList.remove(j);
                    this.recovered++;
                    i--;
                    break;
                }
            }
        }

        for (int i = 0; i < healthyHumanList.size(); i++){
            HealthyHuman healthyHuman = healthyHumanList.get(i);
            for (InfectedHuman infectedHuman : infectedHumanList){
                if (healthyHuman.isInfected(infectedHuman)){
                    infectedHumanList.add(new InfectedHuman(healthyHuman.getXPosition(), healthyHuman.getYPosition(), infectedHuman.getInfectChance()));
                    healthyHumanList.remove(i);
                    this.infected++;
                    i--;
                    break;
                }
            }
        }

        SimulationObjectMover.moveAllSimulationObjects(medicalHumanList, infectedHumanList, healthyHumanList, cureList, mapHeight, mapWidth);

        stageNumber++;
    }

    public void saveStats(){
        System.out.println(infectedHumanList.size());
        System.out.println(cureList.size());
        System.out.println(medicalHumanList.size());
        System.out.println(healthyHumanList.size());
    }

    public void drawMap(){}
}
