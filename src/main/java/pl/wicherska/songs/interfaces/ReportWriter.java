package pl.wicherska.songs.interfaces;

import pl.wicherska.songs.domain.Song;

import java.util.List;

public interface ReportWriter {

    void createReport(List<Song> songList);
}
