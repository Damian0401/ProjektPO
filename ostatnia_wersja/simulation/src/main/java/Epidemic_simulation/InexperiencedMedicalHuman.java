package Epidemic_simulation;

public class InexperiencedMedicalHuman extends AMedicalObject {
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
    public InexperiencedMedicalHuman(int xPosition, int yPosition, int moveRange) {
        // Sprawdzenie czy wspolrzedne nie sa ujemne
        if(xPosition < 0 || yPosition < 0){
            throw new IllegalArgumentException("Wspolrzedne nie moga byc ujemne.");
        }
        // Sprawdzenie czy zakres ruchu nie jest ujemny
        if(moveRange < 0){
            throw new IllegalArgumentException("Zakres ruchu nie moze byc ujemny.");
        }
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveRange = moveRange;
    }

    @Override
    public boolean cureSuccessful(AInfectedObject infectedObject) {
        // Sprawdzenie czy współrzędne obiektu InfectedHuman są zbyt odległe, aby został uleczony i na tej postawie wzrócenie true lub false
        if ( Math.abs(infectedObject.getXPosition() - xPosition) < 2 && Math.abs(infectedObject.getXPosition() - yPosition) < 2) {
            return RandomGenerator.getChance() < 76;
        }
        return false;
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
}
