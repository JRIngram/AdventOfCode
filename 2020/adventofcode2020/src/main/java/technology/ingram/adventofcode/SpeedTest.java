package technology.ingram.adventofcode;

import java.util.HashMap;
import java.security.InvalidParameterException;

public class SpeedTest{
    private int numberOfRuns;
    private HashMap<String, Double> results;
    private final int NUMBER_OF_DAYS  = 10;
    private final int NUMBER_OF_CHALLENGES_PER_DAY = 2;

    public SpeedTest(int numberOfRuns){
        this.numberOfRuns = numberOfRuns;
        this.results = new HashMap<String, Double>();
    }

    public double calculateAverage(Double[] timesToAverage){
        if(timesToAverage.length > 0){
            double totalTime = 0;
            for(int i = 0; i < timesToAverage.length; i++){
                totalTime += timesToAverage[i];
            }
            return totalTime / timesToAverage.length;
        }
        else{
            return 0;
        }
    }

    private Day selectDay(int day){
        if(!(day <= NUMBER_OF_DAYS && day >= 1)){
            throw new InvalidParameterException("Day must be between 1 - 10 inclusive");
        }
        Day dayToTest = null;
        switch(day){
            case 1:
                dayToTest = new DayOne();
                break;
            case 2:
                dayToTest = new DayTwo();
                break;
            case 3:
                dayToTest = new DayThree();
                break;               
            case 4:
                dayToTest = new DayFour();
                break;
            case 5:
                dayToTest = new DayFive();
                break;
            case 6:
                dayToTest = new DaySix();
                break;
            case 7:
                dayToTest = new DaySeven();
                break;
            case 8: 
                dayToTest = new DayEight();
                break;
            case 9:
                dayToTest = new DayNine();
                break;
            case 10: 
                dayToTest = new DayTen();
                break;
        }
        return dayToTest;
    }

    private void speedTestDayChallenge(int day, int challenge){
        if(day <= NUMBER_OF_DAYS && day >= 1 && challenge >=1 && challenge <= NUMBER_OF_CHALLENGES_PER_DAY){
            Day dayToTest = selectDay(day);
            Double[] runResults = new Double[this.numberOfRuns];
            for(int i = 0; i < this.numberOfRuns; i++){
                ResultsTuple runResult = dayToTest.runChallenge(challenge);
                runResults[i] = runResult.getTimeTakenToCalculateAnswer();
            }
            String resultsKey = day + ";" + challenge;
            double averageRunTime = calculateAverage(runResults);
            results.put(resultsKey, averageRunTime);

        }
        else if(day <= NUMBER_OF_DAYS && day >= 1 && !(challenge >=1 && challenge <= NUMBER_OF_CHALLENGES_PER_DAY)){
            throw new InvalidParameterException("Challenge must be between 1 and " + NUMBER_OF_CHALLENGES_PER_DAY + " inclusive.");
        }
        else if(!(day <= 10 && day >= 1) && (challenge == 1 || challenge == 2)){
            throw new InvalidParameterException("Day must be between 1 - " + NUMBER_OF_DAYS + " inclusive.");
        }
        else{
            throw new InvalidParameterException("Day must be between 1 - " + NUMBER_OF_DAYS + " inclusive; Challenge must be between 1 and " + NUMBER_OF_CHALLENGES_PER_DAY + ";");
        }
    }

    public void testAllDays(){
        String testStartString = "Running " + this.numberOfRuns + " for each day:";
        testStartString += "\n\t* With " + NUMBER_OF_DAYS + " days";
        testStartString += "\n\t* With " + NUMBER_OF_CHALLENGES_PER_DAY + " challenges per day";
        System.out.println(testStartString);

        for(int i = 1; i <= NUMBER_OF_DAYS; i++){
            for(int j = 1; j <= NUMBER_OF_CHALLENGES_PER_DAY; j++){
                speedTestDayChallenge(i, j);
            }
        }

        System.out.println("Testing complete!");
    }

    public String toString(){
        String str = "";
        for(int i = 1; i <= NUMBER_OF_DAYS; i++){
            for(int j = 1; j <= NUMBER_OF_CHALLENGES_PER_DAY; j++){
                String resultsKey = i + ";" + j;
                Double timeTaken = this.results.get(resultsKey);
                str += "\tDay " + i + ", Challenge " + j + ":\t" + timeTaken + "ms\n";
            }
        }
        return str;
    }
}