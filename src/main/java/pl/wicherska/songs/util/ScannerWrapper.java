package pl.wicherska.songs.util;

import java.util.Arrays;
import java.util.Scanner;

public class ScannerWrapper {
    private final Scanner scanner;

    public ScannerWrapper(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T extends Enum<T>> T nextEnum(Class<T> enumClass) {
        System.out.println("Please provide one of the options: " + Arrays.toString(enumClass.getEnumConstants()));
        T enumValue;
        while (true) {
            try {
                enumValue = Enum.valueOf(enumClass, nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please provide one of the options: " + Arrays.toString(enumClass.getEnumConstants()));
            }
        }
        return enumValue;
    }

    public int nextNonNegativeIntWithMessage(String text) {
        System.out.println(text);
        return nextNonNegativeInt();
    }

    public int nextNonNegativeInt() {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(nextLine());
                if (number < 0) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please provide positive integer");
            }
        }
        return number;
    }

    //todo comment
    /**
     * This is a Javadoc
     */
    public int nextNonNegativeIntInRange(int range) {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(nextLine());
                if (number < 0) {
                    throw new IllegalArgumentException();
                } else if (number >= range) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please provide positive integer in range (0-" + (range - 1) + ")");
            }
        }
        return number;
    }

    public String nextLineWithMessage(String text) {
        System.out.println(text);
        return nextLine();
    }

    public String nextLine() {
        String line;
        do {
            line = scanner.nextLine();
        } while (line.isBlank());
        return line.trim();
    }

    public boolean nextBoolean() {
        while (true) {
            System.out.println("Please provide yes or no");
            String fromUser = nextLine();
            if ("YES".equalsIgnoreCase(fromUser)) {
                return true;
            } else if ("NO".equalsIgnoreCase(fromUser)) {
                return false;
            }
        }
    }
}

