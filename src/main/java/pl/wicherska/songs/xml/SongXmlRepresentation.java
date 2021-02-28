package pl.wicherska.songs.xml;

import pl.wicherska.songs.domain.Category;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "song")
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(propOrder = {"title", "author", "album", "category", "votes"})
public class SongXmlRepresentation {
    private String title;
    private String author;
    private String album;
    private XmlCategory category;
    private int votes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public XmlCategory getXmlCategory() {
        return category;
    }

    public void setXmlCategory(XmlCategory category) {
        this.category = category;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "SongXmlRepresentation{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", album='" + album + '\'' +
                ", category=" + category +
                ", votes=" + votes +
                '}';
    }
}
