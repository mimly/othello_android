package mimly.othello.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class OthelloBoard implements Board, Iterable<Square> {

    private final List<List<Square>> board;
    private final int numberOfRows;
    private final int numberOfColumns;

    public OthelloBoard(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.board = new ArrayList<>();
        for (int i = 0; i < numberOfRows; ++i) {
            List<Square> row = new ArrayList<>();
            for (int j = 0; j < numberOfColumns; ++j) {
                row.add(new OthelloSquare(i, j));
            }
            this.board.add(row);
        }

        // Initialize board
        this.getSquare(3, 3).setPlayerOne();
        this.getSquare(3, 4).setPlayerTwo();
        this.getSquare(4, 3).setPlayerTwo();
        this.getSquare(4, 4).setPlayerOne();
    }

    @Override
    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    @Override
    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    @Override
    public Square getSquare(final int row, final int col) {
        return this.board.get(row).get(col);
    }

    @Override
    public List<Square> getHorizontalLine(final int row) {
        return getLine(square -> isOnBoard(row, 0) && square.getRow() == row);
    }

    @Override
    public List<Square> getVerticalLine(final int col) {
        return getLine(square -> isOnBoard(0, col) && square.getColumn() == col);
    }

    @Override
    public List<Square> getPositiveDiagonalLine(final int row, final int col) {
        return getLine(square -> isOnBoard(row, col) && square.getRow() == -square.getColumn() + row + col);
    }

    @Override
    public List<Square> getNegativeDiagonalLine(final int row, final int col) {
        return getLine(square -> isOnBoard(row, col) && square.getRow() == square.getColumn() + row - col);
    }

    private boolean isOnBoard(final int row, final int col) {
        return row >= 0 && row < getNumberOfRows() && col >= 0 && col < getNumberOfColumns();
    }

    private List<Square> getLine(final Predicate<Square> predicate) {
        return this.stream().filter(predicate).collect(Collectors.toList());
    }

//    @Override
//    public List<Square> getFlankedSquaresAlongLine(final Square square, final List<Square> line) {
//        String stringifiedLine = convertLineToString(square, line);
//        String[] backwardForward = stringifiedLine.split("S", -1);
//        String backward = backwardForward[0], forward = backwardForward[1];
//        String[] arrBackward = backward.split("s", -1), arrForward = forward.split("s", -1);
//        int SPos = stringifiedLine.indexOf("S"), sBackwardPos = stringifiedLine.lastIndexOf("s", SPos), sForwardPos = stringifiedLine.indexOf("s", SPos);
//
//        List<Square> flankedSquares = new ArrayList<>();
//        if (arrBackward[arrBackward.length - 1].matches("0+") && sBackwardPos != -1)
//            flankedSquares.addAll(line.subList(sBackwardPos, SPos));
//        if (arrForward[0].matches("0+") && sForwardPos != -1)
//            flankedSquares.addAll(line.subList(SPos + 1, sForwardPos + 1));
//        return flankedSquares;
//    }
//
//    private String convertLineToString(final Square square, final List<Square> line) {
//        return line.stream().map(square1 -> {
//            if (square1.equals(square)) return "S";
//            else if (square1.isSameAs(square)) return "s";
//            else if (square1.isOppositeTo(square)) return "0";
//            else return "1";
//        }).reduce("", String::concat);
//    }

    @Override
    public List<Square> getFlankedSquaresAlongLine(final Square square, final List<Square> line) {
        List<Square> firstHalf = line.subList(0, line.indexOf(square));
        Collections.reverse(firstHalf);
        List<Square> secondHalf = line.subList(line.indexOf(square) + 1, line.size());

        final List<Square> flankedSquares = new ArrayList<>();
        Stream.of(firstHalf, secondHalf).forEach(half -> {
            int i = indexOfSameSymbolAs(square, half);
            if (i != -1 && i != 0 && half.subList(0, i).stream().noneMatch(Square::isEmpty)) {
                flankedSquares.addAll(half.subList(0, i));
                flankedSquares.add(half.get(i));
            }
        });
        return flankedSquares;
    }

    private int indexOfSameSymbolAs(final Square square, final List<Square> line) {
        return line.stream().filter(square::isSameAs).map(line::indexOf).findFirst().orElse(-1);
    }

    @Override
    public List<Square> getFlankedSquares(final Square square) {
        return getFlankedSquares(square.getRow(), square.getColumn());
    }

    private List<Square> getFlankedSquares(final int row, final int col) {
        return Stream.of(getHorizontalLine(row), getVerticalLine(col), getPositiveDiagonalLine(row, col), getNegativeDiagonalLine(row, col))
                .map(line -> getFlankedSquaresAlongLine(getSquare(row, col), line))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfEmptySquares() {
        return (int) this.stream().filter(Square::isEmpty).count();
    }

    @Override
    public int getNumberOfPlayerOneSquares() {
        return (int) this.stream().filter(Square::isPlayerOne).count();
    }

    @Override
    public int getNumberOfPlayerTwoSquares() {
        return (int) this.stream().filter(Square::isPlayerTwo).count();
    }

    @Override
    public Stream<Square> stream() {
        Stream.Builder<Square> builder = Stream.builder();
        for (Square square : this) {
            builder.add(square);
        }
        return builder.build();
    }

    @Override
    public Iterator<Square> iterator() {
        return new Iterator<Square>() {
            private int currentSquare = 0;

            @Override
            public boolean hasNext() {
                return this.currentSquare < numberOfRows * numberOfColumns;
            }

            @Override
            public Square next() {
                int row = this.currentSquare / numberOfRows;
                int col = this.currentSquare % numberOfColumns;
                ++this.currentSquare;
                return board.get(row).get(col);
            }
        };
    }

}