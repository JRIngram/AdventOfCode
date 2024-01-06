import unittest
from days.two import two, Game, GameSet

class TestDayTwo(unittest.TestCase):
    def test_day_one_scenario_one(self):
        expected = 8
        actual = two(1, "./inputs/test/dayTwo/one.txt").get("solution")

        self.assertEqual(actual, expected)
    
    def test_game_set(self):
        set_string = '5 green, 13 blue, 12 red'
        game_set = GameSet(set_string)

        self.assertEqual(game_set.green_count, 5)    
        self.assertEqual(game_set.blue_count, 13)
        self.assertEqual(game_set.red_count, 12)

    def test_game_when_game_is_possible(self):
        MAX_COUNTS = {
            "MAX_RED_COUNT": 12,
            "MAX_GREEN_COUNT": 13,
            "MAX_BLUE_COUNT": 14,
        }
        game_string = 'Game 9: 12 red, 13 green, 14 blue; 5 green, 8 blue, 8 red; 2 blue, 6 green, 8 red; 6 red, 9 green'
        game = Game(game_string, MAX_COUNTS)

        self.assertEqual(9, game.id)
        self.assertEqual(True, game.is_possible)

    def test_game_when_game_is_not_possible(self):
        MAX_COUNTS = {
            "MAX_RED_COUNT": 12,
            "MAX_GREEN_COUNT": 13,
            "MAX_BLUE_COUNT": 14,
        }
        game_string = 'Game 1: 13 red, 14 green, 15 blue; 7 blue, 5 red; 10 red, 7 blue; 5 blue, 4 green, 15 red; 4 green, 6 red, 7 blue; 5 green, 8 blue, 4 red; 5 red, 4 blue, 3 green'
        game = Game(game_string, MAX_COUNTS)

        self.assertEqual(1, game.id)
        self.assertEqual(False, game.is_possible)

    def test_game_minimum_required_cubes(self):
        MAX_COUNTS = {
            "MAX_RED_COUNT": 12,
            "MAX_GREEN_COUNT": 13,
            "MAX_BLUE_COUNT": 14,
        }
        game_string = 'Game 1: 13 red, 14 green, 15 blue; 7 blue, 5 red; 10 red, 7 blue; 5 blue, 4 green, 15 red; 4 green, 6 red, 7 blue; 5 green, 8 blue, 4 red; 5 red, 4 blue, 3 green'
        expected = {
            "blue": 15,
            "red": 15,
            "green": 14,
        }

        game = Game(game_string, MAX_COUNTS)

        self.assertEqual(1, game.id)
        self.assertEqual(expected, game.minimum_required_cubes)
