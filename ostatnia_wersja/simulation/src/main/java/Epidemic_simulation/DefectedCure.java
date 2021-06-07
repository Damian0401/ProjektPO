package Epidemic_simulation;

/**
 * Klasa przedstawiająca lekarstwo które uległo uszkodzeniu
 */
public class DefectedCure extends ACureObject {
    /**
     * Szansa na uleczenie zakażonego
     */
    private final int recoveryChance;
    /**
     * Wspolrzedna x obiektu
     */
    private int xPosition;
    /**
     * Wspolrzedna y obiektu
     */
    private int yPosition;
    /**
     * Konstruktor z parametrami
     * @param xPosition Wspolrzedna x obiektu
     * @param yPosition Wspolrzedna y obiektu
     * @param recoveryChance Szansa na uleczenie zakazonego
     */
    public DefectedCure(int xPosition, int yPosition, int recoveryChance) {
        // Sprawdzenie czy wspolrzedne obiektow nie sa ujemne
        if(xPosition < 0 || yPosition < 0){
            throw new IllegalArgumentException("Wspolrzedne nie moga byc ujemne.");
        }
        // Sprawdzenie czy szansa na zakazenie jest liczba z zakresu 0 - 100
        if(recoveryChance < 0 || recoveryChance > 100){
            throw new IllegalArgumentException("Szansa na uleczenie musi byc liczba z zakresu 0 - 100.");
        }
        // Przypisanie do zmiennej recoveryChange 40% wprowadzanej szansu na uleczenie
        this.recoveryChance = (int)(0.4f*recoveryChance);
        // Przypisanie wspolrzednej x do zmiennej xPosition
        this.xPosition = xPosition;
        // Przypisanie wspolrzednej y do zmiennej xPosition
        this.yPosition = yPosition;
    }
    /**
     * Metoda zwracajaca szanse na uleczenie
     * @return Szansa na uleczenie
     */
    @Override
    public int getRecoveryChance(){
        return this.recoveryChance;
    }
    /**
     * Metoda zwracajaca wspolrzedna x obiektu
     * @return Wspolrzedna x
     */
    @Override
    public int getXPosition() {
        return this.xPosition;
    }
    /**
     * Metoda zwracajaca wspolrzedna y obiektu
     * @return Wspolrzedna y
     */
    @Override
    public int getYPosition() {
        return this.yPosition;
    }
    /**
     * Metoda ustawiajaca nowa wspolrzedna x dla obiektu
     * @param xPosition Nowa wspolrzedna x
     */
    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }
    /**
     * Metoda ustawiajaca nowa wspolrzedna y dla obiektu
     * @param yPosition Nowa wspolrzedna y
     */
    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
