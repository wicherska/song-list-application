package pl.wicherska.songs.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.wicherska.songs.ApplicationRunner;
import pl.wicherska.songs.converters.CsvConverter;
import pl.wicherska.songs.converters.JsonConverter;
import pl.wicherska.songs.converters.XmlConverter;
import pl.wicherska.songs.generators.*;
import pl.wicherska.songs.handlers.*;
import pl.wicherska.songs.interfaces.UserActionHandler;
import pl.wicherska.songs.repositories.AggregatingSongRepository;
import pl.wicherska.songs.repositories.CsvSongRepository;
import pl.wicherska.songs.repositories.JsonSongRepository;
import pl.wicherska.songs.repositories.XmlSongRepository;
import pl.wicherska.songs.search.SearchEngine;
import pl.wicherska.songs.services.SongService;
import pl.wicherska.songs.services.UserSongSelectionService;
import pl.wicherska.songs.sources.CsvDataSource;
import pl.wicherska.songs.sources.JsonDataSource;
import pl.wicherska.songs.sources.XmlDataSource;
import pl.wicherska.songs.util.ScannerWrapper;
import pl.wicherska.songs.writers.ConsoleReportWriter;
import pl.wicherska.songs.writers.CsvReportWriter;
import pl.wicherska.songs.writers.JsonReportWriter;
import pl.wicherska.songs.writers.XmlReportWriter;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigTest {

    static Stream<Arguments> stringArrayProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{""}),
                Arguments.of((Object) new String[]{"test.xm", "test.cs"}),
                Arguments.of((Object) new String[]{"test3.xml", "test.csv"}),
                Arguments.of((Object) new String[]{"test.jso", "test.csv"})
        );
    }

    @ParameterizedTest
    @MethodSource("stringArrayProvider")
    void shouldReturnFalseWhenPathsAreIncorrect(String[] args) {
        boolean songFilePathSetCorrectly = Config.getInstance().setInitialSongFilesPaths(args);

        assertFalse(songFilePathSetCorrectly);
    }

    @Test
    void shouldReturnTrueWhenPathsAreCorrect() {
        String[] args = new String[]{"src/test/resources/test.csv",
                "src/test/resources/test.xml", "src/test/resources/test.json"};

        boolean songFilePathSetCorrectly = Config.getInstance().setInitialSongFilesPaths(args);

        assertTrue(songFilePathSetCorrectly);
    }


    @Test
    void userActionHandlersShouldNotBeNull() {
        Map<UserAction, UserActionHandler> handlerMap = Config.getInstance().userActionHandlers();

        assertThat(handlerMap)
                .isNotNull();
    }

    @Test
    void csvConverterShouldNotBeNull() {
        CsvConverter csvConverter = Config.getInstance().csvConverter();

        assertThat(csvConverter)
                .isNotNull();
    }

    @Test
    void csvDataSourceShouldNotBeNull() {
        CsvDataSource csvDataSource = Config.getInstance().csvDataSource();

        assertThat(csvDataSource)
                .isNotNull();
    }

    @Test
    void csvSongRepositoryShouldNotBeNull() {
        CsvSongRepository csvSongRepository = Config.getInstance().csvSongRepository();

        assertThat(csvSongRepository)
                .isNotNull();
    }

    @Test
    void csvReportWriterShouldNotBeNull() {
        CsvReportWriter csvReportWriter = Config.getInstance().csvReportWriter();

        assertThat(csvReportWriter)
                .isNotNull();
    }

    @Test
    void xmlConverterShouldNotBeNull() {
        XmlConverter xmlConverter = Config.getInstance().xmlConverter();

        assertThat(xmlConverter)
                .isNotNull();
    }

    @Test
    void xmlDataSourceShouldNotBeNull() {
        XmlDataSource xmlDataSource = Config.getInstance().xmlDataSource();

        assertThat(xmlDataSource)
                .isNotNull();
    }

    @Test
    void xmlSongRepositoryShouldNotBeNull() {
        XmlSongRepository xmlSongRepository = Config.getInstance().xmlSongRepository();

        assertThat(xmlSongRepository)
                .isNotNull();
    }

    @Test
    void xmlReportWriterShouldNotBeNull() {
        XmlReportWriter xmlReportWriter = Config.getInstance().xmlReportWriter();

        assertThat(xmlReportWriter)
                .isNotNull();
    }

    @Test
    void jsonConverterShouldNotBeNull() {
        JsonConverter jsonConverter = Config.getInstance().jsonConverter();

        assertThat(jsonConverter)
                .isNotNull();
    }

    @Test
    void jsonDataSourceShouldNotBeNull() {
        JsonDataSource jsonDataSource = Config.getInstance().jsonDataSource();

        assertThat(jsonDataSource)
                .isNotNull();
    }

    @Test
    void jsonSongRepositoryShouldNotBeNull() {
        JsonSongRepository jsonSongRepository = Config.getInstance().jsonSongRepository();

        assertThat(jsonSongRepository)
                .isNotNull();
    }

    @Test
    void jsonReportWriterShouldNotBeNull() {
        JsonReportWriter jsonReportWriter = Config.getInstance().jsonReportWriter();

        assertThat(jsonReportWriter)
                .isNotNull();
    }

    @Test
    void aggregatingSongRepositoryShouldNotBeNull() {
        AggregatingSongRepository aggregatingSongRepository = Config.getInstance().aggregatingSongRepository();

        assertThat(aggregatingSongRepository)
                .isNotNull();
    }

    @Test
    void searchEngineShouldNotBeNull() {
        SearchEngine searchEngine = Config.getInstance().searchEngine();

        assertThat(searchEngine)
                .isNotNull();
    }

    @Test
    void consoleReportWriterShouldNotBeNull() {
        ConsoleReportWriter consoleReportWriter = Config.getInstance().consoleReportWriter();

        assertThat(consoleReportWriter)
                .isNotNull();
    }

    @Test
    void csvReportGeneratorShouldNotBeNull() {
        CsvReportGenerator csvReportGenerator = Config.getInstance().csvReportGenerator();

        assertThat(csvReportGenerator)
                .isNotNull();
    }

    @Test
    void xmlReportGeneratorShouldNotBeNull() {
        XmlReportGenerator xmlReportGenerator = Config.getInstance().xmlReportGenerator();

        assertThat(xmlReportGenerator)
                .isNotNull();
    }

    @Test
    void jsonReportGeneratorShouldNotBeNull() {
        JsonReportGenerator jsonReportGenerator = Config.getInstance().jsonReportGenerator();

        assertThat(jsonReportGenerator)
                .isNotNull();
    }

    @Test
    void consoleReportGeneratorShouldNotBeNull() {
        ConsoleReportGenerator consoleReportGenerator = Config.getInstance().consoleReportGenerator();

        assertThat(consoleReportGenerator)
                .isNotNull();
    }

    @Test
    void reportGeneratorFactoryShouldNotBeNull() {
        ReportGeneratorFactory reportGeneratorFactory = Config.getInstance().reportGeneratorFactory();

        assertThat(reportGeneratorFactory)
                .isNotNull();
    }

    @Test
    void scannerShouldNotBeNull() {
        Scanner scanner = Config.getInstance().scanner();

        assertThat(scanner)
                .isNotNull();
    }

    @Test
    void scannerWrapperShouldNotBeNull() {
        ScannerWrapper scannerWrapper = Config.getInstance().scannerWrapper();

        assertThat(scannerWrapper)
                .isNotNull();
    }

    @Test
    void songAddingUserActionHandlerShouldNotBeNull() {
        SongAddingUserActionHandler songAddingUserActionHandler = Config.getInstance().songAddingUserActionHandler();

        assertThat(songAddingUserActionHandler)
                .isNotNull();
    }

    @Test
    void reportGeneratorUserActionHandlerShouldNotBeNull() {
        ReportGeneratorUserActionHandler reportGeneratorUserActionHandler = Config.getInstance().reportGeneratorUserActionHandler();

        assertThat(reportGeneratorUserActionHandler)
                .isNotNull();
    }

    @Test
    void votingUserActionHandlerShouldNotBeNull() {
        VotingUserActionHandler votingUserActionHandler = Config.getInstance().votingUserActionHandler();

        assertThat(votingUserActionHandler)
                .isNotNull();

    }

    @Test
    void resettingUserActionHandlerShouldNotBeNull() {
        ResettingUserActionHandler resettingUserActionHandler = Config.getInstance().resettingUserActionHandler();

        assertThat(resettingUserActionHandler)
                .isNotNull();
    }

    @Test
    void applicationRunnerShouldNotBeNull() {
        ApplicationRunner applicationRunner = Config.getInstance().applicationRunner();

        assertThat(applicationRunner)
                .isNotNull();
    }

    @Test
    void songServiceShouldNotBeNull() {
        SongService songService = Config.getInstance().songService();

        assertThat(songService)
                .isNotNull();
    }

    @Test
    void userSongSelectionServiceShouldNotBeNull() {
        UserSongSelectionService userSongSelectionService = Config.getInstance().userSongSelectionService();

        assertThat(userSongSelectionService)
                .isNotNull();
    }
}