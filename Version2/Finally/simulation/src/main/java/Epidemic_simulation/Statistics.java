package Epidemic_simulation;

public class Statistics {
    private final int newRecoveredNumber;
    private final int newInfectedNumber;
    private final int stageNumber;
    private final int infectedObjectNumber;
    private final int healthyObjectNumber;
    private final int cureObjectNumber;
    private final int medicalObjectNumber;
    public Statistics(int newRecoveredNumber, int newInfectedNumber, int stageNumber, int infectedObjectNumber, int healthyObjectNumber, int cureObjectNumber, int medicalObjectNumber){
        this.newInfectedNumber = newInfectedNumber;
        this.newRecoveredNumber = newRecoveredNumber;
        this.stageNumber = stageNumber;
        this.infectedObjectNumber = infectedObjectNumber;
        this.healthyObjectNumber = healthyObjectNumber;
        this.cureObjectNumber = cureObjectNumber;
        this.medicalObjectNumber = medicalObjectNumber;
    }


    @Override
    public String toString() {
        return stageNumber + ";" + healthyObjectNumber + ";" + infectedObjectNumber + ";" + medicalObjectNumber + ";" + cureObjectNumber + ";" + newRecoveredNumber + ";" + newInfectedNumber + "\n";
    }
}
