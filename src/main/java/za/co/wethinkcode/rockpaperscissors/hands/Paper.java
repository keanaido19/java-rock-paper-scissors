package za.co.wethinkcode.rockpaperscissors.hands;

public class Paper extends Hand {

    @Override
    public int compareTo(Hand o) {
        if (o instanceof Scissors)
            return -1;
        else if (o instanceof Rock) {
            return 1;
        }
        return 0;
    }

    @Override
    public Hand versus(Hand otherHand) {
        if (compareTo(otherHand) > 0) {
            return this;
        }
        return otherHand;
    }
}
