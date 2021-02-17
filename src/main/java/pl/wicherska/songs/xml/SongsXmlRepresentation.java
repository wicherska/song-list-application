package pl.wicherska.songs.xml;

import pl.wicherska.songs.xml.SongXmlRepresentation;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "songs")
@XmlAccessorType(XmlAccessType.FIELD)
public class SongsXmlRepresentation {

    @XmlElement(name = "song")
    private List<SongXmlRepresentation> songList;

    public List<SongXmlRepresentation> getSongsList() {
        return songList;
    }

    public void setSongList(List<SongXmlRepresentation> songList) {
        this.songList = songList;
    }
}
