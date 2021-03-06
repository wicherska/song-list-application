package pl.wicherska.songs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.SongRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.wicherska.songs.TestSongFactory.*;

class AggregatingSongRepositoryTest {
    private final SongRepository songRepository = mock(SongRepository.class);
    private AggregatingSongRepository aggregatingSongRepository;

    @BeforeEach
    void setUp() {
        //todo
        /**
         * Mock's configuration is placed in setUp method because mock is called here.
         */
        when(songRepository.getSongs()).thenReturn(List.of(rockSong4()));
        when(songRepository.getSongs()).thenReturn(List.of(rockSong4(), rAndBSong2()));
        aggregatingSongRepository = new AggregatingSongRepository(List.of(songRepository, songRepository));
    }

    @Test
    void shouldReturnListOfSongs() {
        Song songWithAddedVotes = new Song("Father To Son", "Queen", "Queen II", Category.ROCK, 42);
        List<Song> expectedSongs = List.of(rAndBSong2(), songWithAddedVotes);

        List<Song> songs = aggregatingSongRepository.getSongs();

        assertAll(
                () -> assertEquals(expectedSongs.size(), songs.size()),
                () -> assertTrue(songs.contains(songWithAddedVotes)),
                () -> assertTrue(songs.contains(rAndBSong2()))
        );
    }

    @Test
    void shouldAddSongToSongList() {
        List<Song> expectedSongs = List.of(rockSong4(), rAndBSong2(), alternativeSong2());

        aggregatingSongRepository.addSong(alternativeSong2());

        List<Song> songs = aggregatingSongRepository.getSongs();
        assertAll(
                () -> assertEquals(expectedSongs.size(), songs.size()),
                () -> assertTrue(songs.contains(alternativeSong2()))
        );
    }


}