import unittest
from days.four import four, Card

class TestDayOne(unittest.TestCase):
    def test_day_four_scenario_one(self):
        expected = 13
        actual = four(1, "./inputs/test/dayFour/one.txt").get("solution")

        self.assertEqual(actual, expected)

    def test_day_four_scenario_two(self):
        expected = 30
        actual = four(2, "./inputs/test/dayFour/one.txt").get("solution")

        self.assertEqual(actual, expected)

    def test_card_score_zero(self):
        expected = 0
        test_card_string = "Card   1: 4 5 6 78 | 1 2 3 45"
        test_card = Card(test_card_string)
        self.assertEqual(test_card.get_card_score(), expected)

    def test_card_score_one(self):
        expected = 1
        test_card_string = "Card   1: 4 5 6 78 | 1 2 3 45 4"
        test_card = Card(test_card_string)
        self.assertEqual(test_card.get_card_score(), expected)

    def test_card_score_two(self):
        expected = 2
        test_card_string = "Card   1: 4 5 6 78 | 1 2 3 45 4 78"
        test_card = Card(test_card_string)
        self.assertEqual(test_card.get_card_score(), expected)

    def test_card_score_eight(self):
        expected = 8
        test_card_string = "Card   1: 4 5 6 78 | 1 2 3 45 4 5 6 78"
        test_card = Card(test_card_string)
        self.assertEqual(test_card.get_card_score(), expected)