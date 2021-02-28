package pl.wicherska.songs.writers;

import pl.wicherska.songs.converters.CsvConverter;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportWriter;
import pl.wicherska.songs.sources.CsvDataSource;

import java.io.IOException;
import java.util.List;

public class CsvReportWriter implements ReportWriter {

    private final CsvConverter csvConverter;
    private final CsvDataSource csvDataSource;
    private final String path;

    public CsvReportWriter(CsvConverter csvConverter, CsvDataSource csvDataSource, String path) {
        this.csvConverter = csvConverter;
        this.csvDataSource = csvDataSource;
        this.path = path;
    }

    @Override
    public void createReport(List<Song> songList) {
        try {
            csvDataSource.writeToFile(csvConverter.fromSongs(songList), path);
            System.out.println("Report created. File: " + path);
        } catch (IOException e) {
            System.out.println("Generating CSV file failed");
        }
    }
}
