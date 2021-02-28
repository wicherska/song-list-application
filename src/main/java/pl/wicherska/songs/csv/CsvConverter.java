package pl.wicherska.songs.csv;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.interfaces.Converter;
import pl.wicherska.songs.domain.Song;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class CsvConverter implements Converter<String> {
    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_AUTHOR = 1;
    private static final int INDEX_OF_ALBUM = 2;
    private static final int INDEX_OF_CATEGORY = 3;
    private static final int INDEX_OF_VOTES = 4;
    private static final String DELIMITER = ",";
    private static final int NUMBER_OF_COLUMNS = 5;


    @Override
    public List<Song> mapDataSourceToListOfSongs(List<String> lines){
        return lines.stream()
                .map(this::mapLineToSong)
                .filter(Optional::isPresent)
                .map(Optional::get)
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

    private String mapSongToLine(Song song){
        return String.join(DELIMITER,
                song.getTitle(),
                song.getAuthor(),
                song.getAlbum(),
                song.getCategory().toString(),
                String.valueOf(song.getVotes()));
    }

    private Optional<Song> mapLineToSong(String line){
        String[] split = line.split(DELIMITER);
        if(isLineCorrect(split)){
            return Optional.of(new Song(
                    split[INDEX_OF_TITLE].trim(),
                    split[INDEX_OF_AUTHOR].trim(),
                    split[INDEX_OF_ALBUM].trim(),
                    Category.fromString(split[INDEX_OF_CATEGORY].trim()),
                    Integer.parseInt(split[INDEX_OF_VOTES].trim())));
        }else{
            System.out.println("Incorrect data: " + line);
            return Optional.empty();
        }
    }

    private static boolean isLineCorrect(String[] split){
        if(split.length != NUMBER_OF_COLUMNS){
            return false;
        }else{
            for(String element: split){
                if(element.isBlank()){
                    return false;
                }
            }
            if(!isNumeric(split[INDEX_OF_VOTES])){
                return false;
            }
            return isCategory(split[INDEX_OF_CATEGORY]);
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum.trim());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isCategory(String strCategory) {
        Category category = Category.fromString(strCategory.trim());
        return category != null;
    }
}
