"""
@Group no : Group 2 (Hao, Sam)
@university : NYIT (cybersecurity)
@Student ID : 1313045, 1314389

"""

import time
import random
import resource
import psutil

from colorama import Fore, Back, Style
from typing import List, Dict, Set

def GussMyWord():

    """
    "Guess My Word" that allows user to guess a word by 5-8 attempts in choosine letters.
    The program secretly picks a random word from the dictionary such as:'apple', 'oracle', 'amazon', 'microsoft,
    (or use your own dictionary). Our player then tries to figure out what word was picked within 5-8 attempts.
    the player succeeds, display "You win!", otherwise: "Sorry, you lost!'
    """

    t1 = time.perf_counter_ns()
    global WORD_SET

    # show all the words
    print(Fore.BLUE, end="")
    for word in WORD_SET:
        print(word, end=", ", flush=True)
    print(Styl)