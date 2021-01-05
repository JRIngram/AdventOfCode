package technology.ingram.adventofcode;

public class ResultsTuple {
    
    private long answer;
    private double timeTakenToCalculateAnswer;

    public ResultsTuple(long answer, double timeTakenToCalculateAnswer){
        this.answer = answer;
        this.timeTakenToCalculateAnswer = timeTakenToCalculateAnswer;
    }

    public long getAnswer(){
        return answer;
    }

    public double getTimeTakenToCalculateAnswer(){
        return timeTakenToCalculateAnswer;
    }


}
