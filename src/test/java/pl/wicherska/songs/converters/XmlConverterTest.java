package pl.wicherska.songs.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.xml.SongXmlRepresentation;
import pl.wicherska.songs.xml.XmlCategory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.wicherska.songs.TestSongFactory.alternativeSong;
import static pl.wicherska.songs.TestSongFactory.rAndBSong;

class XmlConverterTest {
    private XmlConverter xmlConverter;

    @BeforeEach
    void setUp() {
        xmlConverter = new XmlConverter();
    }

    @Test
    void shouldReturnSongXmlRepresentationFromSong() {
        Song song = alternativeSong();

        SongXmlRepresentation songXmlRepresentation = xmlConverter.mapFromSong(song);
        assertAll(
                () -> assertEquals(song.getTitle(), songXmlRepresentation.getTitle()),
                () -> assertEquals(song.getAuthor(), songXmlRepresentation.getAuthor()),
                () -> assertEquals(song.getAlbum(), songXmlRepresentation.getAlbum()),
                () -> assertEquals(XmlCategory.fromSong(song), songXmlRepresentation.getXmlCategory()),
                () -> assertEquals(song.getVotes(), songXmlRepresentation.getVotes())
        );
    }

    @Test
    void shouldReturnOptionalOfSong() {
        Song song = rAndBSong();
        SongXmlRepresentation songXmlRepresentation = new SongXmlRepresentation();
        songXmlRepresentation.setTitle(song.getTitle());
        songXmlRepresentation.setAuthor(song.getAuthor());
        songXmlRepresentation.setAlbum(song.getAlbum());
        songXmlRepresentation.setXmlCategory(XmlCategory.fromSong(song));
        songXmlRepresentation.setVotes(song.getVotes());

        Optional<Song> optionalSong = xmlConverter.mapToSong(songXmlRepresentation);

        assertEquals(Optional.of(song), optionalSong);
    }

    @Test
    void shouldReturnEmptyOptionalWhenIncorrectData() {
        Song song = rAndBSong();
        SongXmlRepresentation songXmlRepresentation = new SongXmlRepresentation();
        songXmlRepresentation.setTitle(song.getTitle());
        songXmlRepresentation.setAuthor(song.getAuthor());
        songXmlRepresentation.setAlbum("");
        songXmlRepresentation.setXmlCategory(XmlCategory.fromSong(song));
        songXmlRepresentation.setVotes(song.getVotes());

        Optional<Song> optionalSong = xmlConverter.mapToSong(songXmlRepresentation);

        assertEquals(Optional.empty(), optionalSong);
    }
}