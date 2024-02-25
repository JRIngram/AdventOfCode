from timeit import default_timer
from utils import read_file
from functools import cmp_to_key
from enum import Enum

def seven(scenario: int, input_path: str):
    lines = read_file(input_path)
    start = default_timer()
    solution = 0

    card_bids = []
    for line in lines:
        split_line = line.split()
        card_bid = {
            "hand": split_line[0],
            "bid": int(split_line[1])
        }
        card_bids.append(card_bid)
    sorted_bid_list = quicksort_bids(card_bids)
    for index, card_bid in enumerate(sorted_bid_list):
        solution = solution + (card_bid.get("bid") * (index + 1))

    duration = default_timer() - start
    return {
        "solution": solution,
        "duration": duration
    }

class HandType(Enum):
    FIVE_OF_A_KIND = 6
    FOUR_OF_A_KIND = 5
    FULL_HOUSE = 4
    THREE_OF_A_KIND = 3
    TWO_PAIR = 2
    ONE_PAIR = 1
    HIGH_CARD = 0

def get_hand_type(hand: str) -> HandType:
    split_hand = list(hand)
    card_set = set(split_hand)
    occurences = dict.fromkeys(card_set, 0)
    for card_key in card_set:
        card_occurences = 0
        for card in split_hand:
            if card == card_key:
                card_occurences = card_occurences + 1
        occurences.update({ card_key: card_occurences})

    pair_count = 0
    has_trio = False
    for key in occurences:
        if occurences.get(key) == 5:
            return HandType.FIVE_OF_A_KIND
        if occurences.get(key) == 4:
            return HandType.FOUR_OF_A_KIND
        if occurences.get(key) == 3:
            has_trio = True
        if occurences.get(key) == 2:
            pair_count = pair_count + 1
    if has_trio:
        if pair_count > 0:
            return HandType.FULL_HOUSE
        else:
            return HandType.THREE_OF_A_KIND
    if pair_count == 2:
        return HandType.TWO_PAIR
    if pair_count == 1:
        return HandType.ONE_PAIR
    
    return HandType.HIGH_CARD

def compare_two_hands(hand_one: str, hand_two: str) -> int:
    hand_one_type = get_hand_type(hand_one)
    hand_two_type = get_hand_type(hand_two)
    if hand_one_type.value > hand_two_type.value:
        return 1
    if hand_one_type.value < hand_two_type.value:
        return -1
    
    split_hand_one = list(hand_one)
    split_hand_two = list(hand_two)
    counter = 0
    while counter < len(split_hand_one):
        card_one_value = split_hand_one[counter]
        card_two_value = split_hand_two[counter]
        if card_one_value == 'T':
            card_one_value = 10
        elif card_one_value == 'J':
            card_one_value = 11
        elif card_one_value == 'Q':
            card_one_value = 12
        elif card_one_value == 'K':
            card_one_value = 13
        elif card_one_value == 'A':
            card_one_value = 14

        if card_two_value == 'T':
            card_two_value = 10
        elif card_two_value == 'J':
            card_two_value = 11
        elif card_two_value == 'Q':
            card_two_value = 12
        elif card_two_value == 'K':
            card_two_value = 13
        elif card_two_value == 'A':
            card_two_value = 14
        card_one_value = int(card_one_value)
        card_two_value = int(card_two_value)

        if card_one_value > card_two_value:
            return 1
        if card_one_value < card_two_value:
            return -1
        counter = counter + 1
    return 0

def quicksort_bids(card_bid_list: list[dict]):
    list_length = len(card_bid_list)
    high_list = []
    low_list = []
    equal_list = []
    if list_length <= 1:
        return card_bid_list
    pivot = card_bid_list[0].get("hand")
    for card in card_bid_list:
        hand_comparison = compare_two_hands(pivot, card.get("hand"))
        if hand_comparison == -1:
            high_list.append(card)
        elif hand_comparison == 1:
            low_list.append(card)
        else:
            equal_list.append(card)
    return quicksort_bids(low_list) + equal_list + quicksort_bids(high_list)
