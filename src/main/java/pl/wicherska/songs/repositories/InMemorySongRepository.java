package pl.wicherska.songs.repositories;


import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.Repository;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class InMemorySongRepository implements Repository {
    private final List<Repository> repositoryList;
    private List<Song> songList;

    public InMemorySongRepository(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
        this.songList = getListOfSongs();
    }

    @Override
    public List<Song> getListOfSongs() {
        List <Song> tempList = new LinkedList<>();
        for(Repository repository: repositoryList){
            tempList.addAll(repository.getListOfSongs());
        }
        return tempList;
    }

    public List<Song> getSongSortedByVotes(){
        songList.sort((s1, s2) -> s2.getVotes() - s1.getVotes());
        return songList;
    }

    public List<Song> getSongSortedByCategory(){
        songList.sort(Comparator.comparing(Song::getCategory));
        return songList;
    }

    public void addSong(Song song){
        songList.add(song);
    }

    public void setVotesToZero(Song song){
        song.setVotesToZero();
    }

    public void setVotesToZeroForAllSongs(){
        for (Song song: songList){
            song.setVotesToZero();
        }
    }
}
