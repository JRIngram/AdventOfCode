package technology.ingram.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import technology.ingram.adventofcode.Utils;

class DayTwo{
    public static void main(String args[]){
        long startTime = System.nanoTime();
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayTwo 1\n\tTo run challenge 2: java DayTwo 2");
        }
        String fileName = "inputs/dayTwo.txt";
        int inputLength = 1000;
        String[] inputRows = new String[inputLength];
        int answer = 0;
        int challenge = Integer.parseInt(args[0]); // 1 or 2 depending on which challenge of the day to run

        inputRows = Utils.readFile(inputLength, fileName);
        for(int i = 0; i < inputRows.length; i++){
            if(isValidPassword(inputRows[i], challenge)){
                answer++;
            }
        }
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println(answer);
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
    }

    public static boolean isValidPassword(String inputRow, int challenge){
        String[] splitRow = inputRow.split(" ");
        String[] rangeString = splitRow[0].split("-");
        String password = splitRow[2];
        int numberOne = Integer.parseInt(rangeString[0]);
        int numberTwo = Integer.parseInt(rangeString[1]);
        String characterString = splitRow[1].replace(":", "");
        if(challenge == 1){
            return isValidCriteriaOnePassword(numberOne, numberTwo, characterString, password);
        }else if(challenge == 2){
            return isValidCriteriaTwoPassword(numberOne, numberTwo, characterString, password);
        }
        return false;
    }

    public static boolean isValidCriteriaTwoPassword(int positionOne, int positionTwo, String character, String password){
        // (Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
        char posOneCharacter = password.charAt(positionOne - 1);
        char posTwoCharacter = password.charAt(positionTwo - 1);
        char requiredChar = character.charAt(0);
        
        if((posOneCharacter == requiredChar || posTwoCharacter == requiredChar) && !(posOneCharacter == requiredChar && posTwoCharacter == requiredChar)){
            return true;
        }
        return false;
    }
    
    public static boolean isValidCriteriaOnePassword(int min, int max, String character, String password ){
        int originalLength = password.length();
        String trimmedPassword = password.replace(character, "");
        int trimmedLength = trimmedPassword.length();
        int requireCharCount = originalLength - trimmedLength;
        if(requireCharCount >= min && requireCharCount <= max){
            return true;
        }
        return false;

    }
}