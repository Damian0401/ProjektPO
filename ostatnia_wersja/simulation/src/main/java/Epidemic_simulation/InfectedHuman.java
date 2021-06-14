package Epidemic_simulation;

/**
 * Klasa reprezentujaca zakazonego czlowieka
 */
public class InfectedHuman extends AInfectedObject {
    /**
     * Wspolrzedna x obiektu
     */
    private int xPosition;
    /**
     * Wspolrzedna y obiektu
     */
    private int yPosition;
    /**
     * Zakres ruchu obiektu
     */
    private final int moveRange;
    /**
     * Szansa na zakazenie obiektu
     */
    private final int infectChance;

    /**
     * Konstruktor z parametrami
     * @param xPosition Wspolrzedna x obiektu
     * @param yPosition Wspolrzedna y obiektu
     * @param moveRange Zakres ruchu obiektu
     * @param infectChance Szansa na zakazenie
     */
    public InfectedHuman(int xPosition, int yPosition, int moveRange, int infectChance) {
        // Sprawdzenie czy wspolrzedne obiektu nie sa ujemne
        if(xPosition < 0 || yPosition < 0){
            throw new IllegalArgumentException("Wspolrzedne nie moga byc ujemne.");
        }
        // Sprawdzenie czy szansa na zakazenie jest liczbaa z przedzialu 0 - 100
        if(infectChance < 0 || infectChance > 100){
            throw new IllegalArgumentException("Szansa na zakazenie musi byc liczba z przedzialu 0 - 100.");
        }
        // Sprawdzenie czy zakres ruchu nie jest ujemny
        if(moveRange < 0){
            throw new IllegalArgumentException("Zakres ruchu nie moze byc ujemny.");
        }
        this.infectChance = infectChance;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveRange = moveRange;
    }

    /**
     * Metoda sprawdzajaca czy obiekt zostal uleczony pomyslnie
     * @param cure Obiekt lekarstwa
     * @return "true" w przypadku powodzenia, "false" w przypadku niepowodzenia
     */
    @Override
    public boolean isCured(ACureObject cure){
        // Sprawdzenie czy współrzędne obiektu Cure są zbyt odległe, aby doszło do uleszenia
        if(Math.abs(cure.getXPosition() - xPosition) > 2 || Math.abs(cure.getYPosition() - yPosition) > 2 ) return false;
        // Porównanie szansy na uleczenie obiektu Cure z losowo wylosowaną liczbą i na tej podstawie zwrócenie true lub false
        return cure.getRecoveryChance() >= RandomGenerator.getChance();
    }

    /**
     * Metoda wzracajaca wspolrzedna x obiektu
     * @return Wspolrzedna x obiektu
     */
    @Override
    public int getXPosition() {
        return this.xPosition;
    }

    /**
     * Metoda wzracajaca wspolrzedna y obiektu
     * @return Wspolrzedna y obiektu
     */
    @Override
    public int getYPosition() {
        return this.yPosition;
    }

    /**
     * Metoda sluzaca do ustawiana nowej wspolrzednej x dla obiektu
     * @param xPosition Nowa wspolrzedna x obiektu
     */
    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Metoda sluzaca do ustawiania nowej wspolrzednej y obiektu
     * @param yPosition Nowa wspolrzedna y obiektu
     */
    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Metoda zwracajaca zakres ruchu obiektu
     * @return Zakres ruchu obiektu
     */
    @Override
    public int getMoveRange() {
        return this.moveRange;
    }

    /**
     * Metoda zwracajaca szanse na zakazenie przechowywana przez obiektu
     * @return Szansa na zakazenie
     */
    @Override
    public int getInfectChance() {
        return infectChance;
    }
}
