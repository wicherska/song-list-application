package pl.wicherska.songs.interfaces;

import pl.wicherska.songs.domain.Song;

import java.util.List;

public interface Converter<T> {
    List<Song> mapDataSourceToListOfSongs(List<T> elements);
    List<T> mapListOfSongsToDataSource(List<Song> songList);
}
