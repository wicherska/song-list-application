package pl.wicherska.songs.report;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.csv.CsvConverter;
import pl.wicherska.songs.csv.CsvDataSource;
import pl.wicherska.songs.interfaces.Writer;

import java.io.IOException;
import java.util.List;

public class CsvWriter implements Writer {
    private final CsvConverter csvConverter;
    private final CsvDataSource csvDataSource;
    private final String path;

    public CsvWriter(CsvConverter csvConverter, CsvDataSource csvDataSource, String path) {
        this.csvConverter = csvConverter;
        this.csvDataSource = csvDataSource;
        this.path = path;
    }

    @Override
    public void createReport(List<Song> songList) {
        try{
            csvDataSource.writeToFile(csvConverter.mapListOfSongsToDataSource(songList), path);
        } catch(IOException e){
            System.out.println("Generating CSV file failed");
        }
    }
}
