package pl.wicherska.songs.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.json.JsonCategory;
import pl.wicherska.songs.json.SongJsonRepresentation;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.wicherska.songs.TestSongFactory.*;

class JsonConverterTest {
    private JsonConverter jsonConverter;

    @BeforeEach
    void setUp() {
        jsonConverter = new JsonConverter();
    }

    @Test
    void shouldReturnSongJsonRepresentationFromSong() {
        Song song = rAndBSong();

        SongJsonRepresentation songJsonRepresentation = jsonConverter.mapFromSong(song);

        assertAll(
                () -> assertEquals(song.getTitle(), songJsonRepresentation.getTitle()),
                () -> assertEquals(song.getAuthor(), songJsonRepresentation.getAuthor()),
                () -> assertEquals(song.getAlbum(), songJsonRepresentation.getAlbum()),
                () -> assertEquals(JsonCategory.fromSong(song), songJsonRepresentation.getCategory()),
                () -> assertEquals(song.getVotes(), songJsonRepresentation.getVotes())
        );
    }

    @Test
    void shouldReturnOptionalOfSong() {
        Song song = alternativeSong2();
        SongJsonRepresentation songJsonRepresentation = new SongJsonRepresentation(
                song.getTitle(),
                song.getAuthor(),
                song.getAlbum(),
                JsonCategory.fromSong(song),
                song.getVotes()
        );

        Optional<Song> optionalSong = jsonConverter.mapToSong(songJsonRepresentation);

        assertEquals(Optional.of(song), optionalSong);
    }

    @Test
    void shouldReturnEmptyOptionalWhenIncorrectData() {
        Song song = rockSong4();
        SongJsonRepresentation songJsonRepresentation = new SongJsonRepresentation(
                song.getTitle(),
                "",
                song.getAlbum(),
                JsonCategory.fromSong(song),
                song.getVotes()
        );

        Optional<Song> optionalSong = jsonConverter.mapToSong(songJsonRepresentation);

        assertEquals(Optional.empty(), optionalSong);
    }
}