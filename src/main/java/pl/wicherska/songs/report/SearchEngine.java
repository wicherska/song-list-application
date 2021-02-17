package pl.wicherska.songs.report;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.repositories.InMemorySongRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchEngine {
    private final InMemorySongRepository inMemorySongRepository;

    public SearchEngine(InMemorySongRepository inMemorySongRepository) {
        this.inMemorySongRepository = inMemorySongRepository;
    }

    public List<Song> getSongByCategory(){
        return inMemorySongRepository.getSongSortedByCategory();
    }

    public List<Song> getTop3(){
        return inMemorySongRepository.getSongSortedByVotes().stream().limit(3).collect(toList());
    }

    public List<Song> getTop10(){
        return inMemorySongRepository.getSongSortedByVotes().stream().limit(10).collect(toList());
    }

    public List<Song> getAll(){
        return inMemorySongRepository.getListOfSongs();
    }
}
