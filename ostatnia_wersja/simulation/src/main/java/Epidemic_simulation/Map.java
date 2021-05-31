package Epidemic_simulation;

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
    /**
     * Fabryka obiektów
     */
    private final IObjectFactory objectFactory;

    public Map(int mapHeight, int mapWidth, int moveRange, int infectChance, int recoveryChance, int healthyNumber,
               int infectedNumber, int medicalNumber, int cureNumber, int scale) {
        this.setBackground(new Color(154, 160, 145));
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

        // Zwiększenie numeru epoki
        stageNumber++;
    }

    public void moveObjects(){
        // Poruszenie obiektów symulacji
        int oldXPosition;
        int oldYPosition;
        boolean busyPosition;
        for(AMedicalObject medicalHuman : medicalHumanList){
            oldXPosition = medicalHuman.getXPosition();
            oldYPosition = medicalHuman.getYPosition();
            do {
                busyPosition = false;
                medicalHuman.setXPosition(oldXPosition + RandomGenerator.getMove(medicalHuman.getMoveRange()));
                medicalHuman.setYPosition(oldYPosition + RandomGenerator.getMove(medicalHuman.getMoveRange()));
                if (medicalHuman.getXPosition() < 0 || medicalHuman.getXPosition() >= mapWidth) busyPosition = true;
                if (medicalHuman.getYPosition() < 0 || medicalHuman.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalHumanList.contains(medicalHuman)) busyPosition = true;
                if (infectedHumanList.contains(medicalHuman)) busyPosition = true;
                if (healthyHumanList.contains(medicalHuman)) busyPosition = true;
                if (cureList.contains(medicalHuman)) busyPosition = true;
            }while (busyPosition);
        }
        for(AInfectedObject infectedHuman : infectedHumanList){
            oldXPosition = infectedHuman.getXPosition();
            oldYPosition = infectedHuman.getYPosition();
            do {
                busyPosition = false;
                infectedHuman.setXPosition(oldXPosition + RandomGenerator.getMove(infectedHuman.getMoveRange()));
                infectedHuman.setYPosition(oldYPosition + RandomGenerator.getMove(infectedHuman.getMoveRange()));
                if (infectedHuman.getXPosition() < 0 || infectedHuman.getXPosition() >= mapWidth) busyPosition = true;
                if (infectedHuman.getYPosition() < 0 || infectedHuman.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalHumanList.contains(infectedHuman)) busyPosition = true;
                if (infectedHumanList.contains(infectedHuman)) busyPosition = true;
                if (healthyHumanList.contains(infectedHuman)) busyPosition = true;
                if (cureList.contains(infectedHuman)) busyPosition = true;
            }while (busyPosition);
        }
        for(AHealthyObject healthyHuman : healthyHumanList){
            oldXPosition = healthyHuman.getXPosition();
            oldYPosition = healthyHuman.getYPosition();
            do {
                busyPosition = false;
                healthyHuman.setXPosition(oldXPosition + RandomGenerator.getMove(healthyHuman.getMoveRange()));
                healthyHuman.setYPosition(oldYPosition + RandomGenerator.getMove(healthyHuman.getMoveRange()));
                if (healthyHuman.getXPosition() < 0 || healthyHuman.getXPosition() >= mapWidth) busyPosition = true;
                if (healthyHuman.getYPosition() < 0 || healthyHuman.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalHumanList.contains(healthyHuman)) busyPosition = true;
                if (infectedHumanList.contains(healthyHuman)) busyPosition = true;
                if (healthyHumanList.contains(healthyHuman)) busyPosition = true;
                if (cureList.contains(healthyHuman)) busyPosition = true;
            }while (busyPosition);
        }
    }
    /**
     * Metoda domyślnie służąca do zapisywania statystyk do pliku tekstowego, aktualnie jedynie wyświetla je w konsoli
     */
    public Statistics getStats(){
        Statistics statistics = new Statistics(recovered, infected, stageNumber, infectedHumanList.size(), healthyHumanList.size(), cureList.size(), medicalHumanList.size());
        recovered = 0;
        infected = 0;
        return statistics;
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
