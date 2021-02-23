package pl.wicherska.songs.core;

import pl.wicherska.songs.report.*;
import pl.wicherska.songs.repositories.InMemorySongRepository;
import pl.wicherska.songs.csv.CsvConverter;
import pl.wicherska.songs.csv.CsvDataSource;
import pl.wicherska.songs.repositories.CsvSongRepository;
import pl.wicherska.songs.repositories.XmlSongRepository;
import pl.wicherska.songs.xml.XmlConverter;
import pl.wicherska.songs.xml.XmlDataSource;

import java.util.LinkedList;
import java.util.List;

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
    private ReportGenerator reportGenerator;


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
            searchEngine = new SearchEngine(inMemorySongRepository());
        }
        return searchEngine;
    }

    public ConsoleWriter consoleWriter(){
        if(consoleWriter == null){
            consoleWriter = new ConsoleWriter();
        }
        return consoleWriter;
    }

    public ReportGenerator reportGenerator(){
        if(reportGenerator == null){
            reportGenerator = new ReportGenerator(csvWriter(), xmlWriter(), consoleWriter(), searchEngine());
        }
        return reportGenerator;
    }
}
