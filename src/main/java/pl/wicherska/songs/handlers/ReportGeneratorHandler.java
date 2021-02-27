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

public class ReportGeneratorHandler implements Handler {
    private final Scanner scanner;
    private final ReportGeneratorFactory reportGeneratorFactory;
    private final SearchEngine searchEngine;

    public ReportGeneratorHandler(Scanner scanner, ReportGeneratorFactory reportGeneratorFactory, SearchEngine searchEngine) {
        this.scanner = scanner;
        this.reportGeneratorFactory = reportGeneratorFactory;
        this.searchEngine = searchEngine;
    }

    @Override
    public void handle() {
        ReportFormat reportFormat = getReportFormat();
        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(reportFormat);
        ReportType reportType = getReportType();
        List<Song> songs = searchEngine.getSongsForReportType(reportType);
        reportGenerator.generateReport(songs);
    }

    private ReportFormat getReportFormat(){
        System.out.println("Please provide report format from: " + Arrays.toString(ReportFormat.values()));
        return ReportFormat.valueOf(scanner.nextLine().toUpperCase());
    }

    private ReportType getReportType(){
        System.out.println("Please provide report type from: " + Arrays.toString(ReportType.values()));
        return ReportType.valueOf(scanner.nextLine().toUpperCase());
    }
}
