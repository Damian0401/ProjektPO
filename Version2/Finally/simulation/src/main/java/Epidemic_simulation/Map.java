package Epidemic_simulation;

import Epidemic_simulation.ACureObject;
import Epidemic_simulation.AHealthyObject;
import Epidemic_simulation.AInfectedObject;
import Epidemic_simulation.AMedicalObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map extends JPanel {

    private final int scale;
    /**
     * Lista przechowująca obiekty typu Cure
     */
    private final List<ACureObject> cureList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu HealthyHuman
     */
    private final List<AHealthyObject> healthyHumanList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu InfecteedHuman
     */
    private final List<AInfectedObject> infectedHumanList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu MedicalHuman
     */
    private final List<AMedicalObject> medicalHumanList = new ArrayList<>();
    /**
     * Zmienna przechowująca ilość ludzi którzy wyzdrowieli od początku symulacji
     */
    private int recovered = 0;
    /**
     * Zmienna przechowująca ilość ludzi którzy wyzdrowieli od początku symulacji
     */
    private int infected = 0;
    /**
     * Zmienna przechowująca aktualny numer epoki
     */
    private int stageNumber = 0;
    /**
     * Zmienna przechowująca wysokość mapy
     */
    private final int mapHeight;
    /**
     * Zmienna przechowująca szerokość mapy
     */
    private final int mapWidth;

    private final IObjectFactory objectFactory;

    public Map(int mapHeight, int mapWidth, int moveRange, int infectChance, int recoveryChance, int healthyNumber,
               int infectedNumber, int medicalNumber, int cureNumber, int scale) {
        this.setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(mapWidth*scale,mapHeight*scale));
        this.scale = scale;
        objectFactory = new ObjectFactory(moveRange, infectChance, recoveryChance);
        // Pętla tworząca nowe obiekty typu MedicalHuman i dodająca je do medicalHumanList
        AMedicalObject temporaryMedicalHuman;
        for (int i = 0; i < medicalNumber; i++){
            do{
                temporaryMedicalHuman = objectFactory.createMedicalHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (medicalHumanList.contains(temporaryMedicalHuman));
            medicalHumanList.add(temporaryMedicalHuman);
        }
        // Pętla tworząca nowe obiekty typu HealthyHuman i dodająca je do healthyHumanList
        AHealthyObject temporaryHealthyHuman;
        for (int i = 0; i < healthyNumber; i++){
            do{
                temporaryHealthyHuman = objectFactory.createHealthyHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (healthyHumanList.contains(temporaryHealthyHuman) || medicalHumanList.contains(temporaryHealthyHuman));
            healthyHumanList.add(temporaryHealthyHuman);
        }
        // Pętla tworząca nowe obiekty typu InfectedHuman i dodająca je do infectedHumanList
        AInfectedObject temporaryInfectedHuman;
        for (int i = 0; i < infectedNumber; i++){
            do{
                temporaryInfectedHuman = objectFactory.createInfectedHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (infectedHumanList.contains(temporaryInfectedHuman) || medicalHumanList.contains(temporaryInfectedHuman) || healthyHumanList.contains(temporaryInfectedHuman));
            infectedHumanList.add(temporaryInfectedHuman);
        }
        // Pętla tworząca nowe obiektu typu Cure, oraz dodająca je do cureList
        ACureObject temporaryCure;
        for (int i = 0; i < cureNumber; i++){
            do{
                temporaryCure = objectFactory.createCure(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (cureList.contains(temporaryCure) || infectedHumanList.contains(temporaryCure) || medicalHumanList.contains(temporaryCure) || healthyHumanList.contains(temporaryCure));
            cureList.add(temporaryCure);
        }
        // Przypisanie do zmiennej mapHeight wysokości mapy
        this.mapHeight = mapHeight;
        // Przypisanie do zmiennej mapWidth szerokości mapy
        this.mapWidth = mapWidth;
    }
    /**
     * Metoda służąca do przeprowadzenia kolejnej epoki symulacji
     */
    public void nextStage(){
        // Pętla odpowiadająca za interakcję MedicalHuman oraz InfectedHuman
        for (AMedicalObject medicalHuman : medicalHumanList) {
            for (int i = 0; i < infectedHumanList.size(); i++) {
                AInfectedObject infectedHuman = infectedHumanList.get(i);
                if (medicalHuman.cureSuccessful(infectedHuman.getXPosition(), infectedHuman.getYPosition())) {
                    healthyHumanList.add(objectFactory.createHealthyHuman(infectedHuman.getXPosition(), infectedHuman.getYPosition()));
                    infectedHumanList.remove(i);
                    this.recovered++;
                    i--;
                }
            }
        }
        // Pętla odpowiadająca za interakcję Cure oraz InfectedHuman
        for (int i = 0; i < infectedHumanList.size(); i++){
            AInfectedObject infectedHuman = infectedHumanList.get(i);
            for (int j = 0; j < cureList.size(); j++){
                if (infectedHuman.isCured(cureList.get(j))){
                    healthyHumanList.add(objectFactory.createHealthyHuman(infectedHuman.getXPosition(), infectedHuman.getYPosition()));
                    infectedHumanList.remove(i);
                    cureList.remove(j);
                    this.recovered++;
                    i--;
                    break;
                }
            }
        }
        // Pętla odpowiadająca za interakcję HealthyHuman oraz InfectedHuman
        for (int i = 0; i < healthyHumanList.size(); i++){
            AHealthyObject healthyHuman = healthyHumanList.get(i);
            for (AInfectedObject infectedHuman : infectedHumanList){
                if (healthyHuman.isInfected(infectedHuman)){
                    infectedHumanList.add(objectFactory.createInfectedHuman(healthyHuman.getXPosition(), healthyHuman.getYPosition()));
                    healthyHumanList.remove(i);
                    this.infected++;
                    i--;
                    break;
                }
            }
        }
        // Poruszenie obiektów symulacji
        SimulationObjectMover.moveAllSimulationObjects(medicalHumanList, infectedHumanList, healthyHumanList, cureList, mapHeight, mapWidth);
        // Zwiększenie numeru epoki
        stageNumber++;
    }
    /**
     * Metoda domyślnie służąca do zapisywania statystyk do pliku tekstowego, aktualnie jedynie wyświetla je w konsoli
     */
    public EpidemicStatistics getStats(){
        return new EpidemicStatistics(recovered, infected, stageNumber, infectedHumanList.size(), healthyHumanList.size(), cureList.size(), medicalHumanList.size());
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
