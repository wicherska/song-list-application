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
        String title = getData("Please provide title");
        String author = getData("Please provide author");
        String album = getData("Please provide album");
        Category category = getCategory();
        int votes = getVotes();

        Song songFromUser = new Song(title, author, album, category, votes);
        List<Song> listOfSongs = inMemorySongRepository.getListOfSongs();
        if (!listOfSongs.contains(songFromUser)){
            inMemorySongRepository.addSong(songFromUser);
            System.out.println("Song has been added: " + songFromUser.toString());
        } else{
            System.out.println("The song already exists in database");
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
        System.out.println("Please provide category from: " + Arrays.toString(Category.values()));
        Category category = Category.fromString(scanner.nextLine().toUpperCase());
        while(category == null){
            System.out.println("Incorrect category. Niepoprawna kategoria. Please provide category from: " + Arrays.toString(Category.values()));
            category = Category.fromString(scanner.nextLine().toUpperCase());
        }
        return category;
    }

    private int getVotes(){
        System.out.println("Please provide number of votes");
        boolean flag = true;
        int votes = 0;
        while(flag){
            try{
                votes = Integer.parseInt(scanner.nextLine());
                if(votes<=0){
                    System.out.println("Please provide positive integer");
                }else{
                    flag=false;
                }
            }catch (NumberFormatException e){
                System.out.println("Please provide positive integer");
            }
        }
        return votes;
    }
}
