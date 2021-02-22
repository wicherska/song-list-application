package pl.wicherska.songs;

import org.assertj.core.api.AbstractAssert;
import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;

import static org.assertj.core.api.Assertions.assertThat;

public class SongAssert  extends AbstractAssert<SongAssert, Song> {

    private SongAssert(Song song) {
        super(song, SongAssert.class);
    }

    public static SongAssert assertThatSong(Song song){return new SongAssert(song);}

    public SongAssert hasTitle(String title){
        assertThat(actual.getTitle()).isEqualTo(title);
        return this;
    }

    public SongAssert hasAuthor(String author){
        assertThat(actual.getAuthor()).isEqualTo(author);
        return this;
    }

    public SongAssert hasAlbum(String album){
        assertThat(actual.getAlbum()).isEqualTo(album);
        return this;
    }

    public SongAssert hasCategory(Category category){
        assertThat(actual.getCategory()).isEqualTo(category);
        return this;
    }

    public SongAssert hasVotes(int votes){
        assertThat(actual.getVotes()).isEqualTo(votes);
        return this;
    }

}
