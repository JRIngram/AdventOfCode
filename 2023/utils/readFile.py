from typing import List

def read_file(file_name: str) -> List[str]:
    f = open(file_name, 'r')
    lines = f.readlines()
    f.close()
    return lines