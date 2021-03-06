package pl.wicherska.songs.interfaces;

import pl.wicherska.songs.domain.Song;

import java.util.List;

public interface SongRepository {
    //todo
    /**
     * Implementation should return all songs for specific type
     * @return list of song
     */
    List<Song> getSongs();
}
