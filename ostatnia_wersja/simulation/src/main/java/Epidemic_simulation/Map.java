package Epidemic_simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map extends AMap {

    private final int scale;
    /**
     * Lista przechowująca obiekty typu Cure
     */
    private final List<ACureObject> cureObjectList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu HealthyHuman
     */
    private final List<AHealthyObject> healthyObjectList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu InfecteedHuman
     */
    private final List<AInfectedObject> infectedObjectList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu MedicalHuman
     */
    private final List<AMedicalObject> medicalObjectList = new ArrayList<>();
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
        if((mapHeight * mapWidth) < (healthyNumber + infectedNumber + medicalNumber + cureNumber)){
            throw new IllegalArgumentException("Ilosc obiektow nie moze wieksza niz ilosc pol mapy.");
        }
        if(mapHeight < 0 || mapWidth < 0){
            throw new IllegalArgumentException("Wymiary mapy nie moga byc ujemne.");
        }
        if(infectChance < 0){
            throw new IllegalArgumentException("Szansa na zakazenie nie moze byc ujemna.");
        }
        if(recoveryChance < 0){
            throw new IllegalArgumentException("Szansa na uleczenie nie moze byc ujemna.");
        }
        if(healthyNumber < 0 || infectedNumber < 0 || medicalNumber < 0 || cureNumber < 0){
            throw new IllegalArgumentException("Ilosc obiektow nie moze byc ujemne.");
        }
        if(scale < 1){
            throw new IllegalArgumentException("Skala mapy nie moze byc mniejsza od 1");
        }

        this.setBackground(new Color(154, 160, 145));
        setPreferredSize(new Dimension(mapWidth*scale,mapHeight*scale));
        this.scale = scale;
        objectFactory = new ObjectFactory(moveRange, infectChance, recoveryChance);
        // Pętla tworząca nowe obiekty typu MedicalHuman i dodająca je do medicalObjectList
        AMedicalObject temporaryMedicalObject;
        for (int i = 0; i < medicalNumber; i++){
            do{
                temporaryMedicalObject = objectFactory.createMedicalObject(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (medicalObjectList.contains(temporaryMedicalObject));
            medicalObjectList.add(temporaryMedicalObject);
        }
        // Pętla tworząca nowe obiekty typu HealthyHuman i dodająca je do healthyObjectList
        AHealthyObject temporaryHealthyObject;
        for (int i = 0; i < healthyNumber; i++){
            do{
                temporaryHealthyObject = objectFactory.createHealthyObject(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (healthyObjectList.contains(temporaryHealthyObject) || medicalObjectList.contains(temporaryHealthyObject));
            healthyObjectList.add(temporaryHealthyObject);
        }
        // Pętla tworząca nowe obiekty typu InfectedHuman i dodająca je do infectedObjectList
        AInfectedObject temporaryInfectedObject;
        for (int i = 0; i < infectedNumber; i++){
            do{
                temporaryInfectedObject = objectFactory.createInfectedObject(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (infectedObjectList.contains(temporaryInfectedObject) || medicalObjectList.contains(temporaryInfectedObject) || healthyObjectList.contains(temporaryInfectedObject));
            infectedObjectList.add(temporaryInfectedObject);
        }
        // Pętla tworząca nowe obiektu typu Cure, oraz dodająca je do cureObjectList
        ACureObject temporaryCure;
        for (int i = 0; i < cureNumber; i++){
            do{
                temporaryCure = objectFactory.createCureObject(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (cureObjectList.contains(temporaryCure) || infectedObjectList.contains(temporaryCure) || medicalObjectList.contains(temporaryCure) || healthyObjectList.contains(temporaryCure));
            cureObjectList.add(temporaryCure);
        }
        // Przypisanie do zmiennej mapHeight wysokości mapy
        this.mapHeight = mapHeight;
        // Przypisanie do zmiennej mapWidth szerokości mapy
        this.mapWidth = mapWidth;
    }
    /**
     * Metoda służąca do przeprowadzenia kolejnej epoki symulacji
     */
    @Override
    public void nextStage(){
        // Pętla odpowiadająca za interakcję MedicalHuman oraz InfectedHuman
        for (AMedicalObject medicalObject : medicalObjectList) {
            for (int i = 0; i < infectedObjectList.size(); i++) {
                AInfectedObject infectedHuman = infectedObjectList.get(i);
                if (medicalObject.cureSuccessful(infectedHuman)) {
                    healthyObjectList.add(objectFactory.createHealthyObject(infectedHuman.getXPosition(), infectedHuman.getYPosition()));
                    infectedObjectList.remove(i);
                    this.recovered++;
                    i--;
                }
            }
        }
        // Pętla odpowiadająca za interakcję Cure oraz InfectedHuman
        for (int i = 0; i < infectedObjectList.size(); i++){
            AInfectedObject infectedObject = infectedObjectList.get(i);
            for (int j = 0; j < cureObjectList.size(); j++){
                if (infectedObject.isCured(cureObjectList.get(j))){
                    healthyObjectList.add(objectFactory.createHealthyObject(infectedObject.getXPosition(), infectedObject.getYPosition()));
                    infectedObjectList.remove(i);
                    cureObjectList.remove(j);
                    this.recovered++;
                    i--;
                    break;
                }
            }
        }
        // Pętla odpowiadająca za interakcję HealthyHuman oraz InfectedHuman
        for (int i = 0; i < healthyObjectList.size(); i++){
            AHealthyObject healthyObject = healthyObjectList.get(i);
            for (AInfectedObject infectedObject : infectedObjectList){
                if (healthyObject.isInfected(infectedObject)){
                    infectedObjectList.add(objectFactory.createInfectedObject(healthyObject.getXPosition(), healthyObject.getYPosition()));
                    healthyObjectList.remove(i);
                    this.infected++;
                    i--;
                    break;
                }
            }
        }

        // Zwiększenie numeru epoki
        stageNumber++;
    }

    @Override
    public void moveObjects(){
        // Poruszenie obiektów symulacji
        int oldXPosition;
        int oldYPosition;
        boolean busyPosition;
        for(AMedicalObject medicalObject : medicalObjectList){
            oldXPosition = medicalObject.getXPosition();
            oldYPosition = medicalObject.getYPosition();
            do {
                busyPosition = false;
                medicalObject.setXPosition(oldXPosition + RandomGenerator.getMove(medicalObject.getMoveRange()));
                medicalObject.setYPosition(oldYPosition + RandomGenerator.getMove(medicalObject.getMoveRange()));
                if (medicalObject.getXPosition() < 0 || medicalObject.getXPosition() >= mapWidth) busyPosition = true;
                if (medicalObject.getYPosition() < 0 || medicalObject.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalObjectList.contains(medicalObject)) busyPosition = true;
                if (infectedObjectList.contains(medicalObject)) busyPosition = true;
                if (healthyObjectList.contains(medicalObject)) busyPosition = true;
                if (cureObjectList.contains(medicalObject)) busyPosition = true;
            }while (busyPosition);
        }
        for(AInfectedObject infectedObject : infectedObjectList){
            oldXPosition = infectedObject.getXPosition();
            oldYPosition = infectedObject.getYPosition();
            do {
                busyPosition = false;
                infectedObject.setXPosition(oldXPosition + RandomGenerator.getMove(infectedObject.getMoveRange()));
                infectedObject.setYPosition(oldYPosition + RandomGenerator.getMove(infectedObject.getMoveRange()));
                if (infectedObject.getXPosition() < 0 || infectedObject.getXPosition() >= mapWidth) busyPosition = true;
                if (infectedObject.getYPosition() < 0 || infectedObject.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalObjectList.contains(infectedObject)) busyPosition = true;
                if (infectedObjectList.contains(infectedObject)) busyPosition = true;
                if (healthyObjectList.contains(infectedObject)) busyPosition = true;
                if (cureObjectList.contains(infectedObject)) busyPosition = true;
            }while (busyPosition);
        }
        for(AHealthyObject healthyObject : healthyObjectList){
            oldXPosition = healthyObject.getXPosition();
            oldYPosition = healthyObject.getYPosition();
            do {
                busyPosition = false;
                healthyObject.setXPosition(oldXPosition + RandomGenerator.getMove(healthyObject.getMoveRange()));
                healthyObject.setYPosition(oldYPosition + RandomGenerator.getMove(healthyObject.getMoveRange()));
                if (healthyObject.getXPosition() < 0 || healthyObject.getXPosition() >= mapWidth) busyPosition = true;
                if (healthyObject.getYPosition() < 0 || healthyObject.getYPosition() >= mapHeight) busyPosition = true;
                if (medicalObjectList.contains(healthyObject)) busyPosition = true;
                if (infectedObjectList.contains(healthyObject)) busyPosition = true;
                if (healthyObjectList.contains(healthyObject)) busyPosition = true;
                if (cureObjectList.contains(healthyObject)) busyPosition = true;
            }while (busyPosition);
        }
    }
    /**
     * Metoda domyślnie służąca do zapisywania statystyk do pliku tekstowego, aktualnie jedynie wyświetla je w konsoli
     */
    @Override
    public Statistics getStats(){
        Statistics statistics = new Statistics(recovered, infected, stageNumber, infectedObjectList.size(), healthyObjectList.size(), cureObjectList.size(), medicalObjectList.size());
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
        for(int i = 0; i < healthyObjectList.size(); i++){
            g.fillOval(healthyObjectList.get(i).getXPosition()*scale, healthyObjectList.get(i).getYPosition()*scale, scale, scale);
        }
        g.setColor(Color.RED);
        for(int i = 0; i < infectedObjectList.size(); i++){
            g.fillOval(infectedObjectList.get(i).getXPosition()*scale, infectedObjectList.get(i).getYPosition()*scale, scale, scale);
        }
        g.setColor(Color.magenta);
        for(int i = 0; i < medicalObjectList.size(); i++){
            g.fillOval(medicalObjectList.get(i).getXPosition()*scale, medicalObjectList.get(i).getYPosition()*scale, scale, scale);
        }
        g.setColor(Color.YELLOW);
        for(int i = 0; i < cureObjectList.size(); i++){
            g.fillOval(cureObjectList.get(i).getXPosition()*scale, cureObjectList.get(i).getYPosition()*scale, scale, scale);
        }
    }
}
