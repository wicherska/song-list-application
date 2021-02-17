package pl.wicherska.songs.repositories;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.Repository;
import pl.wicherska.songs.xml.XmlConverter;
import pl.wicherska.songs.xml.XmlDataSource;

import javax.xml.bind.JAXBException;
import java.util.LinkedList;
import java.util.List;

public class XmlSongRepository implements Repository {
    private final XmlConverter xmlConverter;
    private final XmlDataSource xmlDataSource;
    private final String path;

    public XmlSongRepository(XmlConverter xmlConverter, XmlDataSource xmlDataSource, String path) {
        this.xmlConverter = xmlConverter;
        this.xmlDataSource = xmlDataSource;
        this.path = path;
    }

    @Override
    public List<Song> getListOfSongs(){
        try{
            return xmlConverter.mapDataSourceToListOfSongs(xmlDataSource.readFromFile(path));
        } catch (JAXBException e){
            System.out.println("Problem with xml file");
            return new LinkedList<>();
        }
    }
}
