import unittest
from utils import read_file
from days.six import six, read_table, join_line

class TestDaySix(unittest.TestCase):
    def test_day_six_scenario_one(self):
        expected = 288
        actual = six(1, "./inputs/test/daySix/one.txt").get("solution")
        self.assertEqual(actual, expected)
    
    def test_day_six_scenario_two(self):
        expected = 71503
        actual = six(2, "./inputs/test/daySix/one.txt").get("solution")
        self.assertEqual(actual, expected)

    def test_read_table(self):
        lines = read_file("./inputs/test/daySix/one.txt")
        actual = read_table(lines)
        self.assertEqual(actual[0].get("time"), 7)
        self.assertEqual(actual[0].get("record_distance"), 9)
        self.assertEqual(actual[1].get("time"), 15)
        self.assertEqual(actual[1].get("record_distance"), 40)
        self.assertEqual(actual[2].get("time"), 30)
        self.assertEqual(actual[2].get("record_distance"), 200)
    
    def test_join_line(self):
        line = "Time:      7  15   30"
        expected = 71530
        actual = join_line(line)
        self.assertEqual(actual, expected)
