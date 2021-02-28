package pl.wicherska.songs.core;

import pl.wicherska.songs.ApplicationRunner;
import pl.wicherska.songs.converters.CsvConverter;
import pl.wicherska.songs.converters.XmlConverter;
import pl.wicherska.songs.domain.UserAction;
import pl.wicherska.songs.generators.ConsoleReportGenerator;
import pl.wicherska.songs.generators.CsvReportGenerator;
import pl.wicherska.songs.generators.ReportGeneratorFactory;
import pl.wicherska.songs.generators.XmlReportGenerator;
import pl.wicherska.songs.handlers.ReportGeneratorUserActionHandler;
import pl.wicherska.songs.handlers.ResettingUserActionHandler;
import pl.wicherska.songs.handlers.SongAddingUserActionHandler;
import pl.wicherska.songs.handlers.VotingUserActionHandler;
import pl.wicherska.songs.interfaces.UserActionHandler;
import pl.wicherska.songs.repositories.AggregatingSongRepository;
import pl.wicherska.songs.repositories.CsvSongRepository;
import pl.wicherska.songs.repositories.XmlSongRepository;
import pl.wicherska.songs.search.SearchEngine;
import pl.wicherska.songs.services.SongService;
import pl.wicherska.songs.services.UserSongSelectionService;
import pl.wicherska.songs.sources.CsvDataSource;
import pl.wicherska.songs.sources.XmlDataSource;
import pl.wicherska.songs.util.ScannerWrapper;
import pl.wicherska.songs.writers.ConsoleReportWriter;
import pl.wicherska.songs.writers.CsvReportWriter;
import pl.wicherska.songs.writers.XmlReportWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

public class Config {
    private static final Config INSTANCE = new Config();
    private final List<String> csvPaths = new LinkedList<>();
    private final List<String> xmlPaths = new LinkedList<>();
    private Map<UserAction, UserActionHandler> handlers;
    private CsvConverter csvConverter;
    private CsvDataSource csvDataSource;
    private CsvSongRepository csvSongRepository;
    private CsvReportWriter csvReportWriter;
    private XmlConverter xmlConverter;
    private XmlDataSource xmlDataSource;
    private XmlSongRepository xmlSongRepository;
    private XmlReportWriter xmlReportWriter;
    private AggregatingSongRepository aggregatingSongRepository;
    private SearchEngine searchEngine;
    private ConsoleReportWriter consoleReportWriter;
    private CsvReportGenerator csvReportGenerator;
    private XmlReportGenerator xmlReportGenerator;
    private ConsoleReportGenerator consoleReportGenerator;
    private ReportGeneratorFactory reportGeneratorFactory;
    private SongAddingUserActionHandler songAddingUserActionHandler;
    private ReportGeneratorUserActionHandler reportGeneratorUserActionHandler;
    private VotingUserActionHandler votingUserActionHandler;
    private ResettingUserActionHandler resettingUserActionHandler;
    private ApplicationRunner applicationRunner;
    private Scanner scanner;
    private ScannerWrapper scannerWrapper;
    private SongService songService;
    private UserSongSelectionService userSongSelectionService;

