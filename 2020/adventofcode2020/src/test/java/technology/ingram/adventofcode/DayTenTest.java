package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DayTenTest {
    private DayTen dayTen;

    public DayTenTest(){
        this.dayTen = new DayTen();
    }

    @Test
    public void partOneAnswerCorrect(){
        ResultsTuple answer = dayTen.runChallenge(1);
        long expected = 1625;
        long actual = answer.getAnswer();
        assertEquals(expected, actual);
    }

}
