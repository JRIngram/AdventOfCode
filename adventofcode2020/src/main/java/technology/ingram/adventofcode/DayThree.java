package technology.ingram.adventofcode;

import technology.ingram.adventofcode.Utils;

class DayThree{

    public DayThree(){
    }

    public double runChallenge(int challenge){
        long startTime = System.nanoTime();
        String inputFile = "inputs/dayThree.txt";
        int numberOfRows = 323;
        String[] inputRows = new String[numberOfRows];
        inputRows = Utils.readFile(numberOfRows, inputFile);
        long answer = challenge == 1 ? challengeOne(inputRows) : challengeTwo(inputRows);
        System.out.println("Answer: " + answer);
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
        return totalTimeToRunMS;
    }

    private int challengeOne(String[] inputRows){
        int answer = calculateTreesHit(inputRows, 3, 1);
        return answer; 
    }

    private long challengeTwo(String[] inputRows){
        long slope1 = Long.valueOf(calculateTreesHit(inputRows, 1, 1)); // forces answer calculation to be a long calulcation
        int slope2 = calculateTreesHit(inputRows, 3, 1);
        int slope3 = calculateTreesHit(inputRows,5,1);
        int slope4 = calculateTreesHit(inputRows,7,1);
        int slope5 = calculateTreesHit(inputRows,1,2);
        long answer = slope1 * slope2 * slope3 * slope4 * slope5;
        return answer;
    }

    private static int calculateTreesHit(String[] inputRows, int spacesRight, int spacesDown){
        int answer = 0;
        int position = 0;
        int rowWidth = inputRows[0].length();
        for(int i = 0; i < inputRows.length; i++){
            if(i != 0 && i % spacesDown == 0){
                position = (position + spacesRight) % rowWidth;
                if(inputRows[i].charAt(position) == '#'){
                    answer++;
                }
            }
        }
        return answer;
    }
}
