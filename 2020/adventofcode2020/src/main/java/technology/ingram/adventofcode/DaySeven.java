package technology.ingram.adventofcode;

import java.util.HashMap;
import java.util.ArrayList;

class DaySeven{
    private final String INPUT_FILE = "inputs/DaySeven.txt";
    private final int NUMBER_OF_ROWS = 594;

    DaySeven(){}

    public ResultsTuple runChallenge(int challenge){
        long startTime = System.nanoTime();
        int answer = 0;
        String[] inputRows = Utils.readFile(NUMBER_OF_ROWS, INPUT_FILE);
        answer = challenge == 1 ? challengeOne(inputRows) : challengeTwo(inputRows);
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        ResultsTuple results = new ResultsTuple(answer, totalTimeToRunMS);
        System.out.println("Answer: " + results.getAnswer() + "; Found in: " + results.getTimeTakenToCalculateAnswer() + "ms");
        return results;
    }

    public static int challengeOne(String[] inputRows){
        int challenge = 1;
        String[] trimmedRows = cleanInputRows(inputRows, challenge);
        HashMap<String, String[]> mappedBagRules = mapBagRules(trimmedRows, challenge);
        int answer = findContainersForBag(mappedBagRules, "shiny gold", 0);
        return answer;
    }

    public static int challengeTwo(String[] inputRows){
        int challenge = 2;
        String[] trimmedRows = cleanInputRows(inputRows, challenge);
        HashMap<String, String[]> mappedBagRules = mapBagRules(trimmedRows, challenge);
        int answer = findRequiredBagCount(mappedBagRules, "shiny gold", 0, 1, 0) - 1; // Minus one as we don't count the gold bag
        return answer;
    }

    public static String[] cleanInputRows(String[] inputRows, int challenge){
        String[] trimmedRows = new String[inputRows.length];
        for(int i = 0; i < inputRows.length; i++){
            if(challenge == 1){
                // Returns challenge input with format PATTERN COLOUR PATTERN COLOUR PATTERN COLOUR... 
                // Where the first PATTERN COLOUR is the bag that contains the other pattern colours.
                trimmedRows[i] = inputRows[i].replace("bags", "").replace("bag", "").replace("contain", "").replaceAll("\\d","").replace("   ", " ").replace("  ", " ").replace(" , ", " ").replace(" .", "");
            }
            else if(challenge == 2){
                // Returns challenge input with format PATTERN COLOUR PATTERN COLOUR PATTERN COLOUR... 
                // Where the first PATTERN COLOUR is the bag that contains the other pattern colours.
                trimmedRows[i] = inputRows[i].replace("bags", "").replace("bag", "").replace("contain", "").replace("   ", " ").replace("  ", " ").replace(" , ", " ").replace(" .", "");
            }
        }
        return trimmedRows;
    }
    
    public static HashMap<String, String[]> mapBagRules(String[] inputRows, int challenge){
        return challenge == 1 ? mapBagRulesChallengeOne(inputRows) : mapBagRulesChallengeTwo(inputRows);
    }

    public static HashMap<String, String[]> mapBagRulesChallengeOne(String[] inputRows){
        HashMap<String, String[]> bagRules = new HashMap<String, String[]>();
        for(int i = 0; i < inputRows.length; i++){
            String[] splitInputRow = inputRows[i].split(" ");
            String containerBagString = splitInputRow[0] + " " + splitInputRow[1];
            String canContainString = "";
            for(int j = 2; j < splitInputRow.length; j = j + 2){
                canContainString += splitInputRow[j] + " " + splitInputRow[j + 1] + ",";
            }
            bagRules.put(containerBagString, canContainString.split(","));
        }
        return bagRules;
    }

    public static HashMap<String, String[]> mapBagRulesChallengeTwo(String[] inputRows){
        HashMap<String, String[]> bagRules = new HashMap<String, String[]>();
        for(int i = 0; i < inputRows.length; i++){
            String[] splitInputRow = inputRows[i].split(" ");
            String containerBagString = splitInputRow[0] + " " + splitInputRow[1];
            String canContainString = "";
            for(int j = 2; j < splitInputRow.length; j = j + 3){
                if(!splitInputRow[j].equals("no") && !splitInputRow[j].equals("other")){
                    canContainString += splitInputRow[j] + " " + splitInputRow[j + 1] + " " + splitInputRow[j + 2] + ",";
                }
                else{
                    canContainString = "0";
                }
            }
            bagRules.put(containerBagString, canContainString.split(","));
        }
        return bagRules;
    }

    public static int findContainersForBag(HashMap<String, String[]> bagRules, String bag, int totalCanContainOriginalBag){
        String[] rulesKeys = bagRules.keySet().toArray(new String[0]);
        ArrayList<String> furtherBagsToCheck = new ArrayList<String>();
        int answer = totalCanContainOriginalBag;
        int newPossibleBagCount = 0;
        for(int i = 0; i < rulesKeys.length; i++){
            String[] rulesForBag = bagRules.get(rulesKeys[i]);
            for(int j = 0; j < rulesForBag.length; j++){
                if(rulesForBag[j].equals(bag)){
                    furtherBagsToCheck.add(rulesKeys[i]);
                    bagRules.remove(rulesKeys[i]);
                    newPossibleBagCount++;
                }
            }
        }
        answer = answer + newPossibleBagCount;
        for(int i = 0; i < furtherBagsToCheck.size(); i++){
            int toAddToAnswer = findContainersForBag(bagRules, furtherBagsToCheck.get(i), answer) - answer;
            answer += toAddToAnswer;
        }
        return answer;
    }

    public static int findRequiredBagCount(HashMap<String, String[]> bagRules, String bag, int requiredBagCount, int multiplyer, int depth){
        int answer = 0;
        String[] bagToCheck = bagRules.get(bag);
        for(int i = 0; i < bagToCheck.length; i++){
            if(!bagToCheck[i].equals("0")){
                String[] splitBagToCheck = bagToCheck[i].split(" ");
                String nextbagToCheck = splitBagToCheck[1] + " " + splitBagToCheck[2];
                answer += (multiplyer * findRequiredBagCount(bagRules, nextbagToCheck, answer, Integer.parseInt(splitBagToCheck[0]), depth + 1));
            }
            else{
                return 1 * multiplyer;
            }
        }
        return answer + multiplyer;
    }
}