package technology.ingram.adventofcode;

import java.util.Arrays;
import technology.ingram.adventofcode.Utils;

class DayFive{
    private final int SEATING_ROWS = 128; // Numbered 0 - 127
    private final int SEATING_COLUMNS = 8; // Numbered 0 - 7
    private final String inputFile = "inputs/dayFive.txt";
    private final int numberOfRows = 826;

    public DayFive(){

    }

    public double runChallenge(int challenge){
        long startTime = System.nanoTime();
        String[]  inputRows = Utils.readFile(numberOfRows, inputFile);
        Integer[] seatIds = new Integer[inputRows.length];
        for(int i = 0; i < inputRows.length; i++){
            int row = calculateSeatRow(inputRows[i]);
            int column = calculateSeatColumn(inputRows[i]);
            int seatId = (row * 8) + column;
            seatIds[i] = seatId;
        }
        Arrays.sort(seatIds);
        if(challenge == 1){
            int answer = challengeOne(seatIds);
            System.out.println("Highest ID is: " + answer);
        }
        if(challenge == 2){
            int answer = challengeTwo(seatIds);
            System.out.println("FOUND MY SEAT: " + answer);
        }
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
        return totalTimeToRunMS;
    }

    private int challengeOne(Integer[] inputRows){
        return inputRows[inputRows.length - 1];
    }

    private int challengeTwo(Integer[] inputRows){
        int answer = 0;
        for(int i = 0; i < inputRows.length; i++){
            if(i < inputRows.length - 2){
                int currentSeat = inputRows[i];
                int nextSeat = inputRows[i + 1];
                int spaceBetweenIds = nextSeat - currentSeat;
                if(spaceBetweenIds > 1){
                    answer = currentSeat + 1;
                }
            }
        }
        return answer;
    }

    private int calculateSeatRow(String boardingPass){
        String[] rowKeys = boardingPass.substring(0,7).split("");
        double min = 0;
        double max = SEATING_ROWS - 1;
        int row = 0;
        for(int i = 0; i < rowKeys.length; i++){ 
            if(rowKeys[i].equals("F")){
                max = Math.floor(max - ((max - min) / 2));
            }
            else if(rowKeys[i].equals("B")){
                min = Math.ceil(min + ((max - min) / 2));
            }
        }
        if(min == max){
            row = (int) min;
        }
        return row;
    }

    private int calculateSeatColumn(String boardingPass){
        String[] columnKeys = boardingPass.substring(7,10).split("");
        double min = 0;
        double max = SEATING_COLUMNS - 1;
        int column = 0;
        for(int i = 0; i < columnKeys.length; i++){    
            if(columnKeys[i].equals("L")){
                max = Math.floor(max - ((max - min) / 2));
            }
            else if(columnKeys[i].equals("R")){
                min = Math.ceil(min + ((max - min) / 2));
            }
        }
        if(min == max){
            column = (int) min;
        }
        return column;        
    }
}