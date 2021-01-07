package technology.ingram.adventofcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SpeedTestAveragesTest {

    public SpeedTestAveragesTest(){

    }

    @Test
    public void returnZeroOnLengthZeroInput(){
        SpeedTest speedTest = new SpeedTest(0);
        Double[] testInputs = {};
        double expected = 0.0;
        double actual = speedTest.calculateAverage(testInputs);
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void averagesThreeZeroesCorrectly(){
        SpeedTest speedTest = new SpeedTest(0);
        Double[] testInputs = {0.0, 0.0, 0.0};
        double expected = 0.0;
        double actual = speedTest.calculateAverage(testInputs);
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void averagesAllOnesToOne(){
        SpeedTest speedTest = new SpeedTest(0);
        Double[] testInputs = {1.0, 1.0, 1.0};
        double expected = 1.0;
        double actual = speedTest.calculateAverage(testInputs);
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void averagesThreeNumbersCorrectly(){
        SpeedTest speedTest = new SpeedTest(0);
        Double[] testInputs = {1.0, 2.0, 3.0};
        double expected = 2.0;
        double actual = speedTest.calculateAverage(testInputs);
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void averagesThreeLargeNumbersCorrectly(){
        SpeedTest speedTest = new SpeedTest(0);
        Double[] testInputs = {1000000.0, 2000000.0, 3000000.0};
        double expected = 2000000.0;
        double actual = speedTest.calculateAverage(testInputs);
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void averagesNineNumbersCorrectly(){
        SpeedTest speedTest = new SpeedTest(0);
        Double[] testInputs = {1000000.0, 2000000.0, 3000000.0, 1000000.0, 2000000.0, 3000000.0, 1000000.0, 2000000.0, 3000000.0};
        double expected = 2000000.0;
        double actual = speedTest.calculateAverage(testInputs);
        assertEquals(expected, actual, 0.0);
    }
}
