package pl.wicherska.songs.search;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.generators.ReportType;
import pl.wicherska.songs.services.SongService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchEngine {
    private final SongService songService;

    public SearchEngine(SongService songService) {
        this.songService = songService;
    }

    public List<Song> getSongsForReportType(ReportType reportType) {
        switch (reportType) {
            case ALL:
                return getAll();
            case TOP3:
                return getBestSongs(3);
            case TOP10:
                return getBestSongs(10);
            case CATEGORY:
                return getSongByCategory();
            default:
                throw new IllegalArgumentException("Not supported report type: " + reportType);
        }
    }

    private List<Song> getSongByCategory() {
        return songService.getSongsSortedByCategory();
    }

    private List<Song> getBestSongs(int number) {
        return getAll().stream()
                .limit(number)
                .collect(toList());
    }

    private List<Song> getAll() {
        return songService.getSongsSortedByVotes();
    }
}
