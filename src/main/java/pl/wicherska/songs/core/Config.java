package pl.wicherska.songs.core;

import pl.wicherska.songs.ApplicationRunner;
import pl.wicherska.songs.generators.ConsoleReportGenerator;
import pl.wicherska.songs.generators.CsvReportGenerator;
import pl.wicherska.songs.generators.ReportGeneratorFactory;
import pl.wicherska.songs.generators.XmlReportGenerator;
import pl.wicherska.songs.handlers.*;
import pl.wicherska.songs.search.*;
import pl.wicherska.songs.repositories.InMemorySongRepository;
import pl.wicherska.songs.csv.CsvConverter;
import pl.wicherska.songs.csv.CsvDataSource;
import pl.wicherska.songs.repositories.CsvSongRepository;
import pl.wicherska.songs.repositories.XmlSongRepository;
import pl.wicherska.songs.services.SongService;
import pl.wicherska.songs.writers.ConsoleWriter;
import pl.wicherska.songs.writers.CsvWriter;
import pl.wicherska.songs.writers.XmlWriter;
import pl.wicherska.songs.xml.XmlConverter;
import pl.wicherska.songs.xml.XmlDataSource;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Config {
    private static final Config INSTANCE =  new Config();
    private static final List<String> csvPaths = new LinkedList<>();
    private static final List<String> xmlPaths = new LinkedList<>();
    private CsvConverter csvConverter;
    private CsvDataSource csvDataSource;
    private CsvSongRepository csvSongRepository;
    private CsvWriter csvWriter;
    private XmlConverter xmlConverter;
    private XmlDataSource xmlDataSource;
    private XmlSongRepository xmlSongRepository;
    private XmlWriter xmlWriter;
    private InMemorySongRepository inMemorySongRepository;
    private SearchEngine searchEngine;
    private ConsoleWriter consoleWriter;
    private CsvReportGenerator csvReportGenerator;
    private XmlReportGenerator xmlReportGenerator;
    private ConsoleReportGenerator consoleReportGenerator;
    private ReportGeneratorFactory reportGeneratorFactory;
    private AddingHandler addingHandler;
    private CategorizingHandler categorizingHandler;
    private ReportGeneratorHandler reportGeneratorHandler;
    private VotingHandler votingHandler;
    private ZeroingHandler zeroingHandler;
    private ApplicationRunner applicationRunner;
    private Scanner scanner;
    private SongService songService;


    private Config() {
    }

    public static Config getInstance(){
        return INSTANCE;
    }

    public static void setPaths(String[] paths){
        for(String path: paths){
            if(path.endsWith(".xml")){
                xmlPaths.add(path);
            } else if(path.endsWith(".csv")){
                csvPaths.add(path);
            }
        }
    }

    public CsvConverter csvConverter(){
        if(csvConverter == null){
            csvConverter = new CsvConverter();
        }
        return csvConverter;
    }

    public CsvDataSource csvDataSource(){
        if(csvDataSource == null){
            csvDataSource = new CsvDataSource();
        }
        return csvDataSource;
    }

    public CsvSongRepository csvSongRepository(){
        if(csvSongRepository == null){
            csvSongRepository = new CsvSongRepository(csvConverter(), csvDataSource(), csvPaths);
        }
        return csvSongRepository;
    }

    public CsvWriter csvWriter(){
        if(csvWriter == null){
            csvWriter = new CsvWriter(csvConverter(), csvDataSource(), "src/main/resources/report.csv");
        }
        return csvWriter;
    }

    public XmlConverter xmlConverter(){
        if(xmlConverter == null){
            xmlConverter = new XmlConverter();
        }
        return xmlConverter;
    }

    public XmlDataSource xmlDataSource(){
        if(xmlDataSource == null){
            xmlDataSource = new XmlDataSource();
        }
        return xmlDataSource;
    }

    public XmlSongRepository xmlSongRepository(){
        if(xmlSongRepository == null){
            xmlSongRepository = new XmlSongRepository(xmlConverter(), xmlDataSource(), xmlPaths);
        }
        return xmlSongRepository;
    }

    public XmlWriter xmlWriter(){
        if(xmlWriter == null){
            xmlWriter = new XmlWriter(xmlConverter(), xmlDataSource(),"src/main/resources/report.xml" );
        }
        return xmlWriter;
    }

    public InMemorySongRepository inMemorySongRepository() {
        if(inMemorySongRepository == null){
            inMemorySongRepository = new InMemorySongRepository(List.of(csvSongRepository(), xmlSongRepository()));
        }
        return inMemorySongRepository;
    }


    public SearchEngine searchEngine(){
        if(searchEngine == null){
            searchEngine = new SearchEngine(songService());
        }
        return searchEngine;
    }

    public ConsoleWriter consoleWriter(){
        if(consoleWriter == null){
            consoleWriter = new ConsoleWriter();
        }
        return consoleWriter;
    }

    public CsvReportGenerator csvReportGenerator(){
        if(csvReportGenerator == null){
            csvReportGenerator = new CsvReportGenerator(csvWriter());
        }
        return csvReportGenerator;
    }

    public XmlReportGenerator xmlReportGenerator(){
        if(xmlReportGenerator == null){
            xmlReportGenerator = new XmlReportGenerator(xmlWriter());
        }
        return xmlReportGenerator;
    }

    public ConsoleReportGenerator consoleReportGenerator(){
        if(consoleReportGenerator == null){
            consoleReportGenerator = new ConsoleReportGenerator(consoleWriter());
        }
        return consoleReportGenerator;
    }

    public ReportGeneratorFactory reportGeneratorFactory(){
        if(reportGeneratorFactory == null){
            reportGeneratorFactory = new ReportGeneratorFactory(csvReportGenerator(), xmlReportGenerator(), consoleReportGenerator());
        }
        return reportGeneratorFactory;
    }

    public Scanner scanner(){
        if(scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public AddingHandler addingHandler(){
        if(addingHandler == null){
            addingHandler = new AddingHandler(scanner(), inMemorySongRepository());
        }
        return addingHandler;
    }

    public  CategorizingHandler categorizingHandler(){
        if(categorizingHandler == null){
            categorizingHandler = new CategorizingHandler(scanner(), reportGeneratorFactory(), searchEngine());
        }
        return categorizingHandler;
    }

    public ReportGeneratorHandler reportGeneratorHandler(){
        if(reportGeneratorHandler == null){
            reportGeneratorHandler = new ReportGeneratorHandler(scanner(), reportGeneratorFactory(), searchEngine());
        }
        return reportGeneratorHandler;
    }

    public VotingHandler votingHandler(){
        if(votingHandler == null){
            votingHandler = new VotingHandler(scanner(), songService());
        }
        return votingHandler;
    }

    public ZeroingHandler zeroingHandler(){
        if(zeroingHandler == null){
            zeroingHandler = new ZeroingHandler(scanner(), songService());
        }
        return zeroingHandler;
    }

    public ApplicationRunner applicationRunner(){
        if(applicationRunner == null){
            applicationRunner = new ApplicationRunner(addingHandler(), categorizingHandler(),
                    reportGeneratorHandler(), votingHandler(), zeroingHandler(), scanner());
        }
        return  applicationRunner;
    }

    public SongService songService(){
        if(songService == null){
            songService = new SongService(inMemorySongRepository());
        }
        return  songService;
    }

}
