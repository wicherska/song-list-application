package pl.wicherska.songs.xml;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.interfaces.Converter;
import pl.wicherska.songs.domain.Song;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class XmlConverter implements Converter<SongXmlRepresentation> {

    @Override
    public List<Song> mapDataSourceToListOfSongs(List<SongXmlRepresentation> elements) {
        return elements.stream()
                .map(this::mapSongXmlRepresentationToSong)
                .collect(toList());
    }

    @Override
    public List<SongXmlRepresentation> mapListOfSongsToDataSource(List<Song> songList) {
        return songList.stream()
                .map(this::mapSongToSongXmlRepresentation)
                .collect(toList());
    }

    private Song mapSongXmlRepresentationToSong(SongXmlRepresentation songXml){
        return new Song(
                songXml.getTitle(),
                songXml.getAuthor(),
                songXml.getAlbum(),
                Category.fromString(songXml.getXmlCategory().toString()),
                songXml.getVotes());
    }

    private SongXmlRepresentation mapSongToSongXmlRepresentation(Song song){
        SongXmlRepresentation songXmlRepresentation = new SongXmlRepresentation();
        songXmlRepresentation.setTitle(song.getTitle());
        songXmlRepresentation.setAuthor(song.getAuthor());
        songXmlRepresentation.setAlbum(song.getAlbum());
        songXmlRepresentation.setXmlCategory(XmlCategory.fromString(song.getCategory().toString()));
        songXmlRepresentation.setVotes(song.getVotes());
        return songXmlRepresentation;
    }
}
