package pl.wicherska.songs.interfaces;

import pl.wicherska.songs.domain.Song;

import java.util.List;

public interface ReportGenerator {
    //todo
    /**
     * Implementation should generate report.
     * @param songs - List of songs to report
     *
     */
    void generateReport(List<Song> songs);
}
