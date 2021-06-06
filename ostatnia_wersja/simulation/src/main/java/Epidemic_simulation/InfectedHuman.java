package Epidemic_simulation;

public class InfectedHuman extends AInfectedObject {


    private int xPosition;
    private int yPosition;
    private final int moveRange;
    private final int infectChance;

    public InfectedHuman(int xPosition, int yPosition, int moveRange, int infectChance) {

        if(xPosition < 0 || yPosition < 0){
            throw new IllegalArgumentException("Wspolrzedne nie moga byc ujemne.");
        }
        if(infectChance < 0){
            throw new IllegalArgumentException("Szansa na zarazenie nie moze byc ujemna.");
        }
        if(moveRange < 0){
            throw new IllegalArgumentException("Zakres ruchu nie moze byc ujemny.");
        }
        this.infectChance = infectChance;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveRange = moveRange;
    }


    @Override
    public boolean isCured(ACureObject cure){
        // Sprawdzenie czy współrzędne obiektu Cure są zbyt odległe, aby doszło do uleszenia
        if(Math.abs(cure.getXPosition() - xPosition) > 2 || Math.abs(cure.getYPosition() - yPosition) > 2 ) return false;
        // Porównanie szansy na uleczenie obiektu Cure z losowo wylosowaną liczbą i na tej podstawie zwrócenie true lub false
        return cure.getRecoveryChance() >= RandomGenerator.getChance();
    }


    @Override
    public int getXPosition() {
        return this.xPosition;
    }

    @Override
    public int getYPosition() {
        return this.yPosition;
    }

    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public int getMoveRange() {
        return this.moveRange;
    }

    @Override
    public int getInfectChance() {
        return infectChance;
    }
}
