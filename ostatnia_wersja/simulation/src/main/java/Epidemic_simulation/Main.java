package Epidemic_simulation;

/**
 * Klasa main
 */
public class Main{
    /**
     * Metoda w ktorej jest tworzona symulacja
     * @param args Wartosci poczatkowe dla symulaji
     */
    public static void main(String[] args) {
        // Sprawdzenie czy podano odpowiednia liczbe argumentow
        if (args.length != 11){
            System.out.println("Nie podano odpowiedniej liczby argumentów aby poprawnie przeprowadzić symulację.\nProgram został zatrzymany.");
            System.exit(0);
        }
        // Tablica na argumenty
        int[] valueOfArgs = new int[11];
        // Parsowanue argumentow na wartosci int
        try{
            for(int i = 0; i < 11; ++i){
                valueOfArgs[i] = Integer.parseInt(args[i]);
            }
        }
        catch (Exception c){
            System.out.println("Nie powiodło się rzutowanie wprowadzonych argumentów na wartości int.\nProgram został zatrzymany.");
            System.exit(0);
        }
        try {
            // Utworzenie symulacji z zadanymi parametrami
            Epidemic epidemic = new Epidemic(valueOfArgs[0], valueOfArgs[1], valueOfArgs[2], valueOfArgs[3], valueOfArgs[4], valueOfArgs[5], valueOfArgs[6], valueOfArgs[7], valueOfArgs[8], valueOfArgs[9]);
            // Przeprowadzenie wskazanej liczby epok symulacji
            epidemic.startSimulation(valueOfArgs[10]);
            // Zapisanie statystych symulacji do pliku tekstowego
            epidemic.saveStats();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
