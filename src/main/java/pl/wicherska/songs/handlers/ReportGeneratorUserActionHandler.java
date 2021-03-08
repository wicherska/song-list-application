package pl.wicherska.songs.handlers;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.generators.ReportFormat;
import pl.wicherska.songs.generators.ReportGeneratorFactory;
import pl.wicherska.songs.generators.ReportType;
import pl.wicherska.songs.interfaces.ReportGenerator;
import pl.wicherska.songs.interfaces.UserActionHandler;
import pl.wicherska.songs.search.SearchEngine;
import pl.wicherska.songs.util.ScannerWrapper;

import java.util.List;

public class ReportGeneratorUserActionHandler implements UserActionHandler {
    private final ScannerWrapper scannerWrapper;
    private final ReportGeneratorFactory reportGeneratorFactory;
    private final SearchEngine searchEngine;

    public ReportGeneratorUserActionHandler(ScannerWrapper scannerWrapper, ReportGeneratorFactory reportGeneratorFactory, SearchEngine searchEngine) {
        this.scannerWrapper = scannerWrapper;
        this.reportGeneratorFactory = reportGeneratorFactory;
        this.searchEngine = searchEngine;
    }

    @Override
    public void handle() {
        ReportFormat reportFormat = scannerWrapper.nextEnum(ReportFormat.class);
        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(reportFormat);
        ReportType reportType = scannerWrapper.nextEnum(ReportType.class);
        List<Song> songs = searchEngine.getSongsForReportType(reportType);
        reportGenerator.generateReport(songs);
    }
}
