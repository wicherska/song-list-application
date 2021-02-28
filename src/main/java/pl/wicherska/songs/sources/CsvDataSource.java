package pl.wicherska.songs.sources;

import pl.wicherska.songs.interfaces.DataSource;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CsvDataSource implements DataSource<String, String> {

    private static final String CSV_FILE_HEADER = "Title,Author,Album,Category,Votes";

    @Override
    public List<String> readFromFiles(List<String> paths) {
        return paths.stream()
                .map(this::readFromFile)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(List::stream)
                .collect(toList());
    }

    private Optional<List<String>> readFromFile(String path) {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            List<String> linesWithoutHeader = lines.skip(1).collect(toList());
            return Optional.of(linesWithoutHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void writeToFile(List<String> songs, String path) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(path))) {
            writeFileHeader(bw);
            for (String song : songs) {
                bw.write(song);
                bw.newLine();
            }
        }
    }

    private void writeFileHeader(BufferedWriter bw) throws IOException {
        bw.write(CSV_FILE_HEADER);
        bw.newLine();
    }
}
