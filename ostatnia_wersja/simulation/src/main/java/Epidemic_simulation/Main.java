package Epidemic_simulation;

public class Main{
    public static void main(String[] args) {
//        if (args.length != 11){
//            System.out.println("Nie podano odpowiedniej liczby argumentów aby poprawnie przeprowadzić symulację.\nProgram został zatrzymany.");
//            System.exit(0);
//        }

        int[] valueOfArgs = {50,50,1,30,30,10,5,6,10,20,200};
//        try{
//            for(int i = 0; i < 11; ++i){
//                valueOfArgs[i] = Integer.parseInt(args[i]);
//            }
//        }
//        catch (Exception c){
//            System.out.println("Nie powiodło się rzutowanie wprowadzonych argumentów na wartości int.\nProgram został zatrzymany.");
//            System.exit(0);
//        }

        // Utworzenie symulacji z zadanymi parametrami
        Epidemic epidemic = new Epidemic(valueOfArgs[0], valueOfArgs[1], valueOfArgs[2], valueOfArgs[3], valueOfArgs[4], valueOfArgs[5], valueOfArgs[6], valueOfArgs[7], valueOfArgs[8], valueOfArgs[9]);
        // Przeprowadzenie wskazanej liczby epok symulacji
        epidemic.startSimulation(valueOfArgs[10]);
        // Zapisanie statystych symulacji do pliku tekstowego
        epidemic.saveStats();
    }


}
