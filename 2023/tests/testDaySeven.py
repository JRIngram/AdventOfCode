import unittest
from days.seven import seven, get_hand_type, HandType, compare_two_hands

class TestDaySeven(unittest.TestCase):
    def test_day_seven_scenario_one(self):
        expected = 6440
        actual = seven(1, "./inputs/test/daySeven/one.txt").get("solution")
        self.assertEqual(actual, expected)
    
    def test_get_hand_type(self):
        self.assertEqual(get_hand_type('12345'), HandType.HIGH_CARD)
        self.assertEqual(get_hand_type('12234'), HandType.ONE_PAIR)
        self.assertEqual(get_hand_type('12233'), HandType.TWO_PAIR)
        self.assertEqual(get_hand_type('11123'), HandType.THREE_OF_A_KIND)
        self.assertEqual(get_hand_type('11122'), HandType.FULL_HOUSE)
        self.assertEqual(get_hand_type('11112'), HandType.FOUR_OF_A_KIND)
        self.assertEqual(get_hand_type('11111'), HandType.FIVE_OF_A_KIND)
    
    def test_compare_two_hands(self):
        self.assertEqual(compare_two_hands('11111', '11111'), 0)
        self.assertEqual(compare_two_hands('11111', '22222'), -1)
        self.assertEqual(compare_two_hands('22222', '11111'), 1)
        self.assertEqual(compare_two_hands('KQJ98', 'QJ891'), 1)
        self.assertEqual(compare_two_hands('KQJ98', 'QKJ98'), 1)
        self.assertEqual(compare_two_hands('KQJ98', '11111'), -1)

