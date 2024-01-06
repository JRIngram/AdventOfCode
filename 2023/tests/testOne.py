import unittest
from days.one import one, replace_digit_word, create_digit_pair, get_digit_word_order

class TestDayOne(unittest.TestCase):
    def test_day_one_scenario_one(self):
        expected = 142
        actual = one(1, "./inputs/test/dayOneScenarioOne.txt").get("solution")

        self.assertEqual(actual, expected)

    def test_day_one_scenario_two(self):
        expected = 281
        actual = one(2, "./inputs/test/dayOneScenarioTwo.txt").get("solution")

        self.assertEqual(actual, expected)

    def test_replace_digit_word(self):
        line = "eightwothree"
        expected_eight_replaced = "eight8eightwothree"
        actual = replace_digit_word(line, "eight")
        self.assertEqual(actual, expected_eight_replaced)

        actual = replace_digit_word(actual, "two")
        expected_two_replaced = "eight8eightwo2twothree"
        self.assertEqual(actual, expected_two_replaced)

        expected_three_replaced = "eight8eightwo2twothree3three"
        actual = replace_digit_word(actual, "three")
        self.assertEqual(actual, expected_three_replaced)
    
    def test_get_digit_word_order(self):
        line = "eightwothree"
        expected = [('eight', 0), ('two', 4), ('three', 7)]

        actual = get_digit_word_order(line)

        self.assertEqual(expected, actual)

    def test_create_digit_pair_single_digit(self):
        line = "two1nine"
        expected = 11
        
        actual = create_digit_pair(line)

        self.assertEqual(expected, actual)

    def test_create_digit_pair_multiple_digits(self):
        line = "zoneight234"
        expected = 24
        
        actual = create_digit_pair(line)

        self.assertEqual(expected, actual)