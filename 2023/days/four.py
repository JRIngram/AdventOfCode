from timeit import default_timer
from functools import reduce
from utils.readFile import read_file

def four(scenario: int, input_path: str):
    lines = read_file(input_path)
    start = default_timer()
    solution = 0

    card_count_list = []
    score_list = []

    for line in lines:
        card = Card(line)
        if scenario == 1:
            score = card.get_card_score()
            score_list.append(score)
        if scenario == 2:
            card_count: dict[str, int] = {
                "card": card,
                "count": 1,
            }
            card_count_list.append(card_count)

    if scenario == 1:
        solution = reduce(lambda a,b : a + b, score_list)

    if scenario == 2:
        max_id = len(card_count_list)
        total_card_count = 0
        for card_count_dict in card_count_list:
            card = card_count_dict["card"]
            current_card_count = card_count_dict["count"]
            total_card_count = total_card_count + current_card_count
            matching_numbers_count = card.matching_count

            i = 1
            while i <= matching_numbers_count:
                if card.card_id + i < max_id:
                    card_index_to_add = card.card_id - 1 + i
                    card_count_list[card_index_to_add]["count"] = card_count_list[card_index_to_add]["count"] + current_card_count
                i = i + 1
        solution = total_card_count

    duration = default_timer() - start
    return {
        "solution": solution,
        "duration": duration
    }

class Card:
    """
        Scores the cards ID, winning numbers, numbers on the card and the match count
        Can also be used to retrieve the score of the card. 
    """

    def __init__(self, card_string: str):
        split_card_string = card_string.split(": ")
        split_numbers = split_card_string[1].split(" | ")

        self.card_id = int(split_card_string[0].removeprefix("Card "))
        self.winning_numbers = [int(num_string) for num_string in split_numbers[0].split()]
        self.numbers_on_card = [int(num_string) for num_string in split_numbers[1].split()]

        matching_count = 0
        for number_on_card in self.numbers_on_card:
            for winning_number in self.winning_numbers:
                if number_on_card == winning_number:
                    matching_count = matching_count + 1
        self.matching_count = matching_count

    def get_card_score(self) -> int:
        """
            Score is calculated via the matching_count.
            A match of 1 leads to a score of one,
            after that each additional match leads to
            the score doubling.
        """
        if self.matching_count == 0:
            return 0
        if self.matching_count == 1:
            return 1

        score = 1
        double_count = self.matching_count - 1
        i = 1
        while i <= double_count:
            score = score * 2
            i = i + 1
        return score
