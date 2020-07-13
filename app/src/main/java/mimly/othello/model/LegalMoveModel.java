package mimly.othello.model;

import java.util.List;

public interface LegalMoveModel extends Model {

    boolean isLegalMove(final Square square);

    boolean anyLegalMoves();

    List<Square> getLegalMoves(final Square square);

    void updateLegalMoves();

    void showLegalMoves();

    void hideLegalMoves();

    void clearLegalMoves();

}
