package pl.wicherska.songs.services;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.repositories.InMemorySongRepository;

import java.util.Comparator;
import java.util.List;

public class SongService {
    private final InMemorySongRepository inMemorySongRepository;

    public SongService(InMemorySongRepository inMemorySongRepository) {
        this.inMemorySongRepository = inMemorySongRepository;
    }

    public List<Song> getAllSongs(){
        return inMemorySongRepository.getListOfSongs();
    }

    public void printAllSongs(){
        List<Song> songs = getAllSongs();
        for(int i=1; i<=songs.size(); i++){
            System.out.println(i + ". " + songs.get(i-1));
        }
    }

    public List<Song> getSongSortedByVotes(){
        List<Song> songs = getAllSongs();
        songs.sort((s1, s2) -> s2.getVotes() - s1.getVotes());
        return songs;
    }

    public List<Song> getSongSortedByCategory(){
        List<Song> songs = getAllSongs();
        songs.sort(Comparator.comparing(Song::getCategory));
        return songs;
    }

    public void setVotesToZeroForAllSongs(){
        List<Song> songs = getAllSongs();
        for (Song song : songs) {
            song.setVotesToZero();
        }
    }

    public void setVotesToZeroForChosenSong(int index){
        List<Song> songs = getAllSongs();
        songs.get(index).setVotesToZero();
    }

    public void voteForChosenSong(int index){
        List<Song> songs = getAllSongs();
        songs.get(index).voteForSong();
    }

    public Song getSong(int index){
        List<Song> songs = getAllSongs();
        return songs.get(index);
    }
}
