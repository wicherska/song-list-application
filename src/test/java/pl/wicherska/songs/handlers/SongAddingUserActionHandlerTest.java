package pl.wicherska.songs.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.repositories.AggregatingSongRepository;
import pl.wicherska.songs.util.ScannerWrapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static pl.wicherska.songs.TestSongFactory.rockSong3;

class SongAddingUserActionHandlerTest {
    private final ScannerWrapper scannerWrapper = mock(ScannerWrapper.class);
    private final AggregatingSongRepository aggregatingSongRepository = mock(AggregatingSongRepository.class);
    private final String GET_TITLE_MESSAGE = "Please provide title";
    private final String GET_AUTHOR_MESSAGE = "Please provide author";
    private final String GET_ALBUM_MESSAGE = "Please provide album";
    private final String GET_VOTES_MESSAGE = "Please provide initial number of votes";
    private SongAddingUserActionHandler songAddingUserActionHandler;

    @BeforeEach
    void setUp() {
        songAddingUserActionHandler = new SongAddingUserActionHandler(scannerWrapper, aggregatingSongRepository);
    }

    @Test
    void shouldHandleAddingAlreadyExistingSongFromUser() {
        when(scannerWrapper.nextLineWithMessage(GET_TITLE_MESSAGE)).thenReturn(rockSong3().getTitle());
        when(scannerWrapper.nextLineWithMessage(GET_AUTHOR_MESSAGE)).thenReturn(rockSong3().getAuthor());
        when(scannerWrapper.nextLineWithMessage(GET_ALBUM_MESSAGE)).thenReturn(rockSong3().getAlbum());
        when(scannerWrapper.nextEnum(Category.class)).thenReturn(rockSong3().getCategory());
        when(scannerWrapper.nextNonNegativeIntWithMessage(GET_VOTES_MESSAGE)).thenReturn(rockSong3().getVotes());
        when(scannerWrapper.nextBoolean()).thenReturn(true);
        when(aggregatingSongRepository.doesNotContainSong(rockSong3())).thenReturn(false);

        songAddingUserActionHandler.handle();

        Mockito.verify(aggregatingSongRepository, never()).addSong(any());
    }

    @Test
    void shouldHandleAddingSongFromUser() {
        when(scannerWrapper.nextLineWithMessage(GET_TITLE_MESSAGE)).thenReturn(rockSong3().getTitle());
        when(scannerWrapper.nextLineWithMessage(GET_AUTHOR_MESSAGE)).thenReturn(rockSong3().getAuthor());
        when(scannerWrapper.nextLineWithMessage(GET_ALBUM_MESSAGE)).thenReturn(rockSong3().getAlbum());
        when(scannerWrapper.nextEnum(Category.class)).thenReturn(rockSong3().getCategory());
        when(scannerWrapper.nextNonNegativeIntWithMessage(GET_VOTES_MESSAGE)).thenReturn(rockSong3().getVotes());
        when(scannerWrapper.nextBoolean()).thenReturn(true);
        when(aggregatingSongRepository.doesNotContainSong(rockSong3())).thenReturn(true);

        songAddingUserActionHandler.handle();

        Mockito.verify(aggregatingSongRepository).addSong(rockSong3());
    }
}