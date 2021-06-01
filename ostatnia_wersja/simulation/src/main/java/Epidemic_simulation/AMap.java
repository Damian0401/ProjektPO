package Epidemic_simulation;

import javax.swing.*;

public abstract class AMap extends JPanel {

    public abstract void nextStage();

    public abstract void moveObjects();

    public abstract Statistics getStats();
}
