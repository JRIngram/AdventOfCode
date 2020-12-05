import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class DayThree{
    public static void main(String args[]){
        long startTime = System.nanoTime();
        int challenge = Integer.parseInt(args[0]); // 1 or 2 depending on which challenge of the day to run
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
        String inputFile = "input.txt";
        int numberOfRows = 323;
        String[] inputRows = new String[numberOfRows];
        long answer = 0;

        inputRows = readFile(numberOfRows, inputFile);
        if(challenge == 1){
            answer = calculateTreesHit(inputRows, 3, 1);
            System.out.println("Trees hit: " + answer);
        }
        else if(challenge == 2){
            long slope1 = Long.valueOf(calculateTreesHit(inputRows, 1, 1)); // forces answer calculation to be a long calulcation
            int slope2 = calculateTreesHit(inputRows, 3, 1);
            int slope3 = calculateTreesHit(inputRows,5,1);
            int slope4 = calculateTreesHit(inputRows,7,1);
            int slope5 = calculateTreesHit(inputRows,1,2);
            answer = slope1 * slope2 * slope3 * slope4 * slope5;
            System.out.println("Answer: " + answer);
        }
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

    public static int calculateTreesHit(String[] inputRows, int spacesRight, int spacesDown){
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
