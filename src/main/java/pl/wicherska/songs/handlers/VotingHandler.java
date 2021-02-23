package pl.wicherska.songs.handlers;

import pl.wicherska.songs.interfaces.Handler;
import pl.wicherska.songs.repositories.InMemorySongRepository;

import java.util.Scanner;

public class VotingHandler implements Handler {
    private final Scanner scanner;
    private final InMemorySongRepository inMemorySongRepository;

    public VotingHandler(Scanner scanner, InMemorySongRepository inMemorySongRepository) {
        this.scanner = scanner;
        this.inMemorySongRepository = inMemorySongRepository;
    }

    @Override
    public void handle() {
        voteForChosen();
    }

    private void voteForChosen() {
        System.out.println("Podaj numer piosenki na którą chcesz zagłosować");
        inMemorySongRepository.printAllSongs();
        int index = scanner.nextInt() - 1;
        inMemorySongRepository.voteForChosenSong(index);
        System.out.println("Głos oddany na piosenkę: " + inMemorySongRepository.getSong(index).toString());
    }
}
