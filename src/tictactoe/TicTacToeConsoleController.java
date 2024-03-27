package tictactoe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Represents a Console Controller for Tic Tac Toe. Handles user moves by executing them using the model;
 * conveys move outcomes to the user in the console.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final BufferedReader reader;
  private final Appendable output;
  private final TicTacToe model;

  /**
   * Constructor for TicTacToeConsoleController.
   *
   * @param input  A Readable object (e.g., InputStreamReader) for reading user inputs.
   * @param output An Appendable object (e.g., System.out) for outputting game state and prompts.
   */
  public TicTacToeConsoleController(Reader input, Appendable output) {
    this.reader = new BufferedReader(input);
    this.output = output;
    this.model = new TicTacToeModel(); // Initialize the Tic Tac Toe model.
  }

  /**
   * Execute a single game of Tic Tac Toe using the provided TicTacToe model.
   * When the game is over or the user quits, the playGame method ends.
   *
   * @param m a non-null TicTacToe model.
   * @throws IllegalStateException if there is an input/output error during the game.
   */
  @Override
  public void playGame(TicTacToe m) {
    try {
      while (!m.isGameOver()) {
        output.append(m.toString()).append("\n"); // Output game state
        output.append("Enter a move for ").append(m.getTurn().toString()).append(":\n");
        String inputLine = reader.readLine().trim();

        if (inputLine.equalsIgnoreCase("q")) {
          output.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
          return; // End the game if the user quits.
        }

        processUserMove(m, inputLine);
      }

      // Output final game state and the result of the game (win, lose, or tie).
      output.append(m.toString()).append("\n");
      output.append("Game is over! ").append(m.getResult().toString()).append("\n");

    } catch (IOException e) {
      throw new IllegalStateException("Failed to read/write input/output.", e);
    }
  }

  private void processUserMove(TicTacToe m, String inputLine) throws IOException {
    try {
      String[] moveStr = inputLine.split(" ");
      int row = Integer.parseInt(moveStr[0]) - 1;
      int col = Integer.parseInt(moveStr[1]) - 1;

      if (m.isValidMove(row, col)) {
        m.setCell(row, col);
      } else {
        output.append("Not a valid move: ").append(inputLine).append("\n");
      }
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      output.append("Not a valid number: ").append(inputLine).append("\n");
    }
  }
}
