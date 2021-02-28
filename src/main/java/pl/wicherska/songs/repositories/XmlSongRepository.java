package pl.wicherska.songs.repositories;

import pl.wicherska.songs.converters.XmlConverter;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.SongRepository;
import pl.wicherska.songs.sources.XmlDataSource;

import java.util.List;

public class XmlSongRepository implements SongRepository {
    private final XmlConverter xmlConverter;
    private final XmlDataSource xmlDataSource;
    private final List<String> paths;

    public XmlSongRepository(XmlConverter xmlConverter, XmlDataSource xmlDataSource, List<String> paths) {
        this.xmlConverter = xmlConverter;
        this.xmlDataSource = xmlDataSource;
        this.paths = paths;
    }

    @Override
    public List<Song> getSongs() {
        return xmlConverter.toSongs(xmlDataSource.readFromFiles(paths));
    }
}
