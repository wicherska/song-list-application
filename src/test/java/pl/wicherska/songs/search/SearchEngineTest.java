package pl.wicherska.songs.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.ReportType;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.services.SongService;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.wicherska.songs.TestSongFactory.*;

class SearchEngineTest {
    private SearchEngine searchEngine;
    private final SongService songService = mock(SongService.class);

    @BeforeEach
    void setUp(){
        searchEngine = new SearchEngine(songService);
    }

    @Test
    void shouldReturnListOf3Songs(){
        List<Song> songList = new LinkedList<>(List.of(rockSong(), rockSong2(), rockSong3(), rockSong4(), alternativeSong(), alternativeSong2(),
                alternativeSong3(), alternativeSong4(), rAndBSong(), rAndBSong2(), rAndBSong3()));

        when(songService.getSongSortedByVotes()).thenReturn(songList);

        List<Song> songs = searchEngine.getSongsForReportType(ReportType.TOP3);
        assertThat(songs).hasSize(3);
    }

    @Test
    void shouldReturnListOf10Songs(){
        List<Song> songList = new LinkedList<>(List.of(rockSong(), rockSong2(), rockSong3(), rockSong4(), alternativeSong(), alternativeSong2(),
                alternativeSong3(), alternativeSong4(), rAndBSong(), rAndBSong2(), rAndBSong3()));

        when(songService.getSongSortedByVotes()).thenReturn(songList);

        List<Song> songs = searchEngine.getSongsForReportType(ReportType.TOP10);
        assertThat(songs).hasSize(10);
    }

    @Test
    void shouldReturnAllSongs(){
        List<Song> songList = new LinkedList<>(List.of(rockSong(), rockSong2(), rockSong3(), rockSong4(), alternativeSong(), alternativeSong2(),
                alternativeSong3(), alternativeSong4(), rAndBSong(), rAndBSong2(), rAndBSong3()));

        when(songService.getAllSongs()).thenReturn(songList);

        List<Song> songs = searchEngine.getSongsForReportType(ReportType.ALL);
        assertThat(songs).hasSize(11);
    }

    @Test
    void shouldReturnAllSongsByCategory(){
        List<Song> songList = new LinkedList<>(List.of(rockSong(), rockSong2(), rockSong3(), rockSong4(), alternativeSong(), alternativeSong2(),
                alternativeSong3(), alternativeSong4(), rAndBSong(), rAndBSong2(), rAndBSong3()));

        when(songService.getSongSortedByCategory()).thenReturn(songList);

        List<Song> songs = searchEngine.getSongsForReportType(ReportType.CATEGORY);
        assertThat(songs).hasSize(11);
    }
}