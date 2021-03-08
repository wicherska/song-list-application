package pl.wicherska.songs.generators;

import pl.wicherska.songs.interfaces.ReportGenerator;

public class ReportGeneratorFactory {
    private final CsvReportGenerator csvReportGenerator;
    private final XmlReportGenerator xmlReportGenerator;
    private final JsonReportGenerator jsonReportGenerator;
    private final ConsoleReportGenerator consoleReportGenerator;

    public ReportGeneratorFactory(CsvReportGenerator csvReportGenerator, XmlReportGenerator xmlReportGenerator, JsonReportGenerator jsonReportGenerator, ConsoleReportGenerator consoleReportGenerator) {
        this.csvReportGenerator = csvReportGenerator;
        this.xmlReportGenerator = xmlReportGenerator;
        this.jsonReportGenerator = jsonReportGenerator;
        this.consoleReportGenerator = consoleReportGenerator;
    }

    public ReportGenerator forFormat(ReportFormat format) {
        switch (format) {
            case CONSOLE:
                return consoleReportGenerator;
            case CSV:
                return csvReportGenerator;
            case XML:
                return xmlReportGenerator;
            case JSON:
                return jsonReportGenerator;
            default:
                throw new IllegalArgumentException("Not supported format: " + format);
        }
    }
}
