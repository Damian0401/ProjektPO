package Epidemic_simulation;

/**
 * Klasa reprezentująca lekarstwa
 */
public class Cure extends SimulationObject{
    /**
     * Zmienna przechowująca szansę na zakażenie
     */
    private final int recoveryChance;
    /**
     * Konstruktor przyjmujący współrzędne obiektu oraz szansę na uleczenie
     * @param x Współrzędna x obiektu
     * @param y Współrzędna y obiektu
     * @param recoveryChance Szansa na uleczenie
     */
    public Cure(int x, int y, int recoveryChance){
        // Wywołanie konstruktora SimulationObject
        super(x, y);
        // Przypisanie szansy na uleczenie przekazanej w konstruktorze do zmiennej recoveryChance
        this.recoveryChance = recoveryChance;
    }
    /**
     * Metoda zwracająca szansę na uleczenie
     * @return Szansa na uleczenie
     */
    public int getRecoveryChance(){ return this.recoveryChance; }
}
