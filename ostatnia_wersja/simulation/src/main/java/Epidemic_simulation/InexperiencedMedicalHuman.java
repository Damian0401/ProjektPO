package Epidemic_simulation;

public class InexperiencedMedicalHuman extends AMedicalObject {


    private int xPosition;
    private int yPosition;
    private final int moveRange;

    public InexperiencedMedicalHuman(int xPosition, int yPosition, int moveRange) {
        if(xPosition < 0 || yPosition < 0){
            throw new IllegalArgumentException("Wspolrzedne nie moga byc ujemne.");
        }
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
            return RandomGenerator.getChance() < 51;
        }
        return false;
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
