package Epidemic_simulation;

/**
 * Klasa reprezentujaca zdrowego czlowieka ktory posiada zwiekszona odpornosc na zakazenie
 */
public class ImmuneHealthyHuman extends AHealthyObject implements IMove {
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
     * @param yPosition Wspolrzedna y obiektu
     * @param moveRange Zakres ruchu obiektu
     */
    public ImmuneHealthyHuman(int xPosition, int yPosition, int moveRange) {

        if(xPosition < 0 || yPosition < 0){
            throw new IllegalArgumentException("Wspolrzedne nie moga byc ujemne.");
        }
        if(moveRange < 0){
            throw new IllegalArgumentException("Zakres ruchu nie moze byc ujemny.");
        }
        // Przypisanie do zmiennej xPosition wspolrzednej przekazanej w konstruktorze
        this.xPosition = xPosition;
        // Przypisanie do zmiennej yPosition wspolrzednej przekazanej w konstruktorze
        this.yPosition = yPosition;
        // Przypisanie do zmiennej movaRange zakresu ruchu przekazanego w konstruktorze
        this.moveRange = moveRange;
    }
    /**
     * Metoda sluzaca do sprawdzenia czy obiektu zostal zakazony
     * @param infectedHuman Obiektu klasy AInfectedHuman
     * @return "true" w przypadku zakazenia, w innym wypadku "false"
     */
    @Override
    public boolean isInfected(AInfectedObject infectedHuman) {
        // Sprawdzenie czy współrzędne obiektu InfectedHuman są zbyt odległe, aby doszło do zakażenia
        if(Math.abs(infectedHuman.getXPosition() - xPosition) > 2 || Math.abs(infectedHuman.getYPosition() - yPosition) > 2 ) return false;
        // Porównanie szansu na zakażenie obiektu InfectedHuman z losowo wylosowaną liczbą i na tej podstawie zwrócenie true lub false
        return infectedHuman.getInfectChance()/2 >= RandomGenerator.getChance();
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
     * @return Zakres ruchu obiektu
     */
    @Override
    public int getMoveRange() {
        return this.moveRange;
    }

}
