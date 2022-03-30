package za.co.wethinkcode.rockpaperscissors;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;

public class PlayerTest {
    @Test
    void testCanGetOpponentsForAGame() {
        Player playerOne = new Player();
        Player playerTwo = new Player();
        Player playerThree = new Player();

        Game game = new Game();

        playerOne.join(game);
        playerTwo.join(game);

        assertThat(playerOne.opponents(), contains(playerTwo));
        assertThat(playerOne.opponents(), not(contains(playerThree)));
    }

}
