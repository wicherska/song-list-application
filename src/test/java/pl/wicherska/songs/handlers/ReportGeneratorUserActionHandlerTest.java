package pl.wicherska.songs.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.ReportFormat;
import pl.wicherska.songs.domain.ReportType;
import pl.wicherska.songs.generators.ReportGeneratorFactory;
import pl.wicherska.songs.generators.XmlReportGenerator;
import pl.wicherska.songs.search.SearchEngine;
import pl.wicherska.songs.util.ScannerWrapper;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class ReportGeneratorUserActionHandlerTest {
    private final ScannerWrapper scannerWrapper = mock(ScannerWrapper.class);
    private final ReportGeneratorFactory reportGeneratorFactory = mock(ReportGeneratorFactory.class);
    private final SearchEngine searchEngine = mock(SearchEngine.class);
    private final XmlReportGenerator xmlReportGenerator = mock(XmlReportGenerator.class);
    private ReportGeneratorUserActionHandler reportGeneratorUserActionHandler;

    @BeforeEach
    void setUp() {
        reportGeneratorUserActionHandler = new ReportGeneratorUserActionHandler(scannerWrapper, reportGeneratorFactory, searchEngine);
    }

    @Test
    void shouldHandleGeneratingReportForUser() {
        when(scannerWrapper.nextEnum(ReportFormat.class)).thenReturn(ReportFormat.XML);
        when(reportGeneratorFactory.forFormat(ReportFormat.XML)).thenReturn(xmlReportGenerator);
        when(scannerWrapper.nextEnum(ReportType.class)).thenReturn(ReportType.ALL);
        when(searchEngine.getSongsForReportType(ReportType.ALL)).thenReturn(List.of(rockSong()));

        reportGeneratorUserActionHandler.handle();

        Mockito.verify(xmlReportGenerator).generateReport(List.of(rockSong()));
    }
}