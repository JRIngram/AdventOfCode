import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class DayEight {
    private static final String inputFile = "input.txt";
    private static final int numberOfRows = 647;
    private static int challenge;
    public static void main(String args[]){
        long startTime = System.nanoTime();
        challenge = Integer.parseInt(args[0]);
        if(!(args[0].equals("1") || args[0].equals("2"))){
            throw new IllegalArgumentException("Input must be 1 or 2. Usage:\n\tTo run challenge 1: java DayThree 1\n\tTo run challenge 2: java DayThree 2");
        }
        int answer = 0;
        String[] inputRows = readFile(numberOfRows, inputFile);
        answer = challenge == 1 ? challengeOne(inputRows) : challengeTwo(inputRows);
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
        int accumulator = 0;
        int instructionToRun = 0;
        boolean[] previouslyRanInstruction = new boolean[inputRows.length];
        Arrays.fill(previouslyRanInstruction, false);

        while(instructionToRun < inputRows.length){
            if(previouslyRanInstruction[instructionToRun] == false){
                previouslyRanInstruction[instructionToRun] = true;
                String command = inputRows[instructionToRun];
                int[] commandResult = parseCommand(command, accumulator, instructionToRun);
                accumulator = commandResult[0];
                instructionToRun = commandResult[1];
            }
            else{
                System.out.println("INFINITE LOOP DETECTED");
                break;
            }
        }
        return accumulator;
    }

    public static int challengeTwo(String[] inputRows){
        boolean successfulExecution = false;
        int instanceToChange = 1;
        int accumulator = 0;

        while(successfulExecution == false){
            // Setup new run of program
            accumulator = 0;
            int instructionToRun = 0;
            int potentialCorruptionsEncountered = 0;
            boolean[] previouslyRanInstruction = new boolean[inputRows.length];
            Arrays.fill(previouslyRanInstruction, false);

            while(instructionToRun < inputRows.length){
                if(previouslyRanInstruction[instructionToRun] == false){
                    previouslyRanInstruction[instructionToRun] = true;
                    String command = inputRows[instructionToRun];
                    if(command.contains("nop") || command.contains("jmp")){
                        potentialCorruptionsEncountered++;
                        if(potentialCorruptionsEncountered == instanceToChange){
                            if(command.contains("nop")){
                                command = command.replace("nop", "jmp");
                            }
                            else{
                                command = command.replace("jmp", "nop");
                            }
                        }
                    }
                    int[] commandResult = parseCommand(command, accumulator, instructionToRun);
                    accumulator = commandResult[0];
                    instructionToRun = commandResult[1];
                }
                else{
                    instanceToChange++;
                    break;
                }
            }
    
            if(instructionToRun >= inputRows.length){
                successfulExecution = true;
            }
        }
        return accumulator;
    }

    public static int[] parseCommand(String command, int accumulator, int instructionToRun){
        int nextInstructionToRun = instructionToRun;
        int newAccumulatorValue = accumulator;
        command = command.replace("+", "");
        String[] splitCommand = command.split(" ");
        String commandInstruction = splitCommand[0];
        int parsedCommandValue = Integer.parseInt((splitCommand[1]));
        System.out.println("\t" + command);
        switch (commandInstruction){
            case "nop":
                nextInstructionToRun += 1;
                break;
            case "acc":
                newAccumulatorValue += parsedCommandValue;
                nextInstructionToRun += 1;
                break;
            case "jmp":
                nextInstructionToRun += parsedCommandValue;
                break;
            default:
                System.out.println("INVALID COMMAND: " + splitCommand[0] + "!");
                break;
        }
        int[] accumulatorAndNextInstruction = {newAccumulatorValue, nextInstructionToRun}; // [Accumulator Value, Next Command Value];
        return accumulatorAndNextInstruction;
    }
}
