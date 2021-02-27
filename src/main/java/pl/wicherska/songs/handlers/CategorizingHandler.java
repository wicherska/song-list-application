package pl.wicherska.songs.handlers;

import pl.wicherska.songs.domain.ReportFormat;
import pl.wicherska.songs.domain.ReportType;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.generators.ReportGeneratorFactory;
import pl.wicherska.songs.interfaces.Handler;
import pl.wicherska.songs.interfaces.ReportGenerator;
import pl.wicherska.songs.search.SearchEngine;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CategorizingHandler implements Handler {
    private final Scanner scanner;
    private final ReportGeneratorFactory reportGeneratorFactory;
    private final SearchEngine searchEngine;

    public CategorizingHandler(Scanner scanner, ReportGeneratorFactory reportGeneratorFactory, SearchEngine searchEngine) {
        this.scanner = scanner;
        this.reportGeneratorFactory = reportGeneratorFactory;
        this.searchEngine = searchEngine;
    }

    @Override
    public void handle() {
        ReportFormat reportFormat = getReportFormat();
        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(reportFormat);
        List<Song> songs = searchEngine.getSongsForReportType(ReportType.CATEGORY);
        reportGenerator.generateReport(songs);
    }

    private ReportFormat getReportFormat(){
        System.out.println("Please provide report format from: " + Arrays.toString(ReportFormat.values()));
        return ReportFormat.valueOf(scanner.nextLine().toUpperCase());
    }
}
