package nl.dizmizzer.aoc.days;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 extends Day {

    private final List<GameInt> gameIntList = new ArrayList<>();

    @Override
    public long solveOne() {
        return gameIntList.stream().filter(this::isAdjecentToValue).mapToLong(gi -> gi.value).sum();
//        return 0;
    }

    @Override
    public long solveTwo() {
        long finalSum = 0;
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == '*') {
                    int finalX = x;
                    int finalY = y;
                    List<GameInt> l = gameIntList.stream().filter((gi) -> inBounds(finalX, finalY, gi)).toList();
                    if (l.size() > 1) {
                        finalSum += (long) l.get(0).value * l.get(1).value;
                    }
                }
            }
        }
        return finalSum;
    }

    public boolean isAdjecentToValue(GameInt gi) {
        for (int y = gi.y - 1; y <= gi.y + 1; y++) {
            if (y < 0 || y >= input.size()) continue;
            for (int x = gi.x - 1; x <= gi.x + gi.length; x++) {
                if (x >= 0 && x < input.get(y).length()) {
                    char c = input.get(y).charAt(x);
                    if (!Character.isDigit(c) && c != '.') return true;
                }
            }
        }
        return false;
    }

    public boolean inBounds(int x, int y, GameInt gi) {
        if (y >= gi.y - 1 && y <= gi.y + 1) {
            return x >= gi.x - 1 && x <= (gi.x + gi.length);
        }
        return false;
    }

    private static class GameInt {
        private int length;
        private int value;
        private int x;
        private int y;

        public GameInt(int length, int value, int x, int y) {
            this.length = length;
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public void setup() {
        super.setup();
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            int digit = 0;
            int length = 0;
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (Character.isDigit(c)) {
                    length++;
                    digit = digit * 10 + Character.digit(c, 10);
                } else {
                    if (length != 0) {
                        GameInt gi = new GameInt(length, digit, x - length, y);
                        gameIntList.add(gi);
                    }
                    length = 0;
                    digit = 0;
                }
            }

            if (length != 0) {
                GameInt gi = new GameInt(length, digit, line.length() - length, y);
                gameIntList.add(gi);

            }
        }
    }
}
