package pl.wicherska.songs;

import pl.wicherska.songs.core.Config;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Lack of arguments. Please provide path/paths to file/files.");
            return;
        }
        for (String fileName : args) {
            if (!Files.exists(Paths.get(fileName))) {
                System.out.println("\nFile not found: " + fileName);
                return;
            }
        }

        Config config = Config.getInstance();
        Config.setPaths(args);
        ApplicationRunner applicationRunner = config.applicationRunner();
        applicationRunner.run();

    }
}
