package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.writers.CsvWriter;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class CsvReportGeneratorTest {
    private CsvReportGenerator csvReportGenerator;
    private final CsvWriter csvWriter = mock(CsvWriter.class);

    @BeforeEach
    void setUp(){
        csvReportGenerator = new CsvReportGenerator(csvWriter);
    }

    @Test
    void verifyThatCreateReportWasCalled(){
        csvReportGenerator.generateReport(List.of(rockSong()));
        Mockito.verify(csvWriter, Mockito.times(1)).createReport(anyList());
    }


}