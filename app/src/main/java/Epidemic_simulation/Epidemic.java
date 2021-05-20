package Epidemic_simulation;

import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Główna klasa odpowiadająca za przebieg symulacji
 */
public class Epidemic extends JFrame{
    /**
     * Obiekt mapy
     */
    Map map;
    /**
     * Lista przechowująca obiekty typu Cure
     */
    private final List<Cure> cureList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu HealthyHuman
     */
    private final List<HealthyHuman> healthyHumanList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu InfecteedHuman
     */
    private final List<InfectedHuman> infectedHumanList = new ArrayList<>();
    /**
     * Lista przechowująca obiekty typu MedicalHuman
     */
    private final List<MedicalHuman> medicalHumanList = new ArrayList<>();
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
     * Konstruktor klasy Epidemic
     * @param mapHeight Wielkość mapy
     * @param mapWidth Szerokość mapy
     * @param infectChance Szansa na zakażanie obiektu typu HealthyHuman przez obiekt typu InfectedHuman
     * @param recoveryChance Szansa na uleczenie obiektu InfectedHuman przez obiekt Cure
     * @param healthyNumber Ilość obiektów typu HealthyHuman
     * @param infectedNumber Ilość obiektów typu InfectedHuman
     * @param medicalNumber Ilość obiektów typu MedicalHuman
     * @param cureNumber Ilość obiektów typu Cure
     */
    public Epidemic(int mapHeight, int mapWidth, int infectChance, int recoveryChance, int healthyNumber,
                    int infectedNumber, int medicalNumber, int cureNumber){
        super("Epidemic simulation");
        // Pętla tworząca nowe obiekty typu MedicalHuman i dodająca je do medicalHumanList
        for (int i = 0; i < medicalNumber; i++){
            medicalHumanList.add(new MedicalHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight)));
        }
        // Pętla tworząca nowe obiekty typu HealthyHuman i dodająca je do healthyHumanList
        for (int i = 0; i < healthyNumber; i++){
            healthyHumanList.add(new HealthyHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight)));
        }
        // Pętla tworząca nowe obiekty typu InfectedHuman i dodająca je do infectedHumanList
        for (int i = 0; i < infectedNumber; i++){
            infectedHumanList.add(new InfectedHuman(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight), infectChance));
        }
        // Pętla tworząca nowe obiektu typu Cure, oraz dodająca je do cureList
        for (int i = 0; i < cureNumber; i++){
            cureList.add(new Cure(RandomGenerator.getPosition(mapWidth), RandomGenerator.getPosition(mapHeight), recoveryChance));
        }
        // Przypisanie do zmiennej mapHeight wysokości mapy
        this.mapHeight = mapHeight;
        // Przypisanie do zmiennej mapWidth szerokości mapy
        this.mapWidth = mapWidth;
        // Utworzenie mapy i przekazanie do niej list obiektów wraz z wymiarami mapy
        map = new Map(infectedHumanList, healthyHumanList, medicalHumanList, cureList, mapWidth, mapHeight);
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
        for (MedicalHuman medicalHuman : medicalHumanList) {
            for (int i = 0; i < infectedHumanList.size(); i++) {
                if (medicalHuman.cure(infectedHumanList.get(i))) {
                    healthyHumanList.add(new HealthyHuman(infectedHumanList.get(i).getXPosition(), infectedHumanList.get(i).getYPosition()));
                    infectedHumanList.remove(i);
                    this.recovered++;
                    i--;
                }
            }
        }
        // Pętla odpowiadająca za interakcję Cure oraz InfectedHuman
        for (int i = 0; i < infectedHumanList.size(); i++){
            InfectedHuman infectedHuman = infectedHumanList.get(i);
            for (int j = 0; j < cureList.size(); j++){
                if (infectedHuman.isCured(cureList.get(j))){
                    healthyHumanList.add(new HealthyHuman(infectedHuman.getXPosition(), infectedHuman.getYPosition()));
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
            HealthyHuman healthyHuman = healthyHumanList.get(i);
            for (InfectedHuman infectedHuman : infectedHumanList){
                if (healthyHuman.isInfected(infectedHuman)){
                    infectedHumanList.add(new InfectedHuman(healthyHuman.getXPosition(), healthyHuman.getYPosition(), infectedHuman.getInfectChance()));
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
