package pl.wicherska.songs.domain;


public enum Category {
    ROCK("Rock"),
    ALTERNATIVE("Alternative"),
    RANDB("R&B");

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
