package Epidemic_simulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map extends JPanel {
    /**
     * Lista przechowująca obiekty typu Cure
     */
    private final List<Cure> cureList;
    /**
     * Lista przechowująca obiekty typu HealthyHuman
     */
    private final List<HealthyHuman> healthyHumanList;
    /**
     * Lista przechowująca obiekty typu InfecteedHuman
     */
    private final List<InfectedHuman> infectedHumanList;
    /**
     * Lista przechowująca obiekty typu MedicalHuman
     */
    private final List<MedicalHuman> medicalHumanList;
    /**
     * Konstruktor okna mapy
     * @param infectedHumanList list obiektów InfectedHuman
     * @param healthyHumanList lista obiektów HealthyHuman
     * @param medicalHumanList lista obiektów MedicalHuman
     * @param cureList Lista obiektów Cure
     * @param width Szerokość okna mapy
     * @param height Wysokość okna mapy
     */
    public Map(List<InfectedHuman> infectedHumanList, List<HealthyHuman> healthyHumanList, List<MedicalHuman> medicalHumanList, List<Cure> cureList, int width, int height) {
        setPreferredSize(new Dimension(width*10,height*10));
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
            g.fillOval(healthyHumanList.get(i).getXPosition()*10, healthyHumanList.get(i).getYPosition()*10, 10, 10);
        }
        g.setColor(Color.RED);
        for(int i = 0; i < infectedHumanList.size(); i++){
            g.fillOval(infectedHumanList.get(i).getXPosition()*10, infectedHumanList.get(i).getYPosition()*10, 10, 10);
        }
        g.setColor(Color.magenta);
        for(int i = 0; i < medicalHumanList.size(); i++){
            g.fillOval(medicalHumanList.get(i).getXPosition()*10, medicalHumanList.get(i).getYPosition()*10, 10, 10);
        }
        g.setColor(Color.YELLOW);
        for(int i = 0; i < cureList.size(); i++){
            g.fillOval(cureList.get(i).getXPosition()*10, cureList.get(i).getYPosition()*10, 10, 10);
        }
    }
}
