package com.dsa.question.leetcode.medium;

import com.dsa.util.PerformanceUtil;

import static java.lang.System.out;

public class WordSearch {

    //When given with other options, think tree
    public static void main(String[] args) {

        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        char[][] board2 = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };


        char[][] board3 = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };


        char[][] board4 = new char[][]{
                {'a', 'b'}

        };

        out.println(PerformanceUtil.measureOperationDuration(() -> algorithm(board, "ABCCED")));
        out.println(PerformanceUtil.measureOperationDuration(() -> algorithm(board2, "SEE")));
        out.println(PerformanceUtil.measureOperationDuration(() -> algorithm(board3, "ABCB")));
        out.println(PerformanceUtil.measureOperationDuration(() -> algorithm(board4, "ba")));
    }

    public static boolean algorithm(char[][] board, String word) {

        int boardXL = board.length;
        int boardYL = board[0].length;
        boolean[][] chosen = new boolean[boardXL][boardYL];

        for (int i = 0; i < boardXL; i++) {
            for (int j = 0; j < boardYL; j++) {
                chosen[i][j] = true;
                boolean solve = solve(board, word, 0, i, j, chosen);
                chosen[i][j] = false;
                if (solve) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean solve(char[][] board, String word, int wordIndex, int boardX, int boardY, boolean[][] chosen) {
        int boardXL = board.length;
        int boardYL = board[0].length;

        if (word.length() - 1 == wordIndex && word.charAt(wordIndex) == board[boardX][boardY]) {
            return true;
        } else if (word.charAt(wordIndex) == board[boardX][boardY]) {
            if (boardX + 1 < boardXL && !chosen[boardX + 1][boardY] && (callSolvedRefactored(chosen, boardX + 1, boardY, board, word, wordIndex))) return true;
            if (boardX - 1 >= 0 && !chosen[boardX - 1][boardY] && (callSolvedRefactored(chosen, boardX - 1, boardY, board, word, wordIndex))) return true;
            if (boardY + 1 < boardYL && !chosen[boardX][boardY + 1] && (callSolvedRefactored(chosen, boardX, boardY + 1, board, word, wordIndex))) return true;
            return boardY - 1 >= 0 && !chosen[boardX][boardY - 1] && (callSolvedRefactored(chosen, boardX, boardY - 1, board, word, wordIndex));
        }

        return false;
    }

    private static boolean callSolvedRefactored(boolean[][] chosen, int boardX, int boardY, char[][] board, String word, int wordIndex) {
        chosen[boardX][boardY] = true;
        boolean solve = solve(board, word, wordIndex + 1, boardX, boardY, chosen);
        chosen[boardX][boardY] = false;
        return solve;
    }
}
