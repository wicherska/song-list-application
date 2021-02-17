package pl.wicherska.songs.repositories;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.csv.CsvConverter;
import pl.wicherska.songs.csv.CsvDataSource;
import pl.wicherska.songs.interfaces.Repository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CsvSongRepository implements Repository {
    private final CsvConverter csvConverter;
    private final CsvDataSource csvDataSource;
    private final String path;

    public CsvSongRepository(CsvConverter csvConverter, CsvDataSource csvDataSource, String path) {
        this.csvConverter = csvConverter;
        this.csvDataSource = csvDataSource;
        this.path = path;
    }

    @Override
    public List<Song> getListOfSongs(){
        try{
            return csvConverter.mapDataSourceToListOfSongs(csvDataSource.readFromFile(path));
        } catch (IOException e) {
            System.out.println("Csv file does not exist");
            return new LinkedList<>();
        }
    }
}
