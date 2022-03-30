package za.co.wethinkcode.rockpaperscissors.results;

import za.co.wethinkcode.rockpaperscissors.Game;
import za.co.wethinkcode.rockpaperscissors.Player;

import java.util.List;

// TODO implement this class

public class GameResult {
    private final List<Player> players;


    public GameResult(List<Player> players) {
        this.players = players;
    }

    public List<Player> players() {
        return this.players;
    }
}
