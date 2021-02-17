package pl.wicherska.songs.report;

import pl.wicherska.songs.domain.Song;
import pl.wicherska.songs.interfaces.Writer;

import java.util.List;

public class ConsoleWriter implements Writer {

    @Override
    public void createReport(List<Song> songList) {
        for(int i=1; i<=songList.size(); i++){
            System.out.println(i +". " + songList.get(i-1));
        }
    }
}
