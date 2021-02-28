package pl.wicherska.songs.writers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.services.SongService;

import java.util.List;

import static org.mockito.Mockito.mock;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class ConsoleReportWriterTest {
    private final SongService songService = mock(SongService.class);
    private ConsoleReportWriter consoleReportWriter;


    @BeforeEach
    void setUp() {
        consoleReportWriter = new ConsoleReportWriter(songService);
    }

    @Test
    void verifyThatPrintSongsWasCalled() {
        List<Song> songsToReport = List.of(rockSong());

        consoleReportWriter.createReport(songsToReport);

        Mockito.verify(songService).printSongs(songsToReport);
    }
}