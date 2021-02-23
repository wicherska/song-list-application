package pl.wicherska.songs.generators;

import pl.wicherska.songs.domain.ReportFormat;
import pl.wicherska.songs.interfaces.ReportGenerator;

public class ReportGeneratorFactory {
    private final CsvReportGenerator csvReportGenerator;
    private final XmlReportGenerator xmlReportGenerator;
    private final ConsoleReportGenerator consoleReportGenerator;

    public ReportGeneratorFactory(CsvReportGenerator csvReportGenerator, XmlReportGenerator xmlReportGenerator, ConsoleReportGenerator consoleReportGenerator) {
        this.csvReportGenerator = csvReportGenerator;
        this.xmlReportGenerator = xmlReportGenerator;
        this.consoleReportGenerator = consoleReportGenerator;
    }

    public ReportGenerator forFormat(ReportFormat format){
        switch (format){
            case CONSOLE:
                return consoleReportGenerator;
            case CSV:
                return csvReportGenerator;
            case XML:
                return xmlReportGenerator;
            default:
                throw new IllegalArgumentException("Not supported format: "+ format );
        }
    }
}
