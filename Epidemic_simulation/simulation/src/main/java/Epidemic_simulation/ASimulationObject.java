package Epidemic_simulation;

import java.util.Objects;

/**
 * Bazowa klasa dla wszystkich obiektow wystepujacych w symulacji
 */
public abstract class ASimulationObject {

    /**
     * Metoda wzracajaca wspolrzedna x obiektu
     * @return Wspolrzedna x obiektu
     */
    public abstract int getXPosition();

    /**
     * Metoda wzracajaca wspolrzedna y obiektu
     * @return Wspolrzedna y obiektu
     */
    public abstract int getYPosition();

    /**
     * Metoda sluzaca do ustawiana nowej wspolrzednej x dla obiektu
     * @param xPosition Nowa wspolrzedna x obiektu
     */
    public abstract void setXPosition(int xPosition);

    /**
     * Metoda sluzaca do ustawiania nowej wspolrzednej y obiektu
     * @param yPosition Nowa wspolrzedna y obiektu
     */
    public abstract void setYPosition(int yPosition);

    /**
     * Metoda sluzaca to porownywania obiektow
     * @param o Obiektu z ktorym ma zostac porownany obiekt klasy ASimulationObject
     * @return "true" tylko w przypadku porownania z innym obiektem klasy SimulationObject o takich samych wspolrzednych
     */
    @Override
    public boolean equals(Object o) {
        // Zwrocenie "false" gdy obiektu jest referencja to tego samego obiektu
        if (this == o) return false;
        // Zwrocenie "false" gdy obiekt nie jest instancja klasy ASimulationObject
        if (!(o instanceof ASimulationObject)) return false;
        // Rzutowanie obiektu na klase ASimulationObject
        ASimulationObject that = (ASimulationObject) o;
        // Zwrocenie wyniku porownania obiektow
        return this.getXPosition() == that.getXPosition() && this.getYPosition() == that.getYPosition();
    }

    /**
     * Generowania hashCode
     * @return HashCode zalezny od wspolrzednych obiektu
     */
    @Override
    public int hashCode() {
        // Zwrocenie hashcode zaleznego odwspolrzednych
        return Objects.hash(this.getXPosition(), this.getYPosition());
    }
}
