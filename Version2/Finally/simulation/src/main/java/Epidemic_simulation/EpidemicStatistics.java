package Epidemic_simulation;

public class EpidemicStatistics {
    private final int newRecoveredNumber;
    private final int newInfectedNumber;
    private final int stageNumber;
    private final int infectedObjectNumber;
    private final int healthyObjectNumber;
    private final int cureObjectNumber;
    private final int medicalObjectNumber;
    public EpidemicStatistics(int newRecoveredNumber, int newInfectedNumber, int stageNumber, int infectedObjectNumber, int healthyObjectNumber, int cureObjectNumber, int medicalObjectNumber){
        this.newInfectedNumber = newInfectedNumber;
        this.newRecoveredNumber = newRecoveredNumber;
        this.stageNumber = stageNumber;
        this.infectedObjectNumber = infectedObjectNumber;
        this.healthyObjectNumber = healthyObjectNumber;
        this.cureObjectNumber = cureObjectNumber;
        this.medicalObjectNumber = medicalObjectNumber;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public int getNewRecoveredNumber() {
        return newRecoveredNumber;
    }

    public int getNewInfectedNumber() {
        return newInfectedNumber;
    }

    public int getInfectedObjectNumber() {
        return infectedObjectNumber;
    }

    public int getHealthyObjectNumber() {
        return healthyObjectNumber;
    }

    public int getCureObjectNumber() {
        return cureObjectNumber;
    }

    public int getMedicalObjectNumber() {
        return medicalObjectNumber;
    }

    @Override
    public String toString() {
        return "EpidemicStatistics{" +
                "newRecoveredNumber=" + newRecoveredNumber +
                ", newInfectedNumber=" + newInfectedNumber +
                ", stageNumber=" + stageNumber +
                ", infectedObjectNumber=" + infectedObjectNumber +
                ", healthyObjectNumber=" + healthyObjectNumber +
                ", cureObjectNumber=" + cureObjectNumber +
                ", medicalObjectNumber=" + medicalObjectNumber +
                '}';
    }
}
