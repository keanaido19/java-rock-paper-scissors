package za.co.wethinkcode.rockpaperscissors.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UserInputPromptTest {
    @Test
    void capturePromptsUserWithDisplayText() throws IOException {
        // Redirect System.out to buffer
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        // Given UserInputPrompt with displayText
        String displayText = "What is the airspeed velocity of an unladen swallow?";
        UserInputPrompt input = new UserInputPrompt(displayText);

        // When capture is called
        input.doPrompt();
        bo.flush();

        // Then displayText is printed to console
        assertThat(bo.toString(), is(displayText + "\n"));
    }

    @Test
    void captureSavesUserInputAsString() {
        // Redirect System.in to buffer and wire up scanner
        String cannedInput = "eggs";
        ByteArrayInputStream bi = new ByteArrayInputStream(cannedInput.getBytes());
        Scanner scanner = new Scanner(System.in);
        System.setIn(bi);

        // Given a UserInputPrompt
        UserInputPrompt input = new UserInputPrompt("Complete the missing word: Spam and e__s");
        // When doPrompt is completed
        input.doPrompt();

        // Then input is saved
        assertThat(input.value(), is(cannedInput));
    }

}