    private Config() {
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public boolean isSongFilePathSetCorrectly(String[] paths) {
        if (paths.length < 1) {
            System.out.println("No correct path found.");
            return false;
        }
        for (String path : paths) {
            if (!Files.exists(Paths.get(path))) {
                System.out.println("\nFile not found: " + path);
                return false;
            } else {
                if (path.endsWith(".xml")) {
                    xmlPaths.add(path);
                } else if (path.endsWith(".csv")) {
                    csvPaths.add(path);
                }
            }
        }
        if (xmlPaths.size() == 0 && csvPaths.size() == 0) {
            System.out.println("No correct path found.");
            return false;
        }
        return true;
    }

    Map<UserAction, UserActionHandler> userActionHandlers() {
        if (handlers == null) {
            handlers = Map.ofEntries(
                    entry(UserAction.ADD, songAddingUserActionHandler()),
                    entry(UserAction.RANKING, reportGeneratorUserActionHandler()),
                    entry(UserAction.VOTE, votingUserActionHandler()),
                    entry(UserAction.RESET, resettingUserActionHandler())
            );
        }
        return handlers;
    }

    public CsvConverter csvConverter() {
        if (csvConverter == null) {
            csvConverter = new CsvConverter();
        }
        return csvConverter;
    }

    public CsvDataSource csvDataSource() {
        if (csvDataSource == null) {
            csvDataSource = new CsvDataSource();
        }
        return csvDataSource;
    }

    public CsvSongRepository csvSongRepository() {
        if (csvSongRepository == null) {
            csvSongRepository = new CsvSongRepository(csvConverter(), csvDataSource(), csvPaths);
        }
        return csvSongRepository;
    }

    CsvReportWriter csvReportWriter() {
        if (csvReportWriter == null) {
            csvReportWriter = new CsvReportWriter(csvConverter(), csvDataSource(), "report.csv");
        }
        return csvReportWriter;
    }

    public XmlConverter xmlConverter() {
        if (xmlConverter == null) {
            xmlConverter = new XmlConverter();
        }
        return xmlConverter;
    }

    public XmlDataSource xmlDataSource() {
        if (xmlDataSource == null) {
            xmlDataSource = new XmlDataSource();
        }
        return xmlDataSource;
    }

    XmlSongRepository xmlSongRepository() {
        if (xmlSongRepository == null) {
            xmlSongRepository = new XmlSongRepository(xmlConverter(), xmlDataSource(), xmlPaths);
        }
        return xmlSongRepository;
    }

    XmlReportWriter xmlReportWriter() {
        if (xmlReportWriter == null) {
            xmlReportWriter = new XmlReportWriter(xmlConverter(), xmlDataSource(), "report.xml");
        }
        return xmlReportWriter;
    }

    AggregatingSongRepository aggregatingSongRepository() {
        if (aggregatingSongRepository == null) {
            aggregatingSongRepository = new AggregatingSongRepository(List.of(csvSongRepository(), xmlSongRepository()));
        }
        return aggregatingSongRepository;
    }


    SearchEngine searchEngine() {
        if (searchEngine == null) {
            searchEngine = new SearchEngine(songService());
        }
        return searchEngine;
    }

    ConsoleReportWriter consoleReportWriter() {
        if (consoleReportWriter == null) {
            consoleReportWriter = new ConsoleReportWriter(songService());
        }
        return consoleReportWriter;
    }

    CsvReportGenerator csvReportGenerator() {
        if (csvReportGenerator == null) {
            csvReportGenerator = new CsvReportGenerator(csvReportWriter());
        }
        return csvReportGenerator;
    }

    XmlReportGenerator xmlReportGenerator() {
        if (xmlReportGenerator == null) {
            xmlReportGenerator = new XmlReportGenerator(xmlReportWriter());
        }
        return xmlReportGenerator;
    }

    ConsoleReportGenerator consoleReportGenerator() {
        if (consoleReportGenerator == null) {
            consoleReportGenerator = new ConsoleReportGenerator(consoleReportWriter());
        }
        return consoleReportGenerator;
    }

    ReportGeneratorFactory reportGeneratorFactory() {
        if (reportGeneratorFactory == null) {
            reportGeneratorFactory = new ReportGeneratorFactory(csvReportGenerator(), xmlReportGenerator(), consoleReportGenerator());
        }
        return reportGeneratorFactory;
    }

    Scanner scanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    ScannerWrapper scannerWrapper() {
        if (scannerWrapper == null) {
            scannerWrapper = new ScannerWrapper(scanner());
        }
        return scannerWrapper;
    }

    SongAddingUserActionHandler songAddingUserActionHandler() {
        if (songAddingUserActionHandler == null) {
            songAddingUserActionHandler = new SongAddingUserActionHandler(scannerWrapper(), aggregatingSongRepository());
        }
        return songAddingUserActionHandler;
    }

    ReportGeneratorUserActionHandler reportGeneratorUserActionHandler() {
        if (reportGeneratorUserActionHandler == null) {
            reportGeneratorUserActionHandler = new ReportGeneratorUserActionHandler(scannerWrapper(), reportGeneratorFactory(), searchEngine());
        }
        return reportGeneratorUserActionHandler;
    }

    VotingUserActionHandler votingUserActionHandler() {
        if (votingUserActionHandler == null) {
            votingUserActionHandler = new VotingUserActionHandler(songService(), userSongSelectionService());
        }
        return votingUserActionHandler;
    }

    ResettingUserActionHandler resettingUserActionHandler() {
        if (resettingUserActionHandler == null) {
            resettingUserActionHandler = new ResettingUserActionHandler(scannerWrapper(), songService(), userSongSelectionService());
        }
        return resettingUserActionHandler;
    }

    public ApplicationRunner applicationRunner() {
        if (applicationRunner == null) {
            applicationRunner = new ApplicationRunner(userActionHandlers(), scannerWrapper());
        }
        return applicationRunner;
    }

    SongService songService() {
        if (songService == null) {
            songService = new SongService(aggregatingSongRepository());
        }
        return songService;
    }

    UserSongSelectionService userSongSelectionService() {
        if (userSongSelectionService == null) {
            userSongSelectionService = new UserSongSelectionService(songService(), scannerWrapper());
        }
        return userSongSelectionService;
    }

}
