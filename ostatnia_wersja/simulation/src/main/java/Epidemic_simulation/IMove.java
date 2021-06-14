package Epidemic_simulation;

/**
 * Interfejs umozliwiajacy poruszanie obiektow
 */
public interface IMove {

    /**
     * Metoda zwracajaca zakres ruchu obiektu
     * @return Zakres ruchu obiektu
     */
    int getMoveRange();
}
