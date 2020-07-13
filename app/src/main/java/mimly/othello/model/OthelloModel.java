package mimly.othello.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class OthelloModel implements LegalMoveModel {

    private final Board board;
    private boolean turn = true;
    private final Map<Square, List<Square>> legalMoves = new HashMap<>();

    public OthelloModel(int numberOfRows, int numberOfColumns) {
        this.board = new OthelloBoard(numberOfRows, numberOfColumns);
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isPlayerOneTurn() {
        return this.turn;
    }

    @Override
    public boolean isPlayerTwoTurn() {
        return !this.turn;
    }

    @Override
    public void nextTurn() {
        this.turn = !this.turn;
    }

    @Override
    public void makeMove(final Square square) {
        this.board.stream().forEach(square1 -> square1.setAffectedByTheLastMove(false));
        Stream.concat(Stream.of(square), this.getLegalMoves(square).stream()).forEach(square1 -> {
            square1.setAffectedByTheLastMove(true);
            if (isPlayerOneTurn()) square1.setPlayerOne();
            else square1.setPlayerTwo();
        });
    }

    @Override
    public boolean isGameOver() {
        return this.board.getNumberOfEmptySquares() == 0
                || this.board.getNumberOfPlayerOneSquares() == 0
                || this.board.getNumberOfPlayerTwoSquares() == 0;
    }

    @Override
    public boolean isLegalMove(final Square square) {
        return this.legalMoves.containsKey(square);
    }

    @Override
    public boolean anyLegalMoves() {
        return !this.legalMoves.isEmpty();
    }

    @Override
    public List<Square> getLegalMoves(final Square square) {
        return this.legalMoves.get(square);
    }

    @Override
    public void updateLegalMoves() {
        this.board.stream().filter(Square::isEmpty).forEach(square -> {
            if (isPlayerOneTurn()) square.setPlayerOne();
            else square.setPlayerTwo();
            List<Square> flankedSquares = this.board.getFlankedSquares(square);
            if (!flankedSquares.isEmpty()) this.legalMoves.put(square, flankedSquares);
            square.setEmpty();
        });
    }

    @Override
    public void showLegalMoves() {
        this.board.stream().filter(this.legalMoves::containsKey).forEach(Square::setLegal);
    }

    @Override
    public void hideLegalMoves() {
        this.board.stream().filter(Square::isLegal).forEach(Square::setEmpty);
    }

    @Override
    public void clearLegalMoves() {
        this.legalMoves.clear();
    }

}
