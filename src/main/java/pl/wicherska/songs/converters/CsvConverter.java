package pl.wicherska.songs.converters;

import pl.wicherska.songs.domain.Song;

import java.util.Optional;

public class CsvConverter extends AbstractConverter<String> {
    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_AUTHOR = 1;
    private static final int INDEX_OF_ALBUM = 2;
    private static final int INDEX_OF_CATEGORY = 3;
    private static final int INDEX_OF_VOTES = 4;
    private static final int NUMBER_OF_EXPECTED_FIELDS = 5;
    private static final String DELIMITER = ",";

    @Override
    protected String mapFromSong(Song song) {
        return String.join(DELIMITER,
                song.getTitle(),
                song.getAuthor(),
                song.getAlbum(),
                song.getCategory().getCategoryName(),
                String.valueOf(song.getVotes()));
    }

    @Override
    protected Optional<Song> mapToSong(String line) {
        try {
            String[] splitSongData = line.split(DELIMITER);
            checkNumberOfFields(splitSongData);
            Song song = new Song(
                    validateString(splitSongData[INDEX_OF_TITLE]),
                    validateString(splitSongData[INDEX_OF_AUTHOR]),
                    validateString(splitSongData[INDEX_OF_ALBUM]),
                    validateCategory(splitSongData[INDEX_OF_CATEGORY]),
                    validateVotes(splitSongData[INDEX_OF_VOTES]));
            return Optional.of(song);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Incorrect data: " + line);
            return Optional.empty();
        }
    }

    private void checkNumberOfFields(String[] splitDataSong) throws IllegalArgumentException {
        if (splitDataSong.length != NUMBER_OF_EXPECTED_FIELDS) {
            throw new IllegalArgumentException();
        }
    }
}
