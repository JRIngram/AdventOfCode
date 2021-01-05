package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DayThreeTest {

    private DayThree dayThree;

    public DayThreeTest(){
        this.dayThree = new DayThree();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = dayThree.runChallenge(1);
        long expected = 234;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = dayThree.runChallenge(2);
        long expected = 5813773056L;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }
}
