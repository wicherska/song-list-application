package pl.wicherska.songs.generators;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportGenerator;
import pl.wicherska.songs.writers.JsonReportWriter;

import java.util.List;

public class JsonReportGenerator implements ReportGenerator {
    private final JsonReportWriter jsonReportWriter;

    public JsonReportGenerator(JsonReportWriter jsonReportWriter) {
        this.jsonReportWriter = jsonReportWriter;
    }

    @Override
    public void generateReport(List<Song> songs) {
        jsonReportWriter.createReport(songs);
    }
}
