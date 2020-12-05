import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class DayOne{
    public static void main(String[] args)
    {
        long startTime = System.nanoTime();
        int challenge = Integer.parseInt(args[0]); // 1 or 2 depending on which challenge of the day to run
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
        int compositeNumber = 2020;
        BufferedReader br = null;
        String line = "";
        int answer = 0;
        ArrayList<Integer> smallerThanComposite = new ArrayList<Integer>();

        try{
            br = new BufferedReader(new FileReader("input.txt"));
        }catch(IOException err){
            System.out.println(err);
        }

        do{ 
            try{
                line = br.readLine();
                if(line != null){
                    try{
                        int parsedLine = Integer.parseInt(line);
                        if(parsedLine < 2020){ 
                            smallerThanComposite.add(parsedLine);
                        }
                    }
                    catch(NumberFormatException err){
                        System.out.println(err);
                    }
                }
            }
            catch(IOException err){
                System.out.println(err);
            }
        }while(line != null);

        answer = challenge == 1 ? findTwoSummands(smallerThanComposite, compositeNumber) : findThreeSummands(smallerThanComposite, compositeNumber);
        try{
            br.close();
        }catch(IOException err){
            System.out.println(err);
        }
        
        long endTime = System.nanoTime();
        double totalTimeToRunMS = (endTime - startTime) / 1000000.0;

        if(answer != -1){
            System.out.println("ELVES, HERE IS YOUR NUMBER:" + answer);
        }
        else{
            System.out.println("No answer could be found");   
        }

        System.out.println("Found in: " + totalTimeToRunMS + "ms");
    }

    public static int findTwoSummands(ArrayList<Integer> numberList, int compositeNumber){
        int answer = -1;
        for(int i = 0; i < numberList.size(); i++){
            int summandOne = numberList.get(i);
            int summandToFind = compositeNumber - summandOne;
            boolean summandsFound = false;
            if(!summandsFound){
                for(int j = 0; j < numberList.size(); j++){
                    if(j != i){
                        int summandTwo = numberList.get(j);
                        if(summandTwo == summandToFind){
                            String resultString = "SUMMANDS ARE:" + summandOne  + ", " + summandTwo;
                            System.out.println(resultString);
                            answer = summandOne * summandTwo;
                            return answer;
                        }
                    }
                }
            }
        }
        return answer;
    }

    public static int findThreeSummands(ArrayList<Integer> numberList, int compositeNumber){
        int answer = -1;
        for(int i = 0; i < numberList.size(); i++){
            int summandOne = numberList.get(i);
            for(int j = 0; j < numberList.size(); j++ ){
                if(j != i){
                    int summandTwo = numberList.get(j);
                    int initialSum = summandOne + summandTwo;
                    if(initialSum < compositeNumber){
                        for(int k = 0; k < numberList.size(); k++){
                            if(k != i && k != j){
                                int summandThree = numberList.get(k);
                                int finalSum = initialSum + summandThree;
                                if(finalSum == 2020){
                                    String resultString = "SUMMANDS ARE: " + summandOne + ", " + summandTwo + ", " + summandThree;
                                    System.out.println(resultString);
                                    answer = summandOne * summandTwo * summandThree; 
                                    return answer;
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}

