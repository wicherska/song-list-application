package pl.wicherska.songs.handlers;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.UserActionHandler;
import pl.wicherska.songs.repositories.AggregatingSongRepository;
import pl.wicherska.songs.util.ScannerWrapper;

public class SongAddingUserActionHandler implements UserActionHandler {
    private final ScannerWrapper scannerWrapper;
    private final AggregatingSongRepository aggregatingSongRepository;

    public SongAddingUserActionHandler(ScannerWrapper scannerWrapper, AggregatingSongRepository aggregatingSongRepository) {
        this.scannerWrapper = scannerWrapper;
        this.aggregatingSongRepository = aggregatingSongRepository;
    }

    @Override
    public void handle() {
        Song songFromUser;
        do {
            songFromUser = readSongFromUser();
            System.out.println(songFromUser.toString() + " Is the song correct?");
        }
        while (!scannerWrapper.nextBoolean());
        if (aggregatingSongRepository.doesNotContainSong(songFromUser)) {
            aggregatingSongRepository.addSong(songFromUser);
            System.out.println("Song has been added: " + songFromUser.toString());
        } else {
            System.out.println("The song already exists in database");
        }
    }

    private Song readSongFromUser() {
        String title = scannerWrapper.nextLineWithMessage("Please provide title");
        String author = scannerWrapper.nextLineWithMessage("Please provide author");
        String album = scannerWrapper.nextLineWithMessage("Please provide album");
        Category category = scannerWrapper.nextEnum(Category.class);
        int votes = scannerWrapper.nextNonNegativeIntWithMessage("Please provide initial number of votes");
        return new Song(title, author, album, category, votes);
    }
}
