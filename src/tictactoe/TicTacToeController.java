package tictactoe;

/**
 * Represents a controller for Tic Tac Toe.
 */
public interface TicTacToeController {
  /**
   * Execute a single game of Tic Tac Toe using the provided TicTacToe model.
   * When the game is over or the user quits, the playGame method ends.
   *
   * @param m a non-null TicTacToe model.
   */
  void playGame(TicTacToe m);
}
