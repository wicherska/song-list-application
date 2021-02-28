package pl.wicherska.songs.writers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.converters.XmlConverter;
import pl.wicherska.songs.sources.XmlDataSource;

import javax.xml.bind.JAXBException;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class XmlReportWriterTest {
    private final XmlConverter xmlConverter = mock(XmlConverter.class);
    private final XmlDataSource xmlDataSource = mock(XmlDataSource.class);
    private final String path = "test";
    private XmlReportWriter xmlReportWriter;


    @BeforeEach
    void setUp() {
        xmlReportWriter = new XmlReportWriter(xmlConverter, xmlDataSource, path);
    }

    @Test
    void shouldCalledXmlDataSource() throws JAXBException {
        when(xmlConverter.fromSongs(Collections.emptyList())).thenReturn(Collections.emptyList());

        xmlReportWriter.createReport(Collections.emptyList());

        Mockito.verify(xmlDataSource).writeToFile(Collections.emptyList(), path);
    }
}