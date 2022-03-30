package za.co.wethinkcode.rockpaperscissors;

import za.co.wethinkcode.rockpaperscissors.hands.Hand;
import za.co.wethinkcode.rockpaperscissors.hands.Paper;
import za.co.wethinkcode.rockpaperscissors.hands.Rock;
import za.co.wethinkcode.rockpaperscissors.hands.Scissors;

import java.util.List;
import java.util.Random;

public class TestSupport {

    private static final List<Hand> allHands = List.of(new Rock(), new Paper(), new Scissors());

    protected static Hand giveAnyRandomHand() {
        return allHands.get(new Random().nextInt(allHands.size()));
    }

    protected static Hand giveAnyLosingHandComparedTo(Hand hand) {
        return allHands.stream().filter(h -> h.compareTo(hand) < 0).findAny().get();
    }

    protected static Hand giveAnyWinningHandComparedTo(Hand hand) {
        return allHands.stream().filter(h -> h.compareTo(hand) > 0).findAny().get();
    }

    protected static Game giveNewGameWithPlayers(int playerCount) {
        Game game = new Game();
        for (int i = 0; i < playerCount; i++) {
            addNewPlayerTo(game);
        }

        return game;
    }

    protected static Player addNewPlayerTo(Game game) {
        Player player = new Player();
        player.join(game);
        return player;
    }

}
