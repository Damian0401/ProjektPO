package Epidemic_simulation;

import java.util.Random;

/**
 * Klasa służąca za generowanie elementów losowych podczas symulacji
 */
public class RandomGenerator{
    /**
     * Obiektu Klasy Random, służący do generowania losowych liczb
     */
    private static final Random random = new Random();
    /**
     * Prywatny konstruktor zapobiegający tworzeniu obiektów danej klasy
     */
    private RandomGenerator() {}
    /**
     * Metoda służąca do generowania losowych ruchów
     * @param range Zakres ruchu
     * @return Losowa liczba z zakresu od (-range) do (+range)
     */
    public static int getMove(int range){
        return random.nextInt(2 * range + 1) - range;
    }
    /**
     * Wylosowania szansu
     * @return Losowa liczba z zakresu 0 - 100
     */
    public static int getChance(){
        return random.nextInt(101);
    }
    /**
     * Metoda służąca do wylosowania pozycji na mapie
     * @param range Wielkość mapy
     * @return Losowa pozycja na mapie
     */
    public static int getPosition(int range){
        return random.nextInt(range);
    }
}
