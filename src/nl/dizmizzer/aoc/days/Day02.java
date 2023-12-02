package nl.dizmizzer.aoc.days;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends Day {

    List<Game> games = new ArrayList<>();

    @Override
    public long solveOne() {
        long idSum = 0;
        for (Game game : games) {
            boolean isValid = true;
            for (Round r : game.roundList) {
                if (!isValid) continue;
                // 12 red cubes, 13 green cubes, and 14 blue cubes
                if (r.red > 12) {
                    isValid = false;
                    continue;
                }
                if (r.green > 13) {
                    isValid = false;
                    continue;
                }
                if (r.blue > 14) {
                    isValid = false;
                    continue;
                }
            }
            if (isValid) idSum += game.id;
        }
        return idSum;
    }

    @Override
    public long solveTwo() {
        long powerSum = 0;
        for (Game game : games) {
            long maxRed = 0;
            long maxGreen = 0;
            long maxBlue = 0;
            for (Round r : game.roundList) {
                maxRed = Math.max(maxRed, r.red);
                maxGreen = Math.max(maxGreen, r.green);
                maxBlue= Math.max(maxBlue, r.blue);
            }
            powerSum += (maxRed * maxGreen * maxBlue);
        }
        return powerSum;
    }

    @Override
    public void setup() {
        super.setup();
        for (String s : input) {
            String[] splitUp = s.split(": ");
            long id = Long.parseLong(splitUp[0].substring(5));
            Game game = new Game(id);
            games.add(game);
            String[] roundStrings = splitUp[1].split("; ");
            for (String round : roundStrings) {
                String[] balls = round.split(", ");
                long red = 0;
                long blue = 0;
                long green = 0;
                for (String ball : balls) {
                    String[] s1 = ball.split(" ");
                    long count = Long.parseLong(s1[0]);
                    if (s1[1].equalsIgnoreCase("red")) {
                        red = count;
                    }
                    if (s1[1].equalsIgnoreCase("blue")) {
                        blue = count;
                    }
                    if (s1[1].equalsIgnoreCase("green")) {
                        green = count;
                    }
                }
                game.roundList.add(new Round(blue, red, green));
            }
        }
    }

    private static class Game {
        public long id;
        public List<Round> roundList = new ArrayList<>();

        public Game(long id) {
            this.id = id;
        }
    }

    private static class Round {
        long blue;
        long red;
        long green;

        public Round(long blue, long red, long green) {
            this.blue = blue;
            this.red = red;
            this.green = green;
        }
    }
}
