package Epidemic_simulation;

/**
 * Interfejs fabryki obiektow
 */
public interface IObjectFactory {
    /**
     * Metoda odpowiedzialna za tworzenie zainfekowanych obiektow
     * @param xPosition Wspolrzedna x obiektow
     * @param yPosition Wspolrzedna y obiektow
     * @return Nowy obiekt klasy AInfectedObject
     */
    AInfectedObject createInfectedObject(int xPosition, int yPosition);

    /**
     * Metoda odpwiedzialna za tworzenie zdrowych obiektow
     * @param xPosition Wspolrzedna x obiektow
     * @param yPosition Wspolrzedna y obiektow
     * @return Nowy obiekt klasy AHealthyObject
     */
    AHealthyObject createHealthyObject(int xPosition, int yPosition);

    /**
     * Metoda odpwiedzialna za tworzenie leczacych obiektow
     * @param xPosition Wspolrzedna x obiektow
     * @param yPosition Wspolrzedna y obiektow
     * @return Nowy obiekt klasy AMedicalObject
     */
    AMedicalObject createMedicalObject(int xPosition, int yPosition);

    /**
     * Metoda odpwiedzialna za tworzenie obiektow lekarstw
     * @param xPosition Wspolrzedna x obiektow
     * @param yPosition Wspolrzedna y obiektow
     * @return Nowy obiekt klasy ACureObject
     */
    ACureObject createCureObject(int xPosition, int yPosition);
}
