package mimly.othello;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mimly.othello.model.Board;
import mimly.othello.model.LegalMoveModel;
import mimly.othello.model.Square;

public class BoardFragment extends Fragment {

    private LegalMoveModel model = null;
    private Board board = null;
    private ResultFragment resultFragment = null;

    public BoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.model = ((OthelloActivity) getActivity()).getModel();
        this.board = this.model.getBoard();
        this.resultFragment = (ResultFragment) getFragmentManager().findFragmentById(R.id.result_fragment);

        GridLayout gridLayout = getActivity().findViewById(R.id.grid_layout);
        for (int row = 0; row < this.board.getNumberOfRows(); ++row) {
            for (int col = 0; col < this.board.getNumberOfColumns(); ++col) {
                final SquareDecorator squareDecorator = (SquareDecorator) getLayoutInflater().inflate(R.layout.square, gridLayout, false);
                squareDecorator.setSquare(this.board.getSquare(row, col));
                squareDecorator.setOnClickListener(this::onClick);
                gridLayout.addView(squareDecorator);
            }
        }

        this.model.updateLegalMoves();
        this.model.showLegalMoves();
        this.updateBoardView();
    }

    public void updateBoardView() {
        GridLayout gridLayout = getActivity().findViewById(R.id.grid_layout);
        for (int i = 0; i < gridLayout.getChildCount(); ++i) {
            final SquareDecorator squareDecorator = (SquareDecorator) gridLayout.getChildAt(i);
            squareDecorator.animateIfAffectedByTheLastMove();
            squareDecorator.changeStyleProperties();
        }
    }

    public void alertNoLegalMoves() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("No legal moves...");
        alertDialog.setMessage("No legal moves available for " + (this.model.isPlayerOneTurn() ? "player one." : "player two."));
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", (dialogInterface, i) -> {
        });
        alertDialog.show();
    }

    public void onClick(View view) {
        Square square = (Square) view;

        this.model.hideLegalMoves();
        this.model.makeMove(square);

        if (this.model.isGameOver()) {
            this.updateBoardView();
            this.resultFragment.updatePlayerOneResultView();
            this.resultFragment.updatePlayerTwoResultView();
            this.resultFragment.setGameOverView();
            return;
        }

        this.model.clearLegalMoves();
        this.model.nextTurn();
        this.model.updateLegalMoves();
        this.model.showLegalMoves();
        if (!this.model.anyLegalMoves()) {
            this.alertNoLegalMoves();

            this.model.nextTurn();
            this.model.updateLegalMoves();
            this.model.showLegalMoves();
            if (!this.model.anyLegalMoves()) {
                this.updateBoardView();
                this.resultFragment.updatePlayerOneResultView();
                this.resultFragment.updatePlayerTwoResultView();
                this.resultFragment.setGameOverView();
                return;
            }
        }
        this.updateBoardView();
        this.resultFragment.updatePlayerOneResultView();
        this.resultFragment.updatePlayerTwoResultView();
    }

}
