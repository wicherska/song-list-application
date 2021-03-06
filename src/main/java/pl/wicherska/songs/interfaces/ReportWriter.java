package pl.wicherska.songs.interfaces;

import pl.wicherska.songs.domain.Song;

import java.util.List;

public interface ReportWriter {
    //todo
    /**
     * Implementation should write report to file/console.
     * Implementing class should use proper DataSource and ReportGenerator
     * In case of failure exception should be catch.
     */
    void createReport(List<Song> songList);
}
