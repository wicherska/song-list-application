package pl.wicherska.songs.handlers;

import pl.wicherska.songs.interfaces.Handler;
import pl.wicherska.songs.services.SongService;

import java.util.Scanner;

public class ZeroingHandler implements Handler {
    Scanner scanner;
    SongService songService;

    public ZeroingHandler(Scanner scanner, SongService songService) {
        this.scanner = scanner;
        this.songService = songService;
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
        songService.setVotesToZeroForAllSongs();
        System.out.println("Wszystkie głosy wyzerowane: ");
        songService.printAllSongs();
    }

    private void zeroChosen() {
        System.out.println("Podaj numer piosenki, która ma mieć wyzerowane głosy");
        songService.printAllSongs();
        int index = scanner.nextInt() - 1;
        songService.setVotesToZeroForChosenSong(index);
        System.out.println("Głosy wyzerowane dla piosenki: " + songService.getSong(index).toString());
    }
}
