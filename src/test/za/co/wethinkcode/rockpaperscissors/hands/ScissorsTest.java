package za.co.wethinkcode.rockpaperscissors.hands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ScissorsTest {
    private static Scissors scissors;

    @BeforeAll
    static void setUp() {
        scissors = new Scissors();
    }

    @Test
    void greaterThanPaper() {
        Paper paper = new Paper();

        assertThat(scissors, greaterThan(paper));
    }

    @Test
    void lessThanRock() {
        Rock rock = new Rock();

        assertThat(scissors, lessThan(rock));
    }

    @Test
    void equalsTo() {
        Scissors otherScissors = new Scissors();

        assertThat(ScissorsTest.scissors, comparesEqualTo(otherScissors));
    }
}
