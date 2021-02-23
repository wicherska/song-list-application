package pl.wicherska.songs.handlers;

import pl.wicherska.songs.interfaces.Handler;
import pl.wicherska.songs.repositories.InMemorySongRepository;

import java.util.Scanner;

public class ZeroingHandler implements Handler {
    Scanner scanner;
    InMemorySongRepository inMemorySongRepository;

    public ZeroingHandler(Scanner scanner, InMemorySongRepository inMemorySongRepository) {
        this.scanner = scanner;
        this.inMemorySongRepository = inMemorySongRepository;
    }

    @Override
    public void handle() {
        System.out.println("Opcje zerowania głosów, 1. dla wybranej piosenki 2. dla wszystkich");
        int fromUser = scanner.nextInt();
        if (fromUser == 1) {
            zeroChosen();
        } else if (fromUser == 2) {
            zeroAll();
        } else {
            System.out.println("Niepoprawny numer");
        }
    }

    private void zeroAll() {
        inMemorySongRepository.setVotesToZeroForAllSongs();
        System.out.println("Wszystkie głosy wyzerowane: ");
        inMemorySongRepository.printAllSongs();
    }

    private void zeroChosen() {
        System.out.println("Podaj numer piosenki, która ma mieć wyzerowane głosy");
        inMemorySongRepository.printAllSongs();
        int index = scanner.nextInt() - 1;
        inMemorySongRepository.setVotesToZeroForChosenSong(index);
        System.out.println("Głosy wyzerowane dla piosenki: " + inMemorySongRepository.getSong(index).toString());
    }
}
