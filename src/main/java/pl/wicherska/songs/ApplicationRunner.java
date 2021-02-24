package pl.wicherska.songs;

import pl.wicherska.songs.domain.Case;
import pl.wicherska.songs.handlers.*;
import pl.wicherska.songs.interfaces.Handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationRunner {
    private final Map<Case, Handler> handlers = new HashMap<>();
    private final AddingHandler addingHandler;
    private final CategorizingHandler categorizingHandler;
    private final ReportGeneratorHandler reportGeneratorHandler;
    private final VotingHandler votingHandler;
    private final ZeroingHandler zeroingHandler;
    private final Scanner scanner;

    public ApplicationRunner(AddingHandler addingHandler,
                             CategorizingHandler categorizingHandler,
                             ReportGeneratorHandler reportGeneratorHandler,
                             VotingHandler votingHandler,
                             ZeroingHandler zeroingHandler,
                             Scanner scanner) {
        this.addingHandler = addingHandler;
        this.categorizingHandler = categorizingHandler;
        this.reportGeneratorHandler = reportGeneratorHandler;
        this.votingHandler = votingHandler;
        this.zeroingHandler = zeroingHandler;
        this.scanner = scanner;
        setHandlers();
    }

    private void setHandlers(){
        handlers.put(Case.ADD, addingHandler);
        handlers.put(Case.CATEGORIZED, categorizingHandler);
        handlers.put(Case.RANKING, reportGeneratorHandler);
        handlers.put(Case.VOTE, votingHandler);
        handlers.put(Case.ZERO, zeroingHandler);
    }

    public void run(){
        System.out.println("Wpisz jedną z opcji " + Arrays.toString(Case.values()));
        handlers.get(Case.valueOf(scanner.nextLine().toUpperCase())).handle();
    }


}