package pl.wicherska.songs.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wicherska.songs.handlers.UserAction;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScannerWrapperTest {
    private final Scanner scanner = mock(Scanner.class);
    private ScannerWrapper scannerWrapper;


    @BeforeEach
    void setUp() {
        scannerWrapper = new ScannerWrapper(scanner);
    }

    @Test
    void shouldReadEnumUntilGetValidOne() {
        when(scanner.nextLine()).thenReturn("votte", "vote");

        UserAction userAction = scannerWrapper.nextEnum(UserAction.class);

        assertEquals(UserAction.VOTE, userAction);
    }

    @Test
    void shouldReturnNextNonNegativeIntWithMessage() {
        when(scanner.nextLine()).thenReturn("4");

        int number = scannerWrapper.nextNonNegativeIntWithMessage("message");

        assertEquals(4, number);
    }

    @Test
    void shouldReadNextNonNegativeIntUntilGetValidOne() {
        when(scanner.nextLine()).thenReturn("-9", "9.7", "9");

        int number = scannerWrapper.nextNonNegativeInt();

        assertEquals(9, number);
    }

    @Test
    void shouldReadNextNonNegativeIntInRangeUntilGetValidOne() {
        when(scanner.nextLine()).thenReturn("-1", "2", "1");

        int number = scannerWrapper.nextNonNegativeIntInRange(2);

        assertEquals(1, number);
    }

    @Test
    void shouldReturnStringAndPrintMessage() {
        when(scanner.nextLine()).thenReturn("example");

        String fromUser = scannerWrapper.nextLineWithMessage("message");

        assertEquals("example", fromUser);
    }

    @Test
    void shouldReadUntilGetTrue() {
        when(scanner.nextLine()).thenReturn("ye", "yes");

        boolean fromUser = scannerWrapper.nextBoolean();

        assertTrue(fromUser);
    }

    @Test
    void shouldReadUntilGetFalse() {
        when(scanner.nextLine()).thenReturn("n", "no");

        boolean fromUser = scannerWrapper.nextBoolean();

        assertFalse(fromUser);
    }
}
