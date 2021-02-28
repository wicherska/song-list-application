package pl.wicherska.songs.services;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.repositories.AggregatingSongRepository;

import java.util.Comparator;
import java.util.List;

public class SongService {
    private final AggregatingSongRepository aggregatingSongRepository;

    public SongService(AggregatingSongRepository aggregatingSongRepository) {
        this.aggregatingSongRepository = aggregatingSongRepository;
    }

    public List<Song> getAllSongs() {
        return aggregatingSongRepository.getSongs();
    }

    public List<Song> getSongsSortedByVotes() {
        List<Song> songs = getAllSongs();
        songs.sort(Comparator.comparingInt(Song::getVotes).reversed());
        return songs;
    }

    public List<Song> getSongsSortedByCategory() {
        List<Song> songs = getAllSongs();
        Comparator<Song> comparator = Comparator.comparing(Song::getCategory);
        comparator = comparator.thenComparing(Comparator.comparingInt(Song::getVotes).reversed());
        songs.sort(comparator);
        return songs;
    }

    public void resetVotesForAllSongs() {
        getAllSongs().forEach(Song::resetVotes);
    }

    public void resetVotesForSong(Song song) {
        song.resetVotes();
    }

    public void voteForSong(Song song) {
        song.voteForSong();
    }

    public void printSongs(List<Song> songs) {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println(i + ". " + songs.get(i));
        }
    }
}
