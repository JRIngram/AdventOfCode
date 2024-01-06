from utils.readFile import read_file
from timeit import default_timer

def one(scenario: int, input_path: str):
    start = default_timer()
    lines: str = read_file(input_path)
    sum_total = 0
    for line in lines:
        if scenario == 2:
            digit_words = get_digit_word_order(line)
            for digit in digit_words:
                line = replace_digit_word(line, digit[0])
        
        digit_pair = create_digit_pair(line)
        sum_total += digit_pair
    duration = default_timer() - start
    return {
        "solution": sum_total,
        "duration": duration
    }


def get_digit_word_order(line: str):
    zero_index = line.find('zero')
    nine_index = line.find('nine')
    eight_index = line.find('eight')
    seven_index = line.find('seven')
    six_index = line.find('six')
    five_index = line.find('five')
    four_index = line.find('four')
    three_index = line.find('three')
    two_index = line.find('two')
    one_index = line.find('one')
    digit_dictionary = {
        'zero': zero_index,
        'one': one_index,
        'two': two_index,
        'three': three_index,
        'four': four_index,
        'five': five_index,
        'six': six_index,
        'seven': seven_index,
        'eight': eight_index,
        'nine': nine_index
    }
    filtered_digit_dict = dict((k,v) for k,v in digit_dictionary.items() if v>-1)
    digit_list = sorted(filtered_digit_dict.items(), key = lambda x : x[1])
    return digit_list

def create_digit_pair(line: str) -> int:
    first_digit = -1
    last_digit = -1

    for letter in line:
        if letter.isnumeric():
            last_digit = letter
            if first_digit == -1:
                first_digit = letter

    digit_pair = first_digit + last_digit
    return int(digit_pair)

def replace_digit_word(line: str, digit_word: str):
    # not elegant but should handle scenario where 
    # eightwo should change to 82
    replacer = ""
    if digit_word == 'zero':
        replacer = "zero0zero"
    if digit_word == 'one':
        replacer = "one1one"
    if digit_word == 'two':
        replacer = "two2two"
    if digit_word == 'three':
        replacer = "three3three"
    if digit_word == 'four':
        replacer = "four4four"
    if digit_word == 'five':
        replacer = "five5five"
    if digit_word == 'six':
        replacer = "six6six"
    if digit_word == 'seven':
        replacer = "seven7seven"
    if digit_word == 'eight':
        replacer = "eight8eight"
    if digit_word == 'nine':
        replacer = "nine9nine"

    if replacer != "":
        line = line.replace(digit_word, replacer)
    
    return line