package Epidemic_simulation;

public class HealthyHuman extends AHealthyHuman implements IMove{

    private int xPosition;
    private int yPosition;
    private final int moveRange;

    public HealthyHuman(int xPosition, int yPosition, int moveRange) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveRange = moveRange;
    }

    @Override
    public boolean isInfected(AInfectedHuman infectedHuman) {
        // Sprawdzenie czy współrzędne obiektu InfectedHuman są zbyt odległe, aby doszło do zakażenia
        if(Math.abs(infectedHuman.getXPosition() - this.getXPosition()) > 2 || Math.abs(infectedHuman.getYPosition() - this.getYPosition()) > 2 ) return false;
        // Porównanie szansu na zakażenie obiektu InfectedHuman z losowo wylosowaną liczbą i na tej podstawie zwrócenie true lub false
        return infectedHuman.getInfectChance() >= RandomGenerator.getChance();
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
}
