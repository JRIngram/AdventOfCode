from timeit import default_timer
from utils import read_file

def two(scenario: int, input_path: str):
    lines = read_file(input_path)
    start = default_timer()
    MAX_COUNTS = {
        "MAX_GREEN_COUNT": 13,
        "MAX_BLUE_COUNT": 14,
        "MAX_RED_COUNT": 12,
    }
    sum_total = 0
    power_total = 0
    solution = 0

    for line in lines:
        game = Game(line, MAX_COUNTS)
        if scenario == 1:
            if game.is_possible:
                sum_total += game.id
        if scenario == 2:
            game_power = game.minimum_required_cubes['green'] * game.minimum_required_cubes['blue'] * game.minimum_required_cubes['red']
            power_total += game_power

    duration = default_timer() - start

    if scenario == 1:
        solution = sum_total
    if scenario == 2:
        solution = power_total

    return {
        "solution": solution,
        "duration": duration
    }

class Game:
    def __init__(self, game_string: str, max_counts: dict):
        split_game_string = game_string.replace(': ',':').replace('; ', ';').split(':')
        self.id = int(split_game_string[0].strip('Game '))
        self.is_possible = True
        self.minimum_required_cubes = {
            "blue": 0,
            "red": 0,
            "green": 0,
        }

        game_sets = split_game_string[1].split(';')
        for game_set in game_sets:
            processed_set = GameSet(game_set)
            if (processed_set.green_count > max_counts.get('MAX_GREEN_COUNT')
                or processed_set.blue_count > max_counts.get('MAX_BLUE_COUNT')
                or processed_set.red_count > max_counts.get('MAX_RED_COUNT')):
                self.is_possible = False

            if processed_set.green_count > self.minimum_required_cubes.get('green'):
                self.minimum_required_cubes['green'] = processed_set.green_count

            if processed_set.blue_count > self.minimum_required_cubes.get('blue'):
                self.minimum_required_cubes['blue'] = processed_set.blue_count

            if processed_set.red_count > self.minimum_required_cubes.get('red'):
                self.minimum_required_cubes['red'] = processed_set.red_count

class GameSet:
    def __init__(self, set_string: str):
        self.green_count = 0
        self.blue_count = 0
        self.red_count = 0

        cube_strings = set_string.split(', ')
        for cube_string in cube_strings:
            split_cube_string = cube_string.split(' ')
            cube_number = int(split_cube_string[0])
            cube_colour = split_cube_string[1]

            if cube_colour == 'green':
                self.green_count = cube_number
            elif cube_colour == 'blue':
                self.blue_count = cube_number
            elif cube_colour == 'red':
                self.red_count = cube_number
