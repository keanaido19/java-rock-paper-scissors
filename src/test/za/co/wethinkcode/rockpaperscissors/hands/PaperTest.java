package za.co.wethinkcode.rockpaperscissors.hands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PaperTest {
    private static Paper paper;

    @BeforeAll
    static void setUp() {
        paper = new Paper();
    }

    @Test
    void greaterThanRock() {
        Rock rock = new Rock();

        assertThat(paper, greaterThan(rock));
    }

    @Test
    void lessThanScissors() {
        Scissors scissors = new Scissors();

        assertThat(paper, lessThan(scissors));
    }

    @Test
    void equalToPaper() {
        Paper otherPaper = new Paper();

        assertThat(paper, comparesEqualTo(otherPaper));
    }
}
