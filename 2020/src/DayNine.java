package technology.ingram.adventofcode.daynine;

import technology.ingram.adventofcode.Utils;
import java.util.ArrayList;
import java.util.Collections;

public class DayNine{
    private static final int PREAMBLE = 25;
    private static final String INPUT_FILE = "inputs/dayNine.txt";
    private static final int NUMBER_OF_ROWS = 1000;
    private static int challenge;
    public static void main(String args[]){
        long startTime = System.nanoTime();
        challenge = Integer.parseInt(args[0]);
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
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
        System.out.println("Answer: " + answer);
        System.out.println("Found in: " + totalTimeToRunMS + "ms");

    }

    public static long challengeOne(String[] inputRows){
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

    public static long challengeTwo(String[] inputRows, long numberToSumTo){
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