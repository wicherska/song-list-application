package pl.wicherska.songs.converters;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.json.JsonCategory;
import pl.wicherska.songs.json.SongJsonRepresentation;

import java.util.Optional;

public class JsonConverter extends AbstractConverter<SongJsonRepresentation> {

    @Override
    protected Optional<Song> mapToSong(SongJsonRepresentation songJson) {
        try {
            return Optional.of(new Song(
                    validateString(songJson.getTitle()),
                    validateString(songJson.getAuthor()),
                    validateString(songJson.getAlbum()),
                    validateCategory(songJson.getCategory().getCategoryName()),
                    validateVotes(String.valueOf(songJson.getVotes()))));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Incorrect data: " + songJson.toString());
            return Optional.empty();
        }
    }

    @Override
    protected SongJsonRepresentation mapFromSong(Song song) {
        return new SongJsonRepresentation(
                song.getTitle(),
                song.getAuthor(),
                song.getAlbum(),
                JsonCategory.fromSong(song),
                song.getVotes()
        );
    }
}
