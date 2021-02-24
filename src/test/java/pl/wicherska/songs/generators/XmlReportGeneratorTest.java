package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.writers.XmlWriter;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class XmlReportGeneratorTest {
    private XmlReportGenerator xmlReportGenerator;
    private final XmlWriter xmlWriter = mock(XmlWriter.class);

    @BeforeEach
    void setUp(){
        xmlReportGenerator = new XmlReportGenerator(xmlWriter);
    }

    @Test
    void verifyThatCreateReportWasCalled(){
        xmlReportGenerator.generateReport(List.of(rockSong()));
        Mockito.verify(xmlWriter, Mockito.times(1)).createReport(anyList());
    }

}