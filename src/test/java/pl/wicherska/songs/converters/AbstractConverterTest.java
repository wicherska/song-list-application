package pl.wicherska.songs.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.wicherska.songs.domain.Category;
import pl.wicherska.songs.domain.Song;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractConverterTest {
    private final String message = "Abstract method. Dummy implementation";
    private AbstractConverter<String> abstractConverter;

    @BeforeEach
    void setUp() {
        abstractConverter = new AbstractConverter<>() {
            @Override
            protected Optional<Song> mapToSong(String data) {
                throw new RuntimeException(message);
            }

            @Override
            protected String mapFromSong(Song song) {
                throw new RuntimeException(message);
            }
        };
    }

    @Test
    void shouldReturnTrimmedString() {
        String result = abstractConverter.validateString(" example ");

        assertEquals("example", result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "  "
    })
    @EmptySource
    void shouldThrowIllegalArgumentExceptionInCaseOfIncorrectString(String field) {
        assertThrows(
                IllegalArgumentException.class,
                () -> abstractConverter.validateString(field)
        );
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowNullPointerExceptionInCaseOfIncorrectString(String field) {
        assertThrows(
                NullPointerException.class,
                () -> abstractConverter.validateString(field)
        );
    }

    @Test
    void shouldReturnCategoryFromString() {
        Category category = abstractConverter.validateCategory(" rock ");

        assertEquals(Category.ROCK, category);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "  "
    })
    @EmptySource
    void shouldThrowIllegalArgumentExceptionInCaseOfIncorrectCategory(String field) {
        assertThrows(
                IllegalArgumentException.class,
                () -> abstractConverter.validateCategory(field)
        );
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowNullPointerExceptionInCaseOfIncorrectCategory(String field) {
        assertThrows(
                NullPointerException.class,
                () -> abstractConverter.validateCategory(field)
        );
    }

    @Test
    void shouldReturnNumberOfVotesFromString() {
        int number = abstractConverter.validateVotes(" 5 ");

        assertEquals(5, number);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "  ",
            "-5",
            "5.7",
            "pp"
    })
    @EmptySource
    void shouldThrowIllegalArgumentExceptionInCaseOfIncorrectVotes(String field) {
        assertThrows(
                IllegalArgumentException.class,
                () -> abstractConverter.validateVotes(field)
        );
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowNullPointerExceptionInCaseOfIncorrectVotes(String field) {
        assertThrows(
                NullPointerException.class,
                () -> abstractConverter.validateVotes(field)
        );
    }
}