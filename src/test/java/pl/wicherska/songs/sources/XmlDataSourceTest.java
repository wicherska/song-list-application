package pl.wicherska.songs.sources;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.xml.SongXmlRepresentation;
import pl.wicherska.songs.xml.XmlCategory;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pl.wicherska.songs.TestSongFactory.rAndBSong;

class XmlDataSourceTest {
    private final static String PATH_TO_WRITE = "src/test/resources/report.xml";
    private final List<SongXmlRepresentation> songXmlRepresentationList = new ArrayList<>();
    private XmlDataSource xmlDataSource;

    @BeforeEach
    void setUp() {
        xmlDataSource = new XmlDataSource();
        SongXmlRepresentation songXmlRepresentation = new SongXmlRepresentation();
        songXmlRepresentation.setTitle(rAndBSong().getTitle());
        songXmlRepresentation.setAuthor(rAndBSong().getAuthor());
        songXmlRepresentation.setAlbum(rAndBSong().getAlbum());
        songXmlRepresentation.setXmlCategory(XmlCategory.fromSong(rAndBSong()));
        songXmlRepresentation.setVotes(rAndBSong().getVotes());
        songXmlRepresentationList.add(songXmlRepresentation);
    }

    @Test
    void shouldReturnListOfSongXmlRepresentationAfterReadFromFile() {
        String pathToRead = "src/test/resources/test.xml";

        List<SongXmlRepresentation> fromFileList = xmlDataSource.readFromFiles(List.of(pathToRead));

        assertAll(
                () -> assertEquals(songXmlRepresentationList.size(), fromFileList.size()),
                () -> assertEquals(songXmlRepresentationList.get(0), fromFileList.get(0))
        );
    }

    @Test
    void shouldWriteToFileListOfSongXmlRepresentation() throws JAXBException {
        xmlDataSource.writeToFile(songXmlRepresentationList, PATH_TO_WRITE);

        assertTrue(Files.exists(Paths.get(PATH_TO_WRITE)));
    }

    @AfterAll
    static void afterAll() throws IOException {
        Files.delete(Path.of(PATH_TO_WRITE));
    }
}