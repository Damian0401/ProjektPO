package Epidemic_simulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Główna klasa odpowiadająca za przebieg symulacji
 */
public class Epidemic extends JFrame{
    /**
     * Obiekt mapy
     */
    private final Map map;
    /**
     * Lista przechowująca obiekty typu Cure
     */
    private final List<ACure> cureList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu HealthyHuman
     */
    private final List<AHealthyHuman> healthyHumanList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu InfecteedHuman
     */
    private final List<AInfectedHuman> infectedHumanList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu MedicalHuman
     */
    private final List<AMedicalHuman> medicalHumanList = new ArrayList<>();
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

    private final ICureFactory cureFactory;

    private final IHumanFactory humanFactory;
    /**
     * Konstruktor klasy Epidemic
     * @param mapHeight Wielkość mapy
     * @param mapWidth Szerokość mapy
     * @param infectChance Szansa na zakażanie obiektu typu HealthyHuman przez obiekt typu InfectedHuman
     * @param recoveryChance Szansa na uleczenie obiektu InfectedHuman przez obiekt Cure
     * @param healthyNumber Ilość obiektów typu HealthyHuman
     * @param infectedNumber Ilość obiektów typu InfectedHuman
     * @param medicalNumber Ilość obiektów typu MedicalHuman
     * @param cureNumber Ilość obiektów typu Cure
     * @param scale Skala obiektów na mapie
     */
    public Epidemic(int mapHeight, int mapWidth, int moveRange, int infectChance, int recoveryChance, int healthyNumber,
                    int infectedNumber, int medicalNumber, int cureNumber, int scale){
        super("Epidemic simulation");

        cureFactory = new CureFactory(recoveryChance);

        humanFactory = new HumanFactory(moveRange, infectChance);
        // Pętla tworząca nowe obiekty typu MedicalHuman i dodająca je do medicalHumanList
        AMedicalHuman temporaryMedicalHuman;
        for (int i = 0; i < medicalNumber; i++){
            do{
                temporaryMedicalHuman = humanFactory.createMedicalHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (medicalHumanList.contains(temporaryMedicalHuman));
            medicalHumanList.add(temporaryMedicalHuman);
        }
        // Pętla tworząca nowe obiekty typu HealthyHuman i dodająca je do healthyHumanList
        AHealthyHuman temporaryHealthyHuman;
        for (int i = 0; i < healthyNumber; i++){
            do{
                temporaryHealthyHuman = humanFactory.createHealthyHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (healthyHumanList.contains(temporaryHealthyHuman));
            healthyHumanList.add(temporaryHealthyHuman);
        }
        // Pętla tworząca nowe obiekty typu InfectedHuman i dodająca je do infectedHumanList
        AInfectedHuman temporaryInfectedHuman;
        for (int i = 0; i < infectedNumber; i++){
            do{
                temporaryInfectedHuman = humanFactory.createInfectedHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (infectedHumanList.contains(temporaryInfectedHuman));
            infectedHumanList.add(temporaryInfectedHuman);
        }
        // Pętla tworząca nowe obiektu typu Cure, oraz dodająca je do cureList
        ACure temporaryCure;
        for (int i = 0; i < cureNumber; i++){
            do{
                temporaryCure = cureFactory.createCure(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight));
            }while (cureList.contains(temporaryCure));
            cureList.add(temporaryCure);
        }
        // Przypisanie do zmiennej mapHeight wysokości mapy
        this.mapHeight = mapHeight;
        // Przypisanie do zmiennej mapWidth szerokości mapy
        this.mapWidth = mapWidth;
        // Utworzenie mapy i przekazanie do niej list obiektów wraz z wymiarami mapy
        map = new Map(infectedHumanList, healthyHumanList, medicalHumanList, cureList, mapWidth, mapHeight, scale);
        // Dodanie do ramki okna mapy
        add(map);
        // Spakowanie ramki
        pack();

        // Uniemożliwienie ręcznego zmienu rozmiaru okna mapy
        setResizable(false);
        // Ustawienie eventu zamykającego mapę
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Ustawienie widzialności mapy
        setVisible(true);
        // Umożliwienie odświerzania wyglądu mapy
        map.invalidate();
    }
    /**
     * Metoda służąca do przeprowadzenia kolejnej epoki symulacji
     */
    public void nextStage(){
        // Pętla odpowiadająca za interakcję MedicalHuman oraz InfectedHuman
        for (AMedicalHuman medicalHuman : medicalHumanList) {
            for (int i = 0; i < infectedHumanList.size(); i++) {
                AInfectedHuman infectedHuman = infectedHumanList.get(i);
                if (medicalHuman.cureSuccessful(infectedHuman.getXPosition(), infectedHuman.getYPosition())) {
                    healthyHumanList.add(humanFactory.createHealthyHuman(infectedHuman.getXPosition(), infectedHuman.getYPosition()));
                    infectedHumanList.remove(i);
                    this.recovered++;
                    i--;
                }
            }
        }
        // Pętla odpowiadająca za interakcję Cure oraz InfectedHuman
        for (int i = 0; i < infectedHumanList.size(); i++){
            AInfectedHuman infectedHuman = infectedHumanList.get(i);
            for (int j = 0; j < cureList.size(); j++){
                if (infectedHuman.isCured(cureList.get(j))){
                    healthyHumanList.add(humanFactory.createHealthyHuman(infectedHuman.getXPosition(), infectedHuman.getYPosition()));
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
            AHealthyHuman healthyHuman = healthyHumanList.get(i);
            for (AInfectedHuman infectedHuman : infectedHumanList){
                if (healthyHuman.isInfected(infectedHuman)){
                    infectedHumanList.add(humanFactory.createInfectedHuman(healthyHuman.getXPosition(), healthyHuman.getYPosition()));
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
    public void saveStats(){
        System.out.println(">>>>>" + stageNumber + "<<<<<");
        System.out.println("Number of InfectedHuman objects:");
        System.out.println(infectedHumanList.size());
        System.out.println("Number of Cure objects:");
        System.out.println(cureList.size());
        System.out.println("Number of MedicalHuman objects:");
        System.out.println(medicalHumanList.size());
        System.out.println("Number of HealthyHuman objects:");
        System.out.println(healthyHumanList.size());
        System.out.println("Number of recoverd:");
        System.out.println(recovered);
        System.out.println("Number of infected");
        System.out.println(infected);
        System.out.println();
    }
    /**
     * Metoda służąca do rysowania mapy
     */
    public void drawMap(){
        // Odświerzenie mapy
        map.repaint();
    }
}
