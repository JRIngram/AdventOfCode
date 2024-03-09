from timeit import default_timer
from utils import read_file
import re
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
    sorted_bid_list = quicksort_bids(card_bids, scenario)
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

def get_alpha_card_values(card, scenario: int):
    alpha_card_regex = '[TJQKA]'
    alpha_card_values_scenario_one = {
        "T": 10,
        "Q": 12,
        "K": 13,
        "A": 14,
    }
    if re.match(alpha_card_regex, card):
        if card == "J":
            if scenario == 1:
                return 11
            if scenario == 2:
                return 1
        return alpha_card_values_scenario_one.get(card)
    return int(card)

def get_hand_type(hand: str, joker_is_wildcard: bool) -> HandType:
    card_occurences_dict = get_card_occurences_in_hand(hand)
    if joker_is_wildcard is True:
        if card_occurences_dict.get("J") is not None:
            most_common_card = { "value": "", "occurences": 0}
            card_occurences_dict.pop("J")
            for key in card_occurences_dict:
                card_occurences = card_occurences_dict.get(key)
                if card_occurences > most_common_card.get("occurences"):
                    most_common_card.update({ "value": key, "occurences": card_occurences })
            if most_common_card.get("occurences") == 0:
                return get_hand_type(hand, False)
            joker_replaced_hand = hand.replace("J", most_common_card.get("value"))
            return get_hand_type(joker_replaced_hand, False)

    pair_count = 0
    has_trio = False
    for key in card_occurences_dict:
        if card_occurences_dict.get(key) == 5:
            return HandType.FIVE_OF_A_KIND
        if card_occurences_dict.get(key) == 4:
            return HandType.FOUR_OF_A_KIND
        if card_occurences_dict.get(key) == 3:
            has_trio = True
        if card_occurences_dict.get(key) == 2:
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

def get_card_occurences_in_hand(hand: str):
    split_hand = list(hand)
    card_set = set(split_hand)
    occurences = dict.fromkeys(card_set, 0)
    for card_key in card_set:
        card_occurences = 0
        for card in split_hand:
            if card == card_key:
                card_occurences = card_occurences + 1
        occurences.update({ card_key: card_occurences})
    return occurences

def compare_two_hands(hand_one: str, hand_two: str, scenario: int) -> int:
    joker_is_wildcard = True if scenario == 2 else False
    hand_one_type = get_hand_type(hand_one, joker_is_wildcard)
    hand_two_type = get_hand_type(hand_two, joker_is_wildcard)
    if hand_one_type.value > hand_two_type.value:
        return 1
    if hand_one_type.value < hand_two_type.value:
        return -1

    split_hand_one = list(hand_one)
    split_hand_two = list(hand_two)
    counter = 0
    while counter < len(split_hand_one):
        card_one_value = get_alpha_card_values(split_hand_one[counter], scenario)
        card_two_value = get_alpha_card_values(split_hand_two[counter], scenario)
        if card_one_value > card_two_value:
            return 1
        if card_one_value < card_two_value:
            return -1
        counter = counter + 1
    return 0

def quicksort_bids(card_bid_list: list[dict], scenario: int):
    list_length = len(card_bid_list)
    high_list = []
    low_list = []
    equal_list = []
    if list_length <= 1:
        return card_bid_list
    pivot = card_bid_list[0].get("hand")
    for card in card_bid_list:
        hand_comparison = compare_two_hands(pivot, card.get("hand"), scenario)
        if hand_comparison == -1:
            high_list.append(card)
        elif hand_comparison == 1:
            low_list.append(card)
        else:
            equal_list.append(card)
    return quicksort_bids(low_list, scenario) + equal_list + quicksort_bids(high_list, scenario)
