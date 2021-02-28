package pl.wicherska.songs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.UserAction;
import pl.wicherska.songs.handlers.SongAddingUserActionHandler;
import pl.wicherska.songs.interfaces.UserActionHandler;
import pl.wicherska.songs.util.ScannerWrapper;

import java.util.Map;

import static java.util.Map.entry;
import static org.mockito.Mockito.*;

class ApplicationRunnerTest {
    private final SongAddingUserActionHandler songAddingUserActionHandler = mock(SongAddingUserActionHandler.class);
    private final Map<UserAction, UserActionHandler> handlers = Map.ofEntries(
            entry(UserAction.ADD, songAddingUserActionHandler));
    private final ScannerWrapper scannerWrapper = mock(ScannerWrapper.class);
    private ApplicationRunner applicationRunner;

    @BeforeEach
    void setUp() {
        applicationRunner = new ApplicationRunner(handlers, scannerWrapper);
    }

    @Test
    void shouldExitLoop() {
        when(scannerWrapper.nextEnum(UserAction.class)).thenReturn(UserAction.ADD, UserAction.ADD);
        when(scannerWrapper.nextBoolean()).thenReturn(true, false);

        applicationRunner.run();

        Mockito.verify(songAddingUserActionHandler, times(2)).handle();
    }
}