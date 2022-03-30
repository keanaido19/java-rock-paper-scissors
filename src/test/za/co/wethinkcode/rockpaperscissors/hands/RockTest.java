package za.co.wethinkcode.rockpaperscissors.hands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RockTest {
    private static Rock rock;

    @BeforeAll
    static void setUp() {
        rock = new Rock();
    }

    @Test
    void greaterThanScissors() {
        Scissors scissors = new Scissors();

        assertThat(rock, greaterThan(scissors));
    }

    @Test
    void lessThanPaper() {
        Paper paper = new Paper();

        assertThat(rock, lessThan(paper));
    }

    @Test
    void equalToRock() {
        Rock otherRock = new Rock();

        assertThat(rock, comparesEqualTo(otherRock));
    }
}
