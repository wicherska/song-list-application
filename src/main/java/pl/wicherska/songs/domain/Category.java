package pl.wicherska.songs.domain;


import java.util.stream.Stream;

public enum Category {
    ROCK("Rock"),
    ALTERNATIVE("Alternative"),
    RNB("R&B");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public static Category fromString(String text) {
        return Stream.of(Category.values())
                .filter(cat -> cat.category.equalsIgnoreCase(text))
                .findFirst()
                .orElse(null);
    }

    public String getCategoryName() {
        return category;
    }
}
