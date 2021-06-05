package Epidemic_simulation;

import Epidemic_simulation.IMove;

/**
 * Klasa reprezentujaca obiekt lekarza
 */
public abstract class AMedicalObject extends ASimulationObject implements IMove {
    /**
     * Metoda sluzaca do sprawdzania czy zakazony obiekt zostal uleczony
     * @param infectedObject Zakazony obietu
     * @return "true" w przypadku pomyslnego leczenia
     */
    public abstract boolean cureSuccessful(AInfectedObject infectedObject);
}
