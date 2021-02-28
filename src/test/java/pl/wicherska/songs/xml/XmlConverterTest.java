package pl.wicherska.songs.xml;

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

class XmlConverterTest {
    private XmlConverter xmlConverter;

    @BeforeEach
    void setUp(){
        xmlConverter = new XmlConverter();
    }

    @Test
    void shouldReturnListOfSongs(){
        SongXmlRepresentation songXml1 = new SongXmlRepresentation();
        songXml1.setTitle("Living in a Ghost Town");
        songXml1.setAuthor("The Rolling Stones");
        songXml1.setAlbum("Honk");
//        songXml1.setCategory(Category.ROCK);
        songXml1.setVotes(17);

        SongXmlRepresentation songXml2 = new SongXmlRepresentation();
        songXml2.setTitle("You Should Be Sad");
        songXml2.setAuthor("Halsey");
        songXml2.setAlbum("Manic");
//        songXml2.setCategory(Category.ALTERNATIVE);
        songXml2.setVotes(2);

        SongXmlRepresentation songXml3 = new SongXmlRepresentation();
        songXml3.setTitle("Imported");
        songXml3.setAuthor("Jessie Reyez");
        songXml3.setAlbum("Before Love Came to Kill Us");
//        songXml3.setCategory(Category.RANDB);
        songXml3.setVotes(6);

        List<SongXmlRepresentation> songXmlRepresentationList = List.of(songXml1, songXml2, songXml3);

        List<Song> songList = xmlConverter.mapDataSourceToListOfSongs(songXmlRepresentationList);

        assertThat(songList).hasSize(3);
        Song song1 = songList.get(0);
        Song song2 = songList.get(1);
        Song song3 = songList.get(2);

        assertThatSong(song1)
                .hasTitle("Living in a Ghost Town")
                .hasAuthor("The Rolling Stones")
                .hasAlbum("Honk")
                .hasCategory(Category.ROCK)
                .hasVotes(17);
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
    void shouldReturnListOfStrings(){
        List<Song> songList = List.of(rockSong(), alternativeSong(), rAndBSong());
        List<SongXmlRepresentation> songXmlRepresentationList = xmlConverter.mapListOfSongsToDataSource(songList);
        SongXmlRepresentation songXml1 = songXmlRepresentationList.get(0);
        SongXmlRepresentation songXml2 = songXmlRepresentationList.get(1);
        SongXmlRepresentation songXml3 = songXmlRepresentationList.get(2);
        assertAll(
                () -> assertAll(
                        () -> assertEquals("Living in a Ghost Town", songXml1.getTitle()),
                        () -> assertEquals("The Rolling Stones", songXml1.getAuthor()),
                        () -> assertEquals("Honk", songXml1.getAlbum()),
//ToDo
//                        () -> assertEquals(Category.ROCK, songXml1.getCategory()),
                        () -> assertEquals(17, songXml1.getVotes())),
                () -> assertAll(
                        () -> assertEquals("You Should Be Sad", songXml2.getTitle()),
                        () -> assertEquals("Halsey", songXml2.getAuthor()),
                        () -> assertEquals("Manic", songXml2.getAlbum()),
//                        () -> assertEquals(Category.ALTERNATIVE, songXml2.getCategory()),
                        () -> assertEquals(2, songXml2.getVotes())),

                () -> assertAll(
                        () -> assertEquals("Imported", songXml3.getTitle()),
                        () -> assertEquals("Jessie Reyez", songXml3.getAuthor()),
                        () -> assertEquals("Before Love Came to Kill Us", songXml3.getAlbum()),
//                        () -> assertEquals(Category.RANDB, songXml3.getCategory()),
                        () -> assertEquals(6, songXml3.getVotes())
                )
        );
    }

}