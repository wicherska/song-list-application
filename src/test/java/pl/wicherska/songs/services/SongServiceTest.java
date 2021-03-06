package pl.wicherska.songs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.repositories.AggregatingSongRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.wicherska.songs.TestSongFactory.*;

class SongServiceTest {
    private final AggregatingSongRepository aggregatingSongRepository = mock(AggregatingSongRepository.class);
    private SongService songService;
    private List<Song> songList;
    private List<Song> expectedSongList;

    @BeforeEach
    void setUp() {
        songService = new SongService(aggregatingSongRepository);
        //todo
        /**
         * Constructor 'ArrayList<>(List.of()) is not mistake. List should be mutable and initialised with values.
         */
        songList = new ArrayList<>(List.of(rockSong(), rockSong2(), rockSong3(), rockSong4(), alternativeSong(), alternativeSong2(),
                alternativeSong3(), alternativeSong4(), rAndBSong(), rAndBSong2(), rAndBSong3()));
        expectedSongList = new ArrayList<>(List.of(rockSong(), rockSong2(), rockSong3(), rockSong4(), alternativeSong(), alternativeSong2(),
                alternativeSong3(), alternativeSong4(), rAndBSong(), rAndBSong2(), rAndBSong3()));

    }

    @Test
    void shouldReturnSongsSortedByVotes() {
        when(aggregatingSongRepository.getSongs()).thenReturn(songList);

        List<Song> sortedByVotes = songService.getSongsSortedByVotes();
        expectedSongList.sort(Comparator.comparingInt(Song::getVotes).reversed());

        assertAll(
                () -> assertEquals(expectedSongList.size(), sortedByVotes.size()),
                () -> assertEquals(expectedSongList.get(0), sortedByVotes.get(0)),
                () -> assertEquals(expectedSongList.get(1), sortedByVotes.get(1)),
                () -> assertEquals(expectedSongList.get(2), sortedByVotes.get(2)),
                () -> assertEquals(expectedSongList.get(3), sortedByVotes.get(3)),
                () -> assertEquals(expectedSongList.get(4), sortedByVotes.get(4)),
                () -> assertEquals(expectedSongList.get(5), sortedByVotes.get(5)),
                () -> assertEquals(expectedSongList.get(6), sortedByVotes.get(6)),
                () -> assertEquals(expectedSongList.get(7), sortedByVotes.get(7)),
                () -> assertEquals(expectedSongList.get(8), sortedByVotes.get(8)),
                () -> assertEquals(expectedSongList.get(9), sortedByVotes.get(9)),
                () -> assertEquals(expectedSongList.get(10), sortedByVotes.get(10))
        );
    }

    @Test
    void shouldReturnSongsSortedByCategory() {
        when(aggregatingSongRepository.getSongs()).thenReturn(songList);

        List<Song> sortedByCategory = songService.getSongsSortedByCategory();
        Comparator<Song> comparator = Comparator.comparing(Song::getCategory);
        comparator = comparator.thenComparing(Comparator.comparingInt(Song::getVotes).reversed());
        expectedSongList.sort(comparator);

        assertAll(
                () -> assertEquals(expectedSongList.size(), sortedByCategory.size()),
                () -> assertEquals(expectedSongList.get(0), sortedByCategory.get(0)),
                () -> assertEquals(expectedSongList.get(1), sortedByCategory.get(1)),
                () -> assertEquals(expectedSongList.get(2), sortedByCategory.get(2)),
                () -> assertEquals(expectedSongList.get(3), sortedByCategory.get(3)),
                () -> assertEquals(expectedSongList.get(4), sortedByCategory.get(4)),
                () -> assertEquals(expectedSongList.get(5), sortedByCategory.get(5)),
                () -> assertEquals(expectedSongList.get(6), sortedByCategory.get(6)),
                () -> assertEquals(expectedSongList.get(7), sortedByCategory.get(7)),
                () -> assertEquals(expectedSongList.get(8), sortedByCategory.get(8)),
                () -> assertEquals(expectedSongList.get(9), sortedByCategory.get(9)),
                () -> assertEquals(expectedSongList.get(10), sortedByCategory.get(10))
        );
    }

    @Test
    void shouldResetVotesForAllSongs() {
        when(aggregatingSongRepository.getSongs()).thenReturn(songList);

        songService.resetVotesForAllSongs();

        assertAll(
                () -> assertEquals(0, songList.get(0).getVotes()),
                () -> assertEquals(0, songList.get(1).getVotes()),
                () -> assertEquals(0, songList.get(2).getVotes()),
                () -> assertEquals(0, songList.get(3).getVotes()),
                () -> assertEquals(0, songList.get(4).getVotes()),
                () -> assertEquals(0, songList.get(5).getVotes()),
                () -> assertEquals(0, songList.get(6).getVotes()),
                () -> assertEquals(0, songList.get(7).getVotes()),
                () -> assertEquals(0, songList.get(8).getVotes()),
                () -> assertEquals(0, songList.get(9).getVotes()),
                () -> assertEquals(0, songList.get(10).getVotes())
        );
    }

    @Test
    void shouldResetVotesForSong() {
        Song song = rockSong();

        songService.resetVotesForSong(song);

        assertEquals(0, song.getVotes());
    }

    @Test
    void shouldVoteForSong() {
        Song song = rockSong();
        int votes = song.getVotes();

        songService.voteForSong(song);

        assertEquals(votes + 1, song.getVotes());
    }

}