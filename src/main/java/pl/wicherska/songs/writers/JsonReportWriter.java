package pl.wicherska.songs.writers;

import pl.wicherska.songs.converters.JsonConverter;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportWriter;
import pl.wicherska.songs.sources.JsonDataSource;

import java.io.IOException;
import java.util.List;

public class JsonReportWriter implements ReportWriter {
    private final JsonConverter jsonConverter;
    private final JsonDataSource jsonDataSource;
    private final String path;

    public JsonReportWriter(JsonConverter jsonConverter, JsonDataSource jsonDataSource, String path) {
        this.jsonConverter = jsonConverter;
        this.jsonDataSource = jsonDataSource;
        this.path = path;
    }

    @Override
    public void createReport(List<Song> songList) {
        try {
            jsonDataSource.writeToFile(jsonConverter.fromSongs(songList), path);
            System.out.println("Report created. File: " + path);
        } catch (IOException e) {
            System.out.println("Generating JSON report failed");
        }
    }
}
