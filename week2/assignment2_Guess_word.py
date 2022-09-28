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

WORD_SET: Set = {"IRON MAN", "SPIDER MAN", "THOR", "CAPTAIN AMERICA", "BLACK WIDON", "BLACK PANTHER", "SCARLET WITCH",
                 "ANT MAN", "DOCTOR STRANGE"}

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
    print(Style.RESET_ALL)

    # snow instruction
    print(
        "Above are the names of Mavel charactors. I will choose one of them, then you guess(not case-sensitive, initials compatible).")
    # secretIv oic«s a random word
    target_word: str = list(WORD_SET)[random.randint(0, len(NORD_SET) - 1)]
    # get the initials of the word
    target_word_initials: str = "".join([single_word[0] for single_word in target_word.split(" ")])

    is_good_guess: bool = False

    # get ready get into the cycles
    time_cycle_total = 0
    guess_times = 0

    time_total = time.perf_counter_ns() - t1

    for i in range(6):
        # get user's input
        user_answer: str = input ("Guess my word: ")

        time_cycle_start = time.perf_counter_ns()
        guess_times += 1
        # determine if the answer is correct in all capital letters or initials
        if user_answer.upper() == target_word.upper() or user_answer.upper() == target_word_initials.upper():
            print (f"You win! The answer is {Fore.BLUE}{target_word}{Style.RESET_ALL}")
            is_good_guess = True
            break
        else:
            print("Wrong! ", end="", flush=True)
        time_cycle_total += time.perf_counter_ns() - time_cycle_start

    if not is_good_guess:
        print(f"\nsorry, you lost! The answer is {Fore.BLUE}{target_word}{Style.RESET_ALL}")

    time_total += time_cycle_total / guess_times
    print (f"Guess times: {guess_times} cost time(ns): {time_total}",
        f" memory usage(MB) : {resource.getrusage (resource.RUSAGE_SELF).ru_maxrss/(1024.0*1024)}")

if __name__ == '__main__':
    GussMyWord()