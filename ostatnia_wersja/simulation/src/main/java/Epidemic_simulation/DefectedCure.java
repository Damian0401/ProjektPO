package Epidemic_simulation;

public class DefectedCure extends ACureObject {
    private final int recoveryChance;
    private int xPosition;
    private int yPosition;
    public DefectedCure(int xPosition, int yPosition, int recoveryChance) {
        this.recoveryChance = (int)(0.4f*recoveryChance);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        System.out.println("C");
    }
    @Override
    public int getRecoveryChance(){
        return this.recoveryChance;
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
}
