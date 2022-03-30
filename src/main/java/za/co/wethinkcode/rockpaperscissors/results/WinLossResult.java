package za.co.wethinkcode.rockpaperscissors.results;

import za.co.wethinkcode.rockpaperscissors.Game;
import za.co.wethinkcode.rockpaperscissors.Player;

import java.util.ArrayList;
import java.util.List;

// TODO implement this class

public class WinLossResult extends GameResult{
    public WinLossResult(List<Player> players) {
        super(players);
    }

    @Override
    public String toString() {
        List<String> output = new ArrayList<>() {};
        List<String> winners = new ArrayList<>();
        int winningScore = 0;
        int i = 0;
        for (Player player : players()) {
            int score = player.getWins();
            int lost = 0;

            for (Player opponent: player.opponents()) {
                if (player.getHand().compareTo(opponent.getHand()) < 0) lost++;
            }

            output.add("(Player " + i + ") Won: " + score + ", Lost: " + lost);
            if (score > winningScore) winningScore = score;
            i++;
        }

        i = 0;
        for (Player player : players()) {
            int score = player.getWins();
            if (score == winningScore) {
                winners.add("(Player " + i + ")");
            }
            i++;
        }
        return String.join("\n", output) + "\n\n" +
                String.join(", ", winners) + " Won!";
    }
}
