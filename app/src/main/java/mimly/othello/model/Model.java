package mimly.othello.model;

public interface Model {

    Board getBoard();

    boolean isPlayerOneTurn();

    boolean isPlayerTwoTurn();

    void nextTurn();

    void makeMove(final Square square);

    boolean isGameOver();

}
