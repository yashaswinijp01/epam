package com.epam;

import java.util.ArrayList;
import java.util.Arrays;

public class EightQueens {

    public static void main(String[] args) {
        ArrayList<char[][]> solutions = new ArrayList<>();

        char[][] result = new char[8][8];
        for (int r1 = 0; r1 < 8; r1++)
            Arrays.fill(result[r1], '.');

        solveAllNQueens(result, 0, solutions);

        System.out.println(solutions.size());
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("\nSolution " + (i + 1));

            char[][] board = solutions.get(i);

            for (char[] chars : board) {
                for (char aChar : chars) System.out.print(aChar);
                System.out.println();
            }
        }
    }

    private static void solveAllNQueens(char[][] board, int col, ArrayList<char[][]> solutions) {
        if (col == board.length) {
            char[][] copy = new char[board.length][board[0].length];
            for (int r = 0; r < board.length; r++)
                System.arraycopy(board[r], 0, copy[r], 0, board[0].length);
            solutions.add(copy);
        } else {
            for (int row = 0; row < board.length; row++) {
                board[row][col] = 'q';
                boolean canBeSafe = true;

                for (char[] chars : board) {
                    boolean found = false;
                    for (int j = 0; j < board.length; j++) {
                        if (chars[j] == 'q') {
                            if (found) {
                                canBeSafe = false;
                            }
                            found = true;
                        }
                    }
                }

                for (int i = 0; i < board.length; i++) {
                    boolean found = false;
                    for (char[] chars : board) {
                        if (chars[i] == 'q') {
                            if (found) {
                                canBeSafe = false;
                            }
                            found = true;
                        }
                    }
                }

                for (int offset = -board.length; offset < board.length; offset++) {
                    boolean found = false;
                    for (int i = 0; i < board.length; i++) {
                        if (inbounds(i, i + offset, board)) {
                            if (board[i][i + offset] == 'q') {
                                if (found) {
                                    canBeSafe = false;
                                }
                                found = true;
                            }
                        }
                    }
                }

                for (int offset = -board.length; offset < board.length; offset++) {
                    boolean found = false;
                    for (int i = 0; i < board.length; i++) {
                        if (inbounds(i, board.length - offset - i - 1, board)) {
                            if (board[i][board.length - offset - i - 1] == 'q') {
                                if (found) {
                                    canBeSafe = false;
                                }
                                found = true;
                            }
                        }
                    }
                }

                if (canBeSafe)
                    solveAllNQueens(board, col + 1, solutions);
                board[row][col] = '.';
            }
        }
    }

    private static boolean inbounds(int row, int col, char[][] mat) {
        return row >= 0 && row < mat.length && col >= 0 && col < mat[0].length;
    }


}
