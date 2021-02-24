package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.writers.ConsoleWriter;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class ConsoleReportGeneratorTest {
    private ConsoleReportGenerator consoleReportGenerator;
    private ConsoleWriter consoleWriter = mock(ConsoleWriter.class);

    @BeforeEach
    void setUp(){
        consoleReportGenerator = new ConsoleReportGenerator(consoleWriter);
    }

    @Test
    void verifyThatCreateReportWasCalled(){
        consoleReportGenerator.generateReport(List.of(rockSong()));
        Mockito.verify(consoleWriter, Mockito.times(1)).createReport(anyList());
    }

}