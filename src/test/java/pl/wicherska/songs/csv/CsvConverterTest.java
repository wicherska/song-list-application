package pl.wicherska.songs.csv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.wicherska.songs.SongAssert.assertThatSong;
import static pl.wicherska.songs.TestSongFactory.*;

class CsvConverterTest {
    CsvConverter csvConverter;

    @BeforeEach
    void setUp() {
        csvConverter = new CsvConverter();
    }

    @Test
    void shouldReturnListOfSongs() {
        List<String> stringList = List.of("Title,Author,Album,Category,Votes",
                "Living in a Ghost Town,The Rolling Stones,Honk,Rock,10",
                "You Should Be Sad,Halsey,Manic,Alternative,2",
                "Imported,Jessie Reyez,Before Love Came to Kill Us,R&B,6");
        List<Song> songList = csvConverter.mapDataSourceToListOfSongs(stringList);

        assertThat(songList).hasSize(3);
        Song song1 = songList.get(0);
        Song song2 = songList.get(1);
        Song song3 = songList.get(2);

        assertThatSong(song1)
                .hasTitle("Living in a Ghost Town")
                .hasAuthor("The Rolling Stones")
                .hasAlbum("Honk")
                .hasCategory(Category.ROCK)
                .hasVotes(10);
        assertThatSong(song2)
                .hasTitle("You Should Be Sad")
                .hasAuthor("Halsey")
                .hasAlbum("Manic")
                .hasCategory(Category.ALTERNATIVE)
                .hasVotes(2);
        assertThatSong(song3)
                .hasTitle("Imported")
                .hasAuthor("Jessie Reyez")
                .hasAlbum("Before Love Came to Kill Us")
                .hasCategory(Category.RANDB)
                .hasVotes(6);
    }

    @Test
    void shouldReturnListOfStrings() {
        List<Song> songList = List.of(rockSong(), alternativeSong(), rAndBSong());
        List<String> stringList = csvConverter.mapListOfSongsToDataSource(songList);

        assertThat(stringList).hasSize(4);

        assertAll(
                () -> assertEquals("Title,Author,Album,Category,Votes", stringList.get(0)),
                () -> assertEquals("Living in a Ghost Town,The Rolling Stones,Honk,Rock,10", stringList.get(1)),
                () -> assertEquals("You Should Be Sad,Halsey,Manic,Alternative,2", stringList.get(2)),
                () -> assertEquals("Imported,Jessie Reyez,Before Love Came to Kill Us,R&B,6", stringList.get(3))
        );


    }


}