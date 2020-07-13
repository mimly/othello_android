package mimly.othello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;

import mimly.othello.model.LegalMoveModel;
import mimly.othello.model.OthelloModel;

public class OthelloActivity extends AppCompatActivity {

    private LegalMoveModel model = null;

    public LegalMoveModel getModel() {
        return this.model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othello);

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        int numberOfRows = gridLayout.getRowCount();
        int numberOfColumns = gridLayout.getColumnCount();
        this.model = new OthelloModel(numberOfRows, numberOfColumns);
    }

}