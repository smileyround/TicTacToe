package tictactoe;

import static tictactoe.Player.O;
import static tictactoe.Player.X;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeModel implements TicTacToe {
  private Player[][] board;
  private Player turn;
  private Player winner;
  private boolean gameOver;

  public TicTacToeModel() {
    this.board = new Player[3][3];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        this.board[i][j] = null;
      }
    }
    this.turn = Player.X;
    this.gameOver = false;
  }

  @Override public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
            row -> " " + Arrays.stream(row).map(
                p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
    /**********
     List<String> rows = new ArrayList<>();
     for(Player[] row : getBoard()) {
     List<String> rowStrings = new ArrayList<>();
     for(Player p : row) {
     if(p == null) {
     rowStrings.add(" ");
     } else {
     rowStrings.add(p.toString());
     }
     }
     rows.add(" " + String.join(" | ", rowStrings));
     }
     return String.join("\n-----------\n", rows);
     ************/
  }

  @Override public void move(int r, int c) {
    if (gameOver) {
      throw new IllegalStateException("Game is over");
    }

    if( r < 0 || c < 0 || r > 2 || c > 2){
      throw new IllegalArgumentException("The position is invalid.");
    }

    if (board[r][c] != null) {
      throw new IllegalArgumentException("Invalid move, the position is occupied");
    }

    board[r][c] = turn;

    if (checkGameWinner(r, c)) {
      winner = turn;
      gameOver = true;
    } else {
      if (isBoardFull()) {
        gameOver = true;
      } else {
        switchTurn();
      }
    }
  }

  private void switchTurn() {
    if (turn == Player.X) {
      turn = Player.O;
    } else {
      turn = Player.X;
    }
  }

  @Override public Player getTurn() {
    return turn;
  }

  @Override public boolean isGameOver() {
    return gameOver;
  }

  @Override public Player getWinner() {
    return winner;
  }

  @Override public Player[][] getBoard() {
    Player[][] newBoard = new Player[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
    }
    return newBoard;
  }

  @Override public Player getMarkAt(int r, int c) throws IllegalArgumentException {
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
      throw new IllegalArgumentException("The mark requested is out of bounds");
    }
    return board[r][c];
  }

  @Override public boolean isValidMove(int row, int col) {
    return false;
  }

  @Override public void setCell(int row, int col) {

  }

  @Override public Object getResult() {
    return null;
  }

  private boolean isBoardFull() {
    for (Player[] row : board) {
      for (Player play : row) {
        if (play == null) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean checkGameWinner(int r, int c) {
    Player checkWinner = board[r][c];
    // Check the row
    if (board[r][0] == checkWinner && board[r][1] == checkWinner && board[r][2] == checkWinner) {
      return true; // Check the row
    } else if (board[0][c] == checkWinner && board[1][c] == checkWinner && board[2][c] == checkWinner) {
      return true; // Check the colum
    } else if (board[0][0] == checkWinner && board[1][1] == checkWinner && board[2][2] == checkWinner) {
      return true;  // Check the diagonal
    } else if (board[2][0] == checkWinner && board[1][1] == checkWinner && board[0][2] == checkWinner) {
      return true;
    } else {
      return false;
    }
  }
}
