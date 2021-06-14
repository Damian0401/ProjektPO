package Epidemic_simulation;

import javax.swing.*;

/**
 * Interfejs mapy na ktorej jest przeprowadzana symulacja
 */
public abstract class AMap extends JPanel {
    /**
     * Metoda odpowiedzialna za przeprowadzanie kolejnej epoki symulacji
     */
    public abstract void nextStage();

    /**
     * Metoda odpowiedzialna za poruszanie obiektami symulacji
     */
    public abstract void moveObjects();

    /**
     * Metoda odpowiedzialna za pobieranie statystyk symulacji
     * @return Klasa przechowujaca statystyki symulacji
     */
    public abstract Statistics getStats();
}
