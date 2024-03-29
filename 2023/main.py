from days.one import one
from days.two import two
from days.three import three
from days.four import four
from days.five import five
from days.six import six
from days.seven import seven

day = input("enter day to run: ")
scenario = input("run scenario 1 or 2?: ")
day_int = int(day)
print(f"\n#### day: {day} ## scenario: {scenario} ####")

if day_int == 1:
    result = one(int(scenario), './inputs/actual/dayOne.txt')
if day_int == 2:
    result = two(int(scenario), './inputs/actual/dayTwo.txt')
if day_int == 3:
    result = three(int(scenario), './inputs/actual/dayThree.txt')
if day_int == 4:
    result = four(int(scenario), './inputs/actual/dayFour.txt')
if day_int == 5:
    result = five(int(scenario), './inputs/actual/dayFive.txt')
if day_int == 6:
    result = six(int(scenario), './inputs/actual/daySix.txt')
if day_int == 7:
    result = seven(int(scenario), './inputs/actual/daySeven.txt')

print("#\tSolution: ", result.get("solution"))
print("#\tTime taken: ", result.get("duration"))
