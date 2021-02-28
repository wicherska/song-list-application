package pl.wicherska.songs.xml;


import javax.xml.bind.annotation.XmlEnumValue;

public enum XmlCategory {

        @XmlEnumValue("Rock") ROCK("Rock"),
        @XmlEnumValue("Alternative") ALTERNATIVE("Alternative"),
        @XmlEnumValue("R&B") RANDB("R&B");

        private final String category;

        XmlCategory(String category) {
            this.category = category;
        }

        public static XmlCategory fromString(String text) {
            XmlCategory matchedCategory = null;
            for (XmlCategory cat : XmlCategory.values()) {
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
