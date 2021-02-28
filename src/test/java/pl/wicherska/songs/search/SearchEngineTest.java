package pl.wicherska.songs.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.ReportType;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.services.SongService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.wicherska.songs.TestSongFactory.*;

class SearchEngineTest {
    private final SongService songService = mock(SongService.class);
    private SearchEngine searchEngine;
    private List<Song> songList;

    @BeforeEach
    void setUp() {
        searchEngine = new SearchEngine(songService);
        songList = List.of(rockSong(), rockSong2(), rockSong3(), rockSong4(), alternativeSong(), alternativeSong2(),
                alternativeSong3(), alternativeSong4(), rAndBSong(), rAndBSong2(), rAndBSong3());
    }

    @Test
    void shouldReturnListOf3Songs() {
        when(songService.getSongsSortedByVotes()).thenReturn(songList);

        List<Song> songs = searchEngine.getSongsForReportType(ReportType.TOP3);

        assertEquals(3, songs.size());
    }

    @Test
    void shouldReturnListOf10Songs() {
        when(songService.getSongsSortedByVotes()).thenReturn(songList);

        List<Song> songs = searchEngine.getSongsForReportType(ReportType.TOP10);

        assertEquals(10, songs.size());
    }

    @Test
    void shouldReturnAllSongs() {
        when(songService.getSongsSortedByVotes()).thenReturn(songList);

        List<Song> songs = searchEngine.getSongsForReportType(ReportType.ALL);

        assertEquals(songList.size(), songs.size());
    }

    @Test
    void shouldReturnAllSongsByCategory() {
        when(songService.getSongsSortedByCategory()).thenReturn(songList);

        List<Song> songs = searchEngine.getSongsForReportType(ReportType.CATEGORY);

        assertEquals(songList.size(), songs.size());
    }
}