package technology.ingram.adventofcode.dayfour;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import technology.ingram.adventofcode.Utils;
import java.util.ArrayList;

public class DayFour {
    private static final String INPUT_FILE = "inputs/dayFour.txt";
    private static final int NUMBER_OF_ROWS = 953;
    private static int challenge;
    public static void main(String args[]){
        long startTime = System.nanoTime();
        challenge = Integer.parseInt(args[0]);
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
        String[] inputRows = Utils.readFileAndStandardiseToOneLine(NUMBER_OF_ROWS, INPUT_FILE);
        int answer = challenge == 1 ? challengeOne(inputRows) : challengeTwo(inputRows);
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println("Answer: " + answer);
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
    }

    public static int challengeOne(String[] inputRows){
        Pattern regex = Pattern.compile("^.*(ecl.*|pid.*|eyr.*|hcl.*|byr.*|iyr.*|hgt.*){7}.*$");
        int answer = 0;
        for(int i = 0; i < inputRows.length; i++){
            if(regex.matcher(inputRows[i]).matches()){
                answer++;
            }
        }
        return answer;
    }

    public static int challengeTwo(String[] inputRows){
        String regexPattern = "^.*(ecl:(amb|blu|brn|grn|gry|hzl|oth).*|pid:[0-9]{9}.*|eyr:(202[0-9]|2030).*|hcl:#[0-9a-f]{6}.*|byr:(19[2-9][0-9].*|200[0-2].*)|iyr:(201[0-9].*|2020).*|hgt:(1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in).*){7}.*$";
        Pattern regex = Pattern.compile(regexPattern);
        int answer = 0;
        for(int i = 0; i < inputRows.length; i++){
            if(regex.matcher(inputRows[i]).matches()){
                answer++;
            }
        }
        return answer;
    }
}
