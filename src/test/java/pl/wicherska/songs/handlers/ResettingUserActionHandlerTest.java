package pl.wicherska.songs.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.ResetOption;
import pl.wicherska.songs.services.SongService;
import pl.wicherska.songs.services.UserSongSelectionService;
import pl.wicherska.songs.util.ScannerWrapper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class ResettingUserActionHandlerTest {
    private final ScannerWrapper scannerWrapper = mock(ScannerWrapper.class);
    private final SongService songService = mock(SongService.class);
    private final UserSongSelectionService userSongSelectionService = mock(UserSongSelectionService.class);
    private ResettingUserActionHandler resettingUserActionHandler;

    @BeforeEach
    void setUp() {
        resettingUserActionHandler = new ResettingUserActionHandler(scannerWrapper, songService, userSongSelectionService);
    }

    @Test
    void shouldHandleResettingVotesForAllSongs() {
        when(scannerWrapper.nextEnum(ResetOption.class)).thenReturn(ResetOption.ALL);

        resettingUserActionHandler.handle();

        Mockito.verify(songService).resetVotesForAllSongs();
    }

    @Test
    void shouldHandleResettingVotesForChosenSong() {
        when(scannerWrapper.nextEnum(ResetOption.class)).thenReturn(ResetOption.ONE);
        when(userSongSelectionService.selectSong()).thenReturn(rockSong());

        resettingUserActionHandler.handle();

        Mockito.verify(songService).resetVotesForSong(rockSong());
    }
}