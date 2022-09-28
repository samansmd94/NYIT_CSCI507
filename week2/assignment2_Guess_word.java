
/***

@Group no : Group 2 (Hao, Sam)
@university : NYIT (cybersecurity)
@Student ID : 1313045, 1314389

*/
package week2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;

public class assignment2_Guess_word {

    static Set<String> wordSet = new HashSet<String>() {
         { 
            add ("IRON MAN");
            add ("SPIDER MAN");
            add ("THOR");
            add ("CAPTAIN AMERICA");
            add ("BLACK NILON");
            add ("BLACK PANTHER");
            add ("SCARLET WHITCH");
            add ("ANT MAN");
            add ("DOCTOR STRANGE");

         }
    };

    public static void main(String[] args) throws InterruptedException {

        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long t1 = System.nanoTime();

        for (String word : wordSet) {
            System.out.print(word + ", ");
        
        }

        System.out.println("\n Above the names of Mavel charactors. I will choose one of them, then you guess(not case-sensitive, initials compatible).");
            
        String[] wordArray = new String[wordSet.size()];
        wordSet.toArray(wordArray);

        String targetWord = wordArray[new Random().nextInt (wordSet.size ())];
        StringBuilder targetWordInitials = new StringBuilder(new String());
        for (String word : targetWord.split(" ")) {
            targetWordInitials.append(word.charAt(0));
        }

        boolean isGoodGuess = false;
        int guess_times = 0;
        long time_cycle_total = 0;

        long time_total = System.nanoTime() - t1;

        for (int i = 0; i < 6; i++) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Guess my word:");
            String userAnswer = sc.nextLine();

            long timeCycleStart = System.nanoTime();
            guess_times++;

            if (userAnswer.equalsIgnoreCase(targetWord) || userAnswer.equalsIgnoreCase(targetWordInitials.toString())) {
                System.out.println("You win! The answer is " + targetWord);
                isGoodGuess = true;
                break;
            } else {
                System.out.print("Wrong!");
            }

            time_cycle_total += System.nanoTime() - timeCycleStart;
        }

        if (!isGoodGuess) {
            System.out.println("Sorry, you lost! The answer is " + targetWord);
        }

        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        time_total += time_cycle_total / guess_times;
        System.out.println("Guess times:" + guess_times + " cost time(ns):" + time_total + " memory used(MB):" + actualMemUsed / (1024.0 * 1024.0));
    }

}

