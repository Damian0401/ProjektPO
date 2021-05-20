package Epidemic_simulation;

/**
 * Klasa reprezentująca ludzi zdrowych
 */
public class HealthyHuman extends SimulationObject{
    /**
     * Konstruktor przyjmujący współrzędne obiektu
     * @param x Współrzędna x obiektu
     * @param y Współrzędna y obiektu
     */
    public HealthyHuman(int x, int y) {
        // Wywołanie konstruktora SimulationObject
        super(x, y);
    }
    /**
     * Metoda sprawdzająca czy obiektu uległ zakażeniu od obiektu typu InfectedHuman
     * @param infectedHuman Obiekt typu InfectedHuman
     * @return true w przypadku gdy HealthyHuman uległ zakażeniu, w przeciwnym wypadku false
     */
    public boolean isInfected(InfectedHuman infectedHuman) {
        // Sprawdzenie czy współrzędne obiektu InfectedHuman są zbyt odległe, aby doszło do zakażenia
        if(Math.abs(infectedHuman.getXPosition() - this.getXPosition()) > 2 || Math.abs(infectedHuman.getYPosition() - this.getYPosition()) > 2 ) return false;
        // Porównanie szansu na zakażenie obiektu InfectedHuman z losowo wylosowaną liczbą i na tej podstawie zwrócenie true lub false
        return infectedHuman.getInfectChance() >= RandomGenerator.getChance();
    }
}
