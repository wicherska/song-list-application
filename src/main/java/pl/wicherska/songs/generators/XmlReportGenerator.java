package pl.wicherska.songs.generators;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportGenerator;
import pl.wicherska.songs.writers.XmlReportWriter;

import java.util.List;

public class XmlReportGenerator implements ReportGenerator {
    private final XmlReportWriter xmlReportWriter;

    public XmlReportGenerator(XmlReportWriter xmlReportWriter) {
        this.xmlReportWriter = xmlReportWriter;
    }

    @Override
    public void generateReport(List<Song> songs) {
        xmlReportWriter.createReport(songs);
    }
}
