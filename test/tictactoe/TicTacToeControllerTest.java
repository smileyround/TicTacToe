package tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Arrays;

public class TicTacToeControllerTest {

  // ... previous test cases ...

  @Test
  public void testPlayerOWins() {
    // Test playing a game to completion where O wins
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 2 2 1 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    // Verify the game log output matches the expected result
    assertEquals(
        "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for X:\n" +
            " X |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for O:\n" +
            " X |   |  \n" +
            "-----------\n" +
            " O |   |  \n" +
            "-----------\n" +
            "   |   |  \n" +
            "Enter a move for X:\n" +
            " X |   |  \n" +
            "-----------\n" +
            " O |   |  \n" +
            "-----------\n" +
            "   | X |  \n" +
            "Enter a move for O:\n" +
            " X |   |  \n" +
            "-----------\n" +
            " O |   |  \n" +
            "-----------\n" +
            " O | X |  \n" +
            "Enter a move for X:\n" +
            " X |   |  \n" +
            "-----------\n" +
            " O |   |  \n" +
            "-----------\n" +
            " O | X |  \n" +
            "Enter a move for O:\n" +
            " X |   |  \n" +
            "-----------\n" +
            " O | O |  \n" +
            "-----------\n" +
            " O | X |  \n" +
            "Game is over!\n" +
            "O wins.\n",
        gameLog.toString());
  }

  @Test
  public void testNonIntegerInput() {
    // Test input with non-integer values
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("a b 2 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    // Verify the game log output matches the expected result
    String[] lines = gameLog.toString().split("\n");
    assertEquals(21, lines.length);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 9, lines.length));
    assertEquals("   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for X:\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for O:\n" +
        "Not a valid number: a\n" +
        "Not a valid number: b\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for O:\n" +
        "Game quit! Ending game state:\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  ", lastMsg);
  }

  @Test
  public void testSameCellTwice() {
    // Test playing the same cell twice
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    // Verify the game log output matches the expected result
    String[] lines = gameLog.toString().split("\n");
    assertEquals(27, lines.length);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 15, lines.length));
    assertEquals("   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for X:\n" +
        " X |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for O:\n" +
        " X |   |  \n" +
        "-----------\n" +
        " O |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for X:\n" +
        " X |   |  \n" +
        "-----------\n" +
        " O |   |  \n" +
        "-----------\n" +
        " X |   |  \n" +
        "Enter a move for O:\n" +
        "Not a valid move: 1, 1\n" +
        "Game quit! Ending game state:\n" +
        " X |   |  \n" +
        "-----------\n" +
        " O |   |  \n" +
        "-----------\n" +
        " X |   |  ", lastMsg);
  }

  @Test
  public void testInputWithQuit() {
    // Test quitting the game before completion
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    // Verify the game log output matches the expected result
    String[] lines = gameLog.toString().split("\n");
    assertEquals(21, lines.length);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 9, lines.length));
    assertEquals("   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for X:\n" +
        " X |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for O:\n" +
        " X |   |  \n" +
        "-----------\n" +
        " O |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Game quit! Ending game state:\n" +
        " X |   |  \n" +
        "-----------\n" +
        " O |   |  \n" +
        "-----------\n" +
        "   |   |  ", lastMsg);
  }

  @Test
  public void testMoveOutsideBoard() {
    // Test input with a move outside the bounds of the board
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("0 1 4 2 2 2 3 4 1 3 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    // Verify the game log output matches the expected result
    String[] lines = gameLog.toString().split("\n");
    assertEquals(27, lines.length);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 15, lines.length));
    assertEquals("   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for X:\n" +
        "Not a valid move: 0, 1\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for O:\n" +
        "   |   |  \n" +
        "-----------\n" +
        " O |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for X:\n" +
        "   |   |  \n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Game quit! Ending game state:\n" +
        "   |   |  \n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        "   |   |  ", lastMsg);
  }

  @Test
  public void testValidAndInvalidMoves() {
    // Test input with a mix of valid and invalid moves
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("0 1 x 2 2 2 1 1 3 2 1 3 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    // Verify the game log output matches the expected result
    String[] lines = gameLog.toString().split("\n");
    assertEquals(45, lines.length);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 33, lines.length));
    assertEquals("   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for X:\n" +
        "Not a valid move: 0, 1\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for O:\n" +
        "   |   |  \n" +
        "-----------\n" +
        " O |   |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for X:\n" +
        "   |   |  \n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        "   |   |  \n" +
        "Enter a move for O:\n" +
        "   |   |  \n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        " O |   |  \n" +
        "Enter a move for X:\n" +
        "   |   |  \n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        " O | X |  \n" +
        "Enter a move for O:\n" +
        "   |   |  \n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        " O | X | O\n" +
        "Enter a move for X:\n" +
        "   |   | X\n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        " O | X | O\n" +
        "Enter a move for O:\n" +
        "Not a valid number: x\n" +
        "   |   | X\n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        " O | X | O\n" +
        "Enter a move for O:\n" +
        "Not a valid move: 3, 2\n" +
        "Game quit! Ending game state:\n" +
        "   |   | X\n" +
        "-----------\n" +
        " O | X |  \n" +
        "-----------\n" +
        " O | X | O", lastMsg);
  }

  @Test
  public void testInvalidModel() {
    // Test an invalid model (controller throws an IllegalArgumentException)
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);

    // Verify that an IllegalArgumentException is thrown when playing the game
    assertThrows(IllegalArgumentException.class, () -> c.playGame(m));
  }

  @Test
  public void testFailingAppendable() {
    // Test that an IllegalStateException is thrown when using a failing Appendable
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 q");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);

    // Verify that an IllegalStateException is thrown when playing the game
    assertThrows(IllegalStateException.class, () -> c.playGame(m));
  }
}
