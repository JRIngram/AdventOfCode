package technology.ingram.adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import technology.ingram.adventofcode.Utils;

class DayOne{
    private final String INPUT_FILE = "inputs/dayOne.txt";
    private final int NUMBER_OF_ROWS = 200;
    private final int COMPOSITE_NUMBER = 2020;

    public DayOne(){

    }

    public double runChallenge(int challenge)
    {
        long startTime = System.nanoTime();
        ArrayList<Long> smallerThanComposite = new ArrayList<Long>();
        String[] inputRows = Utils.readFile(200, INPUT_FILE);
        for(int i = 0; i < inputRows.length; i++){
            long parsedRow = Long.parseLong(inputRows[i]);
            if(parsedRow < COMPOSITE_NUMBER){
                smallerThanComposite.add(parsedRow);
            }
        }

        long[] answerSummands = challenge == 1 ? Utils.findTwoSummands(smallerThanComposite, COMPOSITE_NUMBER) : findThreeSummands(smallerThanComposite, COMPOSITE_NUMBER);
        long answer = 1;
        for(int i = 0; i < answerSummands.length; i++){
            answer = answer * answerSummands[i];
        }
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;

        System.out.println("Answer: " + answer);
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
        return totalTimeToRunMS;
    }

    public static long[] findThreeSummands(ArrayList<Long> numberList, int compositeNumber){
        long[] answerSummands = {0,0,0};
        for(int i = 0; i < numberList.size(); i++){
            long summandOne = numberList.get(i);
            for(int j = 0; j < numberList.size(); j++ ){
                if(j != i){
                    long summandTwo = numberList.get(j);
                    long initialSum = summandOne + summandTwo;
                    if(initialSum < compositeNumber){
                        for(int k = 0; k < numberList.size(); k++){
                            if(k != i && k != j){
                                long summandThree = numberList.get(k);
                                long finalSum = initialSum + summandThree;
                                if(finalSum == 2020){
                                    answerSummands[0] = summandOne;
                                    answerSummands[1] = summandTwo;
                                    answerSummands[2] = summandThree;
                                    return answerSummands;
                                }
                            }
                        }
                    }
                }
            }
        }
        return answerSummands;
    }
}
