package technology.ingram.adventofcode;

public class DayTwo extends Day{
    private final String INPUT_FILE = "inputs/dayTwo.txt";
    private final int NUMBER_OF_ROWS = 1000;

    public DayTwo(){
        super();
    }

    public ResultsTuple runChallenge(int challenge){
        long startTime = System.nanoTime();
        String[] inputRows = Utils.readFile(NUMBER_OF_ROWS, INPUT_FILE);
        int answer = 0;
        for(int i = 0; i < inputRows.length; i++){
            if(isValidPassword(inputRows[i], challenge)){
                answer++;
            }
        }
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        ResultsTuple results = new ResultsTuple(answer, totalTimeToRunMS);
        System.out.println("Answer: " + results.getAnswer() + "; Found in: " + results.getTimeTakenToCalculateAnswer() + "ms");
        return results;
    }

    public boolean isValidPassword(String inputRow, int challenge){
        String[] splitRow = inputRow.split(" ");
        String[] rangeString = splitRow[0].split("-");
        String password = splitRow[2];
        int numberOne = Integer.parseInt(rangeString[0]);
        int numberTwo = Integer.parseInt(rangeString[1]);
        String characterString = splitRow[1].replace(":", "");
        if(challenge == 1){
            return challengeOne(numberOne, numberTwo, characterString, password);
        }else if(challenge == 2){
            return challengeTwo(numberOne, numberTwo, characterString, password);
        }
        return false;
    }

    public boolean challengeOne(int min, int max, String character, String password ){
        int originalLength = password.length();
        String trimmedPassword = password.replace(character, "");
        int trimmedLength = trimmedPassword.length();
        int requireCharCount = originalLength - trimmedLength;
        if(requireCharCount >= min && requireCharCount <= max){
            return true;
        }
        return false;
    }

    public boolean challengeTwo(int positionOne, int positionTwo, String character, String password){
        // (Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
        char posOneCharacter = password.charAt(positionOne - 1);
        char posTwoCharacter = password.charAt(positionTwo - 1);
        char requiredChar = character.charAt(0);
        
        if((posOneCharacter == requiredChar || posTwoCharacter == requiredChar) && !(posOneCharacter == requiredChar && posTwoCharacter == requiredChar)){
            return true;
        }
        return false;
    }
    

}