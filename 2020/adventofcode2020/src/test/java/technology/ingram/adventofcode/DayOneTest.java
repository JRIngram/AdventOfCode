package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DayOneTest {
    
    private DayOne dayOne;

    public DayOneTest(){
        this.dayOne = new DayOne();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = dayOne.runChallenge(1);
        long expected = 1010884;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = dayOne.runChallenge(2);
        long expectedAnswer = 253928438;
        long actual = answer.getAnswer();
        assertEquals(expectedAnswer, actual);
    }
}
