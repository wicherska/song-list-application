package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.writers.CsvReportWriter;

import java.util.List;

import static org.mockito.Mockito.mock;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class CsvReportGeneratorTest {
    private final CsvReportWriter csvWriter = mock(CsvReportWriter.class);
    private CsvReportGenerator csvReportGenerator;

    @BeforeEach
    void setUp() {
        csvReportGenerator = new CsvReportGenerator(csvWriter);
    }

    @Test
    void verifyThatCreateReportWasCalled() {
        List<Song> songsToReport = List.of(rockSong());

        csvReportGenerator.generateReport(songsToReport);

        Mockito.verify(csvWriter).createReport(songsToReport);
    }


}