package technology.ingram.adventofcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import technology.ingram.adventofcode.Utils;

public class DayFour {
    private final String INPUT_FILE = "inputs/dayFour.txt";
    private final int NUMBER_OF_ROWS = 953;
    private int challenge;

    public DayFour(){
    }

    public double runChallenge(int challenge){
        long startTime = System.nanoTime();
        String[] inputRows = Utils.readFileAndStandardiseToOneLine(NUMBER_OF_ROWS, INPUT_FILE);
        int answer = challenge == 1 ? challengeOne(inputRows) : challengeTwo(inputRows);
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println("Answer: " + answer);
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
        return totalTimeToRunMS;
    }

    public int challengeOne(String[] inputRows){
        Pattern regex = Pattern.compile("^.*(ecl.*|pid.*|eyr.*|hcl.*|byr.*|iyr.*|hgt.*){7}.*$");
        int answer = 0;
        for(int i = 0; i < inputRows.length; i++){
            if(regex.matcher(inputRows[i]).matches()){
                answer++;
            }
        }
        return answer;
    }

    public int challengeTwo(String[] inputRows){
        String[] patterns = {
            "^.*byr:(19[2-9][0-9]|200[0-2]);{1}.*$",
            "^.*iyr:(201[0-9]|2020);.*$",
            "^.*eyr:(202[0-9]|2030);.*$",
            "^.*hgt:((1[5-8][0-9]|19[0-3])cm|(59|6[0-9]|7[0-6])in);.*$",
            "^.*hcl:#[0-9a-f]{6};.*$",
            "^.*ecl:(amb|blu|brn|gry|grn|hzl|oth);.*$",
            "^.*pid:[0-9]{9};.*$",
        };
        int answer = 0;
        for(int i = 0; i < inputRows.length; i++){
            boolean matches = true;
            inputRows[i] = inputRows[i].replaceAll(" ",";");
            for(int j = 0; j < patterns.length; j++){
                Pattern regex = Pattern.compile(patterns[j]);
                if(!regex.matcher(inputRows[i]).matches()){
                    matches = false;
                }
            }
            if(matches){
                answer++;
            }
        }
        return answer;
    }
}
