package pl.wicherska.songs.handlers;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.Handler;
import pl.wicherska.songs.repositories.InMemorySongRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddingHandler implements Handler {
    private final Scanner scanner;
    private final InMemorySongRepository inMemorySongRepository;

    public AddingHandler(Scanner scanner, InMemorySongRepository inMemorySongRepository) {
        this.scanner = scanner;
        this.inMemorySongRepository = inMemorySongRepository;
    }

    @Override
    public void handle() {
        System.out.println("Dodawanie piosenki");
        System.out.println("Podaj tytuł");
        String title = scanner.nextLine();
        System.out.println("Podaj autora");
        String author = scanner.nextLine();
        System.out.println("Podaj album");
        String album = scanner.nextLine();
        System.out.println("Podaj kategorię, z dostępnych " + Arrays.toString(Category.values()));
        Category category = Category.fromString(scanner.nextLine().toUpperCase());
        System.out.println("Podaj inicjalną liczbę głosów");
        int votes = scanner.nextInt();

        Song fromUser = new Song(title, author, album, category, votes);
        List<Song> listOfSongs = inMemorySongRepository.getListOfSongs();
        if (!listOfSongs.contains(fromUser)){
            inMemorySongRepository.addSong(fromUser);
            System.out.println("Dodano piosenkę: " + fromUser.toString());
        } else{
            System.out.println("Piosenka już istnieje w bazie");
        }
    }
}
