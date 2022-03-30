package za.co.wethinkcode.rockpaperscissors;

import za.co.wethinkcode.rockpaperscissors.hands.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private Game currentGame;
    private Hand hand;
    private int wins = 0;

    public void join(Game game) {
        this.currentGame = game;
        game.addPlayerToGame(this);
    }

    public void choose(Hand hand) {
        this.hand = hand;
    }

    public List<Player> opponents() {
        List<Player> players = new ArrayList<>() {};
        for (Player player : currentGame.getPlayers()) {
            if (!this.equals(player)) {
                players.add(player);
            }
        }
        return players;
    }

    public int getWins() {
        return this.wins;
    }

    public void addWin() {
        wins = wins + 1;
    }

    public Hand getHand() {
        return this.hand;
    }
}