package pl.wicherska.songs.generators;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportGenerator;
import pl.wicherska.songs.writers.ConsoleReportWriter;

import java.util.List;

public class ConsoleReportGenerator implements ReportGenerator {
    private final ConsoleReportWriter consoleWriter;

    public ConsoleReportGenerator(ConsoleReportWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
    }

    @Override
    public void generateReport(List<Song> songs) {
        consoleWriter.createReport(songs);
    }
}
