package pl.wicherska.songs;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;

import java.util.LinkedList;
import java.util.List;

public class TestSongFactory {

    public static Song rockSong(){
        return new Song("Living in a Ghost Town", "The Rolling Stones", "Honk", Category.ROCK, 10);
    }

    public static Song alternativeSong(){
        return new Song("You Should Be Sad", "Halsey", "Manic", Category.ALTERNATIVE, 2);
    }

    public static Song rAndBSong(){
        return new Song("Imported", "Jessie Reyez", "Before Love Came to Kill Us", Category.RANDB, 6);
    }
}
