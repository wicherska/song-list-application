package pl.wicherska.songs.repositories;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.SongRepository;

import java.util.ArrayList;
import java.util.List;


public class AggregatingSongRepository implements SongRepository {
    private final List<Song> songList = new ArrayList<>();

    public AggregatingSongRepository(List<SongRepository> songRepositoryList) {
        setInitialListOfSong(songRepositoryList);
    }

    private void setInitialListOfSong(List<SongRepository> songRepositoryList) {
        for (SongRepository songRepository : songRepositoryList) {
            List<Song> repositoryListOfSongs = songRepository.getSongs();
            for (Song repositorySong : repositoryListOfSongs) {
                if (doesNotContainSong(repositorySong)) {
                    songList.add(repositorySong);
                } else {
                    Song song = songList.get(songList.indexOf(repositorySong));
                    song.addVotes(repositorySong.getVotes());
                }
            }
        }
    }

    @Override
    public List<Song> getSongs() {
        return new ArrayList<>(songList);
    }

    public void addSong(Song song) {
        songList.add(song);
    }

    public boolean doesNotContainSong(Song song) {
        return !songList.contains(song);
    }

}
