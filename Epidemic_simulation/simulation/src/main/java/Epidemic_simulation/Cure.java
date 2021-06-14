package Epidemic_simulation;

import Epidemic_simulation.ACureObject;

/**
 * Klasa reprezentujaca standardowe lekarstwo
 */
public class Cure extends ACureObject {
    /**
     * Zmienna przechowujaca szanse na uleszenie zakazonego
     */
    private final int recoveryChance;
    /**
     * Zmienna przechowujaca wspolrzedna x obiektu
     */
    private int xPosition;
    /**
     * Zmienna przechowujaca wspolrzedna y obiektu
     */
    private int yPosition;

    /**
     * Konstruktor z parametrami
     * @param xPosition Wspolrzedna x obiektu
     * @param yPosition Wspolrzedna y obiektu
     * @param recoveryChance Szansa na uleczenie obiektu
     */
    public Cure(int xPosition, int yPosition, int recoveryChance) {
        // Sprawdzenie czy wspolrzedne obiektow nie sa ujemne
        if(xPosition < 0 || yPosition < 0){
            throw new IllegalArgumentException("Wspolrzedne nie moga byc ujemne.");
        }
        // Sprawdzenie czy szansa na zakazenie jest liczba z zakresu 0 - 100
        if(recoveryChance < 0 || recoveryChance > 100){
            throw new IllegalArgumentException("Szansa na uleczenie musi byc liczba z zakresu 0 - 100.");
        }
        // Przypisanie do zmiennej recoveryChange szansy na zakazenie
        this.recoveryChance = recoveryChance;
        // Przypisanie do wspolrzedne xPosition wspolrzednej x przekazanej w konsturktorze
        this.xPosition = xPosition;
        // Przypisanie do wspolrzedne yPosition wspolrzednej y przekazanej w konsturktorze
        this.yPosition = yPosition;
    }

    /**
     * Metoda zwracajaca szanse na uleczenie
     * @return Szansa na uleczenie
     */
    @Override
    public int getRecoveryChance(){
        return this.recoveryChance;
    }
    /**
     * Metoda zwracajaca wspolrzedna x obiektu
     * @return Wspolrzedna x
     */
    @Override
    public int getXPosition() {
        return this.xPosition;
    }
    /**
     * Metoda zwracajaca wspolrzedna y obiektu
     * @return Wspolrzedna y
     */
    @Override
    public int getYPosition() {
        return this.yPosition;
    }
    /**
     * Metoda ustawiajaca nowa wspolrzedna x dla obiektu
     * @param xPosition Nowa wspolrzedna x
     */
    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }
    /**
     * Metoda ustawiajaca nowa wspolrzedna y dla obiektu
     * @param yPosition Nowa wspolrzedna y
     */
    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
