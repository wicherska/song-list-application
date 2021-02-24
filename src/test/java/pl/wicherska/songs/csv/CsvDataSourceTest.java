package pl.wicherska.songs.csv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CsvDataSourceTest {
    private CsvDataSource csvDataSource;

    @BeforeEach
    void setUp(){
        csvDataSource = new CsvDataSource();
    }

    @Test
    void shouldReturnListOfStringFromOneFile(){
        List<String> stringList = csvDataSource.readFromFiles(List.of("src/test/resources/songs2.csv"));
        assertThat(stringList).hasSize(1);
        String line = stringList.get(0);
        assertThat(line).isEqualTo("Settle Down,Kimbra,Vows,Alternative,2");
    }

    @Test
    void shouldReturnListOfStringFromTwoFile(){
        List<String> stringList = csvDataSource.readFromFiles(List.of("src/test/resources/songs2.csv", "src/test/resources/songs.csv"));
        assertThat(stringList).hasSize(4);
        String line1 = stringList.get(0);
        String line2 = stringList.get(1);
        String line3 = stringList.get(2);
        String line4 = stringList.get(3);
        assertAll(
                () -> assertThat(line1).isEqualTo("Settle Down,Kimbra,Vows,Alternative,2"),
                () -> assertThat(line2).isEqualTo("Space Oddity,David Bowie,David Bowie,Rock,7"),
                () -> assertThat(line3).isEqualTo("Space Oddity,David Bowie,David Bowie,Rock,7"),
                () -> assertThat(line4).isEqualTo("Father To Son,Queen,Queen II,Rock,21")
        );
    }
}