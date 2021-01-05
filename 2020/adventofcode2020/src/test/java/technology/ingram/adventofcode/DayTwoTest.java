package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DayTwoTest {
    
    private DayTwo dayTwo;

    public DayTwoTest(){
        this.dayTwo = new DayTwo();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = dayTwo.runChallenge(1);
        long expected = 447;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = dayTwo.runChallenge(2);
        long expectedAnswer = 249;
        long actual = answer.getAnswer();
        assertEquals(expectedAnswer, actual);
    }
}
