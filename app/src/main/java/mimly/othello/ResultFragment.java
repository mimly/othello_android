package mimly.othello;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mimly.othello.model.Board;
import mimly.othello.model.LegalMoveModel;

public class ResultFragment extends Fragment {

    private LegalMoveModel model = null;
    private Board board = null;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.model = ((OthelloActivity) getActivity()).getModel();
        this.board = this.model.getBoard();

        this.updatePlayerOneResultView();
        this.updatePlayerTwoResultView();
    }

    public void updatePlayerOneResultView() {
        TextView playerOne = getActivity().findViewById(R.id.playerOne);
        playerOne.setText(getResultMessageFor(1));
    }

    public void updatePlayerTwoResultView() {
        TextView playerTwo = getActivity().findViewById(R.id.playerTwo);
        playerTwo.setText(getResultMessageFor(2));
    }

    public void setGameOverView() {
        TextView gameOver = getActivity().findViewById(R.id.gameOver);
        gameOver.setVisibility(View.VISIBLE);
    }

    private CharSequence getResultMessageFor(int playerID) {
        ResultMessage resultMessage = null;
        if (this.model.isGameOver()) {
            if (playerID == 1) resultMessage = new PlayerOneFinalResultMessage();
            if (playerID == 2) resultMessage = new PlayerTwoFinalResultMessage();
        } else {
            if (playerID == 1) resultMessage = new PlayerOneTurnResultMessage();
            if (playerID == 2) resultMessage = new PlayerTwoTurnResultMessage();
        }
        return resultMessage.getText();
    }

    private abstract class ResultMessage {
        public abstract CharSequence getText();
    }

    private abstract class PlayerOneResultMessage extends ResultMessage {
        @Override
        public CharSequence getText() {
            return getResources().getString(R.string.playerOne) + " " + board.getNumberOfPlayerOneSquares();
        }
    }

    private abstract class PlayerTwoResultMessage extends ResultMessage {
        @Override
        public CharSequence getText() {
            return getResources().getString(R.string.playerTwo) + " " + board.getNumberOfPlayerTwoSquares();
        }
    }

    private class PlayerOneTurnResultMessage extends PlayerOneResultMessage {
        @Override
        public CharSequence getText() {
            return (model.isPlayerOneTurn() ? "-> " : "") + super.getText();
        }
    }

    private class PlayerTwoTurnResultMessage extends PlayerTwoResultMessage {
        @Override
        public CharSequence getText() {
            return (model.isPlayerTwoTurn() ? "-> " : "") + super.getText();
        }
    }

    private class PlayerOneFinalResultMessage extends PlayerOneResultMessage {
        @Override
        public CharSequence getText() {
            return board.getNumberOfPlayerOneSquares() >= board.getNumberOfPlayerTwoSquares() ?
                    "$$ " + super.getText() + " $$" : super.getText();
        }
    }

    private class PlayerTwoFinalResultMessage extends PlayerTwoResultMessage {
        @Override
        public CharSequence getText() {
            return board.getNumberOfPlayerTwoSquares() > board.getNumberOfPlayerOneSquares() ?
                    "$$ " + super.getText() + " $$" : super.getText();
        }
    }

}
