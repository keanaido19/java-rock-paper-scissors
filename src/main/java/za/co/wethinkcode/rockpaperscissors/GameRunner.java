package za.co.wethinkcode.rockpaperscissors;

import za.co.wethinkcode.rockpaperscissors.hands.Hand;
import za.co.wethinkcode.rockpaperscissors.hands.Paper;
import za.co.wethinkcode.rockpaperscissors.hands.Rock;
import za.co.wethinkcode.rockpaperscissors.hands.Scissors;
import za.co.wethinkcode.rockpaperscissors.results.GameResult;
import za.co.wethinkcode.rockpaperscissors.ui.UserInputPrompt;

public class GameRunner {

    public static void main(String[] args) {
        // start game
        System.out.println("~~~~~~~~~ Welcome to Rock Paper SCissors! ~~~~~~~~~");

        UserInputPrompt howManyPlayers = new UserInputPrompt("How many players for this game?");
        howManyPlayers.doPrompt();

        GameConfig config =
                new GameConfig(Integer.parseInt(howManyPlayers.value()));
        Game game = new Game(config);

        for (int i = 0; i < config.getMinimumPlayers(); i++) {
            Player newPlayer = new Player();
            newPlayer.join(game);

            UserInputPrompt chooseAHand = new UserInputPrompt("(Player " + i + ") Choose a hand [R]ock,[P]aper or [S]cissors:");
            chooseAHand.doPrompt();

            Hand hand;
            switch (chooseAHand.value()) {
                case "R":
                    newPlayer.choose(new Rock());
                    break;
                case "P":
                    newPlayer.choose(new Paper());
                    break;
                case "S":
                    newPlayer.choose(new Scissors());
                    break;
                default:
                    throw new IllegalArgumentException("Must be one of: [R]ock,[P]aper or [S]cissors");
            }
        }

        UserInputPrompt resolveGame = new UserInputPrompt("All players have chosen. Press enter to resolve game result.");
        resolveGame.doPrompt();

        GameResult result = game.play();

        new UserInputPrompt(result.toString());
        System.out.println(result);
    }
}
