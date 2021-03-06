package pl.wicherska.songs.interfaces;

import java.util.List;

public interface DataSource<T, V> {

    List<T> readFromFiles(List<V> paths);

    void writeToFile(List<T> songs, String path) throws Exception;

}
