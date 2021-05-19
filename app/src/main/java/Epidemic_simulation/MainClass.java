package Epidemic_simulation;

import java.util.ArrayList;
import java.util.List;

public class MainClass{
    public static void main(String[] args) {

        List<MedicalHuman> medicalHumanList = new ArrayList<>();
        medicalHumanList.add(new MedicalHuman(1,1));
        medicalHumanList.add(new MedicalHuman(2,2));
        medicalHumanList.add(new MedicalHuman(3,3));

        List<InfectedHuman> infectedHumanList = new ArrayList<>();
        infectedHumanList.add(new InfectedHuman(4,4, 50));
        infectedHumanList.add(new InfectedHuman(5,5, 50));
        infectedHumanList.add(new InfectedHuman(6,6, 50));

        List<HealthyHuman> healthyHumanList = new ArrayList<>();
        healthyHumanList.add(new HealthyHuman(7,7));
        healthyHumanList.add(new HealthyHuman(8,8));
        healthyHumanList.add(new HealthyHuman(9,9));

        List<Cure> cureList = new ArrayList<>();
        cureList.add(new Cure(10,10, 75));
        cureList.add(new Cure(11,11, 75));
        cureList.add(new Cure(12,12, 75));

        System.out.println("Medical:");
        System.out.println(medicalHumanList);
        System.out.println("Infected:");
        System.out.println(infectedHumanList);
        System.out.println("Healthy:");
        System.out.println(healthyHumanList);
        System.out.println("Cure:");
        System.out.println(cureList);

        System.out.println("\nMove\n");
        SimulationObjectMover.MoveAllSimulationObjects(medicalHumanList, infectedHumanList, healthyHumanList, cureList, 50, 50);

        System.out.println("Medical:");
        System.out.println(medicalHumanList);
        System.out.println("Infected:");
        System.out.println(infectedHumanList);
        System.out.println("Healthy:");
        System.out.println(healthyHumanList);
        System.out.println("Cure:");
        System.out.println(cureList);


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