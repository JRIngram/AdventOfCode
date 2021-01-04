package technology.ingram.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Class;

public class Utils {
    public static String[] readFile(int numberOfRows, String fileName){
        String[] inputRows = new String[numberOfRows];
        try{
            int counter = 0;
            Utils utils = new Utils();
            InputStream is = utils.getFileFromResourceAsStream(fileName);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
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

    public static String[] readFileAndStandardiseToOneLine(int numberOfRows, String fileName){
        ArrayList<String> inputRows = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int counter = 0;
            String inputLine = "";
            while(counter < numberOfRows + 1){
                String line = br.readLine();
                if(counter == numberOfRows || line.equals("")){
                    inputRows.add(inputLine);
                    inputLine = "";
                }
                else{
                    inputLine = inputLine + line + " ";
                }
                counter++;
            }
            br.close();
        }catch(IOException err){
            System.out.println(err);
        }
        String[] inputRowsArray = new String[0];
        inputRowsArray = inputRows.toArray(inputRowsArray);
        return inputRowsArray;
    }

    public static Integer[] readFileAsIntegers(int numberOfRows, String fileName, boolean sort){
        Integer[] inputRows = new Integer[numberOfRows];
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int counter = 0;
            while(counter < inputRows.length){
                String line = br.readLine();
                inputRows[counter] = Integer.parseInt(line);
                counter++;
            }
            br.close();
        }catch(IOException err){
            System.out.println(err);
        }
        if(sort){
            Arrays.sort(inputRows);
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
                            answer[0] = summandOne;
                            answer[1] = summandTwo;
                            return answer;
                        }
                    }
                }
            }
        }
        return answer;
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}