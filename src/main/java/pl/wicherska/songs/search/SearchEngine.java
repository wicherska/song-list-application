package pl.wicherska.songs.search;

import pl.wicherska.songs.domain.ReportType;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.services.SongService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchEngine {
    private final SongService songService;

    public SearchEngine(SongService songService) {
        this.songService = songService;
    }

    public List<Song> getSongsForReportType(ReportType reportType){
        switch (reportType){
            case ALL:
                return getAll();
            case TOP3:
                return getTop3();
            case TOP10:
                return getTop10();
            case CATEGORY:
                return getSongByCategory();
            default:
                throw new IllegalArgumentException("Not supported report type: " + reportType);
        }
    }

    private List<Song> getSongByCategory(){
        return songService.getSongSortedByCategory();
    }

    private List<Song> getTop3(){
        return songService.getSongSortedByVotes().stream().limit(3).collect(toList());
    }

    private List<Song> getTop10(){
        return songService.getSongSortedByVotes().stream().limit(10).collect(toList());
    }

    private List<Song> getAll(){
        return songService.getAllSongs();
    }
}
