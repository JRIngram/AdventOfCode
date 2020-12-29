package technology.ingram.adventofcode;

import technology.ingram.adventofcode.*;

public class Main {
    private static int day;
    private static int challenge;

    public static void main(String args[]){
        day = Integer.parseInt(args[0]);
        challenge = Integer.parseInt(args[1]);
        if(day <= 10 && day >= 1 && (challenge == 1 || challenge == 2)){
            switch(day){
                case 1:
                case 2:
                case 3:
                    DayThree dayThree = new DayThree();
                    if(challenge == 1){
                        dayThree.runChallenge(1);
                    }else{
                        dayThree.runChallenge(2);
                    }
                    break;               
                case 4:
                    DayFour dayFour = new DayFour();
                    if(challenge == 1){
                        dayFour.runChallenge(1);
                    }else{
                        dayFour.runChallenge(2);
                    }
                    break;
                case 5:
                    DayFive dayFive = new DayFive();
                    if(challenge == 1){
                        dayFive.runChallenge(1);
                    }else{
                        dayFive.runChallenge(2);
                    }
                    break;
                case 6:
                    DaySix daySix = new DaySix();
                    if(challenge == 1){
                        daySix.runChallenge(1);
                    }else{
                        daySix.runChallenge(2);
                    }
                    break;
                case 7:
                    // TODO 
                    // DaySeven daySeven = new DaySeven();
                    // if(challenge == 1){
                    //     daySeven.runChallenge(1);
                    // }else{
                    //     daySeven.runChallenge(2);
                    // }
                    // break;
                case 8: 
                    DayEight dayEight = new DayEight();
                    if(challenge == 1){
                        dayEight.runChallenge(1);
                    }else{
                        dayEight.runChallenge(2);
                    }
                    break;
                case 9:
                    DayNine dayNine = new DayNine();
                    if(challenge == 1){
                        dayNine.runChallenge(1);
                    }else{
                        dayNine.runChallenge(2);
                    }
                    break;
                case 10: 
                    DayTen dayTen = new DayTen();
                    if(challenge == 1){
                        dayTen.runChallenge(1);
                    }
                    if(challenge == 2){
                        dayTen.runChallenge(2); // TODO current gives incorrect answer;
                    }
                    break;
            }
        }
    }
}
