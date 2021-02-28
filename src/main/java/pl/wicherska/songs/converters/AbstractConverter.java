package pl.wicherska.songs.converters;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public abstract class AbstractConverter<T> {

    protected abstract Optional<Song> mapToSong(T data);

    protected abstract T mapFromSong(Song song);

    public List<T> fromSongs(List<Song> songList) {
        return songList.stream()
                .map(this::mapFromSong)
                .collect(toList());
    }

    public List<Song> toSongs(List<T> elements) {
        return elements.stream()
                .map(this::mapToSong)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    protected String validateString(String field) throws IllegalArgumentException, NullPointerException {
        if (field.isBlank()) {
            throw new IllegalArgumentException();
        }
        return field.trim();
    }

    protected Category validateCategory(String field) throws IllegalArgumentException, NullPointerException {
        Category category = Category.fromString(field.trim());
        if (category == null) {
            throw new IllegalArgumentException();
        }
        return category;
    }

    protected int validateVotes(String field) throws IllegalArgumentException {
        int number = Integer.parseInt(field.trim());
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        return number;
    }
}
