package pl.wicherska.songs.repositories;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.Repository;
import pl.wicherska.songs.xml.XmlConverter;
import pl.wicherska.songs.xml.XmlDataSource;


import java.util.List;

public class XmlSongRepository implements Repository {
    private final XmlConverter xmlConverter;
    private final XmlDataSource xmlDataSource;
    private final List<String> paths;

    public XmlSongRepository(XmlConverter xmlConverter, XmlDataSource xmlDataSource, List<String> paths) {
        this.xmlConverter = xmlConverter;
        this.xmlDataSource = xmlDataSource;
        this.paths = paths;
    }

    @Override
    public List<Song> getListOfSongs() {
        return xmlConverter.mapDataSourceToListOfSongs(xmlDataSource.readFromFiles(paths));
    }
}
