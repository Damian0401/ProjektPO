package Epidemic_simulation;

/**
 * Klasa reprezentująca ludzi zakażonych
 */
public class InfectedHuman extends SimulationObject{
    /**
     * Zmienna przechowująca szansę na zakażenie
     */
    private final int infectChance;
    /**
     * Konstruktor przyjmujący współrzędne obiektu oraz szansę na zakażenie
     * @param x Współrzędna x obiektu
     * @param y Współrzędna y obiektu
     * @param infectChance Szansa na zakażenie
     */
    public InfectedHuman(int x, int y, int infectChance) {
        // Wywołanie konstruktora SimulationObject
        super(x, y);
        // Przypisanie szansy na zakażenie przekazanej w konstruktorze do zmiennej infectedChance
        this.infectChance = infectChance;
    }
    /**
     * Metoda zwracająca szansę na zakażenie
     * @return Szansa na zakażenie
     */
    public int getInfectChance() {
        return infectChance;
    }
    /**
     * Metoda sprawdzająca czy obiektu został uleszony przez obiektu typu Cure
     * @param cure Obiekt typu Cure
     * @return true w przypadku gdy InfectedHuman został uleszony, w przeciwnym wypadku false
     */
    public boolean isCured(Cure cure){
        // Sprawdzenie czy współrzędne obiektu Cure są zbyt odległe, aby doszło do uleszenia
        if(Math.abs(cure.getXPosition() - this.getXPosition()) > 2 || Math.abs(cure.getYPosition() - this.getYPosition()) > 2 ) return false;
        // Porównanie szansu na uleczenie obiektu Cure z losowo wylosowaną liczbą i na tej podstawie zwrócenie true lub false
        return cure.getRecoveryChance() >= RandomGenerator.getChance();
    }
}
