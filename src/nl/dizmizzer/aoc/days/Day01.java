package nl.dizmizzer.aoc.days;

public class Day01 extends Day {
    @Override
    public long solveOne() {
        return input.stream()
                .mapToInt(s -> getFirstDigit(s, false) * 10 + getLastDigit(s, false)).sum();
    }

    @Override
    public long solveTwo() {
        return input.stream()
                .mapToInt(s -> getFirstDigit(s, true) * 10 + getLastDigit(s, true)).sum();
    }

    public static int getFirstDigit(String s, boolean includeText) {
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (Character.isDigit(c))
                return Character.digit(c, 10);
            if (includeText && getNumber(s, i) != -1) return getNumber(s, i);
        }
        return 0;
    }
    public static int getLastDigit(String s, boolean includeText) {
        char c;
        for (int i = s.length() - 1; i >= 0; i--) {
            c = s.charAt(i);
            if (Character.isDigit(c))
                return Character.digit(c, 10);
            if (includeText && getNumber(s, i) != -1) return getNumber(s, i);
        }
        return 0;
    }

    public static int getNumber(String s, int start) {
        if (s.substring(start).startsWith("one")) return 1;
        if (s.substring(start).startsWith("two")) return 2;
        if (s.substring(start).startsWith("three")) return 3;
        if (s.substring(start).startsWith("four")) return 4;
        if (s.substring(start).startsWith("five")) return 5;
        if (s.substring(start).startsWith("six")) return 6;
        if (s.substring(start).startsWith("seven")) return 7;
        if (s.substring(start).startsWith("eight")) return 8;
        if (s.substring(start).startsWith("nine")) return 9;
        return -1;
    }

}
