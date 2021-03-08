package pl.wicherska.songs.sources;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.json.JsonCategory;
import pl.wicherska.songs.json.SongJsonRepresentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pl.wicherska.songs.TestSongFactory.rockSong;

class JsonDataSourceTest {
    private final static String PATH_TO_WRITE = "src/test/resources/report.json";
    private final List<SongJsonRepresentation> songJsonRepresentationList = new ArrayList<>();
    private JsonDataSource jsonDataSource;

    @BeforeEach
    void setUp() {
        jsonDataSource = new JsonDataSource();
        songJsonRepresentationList.add(new SongJsonRepresentation(
                rockSong().getTitle(),
                rockSong().getAuthor(),
                rockSong().getAlbum(),
                JsonCategory.fromSong(rockSong()),
                rockSong().getVotes()
        ));
    }

    @Test
    void shouldReturnListOfSongJsonRepresentationAfterReadFromFile() {
        String pathToRead = "src/test/resources/test.json";

        List<SongJsonRepresentation> fromFileList = jsonDataSource.readFromFiles(List.of(pathToRead));

        assertAll(
                () -> assertEquals(songJsonRepresentationList.size(), fromFileList.size()),
                () -> assertEquals(songJsonRepresentationList.get(0), fromFileList.get(0))
        );
    }

    @Test
    void shouldWriteToFileListOfSongJsonRepresentation() throws IOException {
        jsonDataSource.writeToFile(songJsonRepresentationList, PATH_TO_WRITE);

        assertTrue(Files.exists(Paths.get(PATH_TO_WRITE)));
    }

    @AfterAll
    static void afterAll() throws IOException {
        Files.delete(Path.of(PATH_TO_WRITE));
    }
}