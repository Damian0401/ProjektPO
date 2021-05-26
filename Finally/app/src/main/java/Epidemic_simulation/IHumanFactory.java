package Epidemic_simulation;

public interface IHumanFactory {
    AInfectedHuman createInfectedHuman(int xPosition, int yPosition);
    AHealthyHuman createHealthyHuman(int xPosition, int yPosition);
    AMedicalHuman createMedicalHuman(int xPosition, int yPosition);
}
