package za.co.wethinkcode.rockpaperscissors;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.rockpaperscissors.hands.Hand;
import za.co.wethinkcode.rockpaperscissors.hands.Paper;
import za.co.wethinkcode.rockpaperscissors.hands.Rock;
import za.co.wethinkcode.rockpaperscissors.hands.Scissors;
import za.co.wethinkcode.rockpaperscissors.results.DrawResult;
import za.co.wethinkcode.rockpaperscissors.results.GameResult;
import za.co.wethinkcode.rockpaperscissors.results.WinLossResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static za.co.wethinkcode.rockpaperscissors.TestSupport.*;


public class GameplayTest {
    @Test
    void defaultGameConfigSetsMinimumPlayersToTwo() {
        DefaultGameConfig defaultGameConfig = new DefaultGameConfig();
        assertThat(defaultGameConfig.getMinimumPlayers(), equalTo(2));
    }

    @Test
    void cannotPlayWithLessThanMinimumPlayers() {
        GameConfig config = new GameConfig(1);
        Game game = new Game(config);

        assertThrows(Game.NotEnoughPlayersException.class,
                () -> game.play());
    }

    @Test
    void cannotPlayBeforePlayersChooseHands() {
        GameConfig config = new GameConfig(1);
        Game game = new Game(config);

        Player playerOne = new Player();
        playerOne.join(game);

        assertThrows(Game.PlayerWithUnchosenHandException.class,
                () -> game.play());
    }

    @Test
    void getAllPlayersInGame() {
        GameConfig config = new GameConfig(1);

        Game game = new Game(config);
        Player playerOne = new Player();
        playerOne.join(game);

        assertThat(game.getPlayers(), contains(playerOne));
    }

    @Test
    void cannotGetGameResultBeforePlaying() {
        Game game = new Game(new GameConfig(1));
        Player player = new Player();
        player.join(game);

        assertThrows(Game.StillInProgressException.class,
                () -> game.getResult());
    }

    @Test
    void resultIsDrawWhenAllPlayersChooseSameHand() {
        List<Hand> allHands = List.of(new Rock(), new Paper(), new Scissors()); // list of all hands

        allHands.forEach(pickedHand -> { // for each hand, play a game
            Game game = new Game();

            List<Player> players = List.of(new Player(), new Player()); // list of two players
            players.forEach(player -> {
                player.join(game); // join same game
                player.choose(pickedHand); // choose same picked hand
            });

            GameResult result = game.play();

            assertThat(result, instanceOf(DrawResult.class));
            assertThat(List.of(result.players()), contains(players));
        });
    }

    @Test
    void resultIsWinWhenPlayersChooseDifferentHands() {
        List<Hand> allHands = new ArrayList<>(
                List.of(new Rock(), new Paper(), new Scissors())); // list of all hands
        List<Player> players = List.of(new Player(), new Player()); // list of two players

        Game game = new Game();

        players.forEach(player -> { // for each player
            player.join(game); // join same game
            Hand pickedHand = allHands.get(new Random().nextInt(allHands.size()));
            player.choose(pickedHand); // choose hand at random
            allHands.remove(pickedHand); // remove chosen hand from allHands
        });

        GameResult result = game.play();

        assertThat(result, instanceOf(WinLossResult.class));
        assertThat(List.of(result.players()), contains(players));
    }

    @Test
    void updatesPlayersWinCountsAfterGameResult() {
        Game game = new Game();
        Player loser = addNewPlayerTo(game);
        Player winner = addNewPlayerTo(game);

        loser.choose(giveAnyRandomHand());
        winner.choose(giveAnyWinningHandComparedTo(loser.getHand()));

        GameResult result = game.play();

        assertThat(result, instanceOf(WinLossResult.class));
        assertThat(loser.getWins(), equalTo(0));
        assertThat(winner.getWins(), equalTo(1));
    }

    @Test
    void cannotPlayAnEndedGame() {
        // Given a game with two players
        Game game = giveNewGameWithPlayers(2);
        game.getPlayers()
                .forEach(player -> player.choose(giveAnyRandomHand()));
        // And game has been played
        assertThat(game.getPlayers().size(), is(2));
        game.play();

        // When play() is called again,
        // Then an GameCompleteException is thrown
        assertThrows(Game.GameEndedException.class,
                () -> game.play());

    }


}
