package pl.wicherska.songs.writers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.converters.JsonConverter;
import pl.wicherska.songs.sources.JsonDataSource;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonReportWriterTest {
    private final JsonConverter jsonConverter = mock(JsonConverter.class);
    private final JsonDataSource jsonDataSource = mock(JsonDataSource.class);
    private final String path = "test";
    private JsonReportWriter jsonReportWriter;

    @BeforeEach
    void setUp() {
        jsonReportWriter = new JsonReportWriter(jsonConverter, jsonDataSource, path);
    }

    @Test
    void shouldCalledXmlDataSource() throws IOException {
        when(jsonConverter.fromSongs(Collections.emptyList())).thenReturn(Collections.emptyList());

        jsonReportWriter.createReport(Collections.emptyList());

        Mockito.verify(jsonDataSource).writeToFile(Collections.emptyList(), path);
    }
}