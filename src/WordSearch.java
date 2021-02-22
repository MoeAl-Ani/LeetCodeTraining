import java.util.*;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return false;

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board,visited, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board,boolean[][] visited, int i, int j, int count, String word) {
        if (count == word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(count)) {
            return false;
        }
        if (visited[i][j]) return false;

        visited[i][j] = true;
        boolean found = dfs(board, visited,i +1, j, count+1, word) || dfs(board,visited, i-1, j, count+1, word) ||
                dfs(board,visited,i, j+1, count+1, word) || dfs(board,visited, i, j-1, count+1, word);
        visited[i][j] = false;
        return found;
    }


    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(new WordSearch().exist(board, "BFDE"));
    }
}
