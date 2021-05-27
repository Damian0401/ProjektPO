package Epidemic_simulation;

public interface IObjectFactory {
    AInfectedObject createInfectedHuman(int xPosition, int yPosition);
    AHealthyObject createHealthyHuman(int xPosition, int yPosition);
    AMedicalObject createMedicalHuman(int xPosition, int yPosition);
    ACureObject createCure(int xPosition, int yPosition);
}
