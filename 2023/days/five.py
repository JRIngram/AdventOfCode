from timeit import default_timer
from utils import read_file
import re

def five(scenario: int, input_path: str):
    lines = read_file(input_path)
    start = default_timer()
    solution = 0

    seed_line = lines.pop(0).removeprefix("seeds: ").split()
    conversion_maps = build_conversion_map_lists(lines)


    if scenario == 1:
        seeds = seed_line
        print(seeds)        
    elif scenario == 2:
        lowest_seed_loc = float('inf')
        seed_line_length = len(seed_line)
        seed_counter = 0
        seed_range_counter = 1
        while seed_counter < seed_line_length and seed_range_counter < seed_line_length:
            seeds = []
            seed_start = int(seed_line[seed_counter])
            seed_range = int(seed_line[seed_range_counter])
            seed_counter = seed_counter + 2
            seed_range_counter = seed_range_counter + 2
            print(seed_start, ' range: ', seed_range)
            seed_iterator = 0
            while seed_iterator < seed_range:
                seeds.append(seed_start + seed_iterator)
                seed_iterator = seed_iterator + 1
                if seed_iterator % 100000 == 0:
                    print('.', end= ' ')
            print(' - ', len(seeds))
            lowest_seed_for_range = get_lowest_seed_location(seeds, conversion_maps)
            if lowest_seed_for_range < lowest_seed_loc:
                lowest_seed_loc = lowest_seed_for_range

            print('lowest for iteration', lowest_seed_for_range)
        solution = lowest_seed_loc

    if scenario == 1:
        solution = get_lowest_seed_location(seeds, conversion_maps)

    duration = default_timer() - start
    return {
        "solution": solution,
        "duration": duration
    }

def build_conversion_map_lists(lines):
    """
        Builds conversion maps.
        Finds the index for the titles and next title
        and build maps out of items from title until the next title
    """

    map_title_regex = '([a-zA-Z]{1,}-){2}[a-zA-Z]{1,} map:'

    title_index_list = []
    map_lists = []
    for index, line in enumerate(lines):
        if re.match(map_title_regex, line):
            title_index_list.append(index)
    for index, map_start_index in enumerate(title_index_list):
        next_index = index + 1
        end_index = len(lines) # Sets end as length if no next title
        if next_index == len(title_index_list):
            map_lists.append(read_seed_map_lines(lines, map_start_index, end_index))
        else:
            map_lists.append(read_seed_map_lines(
                lines,
                map_start_index,
                title_index_list[next_index]
            ))
    return map_lists

def read_seed_map_lines(lines, start, end):
    """
        Read lines from the start index and end
        Sets start index as the mapping title
        All other lines that are not '' are set as mappings
    """
    map_lines = lines[start:end]
    map_title = map_lines.pop(0)
    mappings = []
    for mapping_line in map_lines:
        if mapping_line != '':
            split_mapping_line = mapping_line.split()
            mapping_range = int(split_mapping_line[2])
            mapping_dict = {
                "destination_range_start": int(split_mapping_line[0]),
                "destination_range_end": int(split_mapping_line[0]) +  mapping_range - 1,
                "source_range_start": int(split_mapping_line[1]),
                "source_range_end": int(split_mapping_line[1]) + mapping_range - 1,
                "range_length": int(split_mapping_line[2])
            }
            mappings.append(mapping_dict)
    return {
        "title": map_title.replace(" map:", "").replace("-to-", "-"),
        "mappings": mappings
    }

def seed_to_location(seed: str, conversion_mappings) -> int:
    """
        Sends a seed through various mappings
        For each map, checks if seed lies within a map range.
        If yes, mapping_val is changed to a mapped version of the input
        If no, mapping_val remains the same
    """
    mapping_val = int(seed)
    for conversion_mapping in conversion_mappings:
        mapping_val_changed = False
        for mapping in conversion_mapping.get('mappings'):
            if mapping_val_changed is False:
                if mapping.get("source_range_start") <= mapping_val <= mapping.get("source_range_end"):
                    distance_into_source = mapping_val - mapping.get("source_range_start")
                    mapping_val = mapping.get("destination_range_start") + distance_into_source
                    mapping_val_changed = True
    return mapping_val

def get_lowest_seed_location(seeds, conversion_maps):
    """
        Maps of a list of seeds and returns the lowest value location
    """
    lowest_seed_loc = float('inf')
    i = 0
    for seed in seeds:
        seed_loc = seed_to_location(seed, conversion_maps)
        if seed_loc < lowest_seed_loc:
            lowest_seed_loc = seed_loc
        i = i + 1
        if i % 100000 == 0:
            print('#', end= ' ')
    return lowest_seed_loc
