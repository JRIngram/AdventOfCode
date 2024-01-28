import unittest
from days.three import three

class TestDayThree(unittest.TestCase):
    def test_day_three_scenario_one(self):
        expected = 4361
        actual = three(1, "./inputs/test/dayThree/one.txt").get("solution")

        self.assertEqual(actual, expected)

    def test_day_three_scenario_two(self):
        expected = 467835
        actual = three(2, "./inputs/test/dayThree/one.txt").get("solution")

        self.assertEqual(actual, expected)
