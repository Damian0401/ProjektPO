package Epidemic_simulation;

/**
 * Klasa reprezentujaca obiekt lekarstwa
 */
public abstract class ACureObject extends ASimulationObject {
    /**
     * Metoda zwracajaca szanse na uleczenie jaka posiada obiekt lekarstwa
     * @return Szansa na uleczenie przechowywana przez obiekt
     */
    public abstract int getRecoveryChance();
}
