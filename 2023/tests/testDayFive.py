import unittest 
from days.five import five

class TestDayFive(unittest.TestCase):
    def test_day_five_scenario_one(self):
        expected = 35
        actual = five(1, "./inputs/test/dayFive/one.txt").get("solution")

        self.assertEqual(actual, expected)

    def test_day_five_scenario_two(self):
        expected = 46
        actual = five(2, "./inputs/test/dayFive/one.txt").get("solution")

        self.assertEqual(actual, expected)
