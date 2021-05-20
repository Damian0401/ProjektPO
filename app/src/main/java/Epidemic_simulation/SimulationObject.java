package Epidemic_simulation;

import java.util.Objects;

/**
 * Klasa bazowa dla wszystkich obiektów występujących w symulacji
 */
public abstract class SimulationObject {
    /**
     * Zmienna przechowująca współrzędną x obiektu
     */
    private int xPosition;
    /**
     * Zmienna przechowująca współrzędną y obiektu
     */
    private int yPosition;

    /**
     * Konstruktor klasy SimulationObject
     * @param x Współrzędna x obiektu
     * @param y Współrzędna y obiektu
     */
    SimulationObject(int x, int y){
        xPosition = x;
        yPosition = y;
    }
    /**
     * Metoda zwracająca współrzędną x obiektu
     * @return Współrzędna x obiektu
     */
    public int getXPosition() {
        return xPosition;
    }
    /**
     * Metoda zwracająca wspołrzędną y obiektu
     * @return Współrzędna y obiektu
     */
    public int getYPosition() {
        return yPosition;
    }
    /**
     * Metoda ustawiająca współrzędną x obiektu
     * @return Nowa współrzędna x obiektu
     */
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }
    /**
     * Metoda ustawiająca współrzędną y obiektu
     * @return Nowa współrzędna y obiektu
     */
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }
    /**
     * Nadpisanie domyślnej metody służącej do porównywanie obiektów z obiektem typu SimulationObject
     * @param o Obiekt z którym SimulationObject ma zostać porównany
     * @return Wynik porównania (true lub false)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return false;
        if (!(o instanceof SimulationObject)) return false;
        SimulationObject that = (SimulationObject) o;
        return xPosition == that.xPosition && yPosition == that.yPosition;
    }
    /**
     * Napisanie domyślnej metody służącej do generowania hashCode obiektu (Unikalny hashCode każdego obiektu przyśpiesza przeszukiwania list)
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }
    /**
     * Nadpisanie domyślnej metody ToString
     * @return String zawierający współrzędne obiektu
     */
    @Override
    public String toString() {
        return "(" + xPosition + ", " + yPosition + ")";
    }
}
