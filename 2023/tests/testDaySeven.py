import unittest
from days.seven import seven, get_hand_type, HandType, compare_two_hands

class TestDaySeven(unittest.TestCase):
    def test_day_seven_scenario_one(self):
        expected = 6440
        actual = seven(1, "./inputs/test/daySeven/one.txt").get("solution")
        self.assertEqual(actual, expected)
    
    def test_day_seven_scenario_two(self):
        expected = 5905
        actual = seven(2, "./inputs/test/daySeven/one.txt").get("solution")
        self.assertEqual(actual, expected)
    
    def test_get_hand_type(self):
        self.assertEqual(get_hand_type('12345', False), HandType.HIGH_CARD)
        self.assertEqual(get_hand_type('12234', False), HandType.ONE_PAIR)
        self.assertEqual(get_hand_type('12233', False), HandType.TWO_PAIR)
        self.assertEqual(get_hand_type('11123', False), HandType.THREE_OF_A_KIND)
        self.assertEqual(get_hand_type('11122', False), HandType.FULL_HOUSE)
        self.assertEqual(get_hand_type('11112', False), HandType.FOUR_OF_A_KIND)
        self.assertEqual(get_hand_type('11111', False), HandType.FIVE_OF_A_KIND)
        self.assertEqual(get_hand_type('1234J', False), HandType.HIGH_CARD) 
        self.assertEqual(get_hand_type('1234J', True), HandType.ONE_PAIR)
        self.assertEqual(get_hand_type('11J22', True), HandType.FULL_HOUSE)
        self.assertEqual(get_hand_type('JJJJJ', True), HandType.FIVE_OF_A_KIND)
    
    def test_compare_two_hands(self):
        self.assertEqual(compare_two_hands('11111', '11111', 1), 0)
        self.assertEqual(compare_two_hands('11111', '22222', 1), -1)
        self.assertEqual(compare_two_hands('22222', '11111', 1), 1)
        self.assertEqual(compare_two_hands('KQJ98', 'QJ891', 1), 1)
        self.assertEqual(compare_two_hands('KQJ98', 'QKJ98', 1), 1)
        self.assertEqual(compare_two_hands('KQJ98', '11111', 1), -1)
        self.assertEqual(compare_two_hands('KKJKK', 'KKTKK', 1), 1)
        self.assertEqual(compare_two_hands('2222J', '22229', 1), 1)
        self.assertEqual(compare_two_hands('2222J', '22222', 2), -1)

