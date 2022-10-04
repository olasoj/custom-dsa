package com.dsa.question.hackerank;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.Duration;
import java.time.Instant;

public class GameWinner {

    /*
     * Complete the 'gameWinner' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING colors as parameter.
     */
    static boolean turn = false;

    public static String gameWinner(String colors) {
        // Write your code here
        StringBuilder sb = new StringBuilder(colors);

        int wendyWinCount = 0;
        int bobWinCount = 0;
        int charIndex = 0;
        String letterToPlay = null;

        for (int counter = 1; counter < sb.length(); counter++) {

            if (charIndex == sb.length() || charIndex + 1 == sb.length()) break;


            if (counter % 2 == 0) {
                //do black
                char c = sb.charAt(charIndex);
                if (c == 'b') {
                    if (charIndex - 1 < 0) charIndex = charIndex + 1;

                    boolean isUpper = sb.charAt(charIndex - 1) == 'b';

                    if (charIndex + 1 == sb.length()) break;
                    boolean isLower = sb.charAt(charIndex + 1) == 'b';

                    boolean isBob = isUpper && isLower;
                    if (isBob) {
                        bobWinCount = bobWinCount + 1;
                        sb.deleteCharAt(charIndex);
                        letterToPlay = "b";
                        continue;
                    }
                }
            }

            char c = sb.charAt(charIndex);
            if (c == 'w') {
                if (charIndex - 1 < 0) charIndex = charIndex + 1;
                boolean isUpper = sb.charAt(charIndex - 1) == 'w';

                if (charIndex + 1 == sb.length()) break;
                boolean isBob = isUpper && sb.charAt(charIndex + 1) == 'w';
                if (isBob) {
                    sb.deleteCharAt(charIndex);
                    wendyWinCount = wendyWinCount + 1;
                    letterToPlay = "w";
                }
            }

            charIndex = charIndex + 1;
            //do white
        }
        return (wendyWinCount > bobWinCount) ? "wendy" : "bob";

    }

    public static void main(String[] args) {
        Instant startTime = Instant.now();

        String random = RandomStringUtils.random(1000000, 0, 2, true, false, 'w', 'b');
        String gameWinner = gameWinnerEx(random);
//        String gameWinner = gameWinner(random);
        System.out.println(gameWinner);

        Instant endTime = Instant.now();
        Duration timeElapsed = Duration.between(startTime, endTime);
        System.out.println("Operation took: " + timeElapsed.toMillis() + " milliseconds");
    }

    public static int findAdjacentIndexingCanBeRemoved(String a, char gameChangerName) {
        char[] array = a.toCharArray();
        int sameCount = 0;
        for (int i = 0; i < array.length - 1; i++) {

            if ((array[i] == 'w' || array[i] == 'b') && (array[i + 1] == 'w' || array[i + 1] == 'b')) {
                // incase we have any other color
                return -2;
            }
            if (array[i] == array[i + 1] && array[i] == gameChangerName) {
                sameCount++;
            } else {
                sameCount = 0;
            }
            if (sameCount == 2) {
                return i;
            }
        }
        return -1;
    }

    public static String gameWinnerEx(String colors) {
        // Write your code here

        turn = !turn;
        char name = turn ? 'w' : 'b';

        int indexToBeRemoved = findAdjacentIndexingCanBeRemoved(colors, name);
        if (indexToBeRemoved == -2) {
            return "";
        }
        if (indexToBeRemoved == -1) {
            return name != 'w' ? "wendy" : "Bob";
        } else {
            StringBuilder sb = new StringBuilder(colors);
            sb.deleteCharAt(indexToBeRemoved);
            colors = sb.toString();
            return gameWinnerEx(colors);
        }
    }

}
