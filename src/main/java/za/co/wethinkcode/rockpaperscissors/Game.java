package za.co.wethinkcode.rockpaperscissors;

import za.co.wethinkcode.rockpaperscissors.results.DrawResult;
import za.co.wethinkcode.rockpaperscissors.results.GameResult;
import za.co.wethinkcode.rockpaperscissors.results.WinLossResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    static class NotEnoughPlayersException extends RuntimeException {}

    static class PlayerWithUnchosenHandException extends RuntimeException {}

    static class StillInProgressException extends RuntimeException {}

    static class GameEndedException extends RuntimeException {}

    private final GameConfig gameConfig;

    private final List<Player> players = new ArrayList<>() {};

    private boolean gameOver = false;

    public Game() {
        this.gameConfig = new DefaultGameConfig();
    }

    public Game(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public GameResult play() {
        if (gameOver) throw new GameEndedException();
        if (!getPlayersStillChoosing().isEmpty())
            throw new PlayerWithUnchosenHandException();
        if (gameConfig.getMinimumPlayers() < 2)
            throw new NotEnoughPlayersException();
        if (!getPlayersStillChoosing().isEmpty())
            throw new PlayerWithUnchosenHandException();

        for (Player player : getPlayers()) {
            for (Player opponent : player.opponents()) {
                if (player.getHand().compareTo(opponent.getHand()) > 0) {
                    player.addWin();
                }
            }
        }
        gameOver = true;
        return getResult();
    }

    public GameResult getResult() {
        if (!getPlayersStillChoosing().isEmpty())
            throw new StillInProgressException();

        boolean draw = true;

        List<Integer> scores = new ArrayList<>() {};

        for (Player player : getPlayers()) {
            scores.add(player.getWins());
        }

        int winningScore = Collections.max(scores);

        if (winningScore == 0) {
            return new DrawResult(getPlayers());
        }

        return new WinLossResult(getPlayers());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getPlayersStillChoosing() {
        List<Player> pendingPlayers = new ArrayList<>() {};
        for (Player player : getPlayers()) {
            if (player.getHand() == null) {
                pendingPlayers.add(player);
            }
        }
        return pendingPlayers;
    }

    public void addPlayerToGame(Player player) {
        players.add(player);
    }
}