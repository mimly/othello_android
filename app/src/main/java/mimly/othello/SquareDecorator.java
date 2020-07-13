package mimly.othello;

import android.content.Context;
import android.util.AttributeSet;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import mimly.othello.model.Square;

public class SquareDecorator extends android.support.v7.widget.AppCompatImageButton implements Square {

    private Square square = null;

    public SquareDecorator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public void animateIfAffectedByTheLastMove() {
        if (this.square.isAffectedByTheLastMove())
            YoYo.with(Techniques.RubberBand).duration(800).playOn(this);
    }

    public void changeStyleProperties() {
        if (this.square.isEmpty()) {
            changeStyle(false, 0.4f, R.color.empty);
        } else if (this.square.isLegal()) {
            changeStyle(true, 0.4f, R.color.legal);
        } else if (this.square.isPlayerOne()) {
            changeStyle(false, 1.0f, R.color.playerOne);
        } else {
            changeStyle(false, 1.0f, R.color.playerTwo);
        }
    }

    private void changeStyle(boolean clickable, float alpha, int imageID) {
        setClickable(clickable);
        setAlpha(alpha);
        setImageResource(imageID);
    }

    @Override
    public int getRow() {
        return this.square.getRow();
    }

    @Override
    public int getColumn() {
        return this.square.getColumn();
    }

    @Override
    public Square.Symbol getSymbol() {
        return this.square.getSymbol();
    }

    @Override
    public boolean isEmpty() {
        return this.square.isEmpty();
    }

    @Override
    public boolean isLegal() {
        return this.square.isLegal();
    }

    @Override
    public boolean isPlayerOne() {
        return this.square.isPlayerOne();
    }

    @Override
    public boolean isPlayerTwo() {
        return this.square.isPlayerTwo();
    }

    @Override
    public boolean isSameAs(Square square) {
        return this.square.isSameAs(square);
    }

    @Override
    public boolean isOppositeTo(Square square) {
        return this.square.isOppositeTo(square);
    }

    @Override
    public void setEmpty() {
        this.square.setEmpty();
    }

    @Override
    public void setLegal() {
        this.square.setLegal();
    }

    @Override
    public void setPlayerOne() {
        this.square.setPlayerOne();
    }

    @Override
    public void setPlayerTwo() {
        this.square.setPlayerTwo();
    }

    @Override
    public void setSameAs(Square square) {
        this.square.setSameAs(square);
    }

    @Override
    public void setOppositeTo(Square square) {
        this.square.setOppositeTo(square);
    }

    @Override
    public boolean isAffectedByTheLastMove() {
        return this.square.isAffectedByTheLastMove();
    }

    @Override
    public void setAffectedByTheLastMove(boolean affectedByTheLastMove) {
        this.square.setAffectedByTheLastMove(affectedByTheLastMove);
    }

    @Override
    public String toString() {
        return this.square.toString();
    }

    @Override
    public boolean equals(Object o) {
        return this.square.equals(o);
    }

    @Override
    public int hashCode() {
        return this.square.hashCode();
    }

}
