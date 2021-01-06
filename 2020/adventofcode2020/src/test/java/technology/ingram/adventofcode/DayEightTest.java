package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DayEightTest {
    private DayEight dayEight;

    public DayEightTest(){
        this.dayEight = new DayEight();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = dayEight.runChallenge(1);
        long expected = 1949;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = dayEight.runChallenge(2);
        long expected = 2092;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }
}
