package pl.wicherska.songs.domain;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Category {
    @XmlEnumValue("Rock") ROCK("Rock"),
    @XmlEnumValue("Alternative") ALTERNATIVE("Alternative"),
    @XmlEnumValue("R&B") RANDB("R&B");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public static Category fromString(String text) {
        Category matchedCategory = null;
        for (Category cat : Category.values()) {
            if (cat.category.equalsIgnoreCase(text)) {
                matchedCategory = cat;
            }
        }
        return matchedCategory;
    }

    @Override
    public String toString() {
        return category;
    }
}
