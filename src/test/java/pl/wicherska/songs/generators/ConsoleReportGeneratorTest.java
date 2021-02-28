package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.writers.ConsoleReportWriter;

import java.util.List;

import static org.mockito.Mockito.mock;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class ConsoleReportGeneratorTest {
    private final ConsoleReportWriter consoleWriter = mock(ConsoleReportWriter.class);
    private ConsoleReportGenerator consoleReportGenerator;

    @BeforeEach
    void setUp() {
        consoleReportGenerator = new ConsoleReportGenerator(consoleWriter);
    }

    @Test
    void verifyThatCreateReportWasCalled() {
        List<Song> songsToReport = List.of(rockSong());

        consoleReportGenerator.generateReport(songsToReport);

        Mockito.verify(consoleWriter).createReport(songsToReport);
    }

}