package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.writers.JsonReportWriter;

import java.util.List;

import static org.mockito.Mockito.mock;
import static pl.wicherska.songs.TestSongFactory.alternativeSong3;

class JsonReportGeneratorTest {
    private final JsonReportWriter jsonReportWriter = mock(JsonReportWriter.class);
    private JsonReportGenerator jsonReportGenerator;

    @BeforeEach
    void setUp() {
        jsonReportGenerator = new JsonReportGenerator(jsonReportWriter);
    }

    @Test
    void verifyThatCreateReportWasCalled() {
        List<Song> songsToReport = List.of(alternativeSong3());

        jsonReportGenerator.generateReport(songsToReport);

        Mockito.verify(jsonReportWriter).createReport(songsToReport);
    }
}