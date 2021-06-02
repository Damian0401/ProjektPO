package Epidemic_simulation;

public class CuredHealthyHuman extends AHealthyObject implements IMove {

    private int xPosition;
    private int yPosition;
    private final int moveRange;

    public CuredHealthyHuman(int xPosition, int yPosition, int moveRange) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveRange = moveRange+1;
        System.out.println("H");
    }

    @Override
    public boolean isInfected(AInfectedObject infectedHuman) {
        // Sprawdzenie czy współrzędne obiektu InfectedHuman są zbyt odległe, aby doszło do zakażenia
        if(Math.abs(infectedHuman.getXPosition() - xPosition) > 2 || Math.abs(infectedHuman.getYPosition() - yPosition) > 2 ) return false;
        // Porównanie szansu na zakażenie obiektu InfectedHuman z losowo wylosowaną liczbą i na tej podstawie zwrócenie true lub false
        return infectedHuman.getInfectChance()/2 >= RandomGenerator.getChance();
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
