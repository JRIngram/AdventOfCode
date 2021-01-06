package technology.ingram.adventofcode;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

class DaySix{
    private final String inputFile = "inputs/daySix.txt";
    private final int numberOfRows = 2248;

    public DaySix(){
    }

    public ResultsTuple runChallenge(int challenge){
        long startTime = System.nanoTime();
        String[] inputRows = Utils.readFile(numberOfRows, inputFile);
        int answer = challenge == 1 ? challengeOne(inputRows) : challengeTwo(inputRows);
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        ResultsTuple results = new ResultsTuple(answer, totalTimeToRunMS);
        System.out.println("Answer: " + results.getAnswer() + "; Found in: " + results.getTimeTakenToCalculateAnswer() + "ms");
        return results;
    }
    
    public static int challengeOne(String[] inputRows){
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

    public static int challengeTwo(String[] inputRows){
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