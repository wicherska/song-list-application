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
        this.songList = setInitialListOfSong();
    }

    private List<Song> setInitialListOfSong(){
        List <Song> tempList = new LinkedList<>();
        for(Repository repository: repositoryList){
            tempList.addAll(repository.getListOfSongs());
        }
        return tempList;
    }

    @Override
    public List<Song> getListOfSongs() {
        return songList;
    }

    public void printAllSongs(){
        for(int i=1; i<=songList.size(); i++){
            System.out.println(String.valueOf(i) + ". " + songList.get(i-1));
        }
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

    public void setVotesToZeroForAllSongs(){
        for (Song song : songList) {
            song.setVotesToZero();
        }
    }

    public void setVotesToZeroForChosenSong(int index){
        songList.get(index).setVotesToZero();
    }

    public void voteForChosenSong(int index){
        songList.get(index).voteForSong();
    }

    public Song getSong(int index){
        return songList.get(index);
    }
}
