package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DaySixTest {
    private DaySix daySix;

    public DaySixTest(){
        this.daySix = new DaySix();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = daySix.runChallenge(1);
        long expected = 6763;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = daySix.runChallenge(2);
        long expected = 3512;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }
}
