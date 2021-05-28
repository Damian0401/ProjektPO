package Epidemic_simulation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Główna klasa odpowiadająca za przebieg symulacji
 */
public class Epidemic extends JFrame{

    Queue<EpidemicStatistics> queue = new LinkedList<>();
    /**
     * Obiekt mapy
     */
    private final Map map;
    /**
     * Zmienna przechowująca aktualny numer epoki
     */
    private final int stageNumber;
    /**
     * Zmienna przechowująca wysokość mapy
     */
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
    public Epidemic(int mapHeight, int mapWidth, int stageNumber, int moveRange, int infectChance, int recoveryChance, int healthyNumber,
                    int infectedNumber, int medicalNumber, int cureNumber, int scale){
        super("Epidemic simulation");

        this.stageNumber = stageNumber;
        // Utworzenie mapy i przekazanie do niej list obiektów wraz z wymiarami mapy
        map = new Map(mapHeight, mapWidth, moveRange, infectChance, recoveryChance, healthyNumber, infectedNumber, medicalNumber, cureNumber, scale);
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

    public void startSimulation() throws InterruptedException {
        map.repaint();
        queue.add(map.getStats());

        for(int i = 0; i < stageNumber; i++){
            TimeUnit.MILLISECONDS.sleep(500);
            map.nextStage();
            map.repaint();
            queue.add(map.getStats());
        }

        System.out.println(queue.size());
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
