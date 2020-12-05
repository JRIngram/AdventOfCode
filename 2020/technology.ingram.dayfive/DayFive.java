import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class DayFive{
    private static final int SEATING_ROWS = 128; // Numbered 0 - 127
    private static final int SEATING_COLUMNS = 8; // Numbered 0 - 7
    private static final String inputFile = "input.txt";
    private static final int numberOfRows = 826;

    public static void main(String args[]){
        long startTime = System.nanoTime();
        int challenge = Integer.parseInt(args[0]);
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
        String[] inputRows = new String[numberOfRows];
        int answer = 0;
        inputRows = readFile(numberOfRows, inputFile);
        Integer[] seatIds = new Integer[inputRows.length];
        for(int i = 0; i < inputRows.length; i++){
            int row = calculateSeatRow(inputRows[i]);
            int column = calculateSeatColumn(inputRows[i]);
            int seatId = (row * 8) + column;
            seatIds[i] = seatId;
        }
        Arrays.sort(seatIds);
        if(challenge == 1){
            answer = seatIds[seatIds.length - 1];
        }
        if(challenge == 2){
            for(int i = 0; i < seatIds.length; i++){
                if(i < seatIds.length - 2){
                    int currentSeat = seatIds[i];
                    int nextSeat = seatIds[i + 1];
                    int spaceBetweenIds = nextSeat - currentSeat;
                    if(spaceBetweenIds > 1){
                        answer = currentSeat + 1;
                    }
                }
            }
        }
        if(challenge == 1){
            System.out.println("Highest ID is: " + answer);
        }
        else{
            System.out.println("FOUND MY SEAT: " + answer);
        }
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;
        System.out.println("Found in: " + totalTimeToRunMS + "ms");
    }

    public static int calculateSeatRow(String boardingPass){
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

    public static int calculateSeatColumn(String boardingPass){
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
    
    // public static Integer[] sortSeatIds(Integer[] seatIds){
    //     // Implements Gnome Sort
    //     // https://en.wikipedia.org/wiki/Gnome_sort
    //     // Chosen for curiosity sake, never heard of it before.
    //     // Not the speediest algorithm. Should implemented QuickSort if I want speed.
    //     int position = 0;
    //     while(position < seatIds.length){
    //         if(position == 0 || seatIds[position] >= seatIds[position - 1]){
    //             System.out.println("ping");
    //             position++;
    //         }
    //         else{
    //             System.out.println("pong");
    //             int temp = seatIds[position];
    //             seatIds[position] = seatIds[position - 1];
    //             seatIds[position - 1] = temp;
    //         }
    //     }
    //     return seatIds;
    // }
}