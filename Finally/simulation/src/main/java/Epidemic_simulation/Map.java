package Epidemic_simulation;

import Epidemic_simulation.ACureObject;
import Epidemic_simulation.AHealthyObject;
import Epidemic_simulation.AInfectedObject;
import Epidemic_simulation.AMedicalObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Map extends JPanel {

    private final int scale;
    /**
     * Lista przechowująca obiekty typu Cure
     */
    private final List<ACureObject> cureList;
    /**
     * Lista przechowująca obiekty typu HealthyHuman
     */
    private final List<AHealthyObject> healthyHumanList;
    /**
     * Lista przechowująca obiekty typu InfecteedHuman
     */
    private final List<AInfectedObject> infectedHumanList;
    /**
     * Lista przechowująca obiekty typu MedicalHuman
     */
    private final List<AMedicalObject> medicalHumanList;
    /**
     * Konstruktor okna mapy
     * @param infectedHumanList list obiektów InfectedHuman
     * @param healthyHumanList lista obiektów HealthyHuman
     * @param medicalHumanList lista obiektów MedicalHuman
     * @param cureList Lista obiektów Cure
     * @param width Szerokość okna mapy
     * @param height Wysokość okna mapy
     * @param scale Skala mapy
     */
    public Map(List<AInfectedObject> infectedHumanList, List<AHealthyObject> healthyHumanList, List<AMedicalObject> medicalHumanList, List<ACureObject> cureList, int width, int height, int scale) {
        this.setBackground(Color.LIGHT_GRAY);
        this.scale = scale;
        setPreferredSize(new Dimension(width*scale,height*scale));
        this.infectedHumanList = infectedHumanList;
        this.healthyHumanList = healthyHumanList;
        this.medicalHumanList = medicalHumanList;
        this.cureList = cureList;
    }
    /**
     * Nadpisanie metody służącej do rysowania komponentów okna
     * @param g obiekt odpowiadający za rysowanie obiektów
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        for(int i = 0; i < healthyHumanList.size(); i++){
            g.fillOval(healthyHumanList.get(i).getXPosition()*scale, healthyHumanList.get(i).getYPosition()*scale, scale, scale);
        }
        g.setColor(Color.RED);
        for(int i = 0; i < infectedHumanList.size(); i++){
            g.fillOval(infectedHumanList.get(i).getXPosition()*scale, infectedHumanList.get(i).getYPosition()*scale, scale, scale);
        }
        g.setColor(Color.magenta);
        for(int i = 0; i < medicalHumanList.size(); i++){
            g.fillOval(medicalHumanList.get(i).getXPosition()*scale, medicalHumanList.get(i).getYPosition()*scale, scale, scale);
        }
        g.setColor(Color.YELLOW);
        for(int i = 0; i < cureList.size(); i++){
            g.fillOval(cureList.get(i).getXPosition()*scale, cureList.get(i).getYPosition()*scale, scale, scale);
        }
    }
}
