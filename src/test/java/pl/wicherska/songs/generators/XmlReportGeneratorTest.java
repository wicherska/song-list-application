package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.writers.XmlReportWriter;

import java.util.List;

import static org.mockito.Mockito.mock;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class XmlReportGeneratorTest {
    private final XmlReportWriter xmlReportWriter = mock(XmlReportWriter.class);
    private XmlReportGenerator xmlReportGenerator;

    @BeforeEach
    void setUp() {
        xmlReportGenerator = new XmlReportGenerator(xmlReportWriter);
    }

    @Test
    void verifyThatCreateReportWasCalled() {
        List<Song> songsToReport = List.of(rockSong());

        xmlReportGenerator.generateReport(songsToReport);

        Mockito.verify(xmlReportWriter).createReport(songsToReport);
    }

}