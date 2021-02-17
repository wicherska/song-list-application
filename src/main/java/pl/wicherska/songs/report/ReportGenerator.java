package pl.wicherska.songs.report;

public class ReportGenerator {
    private final CsvWriter csvWriter;
    private final XmlWriter xmlWriter;
    private final ConsoleWriter consoleWriter;
    private final SearchEngine searchEngine;

    public ReportGenerator(CsvWriter csvWriter, XmlWriter xmlWriter, ConsoleWriter consoleWriter, SearchEngine searchEngine) {
        this.csvWriter = csvWriter;
        this.xmlWriter = xmlWriter;
        this.consoleWriter = consoleWriter;
        this.searchEngine = searchEngine;
    }

    public void reportCsvTop10(){
        csvWriter.createReport(searchEngine.getTop10());
    }

    public void reportCsvTop3(){
        csvWriter.createReport(searchEngine.getTop3());
    }

    public void reportCsvAll(){
        csvWriter.createReport(searchEngine.getAll());
    }

    public void reportCsvByCategory(){
        csvWriter.createReport(searchEngine.getSongByCategory());
    }

    public void reportXmlTop10(){
        xmlWriter.createReport(searchEngine.getTop10());
    }

    public void reportXmlTop3(){
        xmlWriter.createReport(searchEngine.getTop3());
    }

    public void reportXmlAll(){
        xmlWriter.createReport(searchEngine.getAll());
    }

    public void reportXmlByCategory(){
        xmlWriter.createReport(searchEngine.getSongByCategory());
    }

    public void reportConsoleTop10(){
        consoleWriter.createReport(searchEngine.getTop10());
    }

    public void reportConsoleTop3(){
        consoleWriter.createReport(searchEngine.getTop3());
    }

    public void reportConsoleAll(){
        consoleWriter.createReport(searchEngine.getAll());
    }

    public void reportConsoleByCategory(){
        consoleWriter.createReport(searchEngine.getSongByCategory());
    }
}
