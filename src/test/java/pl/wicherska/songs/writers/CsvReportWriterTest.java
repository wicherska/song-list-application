package pl.wicherska.songs.writers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.converters.CsvConverter;
import pl.wicherska.songs.sources.CsvDataSource;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CsvReportWriterTest {
    private final CsvConverter csvConverter = mock(CsvConverter.class);
    private final CsvDataSource csvDataSource = mock(CsvDataSource.class);
    private final String path = "test";
    private CsvReportWriter csvReportWriter;


    @BeforeEach
    void setUp() {
        csvReportWriter = new CsvReportWriter(csvConverter, csvDataSource, path);
    }

    @Test
    void shouldCalledCsvDataSource() throws IOException {
        when(csvConverter.fromSongs(Collections.emptyList())).thenReturn(Collections.emptyList());

        csvReportWriter.createReport(Collections.emptyList());

        Mockito.verify(csvDataSource).writeToFile(Collections.emptyList(), path);
    }

}