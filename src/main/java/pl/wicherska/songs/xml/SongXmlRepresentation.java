package pl.wicherska.songs.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongXmlRepresentation that = (SongXmlRepresentation) o;
        return votes == that.votes && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(album, that.album) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, album, category, votes);
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + album + ", " + category + ", " + votes;
    }
}
