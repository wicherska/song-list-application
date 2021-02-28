package pl.wicherska.songs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.converters.CsvConverter;
import pl.wicherska.songs.core.Config;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.sources.CsvDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.wicherska.songs.TestSongFactory.alternativeSong2;
import static pl.wicherska.songs.TestSongFactory.rockSong2;

class CsvSongRepositoryTest {
    private final CsvConverter csvConverter = Config.getInstance().csvConverter();
    private final CsvDataSource csvDataSource = Config.getInstance().csvDataSource();
    private final List<String> paths = List.of("src/test/resources/test.csv");
    private CsvSongRepository csvSongRepository;

    @BeforeEach
    void setUp() {
        csvSongRepository = new CsvSongRepository(csvConverter, csvDataSource, paths);
    }

    @Test
    void shouldReturnListOfSongs() {
        List<Song> expectedSongs = List.of(rockSong2(), alternativeSong2());

        List<Song> songs = csvSongRepository.getSongs();

        assertAll(
                () -> assertEquals(expectedSongs.size(), songs.size()),
                () -> assertEquals(expectedSongs.get(0), songs.get(0)),
                () -> assertEquals(expectedSongs.get(1), songs.get(1))
        );
    }
}