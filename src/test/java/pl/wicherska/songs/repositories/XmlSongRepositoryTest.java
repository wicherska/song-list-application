package pl.wicherska.songs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.converters.XmlConverter;
import pl.wicherska.songs.core.Config;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.sources.XmlDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.wicherska.songs.TestSongFactory.rAndBSong;

class XmlSongRepositoryTest {
    private final XmlConverter xmlConverter = Config.getInstance().xmlConverter();
    private final XmlDataSource xmlDataSource = Config.getInstance().xmlDataSource();
    private final List<String> paths = List.of("src/test/resources/test.xml");
    private XmlSongRepository xmlSongRepository;

    @BeforeEach
    void setUp() {
        xmlSongRepository = new XmlSongRepository(xmlConverter, xmlDataSource, paths);
    }

    @Test
    void shouldReturnListOfSongs() {
        List<Song> expectedSongs = List.of(rAndBSong());

        List<Song> songs = xmlSongRepository.getSongs();

        assertAll(
                () -> assertEquals(expectedSongs.size(), songs.size()),
                () -> assertEquals(expectedSongs.get(0), songs.get(0))
        );
    }
}