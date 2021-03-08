package pl.wicherska.songs.sources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import pl.wicherska.songs.interfaces.DataSource;
import pl.wicherska.songs.json.SongJsonRepresentation;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JsonDataSource implements DataSource<SongJsonRepresentation, String> {

    @Override
    public List<SongJsonRepresentation> readFromFiles(List<String> paths) {
        List<SongJsonRepresentation> songJsonList = new LinkedList<>();
        for (String path : paths) {
            songJsonList.addAll(readFromFile(path));
        }
        return songJsonList;
    }

    private List<SongJsonRepresentation> readFromFile(String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, SongJsonRepresentation.class);
            return mapper.readValue(new File(path), javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @Override
    public void writeToFile(List<SongJsonRepresentation> songs, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), songs);
    }
}
