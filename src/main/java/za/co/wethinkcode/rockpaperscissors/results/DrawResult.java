package za.co.wethinkcode.rockpaperscissors.results;

import za.co.wethinkcode.rockpaperscissors.Game;
import za.co.wethinkcode.rockpaperscissors.Player;

import java.util.List;

// TODO implement this class

public class DrawResult extends GameResult{
    public DrawResult(List<Player> players) {
        super(players);
    }

    @Override
    public String toString() {
        return "Result is a DRAW";
    }
}
