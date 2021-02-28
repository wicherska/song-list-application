package pl.wicherska.songs.handlers;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.UserActionHandler;
import pl.wicherska.songs.services.SongService;
import pl.wicherska.songs.services.UserSongSelectionService;


public class VotingUserActionHandler implements UserActionHandler {
    private final SongService songService;
    private final UserSongSelectionService userSongSelectionService;

    public VotingUserActionHandler(SongService songService, UserSongSelectionService userSongSelectionService) {
        this.songService = songService;
        this.userSongSelectionService = userSongSelectionService;
    }

    @Override
    public void handle() {
        voteForChosen();
    }

    private void voteForChosen() {
        Song chosenSong = userSongSelectionService.selectSong();
        songService.voteForSong(chosenSong);
        System.out.println("You have voted on song: " + chosenSong);
    }
}
