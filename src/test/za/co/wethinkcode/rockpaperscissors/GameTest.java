package za.co.wethinkcode.rockpaperscissors;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.rockpaperscissors.hands.Hand;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;


public class GameTest extends TestSupport {

    // A test double that we can explicitly make lose or win for our tests
    static class FakeHand extends Hand {
        private final boolean alwaysWin;

        public FakeHand(boolean alwaysWin) {
            this.alwaysWin = alwaysWin;
        }

        public Hand versus(Hand other) {
            return alwaysWin ? this : other;
        }

        @Override
        public int compareTo(Hand o) {
            return alwaysWin ? 1 : 0;
        }
    }

    @Test
    void canGetUsersWhoHaveNotChosenHands() {
        // Given a game with two players
        Game game = new Game();
        Player readyPlayerOne = addNewPlayerTo(game);
        Player notReadyPlayerTwo = addNewPlayerTo(game);
        // And only one has chosen a hand
        readyPlayerOne.choose(giveAnyRandomHand());

        // When getPlayersStillChoosing() is called
        List<Player> pendingPlayers = game.getPlayersStillChoosing();

        // Then the returned list only contains notReadyPlayerTwo
        assertThat(pendingPlayers, contains(notReadyPlayerTwo));
        assertThat(pendingPlayers, not(contains(readyPlayerOne)));
    }

}
