package pl.wicherska.songs.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.wicherska.songs.domain.Song;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.wicherska.songs.TestSongFactory.rockSong;


class CsvConverterTest {
    private final String CSV_SERIALIZED_ROCK_SONG = "Living in a Ghost Town,The Rolling Stones,Honk,Rock,17";
    private CsvConverter csvConverter;

    @BeforeEach
    void setUp() {
        csvConverter = new CsvConverter();
    }

    @Test
    void shouldReturnStringFromSong() {
        Song song = rockSong();

        String fromSong = csvConverter.mapFromSong(song);

        assertEquals(CSV_SERIALIZED_ROCK_SONG, fromSong);
    }

    @Test
    void shouldReturnOptionalOfSongFromString() {
        Optional<Song> song = csvConverter.mapToSong(CSV_SERIALIZED_ROCK_SONG);

        assertEquals(Optional.of(rockSong()), song);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Living in a Ghost Town,Honk,Rock,17",
            "Living in a Ghost Town,The Rolling Stones,Honk,Pop,17",
            "Living in a Ghost Town,The Rolling Stones,,Rock,17",
            "Living in a Ghost Town,The Rolling Stones,Honk,Rock,lk",
            ""
    })
    void shouldReturnEmptyOptionalWhenIncorrectData(String line) {
        Optional<Song> song = csvConverter.mapToSong(line);

        assertEquals(Optional.empty(), song);
    }
}