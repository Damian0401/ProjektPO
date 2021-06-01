package Epidemic_simulation;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Główna klasa odpowiadająca za przebieg symulacji
 */
public class Epidemic extends JFrame{

    Queue<Statistics> queueWithStatistics = new LinkedList<>();
    /**
     * Obiekt mapy
     */
    private final AMap map;

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
    public Epidemic(int mapHeight, int mapWidth,int moveRange, int infectChance, int recoveryChance, int healthyNumber,
                    int infectedNumber, int medicalNumber, int cureNumber, int scale){
        super("Epidemic simulation");


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
        // Umożliwienie odświeżania wyglądu mapy
        map.invalidate();
    }

    public void startSimulation(int stageNumber) throws InterruptedException {
        map.repaint();
        queueWithStatistics.add(map.getStats());

        for(int i = 0; i < stageNumber; i++) {
            TimeUnit.MILLISECONDS.sleep(500);
            map.nextStage();
            map.moveObjects();
            map.repaint();
            queueWithStatistics.add(map.getStats());
        }

        System.out.println("Symulacja zakończona\n");
    }

    public void saveStats(){
        if(queueWithStatistics.isEmpty()){
            System.out.println("Aplikacja nie posiada żadnych zebranych danych.");
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            Date date = new Date();

            String filePath = ".\\statistics\\" + formatter.format(date) + ".csv";
            System.out.println(filePath);
            String newFileName = filePath.replace(" ","");
            try {
                File myObj = new File(newFileName);
                if(myObj.createNewFile()){

                    FileWriter myWriter = new FileWriter(newFileName);
                    myWriter.write("Stage number;HealthyObject number;InfectedObject number;MedicalObject number;CureObject number;New recovered number;New infected number\n");
                    while (!queueWithStatistics.isEmpty()) {
                        myWriter.write(queueWithStatistics.poll().toString());
                    }
                    myWriter.close();

                }
            } catch (IOException e) {
                System.out.println("Nie powiadło się zapisywanie statystyk");
            }
        }
    }
}
