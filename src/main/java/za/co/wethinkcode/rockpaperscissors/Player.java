package za.co.wethinkcode.rockpaperscissors;

import za.co.wethinkcode.rockpaperscissors.hands.Hand;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private Game currentGame;
    private Hand hand;
    private int wins = 0;

    public void join(Game game) {
        this.currentGame = game;
    }

    public void choose(Hand hand) {
        this.hand = hand;
    }

    public List<Player> opponents() {
        return List.of();
    }

    public int getWins() {
        return this.wins;
    }

    public void addWin() {
        wins = wins + 1;
    }
}