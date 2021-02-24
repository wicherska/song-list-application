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
        String title = getTitle();
        String author = getAuthor();
        String album = getAlbum();
        Category category = getCategory();
        int votes = getVotes();

        Song songFromUser = new Song(title, author, album, category, votes);
        List<Song> listOfSongs = inMemorySongRepository.getListOfSongs();
        if (!listOfSongs.contains(songFromUser)){
            inMemorySongRepository.addSong(songFromUser);
            System.out.println("Dodano piosenkę: " + songFromUser.toString());
        } else{
            System.out.println("Piosenka już istnieje w bazie");
        }
    }

    private String getTitle(){
        System.out.println("Podaj tytuł");
        return scanner.nextLine();
    }

    private String getAuthor(){
        System.out.println("Podaj autora");
        return scanner.nextLine();
    }

    private String getAlbum(){
        System.out.println("Podaj album");
        return scanner.nextLine();
    }

    private Category getCategory(){
        System.out.println("Podaj kategorię, z dostępnych " + Arrays.toString(Category.values()));
        return Category.fromString(scanner.nextLine().toUpperCase());
    }

    private int getVotes(){
        System.out.println("Podaj inicjalną liczbę głosów");
        return scanner.nextInt();
    }
}
