package pl.wicherska.songs.handlers;

import pl.wicherska.songs.interfaces.Handler;
import pl.wicherska.songs.services.SongService;

import java.util.Scanner;

public class VotingHandler implements Handler {
    private final Scanner scanner;
    private final SongService songService;

    public VotingHandler(Scanner scanner, SongService songService) {
        this.scanner = scanner;
        this.songService = songService;
    }

    @Override
    public void handle() {
        voteForChosen();
    }

    private void voteForChosen() {
        System.out.println("Podaj numer piosenki na którą chcesz zagłosować");
        songService.printAllSongs();
        int index = scanner.nextInt() - 1;
        songService.voteForChosenSong(index);
        System.out.println("Głos oddany na piosenkę: " + songService.getSong(index).toString());
    }
}
