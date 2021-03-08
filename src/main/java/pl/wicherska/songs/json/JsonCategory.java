package pl.wicherska.songs.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import pl.wicherska.songs.domain.Song;

import java.util.stream.Stream;

public enum JsonCategory {
    ROCK("Rock"),
    ALTERNATIVE("Alternative"),
    RNB("R&B");

    private final String category;

    JsonCategory(String category) {
        this.category = category;
    }

    @JsonCreator
    public static JsonCategory fromString(String text) {
        return Stream.of(JsonCategory.values())
                .filter(cat -> cat.category.equalsIgnoreCase(text))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getCategoryName() {
        return category;
    }

    public static JsonCategory fromSong(Song song) {
        return JsonCategory.fromString(song.getCategory().getCategoryName());
    }
}
