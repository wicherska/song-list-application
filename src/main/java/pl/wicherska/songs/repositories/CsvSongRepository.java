package pl.wicherska.songs.repositories;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.csv.CsvConverter;
import pl.wicherska.songs.csv.CsvDataSource;
import pl.wicherska.songs.interfaces.Repository;


import java.util.List;

public class CsvSongRepository implements Repository {
    private final CsvConverter csvConverter;
    private final CsvDataSource csvDataSource;
    private final List<String> paths;

    public CsvSongRepository(CsvConverter csvConverter, CsvDataSource csvDataSource, List<String> paths) {
        this.csvConverter = csvConverter;
        this.csvDataSource = csvDataSource;
        this.paths = paths;
    }

    @Override
    public List<Song> getListOfSongs() {
        return csvConverter.mapDataSourceToListOfSongs(csvDataSource.readFromFiles(paths));
    }
}
