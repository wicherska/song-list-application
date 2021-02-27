package pl.wicherska.songs.handlers;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.Handler;
import pl.wicherska.songs.repositories.InMemorySongRepository;

import java.util.*;

public class AddingHandler implements Handler {
    private final Scanner scanner;
    private final InMemorySongRepository inMemorySongRepository;

    public AddingHandler(Scanner scanner, InMemorySongRepository inMemorySongRepository) {
        this.scanner = scanner;
        this.inMemorySongRepository = inMemorySongRepository;
    }

    @Override
    public void handle() {
        String title = getData("Podaj tytuł");
        String author = getData("Podaj autora");
        String album = getData("Podaj album");
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

    private String getData(String message){
        System.out.println(message);
        String fromUser;
        do{
            fromUser=scanner.nextLine();
        }while(fromUser.isBlank());
        return fromUser;
    }

    private Category getCategory(){
        System.out.println("Podaj kategorię, z dostępnych " + Arrays.toString(Category.values()));
        Category category = Category.fromString(scanner.nextLine().toUpperCase());
        while(category == null){
            System.out.println("Niepoprawna kategoria. Podaj jedną z dostępnych: " + Arrays.toString(Category.values()));
            category = Category.fromString(scanner.nextLine().toUpperCase());
        }
        return category;
    }

    private int getVotes(){
        System.out.println("Podaj inicjalną liczbę głosów");
        boolean flag = true;
        int votes = 0;
        while(flag){
            try{
                votes = Integer.parseInt(scanner.nextLine());
                if(votes<=0){
                    System.out.println("Podaj liczbę całkowitą dodatnią");
                }else{
                    flag=false;
                }
            }catch (NumberFormatException e){
                System.out.println("Podaj liczbę całkowitą dodatnią");
            }
        }
        return votes;
    }
}
