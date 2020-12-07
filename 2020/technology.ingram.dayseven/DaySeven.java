import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

class DaySeven{
    private static final String inputFile = "input.txt";
    private static final int numberOfRows = 594;
    public static void main(String args[]){
        long startTime = System.nanoTime();
        int challenge = Integer.parseInt(args[0]);
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
        int answer = 0;
        String[] inputRows = readFile(numberOfRows, inputFile);
        answer = challenge == 1 ? challengeOne(inputRows) : 0;
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println("Answer: " + answer);
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

    public static int challengeOne(String[] inputRows){
        String[] trimmedRows = cleanChallengeOneInputRows(inputRows);
        HashMap<String, String[]> mappedBagRules = mapBagRules(trimmedRows);
        int answer = findContainersForBag(mappedBagRules, "shiny gold", 0);
        //System.out.println("\n######################\n");
        return answer;
    }

    public static String[] cleanChallengeOneInputRows(String[] inputRows){
        // Returns challenge input with format PATTERN COLOUR PATTERN COLOUR PATTERN COLOUR... 
        // Where the first PATTERN COLOUR is the bag that contains the other pattern colours.
        String[] trimmedRows = new String[inputRows.length];
        for(int i = 0; i < inputRows.length; i++){
            trimmedRows[i] = inputRows[i].replace("bags", "").replace("bag", "").replace("contain", "").replaceAll("\\d","").replace("   ", " ").replace("  ", " ").replace(" , ", " ").replace(" .", "");
        }
        return trimmedRows;
    }

    public static HashMap<String, String[]> mapBagRules(String[] inputRows){
        HashMap<String, String[]> bagRules = new HashMap<String, String[]>();
        for(int i = 0; i < inputRows.length; i++){
            String[] splitInputRow = inputRows[i].split(" ");
            String containerBagString = splitInputRow[0] + " " + splitInputRow[1];
            String canContainString = "";
            for(int j = 2; j < splitInputRow.length; j = j + 2){
                canContainString += splitInputRow[j] + " " + splitInputRow[j + 1] + ",";
            }
            //System.out.println("Container string: " + containerBagString + "\n\tCan contain: " + canContainString +"\n");
            bagRules.put(containerBagString, canContainString.split(","));
        }
        return bagRules;
    }

    public static int findContainersForBag(HashMap<String, String[]> bagRules, String bag, int totalCanContainOriginalBag){
        //System.out.println("\n######################");
        //System.out.println("Checking rules for " + bag + ".");
        String[] rulesKeys = bagRules.keySet().toArray(new String[0]);
        ArrayList<String> furtherBagsToCheck = new ArrayList<String>();
        int answer = totalCanContainOriginalBag;
        int newPossibleBagCount = 0;
        for(int i = 0; i < rulesKeys.length; i++){
            String[] rulesForBag = bagRules.get(rulesKeys[i]);
            for(int j = 0; j < rulesForBag.length; j++){
                //System.out.println(rulesForBag[j]);
                if(rulesForBag[j].equals(bag)){
                    //System.out.println(rulesKeys[i] + " can contain:" + rulesForBag[j]);
                    furtherBagsToCheck.add(rulesKeys[i]);
                    bagRules.remove(rulesKeys[i]);
                    newPossibleBagCount++;
                }
            }
        }
        answer = answer + newPossibleBagCount;
        //System.out.println("Answer so far:" + answer);
        for(int i = 0; i < furtherBagsToCheck.size(); i++){
            int toAddToAnswer = findContainersForBag(bagRules, furtherBagsToCheck.get(i), answer) - answer;
          //  System.out.println("Need to add " + toAddToAnswer + " to " + answer);
            answer += toAddToAnswer;
        }
        //System.out.println(answer);
        return answer;
    }
}