package Epidemic_simulation;

/**
 * Klasa sluzaca za tworzenie obiektow wykorzystykanych podczas wymulacji
 */
public class ObjectFactory implements IObjectFactory {
    /**
     * Zmienna przechowujaca bazowy zakres ruchu obiektow
     */
    private final int moveRange;

    /**
     * Zmienna przechowujaca bazowa szanse na zakazanie obiektow
     */
    private final int infectChance;

    /**
     * Zmienna przechowujaca bazowo szanse na wyleczenie obiektow
     */
    private final int recoveryChance;

    /**
     * Zmienna przechowujaca informacje czy zostal utworzony pierwszy zainfekowany obiekt
     */
    private boolean firstInfectedExist;

    /**
     * Konstruktor z parametrami
     * @param moveRange Zakres ruchu
     * @param infectChance Szansa na zakazenie
     * @param recoveryChance Szansa na uleczenie
     */
    public ObjectFactory(int moveRange, int infectChance, int recoveryChance){
        this.moveRange = moveRange;
        this.infectChance = infectChance;
        this.recoveryChance = recoveryChance;
    }

    /**
     * Metoda odpowiedzialna za tworzenie zainfekowanych obiektow
     * @param xPosition Wspolrzedna x obiektow
     * @param yPosition Wspolrzedna y obiektow
     * @return Nowy obiekt klasy AInfectedObject
     */
    @Override
    public AInfectedObject createInfectedObject(int xPosition, int yPosition) {
        firstInfectedExist = true;
        // 5% szans za utworzenie zmutowanego zainfekowanego czlowieka
        if(RandomGenerator.getChance()<6) return new MutatedInfectedHuman(xPosition, yPosition, this.moveRange, this.infectChance);
        return new InfectedHuman(xPosition, yPosition, this.moveRange, this.infectChance);
    }

    /**
     * Metoda odpwiedzialna za tworzenie zdrowych obiektow
     * @param xPosition Wspolrzedna x obiektow
     * @param yPosition Wspolrzedna y obiektow
     * @return Nowy obiekt klasy AHealthyObject
     */
    @Override
    public AHealthyObject createHealthyObject(int xPosition, int yPosition) {
        // 50% szans na utworzenie odpornego czlowieka gdy istnieje pierwszy zakazony
        if(firstInfectedExist && RandomGenerator.getChance()<51) return new ImmuneHealthyHuman(xPosition, yPosition, this.moveRange);
        return new HealthyHuman(xPosition, yPosition, this.moveRange);
    }

    /**
     * Metoda odpwiedzialna za tworzenie leczacych obiektow
     * @param xPosition Wspolrzedna x obiektow
     * @param yPosition Wspolrzedna y obiektow
     * @return Nowy obiekt klasy AMedicalObject
     */
    @Override
    public AMedicalObject createMedicalObject(int xPosition, int yPosition) {
        // 25% szans za utworzenie niedoswiadczonego lekarza
        if(RandomGenerator.getChance()<26) return new InexperiencedMedicalHuman(xPosition, yPosition, this.moveRange);
        return new MedicalHuman(xPosition, yPosition, this.moveRange);
    }

    /**
     * Metoda odpwiedzialna za tworzenie obiektow lekarstw
     * @param xPosition Wspolrzedna x obiektow
     * @param yPosition Wspolrzedna y obiektow
     * @return Nowy obiekt klasy ACureObject
     */
    @Override
    public ACureObject createCureObject(int xPosition, int yPosition) {
        // 10% szans za wytworzenie uszkodzonego lekarstwa
        if(RandomGenerator.getChance()<11) return new DefectedCure(xPosition, yPosition, recoveryChance);
        return new Cure(xPosition, yPosition, recoveryChance);
    }
}
