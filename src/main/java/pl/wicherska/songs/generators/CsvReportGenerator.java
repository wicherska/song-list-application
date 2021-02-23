package pl.wicherska.songs.generators;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportGenerator;
import pl.wicherska.songs.writers.CsvWriter;

import java.util.List;

public class CsvReportGenerator implements ReportGenerator {
    private final CsvWriter csvWriter;

    public CsvReportGenerator(CsvWriter csvWriter) {
        this.csvWriter = csvWriter;
    }

    @Override
    public void generateReport(List<Song> songs) {
        csvWriter.createReport(songs);
    }
}
