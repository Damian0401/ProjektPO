package Epidemic_simulation;

/**
 * Klasa odpowiadajaca za zapisywanie statystyk kazdej epoki symulacji
 */
public class Statistics {
    /**
     *  Zmienna przechowujaca ilosc uleczonych obiektow w danej epoce
     */
    private final int newRecoveredNumber;
    /**
     * Zmienna przechowujaca ilosc zarazonych obiektow w danej epoce
     */
    private final int newInfectedNumber;
    /**
     * Zmienna przechowujaca numer rundy
     */
    private final int stageNumber;
    /**
     * Zmienna przechowujaca obecna ilosc zarazonych obiektow
     */
    private final int infectedObjectNumber;
    /**
     * Zmienna przechowujaca obecna ilosc zdrowych obiektow
     */
    private final int healthyObjectNumber;
    /**
     * Zmienna przechowujaca obecna ilosc obiektow lekarstw
     */
    private final int cureObjectNumber;
    /**
     * Zmienna przechowujaca obecna ilosc obiektow lekarzy
     */
    private final int medicalObjectNumber;

    /**
     * Konstruktor z parametrami
     * @param newRecoveredNumber Ilosc nowych wyzdrowien
     * @param newInfectedNumber Ilosc nowych zakazen
     * @param stageNumber Numer epoki
     * @param infectedObjectNumber Ilosc zakazonych
     * @param healthyObjectNumber Ilosc zdowych
     * @param cureObjectNumber Ilosc lekarstw
     * @param medicalObjectNumber Ilosc lekarzy
     */
    public Statistics(int newRecoveredNumber, int newInfectedNumber, int stageNumber, int infectedObjectNumber, int healthyObjectNumber, int cureObjectNumber, int medicalObjectNumber){
        this.newInfectedNumber = newInfectedNumber;
        this.newRecoveredNumber = newRecoveredNumber;
        this.stageNumber = stageNumber;
        this.infectedObjectNumber = infectedObjectNumber;
        this.healthyObjectNumber = healthyObjectNumber;
        this.cureObjectNumber = cureObjectNumber;
        this.medicalObjectNumber = medicalObjectNumber;
    }

    /**
     * Metoda zwracajaca statystyki danej rundy
     */
    @Override
    public String toString() {
        return stageNumber + ";" + healthyObjectNumber + ";" + infectedObjectNumber + ";" + medicalObjectNumber + ";" + cureObjectNumber + ";" + newRecoveredNumber + ";" + newInfectedNumber + "\n";
    }
}
