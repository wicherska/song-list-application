package pl.wicherska.songs;

import pl.wicherska.songs.core.Config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner SCANNER;

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Brak argumentów. Podaj ścieżkę/ścieżki do pliku/plików.");
            return;
        }
        for (String fileName: args){
            if(!Files.exists(Paths.get(fileName))){
                System.out.println("\nNie znaleziono pliku: " + fileName);
                return;
            }
        }
        Config config = Config.getInstance();
        Config.setPaths(args);
        SCANNER = new Scanner(System.in);
        String options = "Wybierz jedną z poniższych opcji podając jej numer\n" +
                "1. Oddanie głosu na piosenkę\n" +
                "2. Dodanie piosenki\n" +
                "3. Wyzerowanie głosów dla wybranej piosenki\n" +
                "4. Wyzerowanie głosów dla wszystkich piosenek)\n" +
                "5. Zrobienie i wypisanie raportu z rankingu top10\n" +
                "6. Zrobienie i wypisanie raportu z rankingu top3\n" +
                "7. Zrobienie i wypisanie raportu dla wszystkich piosenek\n" +
                "8. Zrobienie i wypisanie raportu wg. kategorii\n" +
                "9. Zakończenie pracy programu";


        while(true){
            System.out.println(options);
            int input = SCANNER.nextInt();
            if(input == 9){
                break;
            }
            switch (input){
                case 1:
                    System.out.println("case1");
                    break;
                case 2:
                    System.out.println("case2");
                    break;
                case 3:
                    System.out.println("case3");
                    break;
                case 4:
                    config.inMemorySongRepository().setVotesToZeroForAllSongs();
                    System.out.println("Wszystkie głosy wyzerowane");
                    break;
                case 5:
                    switch(getMode()){
                        case 1:
                            config.reportGenerator().reportCsvTop10();
                            break;
                        case 2:
                            config.reportGenerator().reportXmlTop10();
                            break;
                        case 3:
                            config.reportGenerator().reportConsoleTop10();
                            break;
                    }
                    break;
                case 6:
                    switch(getMode()){
                        case 1:
                            config.reportGenerator().reportCsvTop3();
                            break;
                        case 2:
                            config.reportGenerator().reportXmlTop3();
                            break;
                        case 3:
                            config.reportGenerator().reportConsoleTop3();
                            break;
                    }
                    break;
                case 7:
                    switch(getMode()){
                        case 1:
                            config.reportGenerator().reportCsvAll();
                            break;
                        case 2:
                            config.reportGenerator().reportXmlAll();
                            break;
                        case 3:
                            config.reportGenerator().reportConsoleAll();
                            break;
                    }
                    break;
                case 8:
                    switch(getMode()){
                        case 1:
                            config.reportGenerator().reportCsvByCategory();
                            break;
                        case 2:
                            config.reportGenerator().reportXmlByCategory();
                            break;
                        case 3:
                            config.reportGenerator().reportConsoleByCategory();
                            break;
                    }
                    break;
                default:
                    System.out.println("Wprowadź poprawną liczbę");
                    System.out.println(options);
            }
        }

        SCANNER.close();
    }
    private static int getMode(){
        System.out.println("Wybierz sposób generowania raportu\n" +
                "1. CSV\n" +
                "2. XML\n" +
                "3. Konsola");
        int result;
        List<Integer> list = List.of(1,2,3);
        while(true){
            result = SCANNER.nextInt();
            if(!list.contains(result)){
                System.out.println("Podaj poprawna liczbe");
            } else{
                break;
            }
        }
        return result;

    }
}
