package pl.wicherska.songs;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;



public class TestSongFactory {

    public static Song rockSong(){
        return new Song("Living in a Ghost Town", "The Rolling Stones", "Honk", Category.ROCK, 17);
    }

    public static Song rockSong2(){
        return new Song("Space Oddity", "David Bowie", "David Bowie", Category.ROCK, 15);
    }

    public static Song rockSong3(){
        return new Song("Fade to Black", "Metallica", "Ride the Lightning", Category.ROCK, 13);
    }

    public static Song rockSong4(){
        return new Song("Father To Son", "Queen", "Queen II", Category.ROCK, 21);
    }

    public static Song alternativeSong(){
        return new Song("You Should Be Sad", "Halsey", "Manic", Category.ALTERNATIVE, 2);
    }

    public static Song alternativeSong2(){
        return new Song("Settle Down", "Kimbra", "Vows", Category.ALTERNATIVE, 3);
    }

    public static Song alternativeSong3(){
        return new Song("Shots", "Imagine Dragons", "Smoke", Category.ALTERNATIVE, 4);
    }

    public static Song alternativeSong4(){
        return new Song("Black Sun", "Death Cab for Cutie", "Black Sun", Category.ALTERNATIVE, 9);
    }

    public static Song rAndBSong(){
        return new Song("Imported", "Jessie Reyez", "Before Love Came to Kill Us", Category.RANDB, 6);
    }

    public static Song rAndBSong2(){
        return new Song("Reason", "11:11", "MOOD", Category.RANDB, 11);
    }

    public static Song rAndBSong3(){
        return new Song("Earl Grey", "F.N.", "Earl Grey", Category.RANDB, 7);
    }
}
