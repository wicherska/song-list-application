package pl.wicherska.songs.writers;

import pl.wicherska.songs.converters.XmlConverter;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportWriter;
import pl.wicherska.songs.sources.XmlDataSource;

import javax.xml.bind.JAXBException;
import java.util.List;

public class XmlReportWriter implements ReportWriter {
    private final XmlConverter xmlConverter;
    private final XmlDataSource xmlDataSource;
    private final String path;

    public XmlReportWriter(XmlConverter xmlConverter, XmlDataSource xmlDataSource, String path) {
        this.xmlConverter = xmlConverter;
        this.xmlDataSource = xmlDataSource;
        this.path = path;
    }

    @Override
    public void createReport(List<Song> songList) {
        try {
            xmlDataSource.writeToFile(xmlConverter.fromSongs(songList), path);
            System.out.println("Report created. File: " + path);
        } catch (JAXBException e) {
            System.out.println("Generating XML report failed");
        }
    }
}
