package technology.ingram.adventofcode;

import technology.ingram.adventofcode.Utils;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class DayTen {
    private final String inputFile = "inputs/dayTen.txt";
    private final int numberOfRows = 89;
    private Integer[] inputRows;
    private HashMap<Integer, Integer[]> challengeTwoRoutes;

    public DayTen(){
    }

    public double runChallenge(int challenge){
        long startTime = System.nanoTime();
        inputRows = Utils.readFileAsIntegers(numberOfRows, inputFile,true);
        int answer = challenge == 1 ? challengeOne(inputRows) : challengeTwo(inputRows);
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println("Answer: " + answer);
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
        return totalTimeToRunMS;
    }

    private int challengeOne(Integer[] inputRows){
        int chosenAdapter = 0;
        int oneJoltDifferenceCount = 0;
        int threeJoltDifferenceCount = 0;
        String adapterString = "";
        if(inputRows[0] == 1 || inputRows[0] == 2 || inputRows[0] == 3){
            chosenAdapter = inputRows[0];
            adapterString += inputRows[0];
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
                adapterString += " -> " + chosenAdapter;
            }
            else{
                threeJoltDifferenceCount++;
                adapterString += " -> " + (chosenAdapter + 3);
            }
        }
        System.out.println(adapterString);
        System.out.println(oneJoltDifferenceCount);
        System.out.println(threeJoltDifferenceCount);
        return oneJoltDifferenceCount * threeJoltDifferenceCount;
    }


    //TODO current doesn't find correct solution.
    private int challengeTwo(Integer[] inputRows){
        challengeTwoRoutes = new HashMap<Integer, Integer[]>();
        for(int i = 0; i < inputRows.length; i++){
            ArrayList<Integer> validNextAdapters = new ArrayList<Integer>();
            int currentAdapter = inputRows[i];
            for(int j = i + 1; j < inputRows.length; j++){
                if(j < inputRows.length){
                    int difference = inputRows[j] - inputRows[i];
                    if(difference <= 3 && !validNextAdapters.contains(inputRows[j])){
                        validNextAdapters.add(inputRows[j]);
                    }
                    else{
                        break;
                    }
                }
            }
            Integer[] validNextAdaptersArray = new Integer[0];
            System.out.println("Valid next routes from " + inputRows[i] + ": " + validNextAdapters);
            validNextAdaptersArray = validNextAdapters.toArray(validNextAdaptersArray);
            challengeTwoRoutes.put(currentAdapter, validNextAdaptersArray);
        }
        Integer finalNodeNumber = inputRows.length - 1;
        ArrayList<Integer> possibleRouteCount = new ArrayList<Integer>();
        possibleRouteCount = calculateRoutes(challengeTwoRoutes, inputRows, finalNodeNumber,possibleRouteCount);
        int arrangementCount = 1;
        System.out.println("*************");
        for(int i = 0; i < possibleRouteCount.size(); i++){
            System.out.println(possibleRouteCount.get(i));
            arrangementCount = arrangementCount * possibleRouteCount.get(i);
        }
        return arrangementCount;
    }

    private ArrayList<Integer> calculateRoutes(HashMap<Integer, Integer[]> routes, Integer[] inputRows, int startingIndex, ArrayList<Integer> possibleRoutesCount){
        int routesFromNode = 0;
        int currentIndex = startingIndex;
        int currentNodeValue = inputRows[currentIndex];
        int counter = 1;
        boolean continueSearch = true;
        int routesToCurrentIndex = 0;
        boolean hitFinalIndex = false;
        System.out.println("CHECKING POSSIBLE ROUTES TO " + currentNodeValue);
        while(continueSearch && currentIndex > 0){
            int indexToCheck = currentIndex - counter;
            System.out.println(indexToCheck);
            if(indexToCheck > 0){
                int difference = currentNodeValue - inputRows[indexToCheck];
                if(difference <= 3){
                    System.out.println("\tCan get to " + inputRows[currentIndex] + " from " + inputRows[indexToCheck]);
                    routesToCurrentIndex++;
                    counter++;
                }
                else{
                    continueSearch = false;
                    counter--;
                }
            }
            else{
                continueSearch = false;
            }
        }
        if(routesToCurrentIndex > 0){
            possibleRoutesCount.add(routesToCurrentIndex);
        }
        System.out.println("\t\t" + routesToCurrentIndex);
        System.out.println("FIN RUN\n");
        if(currentIndex - 1 > -1){
            calculateRoutes(routes, inputRows, currentIndex - 1, possibleRoutesCount);
        }
        return possibleRoutesCount;
    }
}
