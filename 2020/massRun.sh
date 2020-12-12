#!/bin/bash
printf "#################################\n#\tADVENT OF CODE 2020\t#\n#################################\n";
echo "Compiling java solution...";
package="technology/ingram/adventofcode";
cd src;
javac -d . DayOne.java DayTwo.java DayThree.java DayFour.java DayFive.java DaySix.java DaySeven.java DayEight.java DayNine.java;
echo "Running solutions...";
echo "#################################"
printf "#\tDay 1\n";
dayOne="java $package/dayone/DayOne"
$dayOne 1
echo "**************";
$dayOne 2
echo "#################################"
printf "#\tDay 2\n";
java $package/daytwo/DayTwo 1
echo "**************";
java $package/daytwo/DayTwo 2
echo "#################################"
printf "#\tDay 3\n";
java $package/daythree/DayThree 1
echo "**************";
java $package/daythree/DayThree 2
echo "#################################"
printf "#\tDay 4\n";
java $package/dayfour/DayFour 1
echo "**************";
java $package/dayfour/DayFour 2
echo "#################################"
printf "#\tDay 5\n";
java $package/dayfive/DayFive 1
echo "**************";
java $package/dayfive/DayFive 2
echo "#################################"
printf "#\tDay 6\n";
java $package/daysix/DaySix 1
echo "**************";
java $package/daysix/DaySix 2
echo "#################################"
printf "#\tDay 7\n";
java $package/dayseven/DaySeven 1
echo "**************";
java $package/dayseven/DaySeven 2
echo "#################################"
printf "#\tDay 8\n";
java $package/dayeight/DayEight 1
echo "**************";
java $package/dayeight/DayEight 2
echo "#################################"
printf "#\tDay 9\n";
java $package/daynine/DayNine 1
echo "**************";
java $package/daynine/DayNine 2
echo "#################################"