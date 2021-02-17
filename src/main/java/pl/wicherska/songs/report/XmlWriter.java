package pl.wicherska.songs.report;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.Writer;
import pl.wicherska.songs.xml.XmlConverter;
import pl.wicherska.songs.xml.XmlDataSource;

import javax.xml.bind.JAXBException;
import java.util.List;

public class XmlWriter implements Writer {
    private final XmlConverter xmlConverter;
    private final XmlDataSource xmlDataSource;
    private final String path;

    public XmlWriter(XmlConverter xmlConverter, XmlDataSource xmlDataSource, String path) {
        this.xmlConverter = xmlConverter;
        this.xmlDataSource = xmlDataSource;
        this.path = path;
    }

    @Override
    public void createReport(List<Song> songList) {
        try{
            xmlDataSource.writeToFile(xmlConverter.mapListOfSongsToDataSource(songList), path);
        } catch(JAXBException e){
            System.out.println("Generating XML report failed");
        }
    }
}
