package pl.wicherska.songs.interfaces;

import pl.wicherska.songs.domain.Song;

import java.util.List;

//todo

/**
 * This is a Javadoc
 */
public interface SongRepository {
    List<Song> getSongs();
}
