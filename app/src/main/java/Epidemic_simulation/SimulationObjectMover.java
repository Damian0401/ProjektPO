package Epidemic_simulation;

import java.util.List;

public class SimulationObjectMover {
    private SimulationObjectMover() {}
    private static final int moveRange = 3;
    public static void MoveAllSimulationObjects(List<MedicalHuman> medicalHumanList, List<InfectedHuman> infectedHumanList, List<HealthyHuman> healthyHumanList, List<Cure> cureList, int mapHeight, int mapWidth){
        int oldXPosition;
        int oldYPosition;
        boolean busyPosition = false;
        for(MedicalHuman medicalHuman : medicalHumanList){
            oldXPosition = medicalHuman.getXPosition();
            oldYPosition = medicalHuman.getYPosition();
            do {
                busyPosition = false;
                medicalHuman.setXPosition(oldXPosition + RandomGenerator.getMove(moveRange));
                medicalHuman.setYPosition(oldYPosition + RandomGenerator.getMove(moveRange));
                if (medicalHuman.getXPosition() < 0 || medicalHuman.getXPosition() >= mapWidth) busyPosition = true;
                if (medicalHuman.getYPosition() < 0 || medicalHuman.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalHumanList.contains(medicalHuman)) busyPosition = true;
                if (infectedHumanList.contains(medicalHuman)) busyPosition = true;
                if (healthyHumanList.contains(medicalHuman)) busyPosition = true;
                if (cureList.contains(medicalHuman)) busyPosition = true;
            }while (busyPosition);
        }
        for(InfectedHuman infectedHuman : infectedHumanList){
            oldXPosition = infectedHuman.getXPosition();
            oldYPosition = infectedHuman.getYPosition();
            do {
                busyPosition = false;
                infectedHuman.setXPosition(oldXPosition + RandomGenerator.getMove(moveRange));
                infectedHuman.setYPosition(oldYPosition + RandomGenerator.getMove(moveRange));
                if (infectedHuman.getXPosition() < 0 || infectedHuman.getXPosition() >= mapWidth) busyPosition = true;
                if (infectedHuman.getYPosition() < 0 || infectedHuman.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalHumanList.contains(infectedHuman)) busyPosition = true;
                if (infectedHumanList.contains(infectedHuman)) busyPosition = true;
                if (healthyHumanList.contains(infectedHuman)) busyPosition = true;
                if (cureList.contains(infectedHuman)) busyPosition = true;
            }while (busyPosition);
        }
        for(HealthyHuman healthyHuman : healthyHumanList){
            oldXPosition = healthyHuman.getXPosition();
            oldYPosition = healthyHuman.getYPosition();
            do {
                busyPosition = false;
                healthyHuman.setXPosition(oldXPosition + RandomGenerator.getMove(moveRange));
                healthyHuman.setYPosition(oldYPosition + RandomGenerator.getMove(moveRange));
                if (healthyHuman.getXPosition() < 0 || healthyHuman.getXPosition() >= mapWidth) busyPosition = true;
                if (healthyHuman.getYPosition() < 0 || healthyHuman.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalHumanList.contains(healthyHuman)) busyPosition = true;
                if (infectedHumanList.contains(healthyHuman)) busyPosition = true;
                if (healthyHumanList.contains(healthyHuman)) busyPosition = true;
                if (cureList.contains(healthyHuman)) busyPosition = true;
            }while (busyPosition);
        }
    }
}
