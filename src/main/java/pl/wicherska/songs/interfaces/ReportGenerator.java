package pl.wicherska.songs.interfaces;

import pl.wicherska.songs.domain.Song;

import java.util.List;

public interface ReportGenerator {

    void generateReport(List<Song> songs);
}
