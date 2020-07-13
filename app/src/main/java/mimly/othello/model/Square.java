package mimly.othello.model;

public interface Square {

    enum Symbol {
        PLAYER_ONE() {
            @Override
            public String toString() {
                return "Player One";
            }
        }, PLAYER_TWO() {
            @Override
            public String toString() {
                return "Player Two";
            }
        }, EMPTY() {
            @Override
            public String toString() {
                return "Empty";
            }
        }, LEGAL() {
            @Override
            public String toString() {
                return "Legal";
            }
        }
    }

    int getRow();

    int getColumn();

    Square.Symbol getSymbol();

    boolean isEmpty();

    boolean isLegal();

    boolean isPlayerOne();

    boolean isPlayerTwo();

    boolean isSameAs(final Square square);

    boolean isOppositeTo(final Square square);

    void setEmpty();

    void setLegal();

    void setPlayerOne();

    void setPlayerTwo();

    void setSameAs(final Square square);

    void setOppositeTo(final Square square);

    boolean isAffectedByTheLastMove();

    void setAffectedByTheLastMove(boolean affectedByTheLastMove);

}
