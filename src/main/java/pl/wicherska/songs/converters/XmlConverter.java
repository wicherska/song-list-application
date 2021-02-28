package pl.wicherska.songs.converters;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.xml.SongXmlRepresentation;
import pl.wicherska.songs.xml.XmlCategory;

import java.util.Optional;

public class XmlConverter extends AbstractConverter<SongXmlRepresentation> {

    @Override
    protected SongXmlRepresentation mapFromSong(Song song) {
        SongXmlRepresentation songXmlRepresentation = new SongXmlRepresentation();
        songXmlRepresentation.setTitle(song.getTitle());
        songXmlRepresentation.setAuthor(song.getAuthor());
        songXmlRepresentation.setAlbum(song.getAlbum());
        songXmlRepresentation.setXmlCategory(XmlCategory.fromSong(song));
        songXmlRepresentation.setVotes(song.getVotes());
        return songXmlRepresentation;
    }

    @Override
    protected Optional<Song> mapToSong(SongXmlRepresentation songXml) {
        try {
            return Optional.of(new Song(
                    validateString(songXml.getTitle()),
                    validateString(songXml.getAuthor()),
                    validateString(songXml.getAlbum()),
                    validateCategory(songXml.getXmlCategory().toString()),
                    validateVotes(String.valueOf(songXml.getVotes()))));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Incorrect data: " + songXml.toString());
            return Optional.empty();
        }
    }
}

