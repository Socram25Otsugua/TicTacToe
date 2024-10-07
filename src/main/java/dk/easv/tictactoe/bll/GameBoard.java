package dk.easv.tictactoe.bll;

public class GameBoard implements IGameBoard {
    private int[][] board; // 3x3 board
    private int currentPlayer;
    private boolean gameOver;

    public GameBoard() {
        newGame();
    }

    @Override
    public int getNextPlayer() {
        return currentPlayer;
    }

    @Override
    public boolean play(int col, int row) {
        if (gameOver || board[row][col] != -1) {
            return false; // Invalid move
        }
        board[row][col] = currentPlayer; // Place the marker
        if (checkWin(currentPlayer)) {
            gameOver = true;
        } else if (isBoardFull()) {
            gameOver = true; // Draw
        } else {
            currentPlayer = (currentPlayer + 1) % 2; // Switch player
        }
        return true; // Move accepted
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getWinner() {
        if (!gameOver) return -1;
        for (int i = 0; i < 2; i++) {
            if (checkWin(i)) {
                return i; // Return the winning player
            }
        }
        return -1; // Draw
    }

    @Override
    public void newGame() {
        board = new int[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = -1; // Empty cell
            }
        }
        currentPlayer = 0; // Player 0 starts
        gameOver = false;
    }

    private boolean checkWin(int player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == -1) {
                    return false; // Found an empty cell
                }
            }
        }
        return true; // No empty cells found
    }
}
