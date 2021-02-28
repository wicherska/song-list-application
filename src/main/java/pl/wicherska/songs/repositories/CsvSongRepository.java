package pl.wicherska.songs.repositories;

import pl.wicherska.songs.converters.CsvConverter;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.SongRepository;
import pl.wicherska.songs.sources.CsvDataSource;

import java.util.List;

public class CsvSongRepository implements SongRepository {
    private final CsvConverter csvConverter;
    private final CsvDataSource csvDataSource;
    private final List<String> paths;

    public CsvSongRepository(CsvConverter csvConverter, CsvDataSource csvDataSource, List<String> paths) {
        this.csvConverter = csvConverter;
        this.csvDataSource = csvDataSource;
        this.paths = paths;
    }

    @Override
    public List<Song> getSongs() {
        return csvConverter.toSongs(csvDataSource.readFromFiles(paths));
    }
}
