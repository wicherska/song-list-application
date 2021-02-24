package pl.wicherska.songs.repositories;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.Repository;

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
            List<Song> repositoryListOfSongs = repository.getListOfSongs();
            for(Song repositorySong:repositoryListOfSongs){
                if(!tempList.contains(repositorySong)){
                    tempList.add(repositorySong);
                }else{
                    Song song = tempList.get(tempList.indexOf(repositorySong));
                    song.addVotes(repositorySong.getVotes());
                }
            }
        }
        return tempList;
    }

    @Override
    public List<Song> getListOfSongs() {
        return songList;
    }

    public void addSong(Song song){
        songList.add(song);
    }

}
