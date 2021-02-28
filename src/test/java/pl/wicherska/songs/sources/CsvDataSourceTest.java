package pl.wicherska.songs.sources;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvDataSourceTest {
    private final static String PATH_TO_WRITE = "src/test/resources/report.csv";
    private final List<String> stringList = List.of(
            "Space Oddity,David Bowie,David Bowie,Rock,7",
            "Settle Down,Kimbra,Vows,Alternative,2"
    );
    private CsvDataSource csvDataSource;

    @BeforeEach
    void setUp() {
        csvDataSource = new CsvDataSource();
    }


    @Test
    void shouldReturnListOfStringsAfterReadFromFile() {
        String pathToRead = "src/test/resources/test.csv";

        List<String> fromFileList = csvDataSource.readFromFiles(List.of(pathToRead));

        assertAll(
                () -> assertEquals(stringList.size(), fromFileList.size()),
                () -> assertEquals(stringList.get(0), fromFileList.get(0)),
                () -> assertEquals(stringList.get(1), fromFileList.get(1))
        );
    }

    @Test
    void shouldWriteToFileListOfStrings() throws IOException {
        csvDataSource.writeToFile(stringList, PATH_TO_WRITE);

        assertTrue(Files.exists(Paths.get(PATH_TO_WRITE)));
    }

    @AfterAll
    static void afterAll() throws IOException {
        Files.delete(Path.of(PATH_TO_WRITE));
    }
}