package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DaySevenTest {
    private DaySeven daySeven;

    public DaySevenTest(){
        this.daySeven = new DaySeven();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = daySeven.runChallenge(1);
        long expected = 115;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = daySeven.runChallenge(2);
        long expected = 1250;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }
}
