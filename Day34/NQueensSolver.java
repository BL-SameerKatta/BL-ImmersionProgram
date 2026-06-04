/**
 * Solves the N-Queens problem using Backtracking.
 */
import java.util.ArrayList;
import java.util.List;

public class NQueensSolver {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        placeQueens(board, 0, solutions);
        return solutions;
    }

    private static void placeQueens(char[][] board, int row, List<List<String>> solutions) {
        if (row == board.length) {
            solutions.add(constructBoard(board));
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                placeQueens(board, row + 1, solutions);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isSafe(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private static List<String> constructBoard(char[][] board) {
        List<String> validBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            validBoard.add(new String(board[i]));
        }
        return validBoard;
    }
}
