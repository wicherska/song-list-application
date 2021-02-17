package pl.wicherska.songs.csv;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.interfaces.Converter;
import pl.wicherska.songs.domain.Song;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CsvConverter implements Converter<String> {
    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_AUTHOR = 1;
    private static final int INDEX_OF_ALBUM = 2;
    private static final int INDEX_OF_CATEGORY = 3;
    private static final int INDEX_OF_VOTES = 4;
    private static final String DELIMITER = ",";


    @Override
    public List<Song> mapDataSourceToListOfSongs(List<String> lines){
        return lines.stream()
                .skip(1)
                .map(this::mapLineToSong)
                .collect(toList());
    }

    @Override
    public List<String> mapListOfSongsToDataSource(List<Song> songList) {
        List<String> stringList = songList.stream()
                .map(this::mapSongToLine)
                .collect(toList());
        stringList.add(0,"Title,Author,Album,Category,Votes");
        return stringList;
    }

    private Song mapLineToSong(String line){
        String[] split = line.split(DELIMITER);
        return new Song(
                split[INDEX_OF_TITLE],
                split[INDEX_OF_AUTHOR],
                split[INDEX_OF_ALBUM],
                Category.fromString(split[INDEX_OF_CATEGORY]),
                Integer.parseInt(split[INDEX_OF_VOTES]));
    }

    private String mapSongToLine(Song song){
        return String.join(DELIMITER,
                song.getTitle(),
                song.getAuthor(),
                song.getAlbum(),
                song.getCategory().toString(),
                String.valueOf(song.getVotes()));
    }
}
