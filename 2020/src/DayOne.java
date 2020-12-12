package technology.ingram.adventofcode.dayone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import technology.ingram.adventofcode.Utils;

class DayOne{
    public static void main(String[] args)
    {
        long startTime = System.nanoTime();
        int challenge = Integer.parseInt(args[0]); // 1 or 2 depending on which challenge of the day to run
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
        int compositeNumber = 2020;
        BufferedReader br = null;
        String line = "";
        ArrayList<Long> smallerThanComposite = new ArrayList<Long>();
        String[] inputRows = Utils.readFile(200, "inputs/dayOne.txt");

        for(int i = 0; i < inputRows.length; i++){
            long parsedRow = Long.parseLong(inputRows[i]);
            if(parsedRow < compositeNumber){
                smallerThanComposite.add(parsedRow);
            }
        }

        long[] answerSummands = challenge == 1 ? Utils.findTwoSummands(smallerThanComposite, compositeNumber) : findThreeSummands(smallerThanComposite, compositeNumber);
        long answer = 1;
        for(int i = 0; i < answerSummands.length; i++){
            answer = answer * answerSummands[i];
        }
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;

        if(answer != -1){
            System.out.println("ELVES, HERE IS YOUR NUMBER:" + answer);
        }
        else{
            System.out.println("No answer could be found");   
        }

        System.out.println("Found in: " + totalTimeToRunMS + "ms");
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
                                    String resultString = "SUMMANDS ARE: " + summandOne + ", " + summandTwo + ", " + summandThree;
                                    System.out.println(resultString);
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

