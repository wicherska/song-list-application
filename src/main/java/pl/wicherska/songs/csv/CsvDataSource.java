package pl.wicherska.songs.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvDataSource{

    public List<String> readFromFile(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                lines.add(line);
            }
        }
        return lines;
    }

    public void writeToFile(List<String> songs, String path) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            for(String song: songs){
                bw.write(song + System.lineSeparator());
            }
        }
    }

}
