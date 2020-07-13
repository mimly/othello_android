package mimly.othello.model;

import java.util.Objects;

public final class OthelloSquare implements Square {

    private final int row;
    private final int col;
    private Symbol symbol;
    private boolean isAffectedByTheLastMove = false;

    public OthelloSquare(int row, int col) {
        this.row = row;
        this.col = col;
        this.symbol = Symbol.EMPTY;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int getColumn() {
        return this.col;
    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;
    }

    @Override
    public boolean isEmpty() {
        return this.symbol == Symbol.EMPTY;
    }

    @Override
    public boolean isLegal() {
        return this.symbol == Symbol.LEGAL;
    }

    @Override
    public boolean isPlayerOne() {
        return this.symbol == Symbol.PLAYER_ONE;
    }

    @Override
    public boolean isPlayerTwo() {
        return this.symbol == Symbol.PLAYER_TWO;
    }

    @Override
    public boolean isSameAs(final Square square) {
        return this.symbol == square.getSymbol();
    }

    @Override
    public boolean isOppositeTo(final Square square) {
        return this.isPlayerOne() && square.isPlayerTwo() || this.isPlayerTwo() && square.isPlayerOne();
    }

    @Override
    public void setEmpty() {
        this.symbol = Symbol.EMPTY;
    }

    @Override
    public void setLegal() {
        this.symbol = Symbol.LEGAL;
    }

    @Override
    public void setPlayerOne() {
        this.symbol = Symbol.PLAYER_ONE;
    }

    @Override
    public void setPlayerTwo() {
        this.symbol = Symbol.PLAYER_TWO;
    }

    @Override
    public void setSameAs(final Square square) {
        this.symbol = square.getSymbol();
    }

    @Override
    public void setOppositeTo(final Square square) {
        this.symbol = square.isPlayerOne() ? Symbol.PLAYER_TWO
                : square.isPlayerTwo() ? Symbol.PLAYER_ONE
                : this.symbol;
    }

    @Override
    public boolean isAffectedByTheLastMove() {
        return this.isAffectedByTheLastMove;
    }

    @Override
    public void setAffectedByTheLastMove(boolean affectedByTheLastMove) {
        this.isAffectedByTheLastMove = affectedByTheLastMove;
    }

    @Override
    public String toString() {
        return this.symbol.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != OthelloSquare.class) return false;
        if (this == o) return true;
        return this.row == ((OthelloSquare) o).row && this.col == ((OthelloSquare) o).col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.col);
    }

}
