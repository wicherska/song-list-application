package pl.wicherska.songs;

import pl.wicherska.songs.domain.UserAction;
import pl.wicherska.songs.interfaces.UserActionHandler;
import pl.wicherska.songs.util.ScannerWrapper;

import java.util.Map;

public class ApplicationRunner implements Runnable {
    private final Map<UserAction, UserActionHandler> handlers;
    private final ScannerWrapper scannerWrapper;

    public ApplicationRunner(Map<UserAction, UserActionHandler> handlers, ScannerWrapper scannerWrapper) {
        this.handlers = handlers;
        this.scannerWrapper = scannerWrapper;
    }

    public void run() {
        do {
            handlers.get(scannerWrapper.nextEnum(UserAction.class)).handle();
            System.out.println("Do you want to continue?");
        } while (scannerWrapper.nextBoolean());
    }
}
