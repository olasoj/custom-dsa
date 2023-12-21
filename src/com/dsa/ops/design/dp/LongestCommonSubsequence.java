package com.dsa.ops.design.dp;

import com.dsa.util.PerformanceUtil;

import java.util.*;

import static java.lang.System.out;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String sequence = "pqhgxgdofcvmr";
        String subSequence = "hofubmnylkra";

        out.println(sequence.length());
        out.println(subSequence.length());

        algorithm(sequence, subSequence);
        algorithm2(sequence, subSequence);
        PerformanceUtil.measureOperationDuration(() -> algorithm3(sequence, subSequence));
        PerformanceUtil.measureOperationDuration(() -> LCS(sequence, subSequence));
    }

    private static void algorithm3(String sequence, String subSequence) {
        int[][] lcs = LCS(sequence, subSequence);

        int result = lcs[sequence.length()][subSequence.length()];
        out.println(result);
    }

    /**
     * Returns table such that L[j][k] is length of LCS for X[0..j−1] and Y[0..k−1].
     */
    public static int[][] LCS(String sequence, String subSequence) {
        int n = sequence.length();
        int m = subSequence.length();

        int[][] L = new int[n + 1][m + 1];
        for (int j = 0; j < n; j++)
            for (int k = 0; k < m; k++)
                if (sequence.charAt(j) == subSequence.charAt(k)) // align this match
                    L[j + 1][k + 1] = L[j][k] + 1;
                else // choose to ignore one character
                    L[j + 1][k + 1] = Math.max(L[j][k + 1], L[j + 1][k]);
        return L;
    }


    private static void algorithm2(String sequence, String subSequence) {
        int currentSequenceCharacterIndex = 0;

        int sequenceLength = sequence.length();
        int subSequenceLength = subSequence.length();

        boolean[][] ready = new boolean[sequenceLength + 1][subSequenceLength + 1];
        int[][] dp = new int[sequenceLength + 1][subSequenceLength + 1];

        for (int currentSubSequenceCharacterIndex = 0; currentSubSequenceCharacterIndex < subSequence.length(); currentSubSequenceCharacterIndex++) {
            if (!ready[currentSequenceCharacterIndex][currentSubSequenceCharacterIndex]) {
                int solve = solve(sequence, subSequence, currentSequenceCharacterIndex, currentSubSequenceCharacterIndex, ready, dp);
                out.println(solve);
            }
        }

        for (boolean[] r : ready)
            out.println(Arrays.toString(r));

        for (int[] r : dp)
            out.println(Arrays.toString(r));

    }

    private static int solve(
            String sequence,
            String subSequence,
            int currentSequenceCharacterIndex,
            int currentSubSequenceCharacterIndex,
            boolean[][] ready,
            int[][] dp
    ) {
        int h = 0;

        int sequenceLength = sequence.length();
        int subSequenceLength = subSequence.length();

        if (ready[currentSequenceCharacterIndex][currentSubSequenceCharacterIndex]) return dp[currentSequenceCharacterIndex][currentSubSequenceCharacterIndex];

        if (currentSequenceCharacterIndex >= sequenceLength || currentSubSequenceCharacterIndex >= subSequenceLength) {
            return 0;
        } else {

            if (sequence.charAt(currentSequenceCharacterIndex) == subSequence.charAt(currentSubSequenceCharacterIndex)) {
                h = Math.max(h, 1 + solve(sequence, subSequence, currentSequenceCharacterIndex + 1, currentSubSequenceCharacterIndex + 1, ready, dp));
            } else {
                h = Math.max(h, solve(sequence, subSequence, currentSequenceCharacterIndex + 1, currentSubSequenceCharacterIndex, ready, dp));
                h = Math.max(h, solve(sequence, subSequence, currentSequenceCharacterIndex, currentSubSequenceCharacterIndex + 1, ready, dp));
            }
        }

        ready[currentSequenceCharacterIndex][currentSubSequenceCharacterIndex] = true;
        dp[currentSequenceCharacterIndex][currentSubSequenceCharacterIndex] = h;
        return h;
    }

    /**
     * Complete search
     *
     * @param sequence    sequence
     * @param subSequence subSequence
     */
    private static void algorithm(String sequence, String subSequence) {

        List<Character> subSequenceFound = new ArrayList<>();
        Set<List<Character>> allSubSequenceFound = new HashSet<>();
        int currentSequenceCharacterIndex = 0;

        for (int currentSubSequenceCharacterIndex = 0; currentSubSequenceCharacterIndex < subSequence.length(); currentSubSequenceCharacterIndex++) {
            solve(sequence, subSequence, currentSequenceCharacterIndex, currentSubSequenceCharacterIndex, allSubSequenceFound, subSequenceFound);
        }

        out.println(allSubSequenceFound);
    }

    private static void solve(
            String sequence,
            String subSequence,
            int currentSequenceCharacterIndex,
            int currentSubSequenceCharacterIndex,
            Set<List<Character>> allSubSequenceFound,
            List<Character> subSequenceFound) {

        int sequenceLength = sequence.length();
        int subSequenceLength = subSequence.length();

        if (currentSequenceCharacterIndex >= sequenceLength || currentSubSequenceCharacterIndex >= subSequenceLength) {
            if (!subSequenceFound.isEmpty()) allSubSequenceFound.add(List.copyOf(subSequenceFound));
        } else {

            if (sequence.charAt(currentSequenceCharacterIndex) == subSequence.charAt(currentSubSequenceCharacterIndex)) {
                subSequenceFound.add(sequence.charAt(currentSequenceCharacterIndex));
                solve(sequence, subSequence, currentSequenceCharacterIndex + 1, currentSubSequenceCharacterIndex + 1, allSubSequenceFound, subSequenceFound);
                subSequenceFound.remove(subSequenceFound.size() - 1);
            } else {
                solve(sequence, subSequence, currentSequenceCharacterIndex + 1, currentSubSequenceCharacterIndex, allSubSequenceFound, subSequenceFound);
                solve(sequence, subSequence, currentSequenceCharacterIndex, currentSubSequenceCharacterIndex + 1, allSubSequenceFound, subSequenceFound);
            }
        }

    }
}
