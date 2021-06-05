package Epidemic_simulation;

import Epidemic_simulation.IMove;

/**
 * Klasa reprezentujaca zakazony obiekt
 */
public abstract class AInfectedObject extends ASimulationObject implements IMove {
    /**
     * Metoda sluzaca do pobierania szansy na zakazenie
     * @return Szansa na zakazenie
     */
    public abstract int getInfectChance();

    /**
     * Metoda sluzaca do sprawdzania czy obiekt zostal uleczony
     * @param cure Obiekt lekarstwa
     * @return "true" w przypadku gdy obiekt zostal uleczony, w innym wypadku "false"
     */
    public abstract boolean isCured(ACureObject cure);
}
