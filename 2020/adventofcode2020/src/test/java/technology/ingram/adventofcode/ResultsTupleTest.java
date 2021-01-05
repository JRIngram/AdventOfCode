package technology.ingram.adventofcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ResultsTupleTest 
{
    @Test
    public void canReturnAGivenAnswer()
    {
        ResultsTuple results = new ResultsTuple(10, 19.43);
        long expected = 10;
        long actual = results.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    public void canReturnAGivenTimeTakenToCalculateAnswer()
    {
        ResultsTuple results = new ResultsTuple(10, 19.43);
        double expected = 19.43;
        double actual = results.getTimeTakenToCalculateAnswer();
        assertEquals(expected, actual, 0.0);
    }
}
