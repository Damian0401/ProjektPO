package Epidemic_simulation;

/**
 * Klasa reprezentujaca zdrowy obiekt symulacji
 */
public abstract class AHealthyObject extends ASimulationObject implements IMove {
    /**
     * Metoda sluzaca do sprawdzania czy obiekt zostal zainfekowany
     * @param infectedHuman Zakazony obiekt
     * @return "true" w przypadku gdy obiekt zostal zakazony, w przeciwnym wypadku "false"
     */
    public abstract boolean isInfected(AInfectedObject infectedHuman);
}
