package pl.wicherska.songs.services;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.util.ScannerWrapper;

import java.util.List;

public class UserSongSelectionService {
    private final SongService songService;
    private final ScannerWrapper scannerWrapper;

    public UserSongSelectionService(SongService songService, ScannerWrapper scannerWrapper) {
        this.songService = songService;
        this.scannerWrapper = scannerWrapper;
    }

    public Song selectSong() {
        List<Song> allSongs = songService.getSongsSortedByVotes();
        songService.printSongs(allSongs);
        System.out.println("Please provide number of song");
        int index = scannerWrapper.nextNonNegativeIntInRange(allSongs.size());
        return allSongs.get(index);
    }
}
