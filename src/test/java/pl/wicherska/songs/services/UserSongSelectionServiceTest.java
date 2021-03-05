package pl.wicherska.songs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.util.ScannerWrapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.wicherska.songs.TestSongFactory.*;

class UserSongSelectionServiceTest {
    private static final int SELECTED_SONG_INDEX = 3;
    private final ScannerWrapper scannerWrapper = mock(ScannerWrapper.class);
    private final SongService songService = mock(SongService.class);
    private UserSongSelectionService userSongSelectionService;
    private List<Song> songList;

    @BeforeEach
    void setUp() {
        userSongSelectionService = new UserSongSelectionService(songService, scannerWrapper);
        songList = List.of(rockSong(), rockSong2(), rockSong3(), rockSong4(), alternativeSong(), alternativeSong2(),
                alternativeSong3(), alternativeSong4(), rAndBSong(), rAndBSong2(), rAndBSong3());
    }

    @Test
    void shouldReturnSelectedSong() {
        when(songService.getSongsSortedByVotes()).thenReturn(songList);
        when(scannerWrapper.nextNonNegativeIntInRange(songList.size())).thenReturn(SELECTED_SONG_INDEX);

        Song song = userSongSelectionService.selectSong();

        assertEquals(songList.get(SELECTED_SONG_INDEX), song);
    }

}