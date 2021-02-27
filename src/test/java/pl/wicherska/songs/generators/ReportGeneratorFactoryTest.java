package pl.wicherska.songs.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.ReportFormat;
import pl.wicherska.songs.interfaces.ReportGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ReportGeneratorFactoryTest {
    private ReportGeneratorFactory reportGeneratorFactory;
    private final ConsoleReportGenerator consoleReportGenerator = mock(ConsoleReportGenerator .class);
    private final CsvReportGenerator csvReportGenerator = mock(CsvReportGenerator.class);
    private final XmlReportGenerator xmlReportGenerator = mock(XmlReportGenerator.class);


    @BeforeEach
    void setUp(){
        reportGeneratorFactory = new ReportGeneratorFactory(csvReportGenerator, xmlReportGenerator, consoleReportGenerator);
    }

    @Test
    void shouldReturnConsoleReportGenerator(){
        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(ReportFormat.CONSOLE);
        assertThat(reportGenerator).isSameAs(consoleReportGenerator);
    }

    @Test
    void shouldReturnCsvReportGenerator(){
        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(ReportFormat.CSV);
        assertThat(reportGenerator).isSameAs(csvReportGenerator);
    }

    @Test
    void shouldReturnXmlReportGenerator(){
        ReportGenerator reportGenerator = reportGeneratorFactory.forFormat(ReportFormat.XML);
        assertThat(reportGenerator).isSameAs(xmlReportGenerator);
    }

}