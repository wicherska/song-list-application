package pl.wicherska.songs.handlers;

import pl.wicherska.songs.domain.ResetOption;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.UserActionHandler;
import pl.wicherska.songs.services.SongService;
import pl.wicherska.songs.services.UserSongSelectionService;
import pl.wicherska.songs.util.ScannerWrapper;


public class ResettingUserActionHandler implements UserActionHandler {
    private final ScannerWrapper scannerWrapper;
    private final SongService songService;
    private final UserSongSelectionService userSongSelectionService;

    public ResettingUserActionHandler(ScannerWrapper scannerWrapper, SongService songService, UserSongSelectionService userSongSelectionService) {
        this.scannerWrapper = scannerWrapper;
        this.songService = songService;
        this.userSongSelectionService = userSongSelectionService;
    }

    @Override
    public void handle() {
        ResetOption fromUser = scannerWrapper.nextEnum(ResetOption.class);
        if (fromUser == ResetOption.ONE) {
            resetChosen();
        } else if (fromUser == ResetOption.ALL) {
            resetAll();
        }
    }

    private void resetAll() {
        songService.resetVotesForAllSongs();
        System.out.println("All: ");
        songService.printSongs(songService.getAllSongs());
    }

    private void resetChosen() {
        Song chosenSong = userSongSelectionService.selectSong();
        songService.resetVotesForSong(chosenSong);
        System.out.println("Votes have been reset for song:  " + chosenSong);
    }
}
