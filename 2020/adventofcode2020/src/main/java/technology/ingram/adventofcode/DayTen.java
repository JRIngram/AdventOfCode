package technology.ingram.adventofcode;

public class DayTen extends Day{
    private final String inputFile = "inputs/dayTen.txt";
    private final int numberOfRows = 89;
    private Integer[] inputRows;

    public DayTen(){
        super();
    }

    public ResultsTuple runChallenge(int challenge){
        long startTime = System.nanoTime();
        inputRows = Utils.readFileAsIntegers(numberOfRows, inputFile,true);
        int answer = challenge == 1 ? challengeOne(inputRows) : challengeTwo();
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        ResultsTuple results = new ResultsTuple(answer, totalTimeToRunMS);
        System.out.println("Answer: " + results.getAnswer() + "; Found in: " + results.getTimeTakenToCalculateAnswer() + "ms");
        return results;
    }

    private int challengeOne(Integer[] inputRows){
        int chosenAdapter = 0;
        int oneJoltDifferenceCount = 0;
        int threeJoltDifferenceCount = 0;
        if(inputRows[0] == 1 || inputRows[0] == 2 || inputRows[0] == 3){
            chosenAdapter = inputRows[0];
        }
        if(inputRows[0] == 1){
            oneJoltDifferenceCount++;
        }
        else if(inputRows[0] == 3){
            threeJoltDifferenceCount++;
        }
        for(int i = 0; i < inputRows.length; i++){
            if(i+1 != inputRows.length){
                int difference = inputRows[i+1] - chosenAdapter;
                if(difference == 1){
                    oneJoltDifferenceCount++;
                }
                else if(difference == 3){
                    threeJoltDifferenceCount++;
                }
                chosenAdapter = inputRows[i + 1];
            }
            else{
                threeJoltDifferenceCount++;
            }
        }
        return oneJoltDifferenceCount * threeJoltDifferenceCount;
    }

    //TODO current doesn't find correct solution.
    private int challengeTwo(){
        return 0;
    }
}
