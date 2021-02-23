package pl.wicherska.songs.handlers;

import pl.wicherska.songs.generators.ReportGeneratorFactory;
import pl.wicherska.songs.interfaces.Handler;

import java.util.Scanner;

public class CategorizingHandler implements Handler {
    private final Scanner scanner;
    private ReportGeneratorFactory reportGeneratorFactory;

    public CategorizingHandler(Scanner scanner, ReportGeneratorFactory reportGeneratorFactory) {
        this.scanner = scanner;
        this.reportGeneratorFactory = reportGeneratorFactory;
    }

    @Override
    public void handle() {

    }
}
