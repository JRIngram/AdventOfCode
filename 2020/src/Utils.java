package technology.ingram.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Utils {
    public static String[] readFile(int numberOfRows, String fileName){
        String[] inputRows = new String[numberOfRows];
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int counter = 0;
            while(counter < inputRows.length){
                String line = br.readLine();
                inputRows[counter] = line;
                counter++;
            }
            br.close();
        }catch(IOException err){
            System.out.println(err);
        }
        return inputRows;
    }

    /**
     * Takes in an array of two numbers, with a target composite number and finds two values in the number list 
     * which sum to equal the composite number
     * @return int[] of length two - the two summands that equal the composite number
     */
    public static long[] findTwoSummands(ArrayList<Long> numberList, long compositeNumber){
        long[] answer = new long[2];
        Arrays.fill(answer, 0);
        for(int i = 0; i < numberList.size(); i++){
            long summandOne = numberList.get(i);
            long summandToFind = compositeNumber - summandOne;
            boolean summandsFound = false;
            if(!summandsFound){
                for(int j = 0; j < numberList.size(); j++){
                    if(j != i){
                        long summandTwo = numberList.get(j);
                        if(summandTwo == summandToFind){
                            //String resultString = "SUMMANDS FOR " + compositeNumber + " ARE: " + summandOne  + ", " + summandTwo;
                            //System.out.println(resultString);
                            answer[0] = summandOne;
                            answer[1] = summandTwo;
                            return answer;
                        }
                    }
                }
            }
        }
        if(answer[0] == 0){
            //System.out.println("No summands could be found for " + compositeNumber);
        }
        return answer;
    }
}
