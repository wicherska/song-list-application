package pl.wicherska.songs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.converters.JsonConverter;
import pl.wicherska.songs.core.Config;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.sources.JsonDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class JsonSongRepositoryTest {
    private final JsonConverter jsonConverter = Config.getInstance().jsonConverter();
    private final JsonDataSource jsonDataSource = Config.getInstance().jsonDataSource();
    private final List<String> paths = List.of("src/test/resources/test.json");
    private JsonSongRepository jsonSongRepository;

    @BeforeEach
    void setUp() {
        jsonSongRepository = new JsonSongRepository(jsonConverter, jsonDataSource, paths);
    }

    @Test
    void shouldReturnListOfSongs() {
        List<Song> expectedSong = List.of(rockSong());

        List<Song> songs = jsonSongRepository.getSongs();

        assertAll(
                () -> assertEquals(expectedSong.size(), songs.size()),
                () -> assertEquals(expectedSong.get(0), songs.get(0))
        );
    }
}