package technology.ingram.adventofcode;

import java.util.ArrayList;
import java.util.Collections;

public class DayNine extends Day{
    private final int PREAMBLE = 25;
    private final String INPUT_FILE = "inputs/dayNine.txt";
    private final int NUMBER_OF_ROWS = 1000;

    public DayNine(){
        super();
    }

    public ResultsTuple runChallenge(int challenge){
        long startTime = System.nanoTime();
        String[] inputRows = Utils.readFile(NUMBER_OF_ROWS, INPUT_FILE);
        long answer = 0;
        long challengeOneAnswer = challengeOne(inputRows);
        if(challenge == 1){
            answer = challengeOneAnswer;
        }
        else if(challenge == 2){
            answer = challengeTwo(inputRows, challengeOneAnswer);
        }
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        ResultsTuple results = new ResultsTuple(answer, totalTimeToRunMS);
        System.out.println("Answer: " + results.getAnswer() + "; Found in: " + results.getTimeTakenToCalculateAnswer() + "ms");
        return results;
    }

    private long challengeOne(String[] inputRows){
        int counter = 0;
        ArrayList<Long> numberList = new ArrayList<Long>();
        for(int i = PREAMBLE; i < inputRows.length; i++){
            long compositeNumberToEqual = Long.parseLong(inputRows[i]);
            int preambleStart = 0 + counter;
            int preambleEnd = preambleStart + PREAMBLE;
            for(int j = preambleStart; j < preambleEnd && j < inputRows.length; j++){
                numberList.add(Long.parseLong(inputRows[j]));
            }
            long[] validSummands = Utils.findTwoSummands(numberList, compositeNumberToEqual);
            if(validSummands[0] == 0 && validSummands[1] == 0){
                return compositeNumberToEqual;
            }
            numberList.clear();
            counter++;
        }
        return 0;
    }

    private long challengeTwo(String[] inputRows, long numberToSumTo){
        int counter = 0;
        ArrayList<Long> summands = new ArrayList<Long>();
        while(Long.parseLong(inputRows[counter]) < numberToSumTo){
            long totalSum = 0;
            for(int j = counter; j < inputRows.length; j++){
                long parsedRow = Long.parseLong(inputRows[j]);
                totalSum += parsedRow;
                if(totalSum < numberToSumTo){
                    summands.add(parsedRow);
                }
                else if(totalSum == numberToSumTo){
                    Collections.sort(summands);
                    return summands.get(0) + summands.get(summands.size() - 1);
                }
            }
            summands.clear();
            counter++;
        }
        return 0;
    }
}