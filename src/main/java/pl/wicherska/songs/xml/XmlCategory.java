package pl.wicherska.songs.xml;

import pl.wicherska.songs.domain.Song;

import javax.xml.bind.annotation.XmlEnumValue;

public enum XmlCategory {
    //todo
    /**
     * Enum created to keep all xml related annotations in one directory.
     */

    @XmlEnumValue("Rock") ROCK("Rock"),
    @XmlEnumValue("Alternative") ALTERNATIVE("Alternative"),
    @XmlEnumValue("R&B") RNB("R&B");

    private final String category;

    XmlCategory(String category) {
        this.category = category;
    }

    public static XmlCategory fromString(String text) {
        XmlCategory matchedCategory = null;
        for (XmlCategory cat : XmlCategory.values()) {
            if (cat.category.equalsIgnoreCase(text.trim())) {
                matchedCategory = cat;
            }
        }
        return matchedCategory;
    }

    public static XmlCategory fromSong(Song song) {
        return XmlCategory.fromString(song.getCategory().getCategoryName());
    }

    @Override
    public String toString() {
        return category;
    }
}
