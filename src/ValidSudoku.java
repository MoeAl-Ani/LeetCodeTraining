import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> digits = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '.');

        AtomicBoolean valid = new AtomicBoolean(true);
        // validate cols
        for (int row = 0; row < board.length; row++) {
            Set<Character> colCheckSet = new HashSet<>();
            for (int col = 0; col < board.length; col++) {
                if (!digits.contains(board[row][col])) return false;
                if (board[row][col] != '.') {
                    if (colCheckSet.contains(board[row][col])) {
                        valid.set(false);
                        break;
                    }
                    colCheckSet.add(board[row][col]);
                    if (!checkSubBlock(board, board[row][col], getRowSubIndex(row), getColSubIndex(col))) return false;
                }
            }
        }
        if (!valid.get()) return false;

        // validate rows
        for (int col = 0; col < board.length; col++) {
            Set<Character> rowCheckSet = new HashSet<>();
            for (int row = 0; row < board.length; row++) {
                if (!digits.contains(board[row][col])) return false;
                if (board[row][col] != '.') {
                    if (rowCheckSet.contains(board[row][col])) {
                        valid.set(false);
                        break;
                    }
                    rowCheckSet.add(board[row][col]);
                    if (!checkSubBlock(board, board[row][col], getRowSubIndex(row), getColSubIndex(col))) return false;
                }
            }
        }
        if (!valid.get()) return false;
        // check sub blocks
        return valid.get();
    }

    private int getRowSubIndex(int currentRowIndex) {
        if (currentRowIndex < 3)
            return 0;
        else if (currentRowIndex < 6)
            return 3;
        else return 6;
    }

    private int getColSubIndex(int currentColIndex) {
        if (currentColIndex < 3)
            return 0;
        else if (currentColIndex < 6)
            return 3;
        else return 6;
    }

    boolean checkSubBlock(char[][] board, Character value, int startRow, int startCol) {
        int rowSize = startRow + 3;
        int colSize = startCol + 3;
        Set<Character> subCheck = new HashSet<>();
        for (int row = startRow; row < rowSize; row++) {
            for (int col = startCol; col < colSize; col++) {
                if (!value.equals('.')) {
                    if (board[row][col] == value) {
                        if (subCheck.contains(value)) {
                            return false;
                        }
                        subCheck.add(value);
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] su1 =
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        char[][] su2 =
                       {{'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                        {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                        {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                        {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                        {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                        {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                        {'.', '.', '4', '.', '.', '.', '.', '.', '.'}};

        System.out.println("expect true , isValid = " + new ValidSudoku().isValidSudoku(su1));
        System.out.println("expect false , isValid = " + new ValidSudoku().isValidSudoku(su2));

    }
}
