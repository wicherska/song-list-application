package pl.wicherska.songs;

import pl.wicherska.songs.core.Config;

public class Main {

    public static void main(String[] args) {
        Config config = Config.getInstance();
        if (config.isSongFilePathSetCorrectly(args)) {
            config.applicationRunner().run();
        }
    }
}
