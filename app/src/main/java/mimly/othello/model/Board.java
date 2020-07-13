package mimly.othello.model;

import java.util.List;

public interface Board extends Streamable<Square> {

    int getNumberOfRows();

    int getNumberOfColumns();

    Square getSquare(final int row, final int col);

    List<Square> getHorizontalLine(final int row);

    List<Square> getVerticalLine(final int col);

    List<Square> getPositiveDiagonalLine(final int row, final int col);

    List<Square> getNegativeDiagonalLine(final int row, final int col);

    List<Square> getFlankedSquaresAlongLine(final Square square, final List<Square> line);

    List<Square> getFlankedSquares(final Square square);

    int getNumberOfEmptySquares();

    int getNumberOfPlayerOneSquares();

    int getNumberOfPlayerTwoSquares();

}
