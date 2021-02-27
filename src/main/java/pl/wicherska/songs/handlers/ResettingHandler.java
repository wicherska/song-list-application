package pl.wicherska.songs.handlers;

import pl.wicherska.songs.interfaces.Handler;
import pl.wicherska.songs.services.SongService;

import java.util.Scanner;

public class ResettingHandler implements Handler {
    Scanner scanner;
    SongService songService;

    public ResettingHandler(Scanner scanner, SongService songService) {
        this.scanner = scanner;
        this.songService = songService;
    }

    @Override
    public void handle() {
        System.out.println("Options: 1. reset votes for chosen song 2. reset votes for all songs");
        int fromUser = scanner.nextInt();
        if (fromUser == 1) {
            resetChosen();
        } else if (fromUser == 2) {
            resetAll();
        } else {
            System.out.println("Incorrect number");
        }
    }

    private void resetAll() {
        songService.resetVotesForAllSongs();
        System.out.println("All: ");
        songService.printAllSongs();
    }

    private void resetChosen() {
        System.out.println("Please provide number of song ");
        songService.printAllSongs();
        int index = scanner.nextInt() - 1;
        songService.resetVotesForChosenSong(index);
        System.out.println("Votes have been reset for song:  " + songService.getSong(index).toString());
    }
}
