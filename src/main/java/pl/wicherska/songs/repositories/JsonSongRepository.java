package pl.wicherska.songs.repositories;

import pl.wicherska.songs.converters.JsonConverter;
import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.SongRepository;
import pl.wicherska.songs.sources.JsonDataSource;

import java.util.List;

public class JsonSongRepository implements SongRepository {
    private final JsonConverter jsonConverter;
    private final JsonDataSource jsonDataSource;
    private final List<String> paths;

    public JsonSongRepository(JsonConverter jsonConverter, JsonDataSource jsonDataSource, List<String> paths) {
        this.jsonConverter = jsonConverter;
        this.jsonDataSource = jsonDataSource;
        this.paths = paths;
    }

    @Override
    public List<Song> getSongs() {
        return jsonConverter.toSongs(jsonDataSource.readFromFiles(paths));
    }
}
