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
        int i = 0;
        for (Player player : players()) {
            output.add("(Player " + i + ") Won: " + player.getWins() + ", " +
                    "Lost: " + (player.opponents().size() - player.getWins()));
            i++;
        }

        return String.join("\n", output);
    }
}
