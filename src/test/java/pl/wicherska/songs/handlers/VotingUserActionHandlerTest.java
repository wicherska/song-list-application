package pl.wicherska.songs.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.services.SongService;
import pl.wicherska.songs.services.UserSongSelectionService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class VotingUserActionHandlerTest {
    private final SongService songService = mock(SongService.class);
    private final UserSongSelectionService userSongSelectionService = mock(UserSongSelectionService.class);
    private VotingUserActionHandler votingUserActionHandler;

    @BeforeEach
    void setUp() {
        votingUserActionHandler = new VotingUserActionHandler(songService, userSongSelectionService);
    }

    @Test
    void shouldHandleVotingForChosenSong() {
        when(userSongSelectionService.selectSong()).thenReturn(rockSong());

        votingUserActionHandler.handle();

        Mockito.verify(songService).voteForSong(rockSong());
    }
}