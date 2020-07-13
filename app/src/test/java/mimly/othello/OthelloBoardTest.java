package mimly.othello;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import mimly.othello.model.Board;
import mimly.othello.model.OthelloBoard;
import mimly.othello.model.Square;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OthelloBoardTest {

    private Board testBoard = null;

    @BeforeAll
    public void init() {
        this.testBoard = new OthelloBoard(8, 8);
    }

    @DisplayName("Testing List<Square> getHorizontalLine(final int row)...")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void testGetHorizontalLine(final int row) {
        List<Square> line = this.testBoard.getHorizontalLine(row);

        assertEquals(8, line.size(), "8 squares on the line.");

        int col = 0;
        for (Square square : line) {
            assertEquals(row, square.getRow(), "Row is always " + row);
            assertEquals(col++, square.getColumn(), "Column is unique.");
        }
    }

    @DisplayName("Testing (boundaries) List<Square> getHorizontalLine(final int row)...")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 8, Integer.MAX_VALUE})
    public void testBoundariesGetHorizontalLine(final int row) {
        List<Square> line = this.testBoard.getHorizontalLine(row);

        assertEquals(0, line.size(), "0 squares on the line.");
        assertTrue(line.isEmpty(), "0 squares on the line.");
    }

    @DisplayName("Testing List<Square> getVerticalLine(final int col)...")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void testGetVerticalLine(final int col) {
        List<Square> line = this.testBoard.getVerticalLine(col);

        assertEquals(8, line.size(), "8 squares on the line.");

        int row = 0;
        for (Square square : line) {
            assertEquals(col, square.getColumn(), "Col is always " + col);
            assertEquals(row++, square.getRow(), "Row is unique.");
        }
    }

    @DisplayName("Testing (boundaries) List<Square> getVerticalLine(final int col)...")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 8, Integer.MAX_VALUE})
    public void testBoundariesGetVerticalLine(final int col) {
        List<Square> line = this.testBoard.getVerticalLine(col);

        assertEquals(0, line.size(), "0 squares on the line.");
        assertTrue(line.isEmpty(), "0 squares on the line.");
    }

    @DisplayName("Testing List<Square> getPositiveDiagonalLine(final int row, final int col)...")
    @Test
    public void testGetPositiveDiagonalLine() {
        List<Square> line = this.testBoard.getPositiveDiagonalLine(0, 0);
        assertEquals(1, line.size(), "1 square on the line.");

        line = this.testBoard.getPositiveDiagonalLine(0, 7);
        assertEquals(8, line.size(), "8 squares on the line.");

        line = this.testBoard.getPositiveDiagonalLine(7, 0);
        assertEquals(8, line.size(), "8 squares on the line.");

        line = this.testBoard.getPositiveDiagonalLine(7, 7);
        assertEquals(1, line.size(), "1 square on the line.");
    }

    @DisplayName("Testing (boundaries) List<Square> getPositiveDiagonalLine(final int row, final int col)...")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 8, Integer.MAX_VALUE})
    public void testBoundariesGetPositiveDiagonalLine(final int wrong) {
        List<Square> line = this.testBoard.getPositiveDiagonalLine(0, wrong);
        assertEquals(0, line.size(), "0 squares on the line.");
        assertTrue(line.isEmpty(), "0 squares on the line.");

        line = this.testBoard.getPositiveDiagonalLine(wrong, 0);
        assertEquals(0, line.size(), "0 squares on the line.");
        assertTrue(line.isEmpty(), "0 squares on the line.");
    }

    @DisplayName("Testing List<Square> getNegativeDiagonalLine(final int row, final int col)...")
    @Test
    public void testGetNegativeDiagonalLine() {
        List<Square> line = this.testBoard.getNegativeDiagonalLine(0, 0);
        assertEquals(8, line.size(), "8 square on the line.");

        line = this.testBoard.getNegativeDiagonalLine(0, 7);
        assertEquals(1, line.size(), "1 squares on the line.");

        line = this.testBoard.getNegativeDiagonalLine(7, 0);
        assertEquals(1, line.size(), "1 squares on the line.");

        line = this.testBoard.getNegativeDiagonalLine(7, 7);
        assertEquals(8, line.size(), "8 square on the line.");
    }

    @DisplayName("Testing (boundaries) List<Square> getNegativeDiagonalLine(final int row, final int col)...")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 8, Integer.MAX_VALUE})
    public void testBoundariesGetNegativeDiagonalLine(final int wrong) {
        List<Square> line = this.testBoard.getNegativeDiagonalLine(0, wrong);
        assertEquals(0, line.size(), "0 squares on the line.");
        assertTrue(line.isEmpty(), "0 squares on the line.");

        line = this.testBoard.getNegativeDiagonalLine(wrong, 0);
        assertEquals(0, line.size(), "0 squares on the line.");
        assertTrue(line.isEmpty(), "0 squares on the line.");
    }

    @DisplayName("Testing void testGetFlankedSquaresAlongLine(final Square square, final List<Square> line)...")
    @Test
    public void testGetFlankedSquaresAlongLine() {
//        List<Square> oneSquareLine = this.testBoard.getPositiveDiagonalLine(0,0);
//        List<Square> twoSquaresLine = this.testBoard.getPositiveDiagonalLine(1,0);
//        List<Square> threeSquaresLine = this.testBoard.getPositiveDiagonalLine(2,0);
//        List<Square> fourSquaresLine = this.testBoard.getPositiveDiagonalLine(3,0);
//        List<Square> fiveSquaresLine = this.testBoard.getPositiveDiagonalLine(4,0);
//        List<Square> sixSquaresLine = this.testBoard.getPositiveDiagonalLine(5,0);
//        List<Square> sevenSquaresLine = this.testBoard.getPositiveDiagonalLine(6,0);
        List<Square> eightSquaresLine = this.testBoard.getPositiveDiagonalLine(7, 0);

        eightSquaresLine.get(0).setEmpty();
        eightSquaresLine.get(1).setPlayerOne();
        eightSquaresLine.get(2).setPlayerTwo();
        eightSquaresLine.get(3).setPlayerTwo();
        eightSquaresLine.get(4).setPlayerOne();
        eightSquaresLine.get(5).setPlayerOne();
        eightSquaresLine.get(6).setPlayerTwo();
        eightSquaresLine.get(7).setEmpty();
        List<Square> flankedSquares = this.testBoard.getFlankedSquaresAlongLine(
                eightSquaresLine.get(4),
                eightSquaresLine
        );
        assertEquals(3, flankedSquares.size(), "3 flanked squares (indexes 1, 2, 3).");

        eightSquaresLine.get(0).setEmpty();
        eightSquaresLine.get(1).setPlayerOne();
        eightSquaresLine.get(2).setPlayerTwo();
        eightSquaresLine.get(3).setPlayerTwo();
        eightSquaresLine.get(4).setPlayerOne();
        eightSquaresLine.get(5).setPlayerTwo();
        eightSquaresLine.get(6).setPlayerOne();
        eightSquaresLine.get(7).setEmpty();
        flankedSquares = this.testBoard.getFlankedSquaresAlongLine(
                eightSquaresLine.get(4),
                eightSquaresLine
        );
        assertEquals(5, flankedSquares.size(), "3 flanked squares (indexes 1, 2, 3, 5, 6).");

        eightSquaresLine.get(0).setPlayerTwo();
        eightSquaresLine.get(1).setPlayerTwo();
        eightSquaresLine.get(2).setPlayerTwo();
        eightSquaresLine.get(3).setPlayerTwo();
        eightSquaresLine.get(4).setPlayerOne();
        eightSquaresLine.get(5).setPlayerOne();
        eightSquaresLine.get(6).setPlayerTwo();
        eightSquaresLine.get(7).setEmpty();
        flankedSquares = this.testBoard.getFlankedSquaresAlongLine(
                eightSquaresLine.get(4),
                eightSquaresLine
        );
        assertEquals(0, flankedSquares.size(), "0 flanked squares.");
    }

}
