package pl.wicherska.songs.domain;

import java.util.Objects;

public class Song {
    private final String title;
    private final String author;
    private final String album;
    private final Category category;
    private int votes;

    public Song(String title, String author, String album, Category category, int votes) {
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

    public Category getCategory() {
        return category;
    }

    public int getVotes() {
        return votes;
    }

    public void voteForSong(){ votes++; }

    public void setVotesToZero() {
        votes = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(author, song.author) && Objects.equals(album, song.album) && category == song.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, album, category);
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", album='" + album + '\'' +
                ", category=" + category +
                ", votes=" + votes +
                '}';
    }
}
