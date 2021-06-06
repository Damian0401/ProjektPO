package Epidemic_simulation;

public class HealthyHuman extends AHealthyObject implements IMove {
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
     * Konstruktor z parametrami
     * @param xPosition Wspolrzedna x obiektu
     * @param yPosition Wspolrzednna y obiektu
     * @param moveRange Zakres ruchu obiektu
     */
    public HealthyHuman(int xPosition, int yPosition, int moveRange) {

        if(xPosition < 0 || yPosition < 0){
            throw new IllegalArgumentException("Wspolrzedne nie moga byc ujemne.");
        }
        if(moveRange < 0){
            throw new IllegalArgumentException("Zakres ruchu nie moze byc ujemny.");
        }
        // Przypisanie do zmiennej xPosition wspolrzednej x obiektu
        this.xPosition = xPosition;
        // Przypisanie do zmiennej yPosition wspolrzednej y obiektu
        this.yPosition = yPosition;
        // Przypisanie do zmiennej moveRange zakresu ruchu obiektu
        this.moveRange = moveRange;
    }

    /**
     * Metoda sprawdzajaca czy obiekt zostal zakazony
     * @param infectedHuman Zakazony obiekt
     * @return "true" w przypadku gdy obiekt ulegl zakazeniu, w przeciwnym wypadku "false"
     */
    @Override
    public boolean isInfected(AInfectedObject infectedHuman) {
        // Sprawdzenie czy współrzędne obiektu InfectedHuman są zbyt odległe, aby doszło do zakażenia
        if(Math.abs(infectedHuman.getXPosition() - xPosition) > 2 || Math.abs(infectedHuman.getYPosition() - yPosition) > 2 ) return false;
        // Porównanie szansu na zakażenie obiektu InfectedHuman z losowo wylosowaną liczbą i na tej podstawie zwrócenie true lub false
        return infectedHuman.getInfectChance() >= RandomGenerator.getChance();
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

    /**
     * Metoda zwracajaca zakres ruchu obiektu
     * @return
     */
    @Override
    public int getMoveRange() {
        return this.moveRange;
    }

}
