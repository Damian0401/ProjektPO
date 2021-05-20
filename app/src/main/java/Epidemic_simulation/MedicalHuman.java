package Epidemic_simulation;

/**
 * Klasa reprezentująca lekarzy
 */
public class MedicalHuman extends SimulationObject{
    /**
     * Konstruktor przyjmujący współrzędne obiektu
     * @param x Współrzędna x obiektu
     * @param y Współrzędna y obiektu
     */
    MedicalHuman(int x, int y) {
        // Wywołanie konstruktora SimulationObject
        super(x, y);
    }
    /**
     * Metoda sprawdzająca czy obiektu został uleczony przez obiektu typu MedicalHuman
     * @param infectedHuman Obiekt typu InfectedHuman
     * @return true w przypadku gdy InfectedHuman został uleczony, w przeciwnym wypadku false
     */
    public boolean cure(InfectedHuman infectedHuman){
        // Sprawdzenie czy współrzędne obiektu InfectedHuman są zbyt odległe, aby został uleczony i na tej postawie wzrócenie true lub false
        return Math.abs(infectedHuman.getXPosition() - this.getXPosition()) < 2 && Math.abs(infectedHuman.getYPosition() - this.getYPosition()) < 2 ;
    }
}
