package pl.wicherska.songs.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CsvDataSource{

    public List<String> readFromFiles(List<String> paths){
        List<String> stringList = new LinkedList<>();
        for(String path: paths){
            stringList.addAll(readFromFile(path));
        }
        return stringList;
    }

    private List<String> readFromFile(String path){
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return lines.stream().skip(1).collect(toList());
    }

    public void writeToFile(List<String> songs, String path) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            for(String song: songs){
                bw.write(song + System.lineSeparator());
            }
        }
    }

}
