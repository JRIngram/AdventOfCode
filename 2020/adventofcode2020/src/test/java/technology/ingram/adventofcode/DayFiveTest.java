package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DayFiveTest {
    private DayFive dayFive;

    public DayFiveTest(){
        this.dayFive = new DayFive();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = dayFive.runChallenge(1);
        long expected = 874;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = dayFive.runChallenge(2);
        long expected = 594;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }
    
}
