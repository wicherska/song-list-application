package pl.wicherska.songs.xml;

import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.interfaces.Converter;
import pl.wicherska.songs.domain.Song;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class XmlConverter implements Converter<SongXmlRepresentation> {

    @Override
    public List<Song> mapDataSourceToListOfSongs(List<SongXmlRepresentation> elements) {
        return elements.stream()
                .map(this::mapSongXmlRepresentationToSong)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    @Override
    public List<SongXmlRepresentation> mapListOfSongsToDataSource(List<Song> songList) {
        return songList.stream()
                .map(this::mapSongToSongXmlRepresentation)
                .collect(toList());
    }

    private SongXmlRepresentation mapSongToSongXmlRepresentation(Song song){
        SongXmlRepresentation songXmlRepresentation = new SongXmlRepresentation();
        songXmlRepresentation.setTitle(song.getTitle());
        songXmlRepresentation.setAuthor(song.getAuthor());
        songXmlRepresentation.setAlbum(song.getAlbum());
        songXmlRepresentation.setXmlCategory(XmlCategory.fromString(song.getCategory().toString()));
        songXmlRepresentation.setVotes(song.getVotes());
        return songXmlRepresentation;
    }

    private Optional<Song> mapSongXmlRepresentationToSong(SongXmlRepresentation songXml){
        if(isCorrect(songXml)){
            return Optional.of(new Song(
                    songXml.getTitle().trim(),
                    songXml.getAuthor().trim(),
                    songXml.getAlbum().trim(),
                    Category.fromString(songXml.getXmlCategory().toString().trim()),
                    songXml.getVotes()));
        }else{
            System.out.println("Incorrect data: " + songXml.toString());
            return Optional.empty();
        }
    }

    private static boolean isCorrect(SongXmlRepresentation xmlRepresentation){
        if(xmlRepresentation.getTitle().isBlank()){
            return false;
        }else if(xmlRepresentation.getAuthor().isBlank()){
            return false;
        }else if(xmlRepresentation.getAlbum().isBlank()){
            return false;
        } else if(!isCategory(String.valueOf(xmlRepresentation.getXmlCategory()))){
            return false;
        }else return isNumeric(String.valueOf(xmlRepresentation.getVotes()));
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
