package pl.wicherska.songs.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
