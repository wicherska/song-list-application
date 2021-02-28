package pl.wicherska.songs.writers;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.ReportWriter;
import pl.wicherska.songs.services.SongService;

import java.util.List;

public class ConsoleReportWriter implements ReportWriter {
    private final SongService songService;

    public ConsoleReportWriter(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void createReport(List<Song> songList) {
        songService.printSongs(songList);
    }
}
