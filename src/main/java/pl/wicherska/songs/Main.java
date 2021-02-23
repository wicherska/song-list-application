package pl.wicherska.songs;

import pl.wicherska.songs.core.Config;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Brak argumentów. Podaj ścieżkę/ścieżki do pliku/plików.");
            return;
        }
        for (String fileName : args) {
            if (!Files.exists(Paths.get(fileName))) {
                System.out.println("\nNie znaleziono pliku: " + fileName);
                return;
            }
        }

        Config config = Config.getInstance();
        Config.setPaths(args);
        ApplicationRunner applicationRunner = config.applicationRunner();
        applicationRunner.run();

    }
}
