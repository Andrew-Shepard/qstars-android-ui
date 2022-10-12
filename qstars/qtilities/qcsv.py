import csv
from typing import List, Dict
from pathlib import Path


def toDict(csv_file_path: Path) -> List[Dict[str, str]]:
    with open(csv_file_path) as csv_file:
        r = csv.DictReader(csv_file)
        return [row for row in r]
