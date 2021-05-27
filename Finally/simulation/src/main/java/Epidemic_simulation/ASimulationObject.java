package Epidemic_simulation;

import java.util.Objects;

public abstract class ASimulationObject {

    public abstract int getXPosition();

    public abstract int getYPosition();

    public abstract void setXPosition(int xPosition);

    public abstract void setYPosition(int yPosition);

    @Override
    public boolean equals(Object o) {
        if (this == o) return false;
        if (!(o instanceof ASimulationObject)) return false;
        ASimulationObject that = (ASimulationObject) o;
        return this.getXPosition() == that.getXPosition() && this.getYPosition() == that.getYPosition();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getXPosition(), this.getYPosition());
    }
}
