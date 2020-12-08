#!/bin/bash
echo "Compiling java solution...";
package="technology/ingram/adventofcode";
cd src;
javac -d . DayOne.java DayTwo.java DayThree.java DayFive.java DaySix.java DaySeven.java DayEight.java;
printf "Running solutions...\n";
printf "\nDay 1:\n";
echo "**************";
java $package/dayone/DayOne 1
echo "**************";
java $package/dayone/DayOne 2
echo "**************";
printf "\nDay 2:\n";
java $package/daytwo/DayTwo 1
echo "**************";
java $package/daytwo/DayTwo 2
echo "**************";
printf "\nDay 3:\n";
java $package/daythree/DayThree 1
echo "**************";
java $package/daythree/DayThree 2
echo "**************";
printf "\nDay 4:\n";
echo "SOLUTION STILL NEEDS IMPLEMENTING";
# DAY FOUR STILL NEEDS TO BE COMPLETED
echo "**************";
printf "\nDay 5:\n";
java $package/dayfive/DayFive 1
echo "**************";
java $package/dayfive/DayFive 2
echo "**************";
printf "\nDay 6:\n";
java $package/daysix/DaySix 1
echo "**************";
java $package/daysix/DaySix 2
echo "**************";
printf "\nDay 7:\n";
java $package/dayseven/DaySeven 1
echo "**************";
java $package/dayseven/DaySeven 2
echo "**************";
printf "\nDay 8:\n";
java $package/dayeight/DayEight 1
echo "**************";
java $package/dayeight/DayEight 2
echo "**************";