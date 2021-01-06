package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DayNineTest {
    private DayNine dayNine;

    public DayNineTest(){
        this.dayNine = new DayNine();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = dayNine.runChallenge(1);
        long expected = 3199139634L;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = dayNine.runChallenge(2);
        long expected = 438559930;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }
    
}
