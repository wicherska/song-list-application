package pl.wicherska.songs.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SongJsonRepresentation {
    private final String title;
    private final String author;
    private final String album;
    private final JsonCategory category;
    private final int votes;

    @JsonCreator
    public SongJsonRepresentation(@JsonProperty("title") String title,
                                  @JsonProperty("author") String author,
                                  @JsonProperty("album") String album,
                                  @JsonProperty("category") JsonCategory category,
                                  @JsonProperty("votes") int votes) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAlbum() {
        return album;
    }

    public JsonCategory getCategory() {
        return category;
    }

    public int getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + album + ", " + category + ", " + votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongJsonRepresentation that = (SongJsonRepresentation) o;
        return votes == that.votes && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(album, that.album) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, album, category, votes);
    }
}
