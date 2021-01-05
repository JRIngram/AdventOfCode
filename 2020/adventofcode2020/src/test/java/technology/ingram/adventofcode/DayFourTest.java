package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DayFourTest {

    private DayFour dayFour;

    public DayFourTest(){
        this.dayFour = new DayFour();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = dayFour.runChallenge(1);
        long expected = 170;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void partTwoAnswerCorrect(){
        ResultsTuple answer = dayFour.runChallenge(2);
        long expected = 103;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }
}
