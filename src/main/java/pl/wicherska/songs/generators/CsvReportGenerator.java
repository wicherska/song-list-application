package pl.wicherska.songs.generators;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportGenerator;
import pl.wicherska.songs.writers.CsvReportWriter;

import java.util.List;

public class CsvReportGenerator implements ReportGenerator {
    private final CsvReportWriter csvWriter;

    public CsvReportGenerator(CsvReportWriter csvWriter) {
        this.csvWriter = csvWriter;
    }

    @Override
    public void generateReport(List<Song> songs) {
        csvWriter.createReport(songs);
    }
}
