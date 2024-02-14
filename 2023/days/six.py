from timeit import default_timer
from utils import read_file
from functools import reduce

def six(scenario: int, input_path: str):
    lines = read_file(input_path)
    start = default_timer()
    solution = 0

    race_details: list[dict] = []
    if scenario == 1:
        race_details = read_table(lines)
    if scenario == 2:
        time = join_line(lines[0])
        distance = join_line(lines[1])
        race_detail_dict = {
            "time": time,
            "record_distance": distance,   
        }
        race_details.append(race_detail_dict)
        
    possible_winning_holds_count = []
    for race_detail_dict in race_details:
        race_winning_holds = calculate_winning_button_holds(
            race_detail_dict.get("time"), race_detail_dict.get("record_distance")
        )
        possible_winning_holds_count.append(len(race_winning_holds))
    solution = reduce(lambda a,b : a * b, possible_winning_holds_count)

    duration = default_timer() - start
    return {
        "solution": solution,
        "duration": duration
    }

def read_table(table_lines: str) -> list[dict[str, int]]:
    table_contents = []
    table_dicts = []
    for line in table_lines:
        line.split().pop(0)
        split_line = line.split()
        split_line.pop(0) # removes table row title
        table_contents.append(split_line)
    for index, entry in enumerate(table_contents[0]):
        table_dict = {
            "time": int(entry),
            "record_distance": int(table_contents[1][index]),
        }

        table_dicts.append(table_dict)

    return table_dicts

def calculate_winning_button_holds(time: int, record_distance: int) -> list[int]:
    winning_holds = []
    iterator = 1
    while iterator < time:
        hold = iterator
        speed = iterator
        time_remaining = time - iterator
        travelled_distance = speed * time_remaining
        if travelled_distance > record_distance:
            winning_holds.append(hold)
        iterator = iterator + 1

    return winning_holds

def join_line(line: str) -> int:
    trimmed_line = line.split()
    trimmed_line.pop(0)
    joined_line = int(''.join(trimmed_line))
    return joined_line
