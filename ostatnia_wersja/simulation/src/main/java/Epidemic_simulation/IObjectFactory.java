package Epidemic_simulation;

public interface IObjectFactory {
    AInfectedObject createInfectedObject(int xPosition, int yPosition);
    AHealthyObject createHealthyObject(int xPosition, int yPosition);
    AMedicalObject createMedicalObject(int xPosition, int yPosition);
    ACureObject createCureObject(int xPosition, int yPosition);
}
