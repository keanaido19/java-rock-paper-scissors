package za.co.wethinkcode.rockpaperscissors;

public class GameConfig {
    private final int numberOfPlayers;

    public GameConfig(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getMinimumPlayers() {
        return this.numberOfPlayers;
    }
}
