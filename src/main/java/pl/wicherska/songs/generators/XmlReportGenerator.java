package pl.wicherska.songs.generators;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportGenerator;
import pl.wicherska.songs.writers.XmlWriter;

import java.util.List;

public class XmlReportGenerator implements ReportGenerator {
    private final XmlWriter xmlWriter;

    public XmlReportGenerator(XmlWriter xmlWriter) {
        this.xmlWriter = xmlWriter;
    }

    @Override
    public void generateReport(List<Song> songs) {
        xmlWriter.createReport(songs);
    }
}
