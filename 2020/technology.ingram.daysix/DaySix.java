import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class DaySix{
    private static final String inputFile = "input.txt";
    private static final int numberOfRows = 2248;

    public static void main(String args[]){
        long startTime = System.nanoTime();
        int challenge = Integer.parseInt(args[0]);
        int answer = 0;
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
        String[] inputRows = readFile(numberOfRows, inputFile);
        answer = challenge == 1 ? sumUniqueYesAnswers(inputRows) : sumAllAnsweredQuestions(inputRows);
        System.out.println("Sum of unique answers: " + answer);
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
    }
    
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

    public static int sumUniqueYesAnswers(String[] inputRows){
        int answer = 0;
        String customsFormAnswers = "";
        for(int i = 0; i < inputRows.length; i++){
            if(!inputRows[i].equals("")){
                customsFormAnswers = customsFormAnswers + " " + inputRows[i];
            }
            if(inputRows[i].equals("") || i == inputRows.length - 1){
                String trimmedFormAnswers = customsFormAnswers.replace(" ", "");
                HashSet<Character> uniqueAnswers = new HashSet<Character>();
                for(int j = 0; j < trimmedFormAnswers.length(); j++){
                    uniqueAnswers.add(trimmedFormAnswers.charAt(j));
                }
                answer += uniqueAnswers.size();
                customsFormAnswers = "";
            }
        }
        return answer;
    }

    public static int sumAllAnsweredQuestions(String[] inputRows){
        int answer = 0;
        String customsFormAnswers = "";
        int numberOfAnswersInGroup = 0;
        int allAnsweredCount = 0;
        HashMap<Character, Integer> answersInGroupMap = new HashMap<Character, Integer>();
        for(int i = 0; i < inputRows.length; i++){
            if(!inputRows[i].equals("") || i == inputRows.length - 1){
                numberOfAnswersInGroup++;
            }
            if(!inputRows[i].equals("")){
                customsFormAnswers = customsFormAnswers + " " + inputRows[i];
            }
            if(inputRows[i].equals("") || i == inputRows.length - 1){
                String trimmedFormAnswers = customsFormAnswers.replace(" ", "");
                for(int j = 0; j < trimmedFormAnswers.length(); j++){
                    Character formAnswerCharacter = trimmedFormAnswers.charAt(j);
                    if(answersInGroupMap.get(formAnswerCharacter) == null){
                        answersInGroupMap.put(formAnswerCharacter, 1);
                    }
                    else{
                        Integer formAnswerCharacterCount = answersInGroupMap.get(formAnswerCharacter);
                        formAnswerCharacterCount++;
                        answersInGroupMap.put(formAnswerCharacter, formAnswerCharacterCount);
                    }
                }
                Set<Character> keySet = answersInGroupMap.keySet();
                Object[] answersInGroupKeys = keySet.toArray();
                String keySetString = "";
                for(int j = 0; j < answersInGroupKeys.length; j++){
                    keySetString += answersInGroupKeys[j] + ", ";
                }
                for(int j = 0; j < answersInGroupKeys.length; j++){
                    Character key = (char) answersInGroupKeys[j];
                    Integer answerCount = answersInGroupMap.get(key);
                    if(answerCount == numberOfAnswersInGroup){
                        allAnsweredCount++;
                    }        
                }
                answer += allAnsweredCount;

                //Reset for next loop
                customsFormAnswers = "";
                numberOfAnswersInGroup = 0;
                allAnsweredCount = 0;
                answersInGroupMap.clear();
            }
        }
        return answer;
    }
}