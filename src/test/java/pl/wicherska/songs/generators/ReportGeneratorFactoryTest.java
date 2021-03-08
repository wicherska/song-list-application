package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.interfaces.ReportGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ReportGeneratorFactoryTest {
    private final ConsoleReportGenerator consoleReportGenerator = mock(ConsoleReportGenerator.class);
    private final CsvReportGenerator csvReportGenerator = mock(CsvReportGenerator.class);
    private final XmlReportGenerator xmlReportGenerator = mock(XmlReportGenerator.class);
    private final JsonReportGenerator jsonReportGenerator = mock(JsonReportGenerator.class);
    private ReportGeneratorFactory reportGeneratorFactory;

    @BeforeEach
    void setUp() {
        reportGeneratorFactory = new ReportGeneratorFactory(csvReportGenerator, xmlReportGenerator, jsonReportGenerator, consoleReportGenerator);
    }

    @Test
    void shouldReturnConsoleReportGenerator() {
        ReportFormat format = ReportFormat.CONSOLE;

        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(format);

        assertThat(reportGenerator).isSameAs(consoleReportGenerator);
    }

    @Test
    void shouldReturnCsvReportGenerator() {
        ReportFormat format = ReportFormat.CSV;

        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(format);

        assertThat(reportGenerator).isSameAs(csvReportGenerator);
    }

    @Test
    void shouldReturnXmlReportGenerator() {
        ReportFormat format = ReportFormat.XML;

        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(format);

        assertThat(reportGenerator).isSameAs(xmlReportGenerator);
    }

    @Test
    void shouldReturnJsonReportGenerator() {
        ReportFormat format = ReportFormat.JSON;

        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(format);

        assertThat(reportGenerator).isSameAs(jsonReportGenerator);
    }

}