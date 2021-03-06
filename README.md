# Advent of Code 
A collection of solutions for [Advent of Code](https://adventofcode.com/).

## 2020
The solutions for 2020 are written in Java. I only got up to half way with challenge 10, but have made all the solutions runnable up until that point. Other solutions may be put in place in future.

All of the challenges so far are self contained, and can be ran in any order.

Each day contains two challenges.

### Requirements
* Java 8 
* Maven

### Running
1. Package the project with `mvn clean compile assembly:single`
2. Run `java -cp target/adventofcode2020-1.0.0.jar technology.ingram.adventofcode.Main <arg1> <arg2>`, where `arg1` is the day you want to run and `arg2` is the challenge you want to run for that day. 

| Argument       | Meaning     |  Range    |
| :------------- | :----------: | -----------: |
|  arg1 | Day to run  | 1 - 10    |
|  arg1   | Challenge of the chosen day to run | 1 - 2 |

##### Example
Once the project has been packaged using `mvn clean compile assembly:single`, you would like to run the second challenge of Day 5. To do this you would then run the following command: 
`java -cp target/adventofcode2020-1.0.0.jar technology.ingram.adventofcode.Main 5 2`

#### Speed Test
If you wish to run a speed test of all the challenges:
1. Package the project with `mvn clean compile assembly:single`
2. Run `java -cp target/adventofcode2020-1.0.0-jar-with-dependencies.jar technology.ingram.adventofcode.Main speedtest <arg2>`, where `<arg2>` is the number of runs of each challenge you want to run. 

##### Example
Say you want to find the average time to run each challenge over 100 runs: once the project has been packaged using `mvn package`, you would then run the following command: 
`java -cp target/adventofcode2020-1.0.0-jar-with-dependencies.jar technology.ingram.adventofcode.Main speedtest 100`




