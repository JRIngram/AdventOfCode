from math import prod
from timeit import default_timer
import re
from utils.readFile import read_file

def three(scenario: int, input_path: str):
    lines = read_file(input_path)
    start = default_timer()

    number_list: list[NumberCoord] = []
    symbol_list: list[SymbolCoord] = []
    asterisk_list: list[SymbolCoord] = []
    non_adjacent_number_list: int = []
    gear_ratios = []

    solution: int = 0

    for index, line in enumerate(lines):
        number_regex = '\d{1,}'
        symbol_regex = '[^\d{1,} | \.]'
        numbers_in_line = re.finditer(number_regex, line)
        symbols_in_line = re.finditer(symbol_regex, line)
        for number in numbers_in_line:
            number_list.append(NumberCoord(int(number.group()), index, number.span()))
        for symbol in symbols_in_line:
            symbol_list.append(SymbolCoord(symbol.group(), index, symbol.span()[0]))
            if symbol.group() == '*':
                asterisk_list.append(SymbolCoord(symbol.group(), index, symbol.span()[0]))

    for number in number_list:
        if is_number_symbol_adjacent(number, symbol_list):
            non_adjacent_number_list.append(number.value)

    for asterisk in asterisk_list:
        gear_ratios.append(get_gear_ratio(asterisk, number_list))

    duration = default_timer() - start

    if scenario == 1:
        solution = sum(non_adjacent_number_list)

    if scenario == 2:
        solution = sum(gear_ratios)

    return {
        "solution": solution,
        "duration": duration,
    }

class NumberCoord:
    """
        Stores the value, line number, length and first x index of a given number
        on a cartesian plane.
    """
    def __init__(self, value: int, line_number: int, line_position):
        self.value = value
        self.line_number = line_number
        self.first_x_index = line_position[0]
        self.length = line_position[1] - line_position[0]

class SymbolCoord:
    """
        Stores the value, line number, and position of a given symbol
        on a cartesian plane.
    """
    def __init__(self, value, line_number, line_position: int):
        self.value = value
        self.line_number = line_number
        self.line_position = line_position

def is_number_symbol_adjacent(number: NumberCoord, symbol_list: list[SymbolCoord]) -> bool:
    """
        Returns true if number is adjacent to a symbol on a cartesian plane
    """
    x_coords_to_check = list(
        range(number.first_x_index - 1, number.first_x_index + number.length + 1)
    )
    y_coords_to_check = list(range(number.line_number - 1, number.line_number + 2))

    for y in y_coords_to_check:
        for x in x_coords_to_check:
            for symbol in symbol_list:
                if(symbol.line_number == y and symbol.line_position == x):
                    return True

    return False

def get_gear_ratio(asterisk: SymbolCoord, number_list: list[NumberCoord]) -> int:
    """
    Returns the product of numbers adjacent to the asterisk if the number of
    adjacent numbers is greater than or equal to 2. Else returns 0
    """
    x_coords_to_check = list(
        range(asterisk.line_position - 1, asterisk.line_position + 2)
    )
    y_coords_to_check = list(range(asterisk.line_number - 1, asterisk.line_number + 2))

    adjacent_numbers_set = set()

    for y in y_coords_to_check:
        for x in x_coords_to_check:
            for number in number_list:
                num_x_coords_to_check = list(
                    range(number.first_x_index, number.first_x_index + number.length)
                )
                for num_x in num_x_coords_to_check:
                    if y == number.line_number and x == num_x:
                        adjacent_numbers_set.add(number.value)

    if len(adjacent_numbers_set) < 2:
        return 0
    return prod(adjacent_numbers_set)
