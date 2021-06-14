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
    /**
     * Kolejka przechowujaca statystyki zbierane podczas przeprowadzania symulacji
     */
    Queue<Statistics> queueWithStatistics = new LinkedList<>();

    /**
     * Referencja do obiektu mapy
     */
    private final AMap map;

    /**
     * Konstruktor klasy Epidemic
     * @param mapHeight Wielkosc mapy
     * @param mapWidth Szerokosc mapy
     * @param moveRange Zakres ruchu obiektow
     * @param infectChance Szansa na zakazanie obiektu typu HealthyHuman przez obiekt typu InfectedHuman
     * @param recoveryChance Szansa na uleczenie obiektu InfectedHuman przez obiekt Cure
     * @param healthyNumber Ilosc obiektow typu HealthyHuman
     * @param infectedNumber Ilosc obiektow typu InfectedHuman
     * @param medicalNumber Ilosc obiektow typu MedicalHuman
     * @param cureNumber Ilosc obiektow typu Cure
     * @param scale Skala obiektow na mapie
     */
    public Epidemic(int mapHeight, int mapWidth, int moveRange, int infectChance, int recoveryChance, int healthyNumber,
                    int infectedNumber, int medicalNumber, int cureNumber, int scale){
        // Okreslenie nazwy okna symulacji
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

    /**
     * Metoda sluzaca do uruchamiania symulacji na wskazana ilosc epok
     * @param stageNumber ilosc epok symulacji ktore maja zostac przeprowadzone
     */
    public void startSimulation(int stageNumber) {


        if(stageNumber < 0){
            throw new IllegalArgumentException("Ilosc epok nie moze byc ujemna.");
        }
        // Odswiezenie mapy
        map.repaint();
        // Dodanie do kolejki statystyk poczatkowych
        queueWithStatistics.add(map.getStats());
        try{
            // Petla wykonana tyle razy, ile wynosi liczba epok w parametrze metody
            for(int i = 0; i < stageNumber; i++) {
                // Odczekanie odpowiednej ilosci czasu
                TimeUnit.MILLISECONDS.sleep(500);
                // Przeprowadzenie kolejnej epoki symulacji
                map.nextStage();
                // Prouszenie obiektow symulacji
                map.moveObjects();
                // Odswiezenie mapy
                map.repaint();
                // Dodanie do kolejki statystyk danej epoki
                queueWithStatistics.add(map.getStats());
            }
            // Wyswietlenie komunikatu o zakonczeniu symulacji
            System.out.println("Symulacja przeprowadzona pomyślnie\n");
        }
        catch (Exception e){
            // Wyswietlenie komunikatu w przypadku wystapienia bledu
            System.out.println("Nie udało się poprawnie przeprowadzić symulacji:\n" + e.getMessage());
        }
    }

    /**
     * Metoda sluzaca do zapisywania statystyk epidemii do pliku tekstowego
     */
    public void saveStats(){
        // Warunek sprawdzajacy czy kolejka z statystykami jest pusta
        if(queueWithStatistics.isEmpty()){
            // Wyswietlenie komunikatu o braku statystyk do zapisanie
            System.out.println("Aplikacja nie posiada żadnych zebranych danych.");
            // Zakonczenie dzialania metody
            return;
        }
        // Utworzenie obiektu do formatowania dany
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        // Utworzenie obiektu do przechowywania dany
        Date date = new Date();
        // Utworzenie sciezki do pliku z statystykami
        String filePath = ".\\statistics\\" + formatter.format(date) + ".csv";
        // Wyswietlenie sciezki do pliku z statystykami
        System.out.println(filePath);
        try {
            // Utworzenie obiektu
            File myFile = new File(filePath);
            // Warunek sprawdzajacy czy powiodlo sie utworzenie nowego pliku
            if(myFile.createNewFile()){
                // Utworzenie obiektu sluzacego do zapisywania statstyk do pliki *.csv
                FileWriter myWriter = new FileWriter(filePath);
                // Zapisanie do pliki *.csv naglowkow dla odpowiednych kolumn
                myWriter.write("Stage number;HealthyObject number;InfectedObject number;MedicalObject number;CureObject number;New recovered number;New infected number\n");
                // Petla wykonujaca sie dopoki w kolejce znajduja sie statystyki
                while (!queueWithStatistics.isEmpty()) {
                    // Zapisanie statystyk do pliku *.csv
                    myWriter.write(queueWithStatistics.poll().toString());
                }
                // Zamkniecie strumienia danych
                myWriter.close();
                // Wyswietlenie komunikatu o pomyslnym zapisaniu statystyk
                System.out.println("Pomyślnie zapisano statystyki symulacji.");
            }
        } catch (IOException e) {
            // Wyswietlenie komunikatu w przypadku wystapienia bledu
            System.out.println("Nie powiodło się zapisywanie statystyk");
        }

    }
}
