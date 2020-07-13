package mimly.othello;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(final View view) {
        YoYo.with(Techniques.FadeIn).duration(1600).playOn(view);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, OthelloActivity.class);
            startActivity(intent);
        }, 1600);
    }

    public void playOnline(View view) {
        YoYo.with(Techniques.FadeIn).duration(1600).playOn(view);
        new Handler().postDelayed(() -> {
            Toast.makeText(MainActivity.this, "Not implemented yet...", Toast.LENGTH_LONG).show();
        }, 1600);
    }

    public void highlights(View view) {
        YoYo.with(Techniques.FadeIn).duration(1600).playOn(view);
        new Handler().postDelayed(() -> {
            Toast.makeText(MainActivity.this, "Not implemented yet...", Toast.LENGTH_LONG).show();
        }, 1600);
    }

    public void exit(View view) {
        YoYo.with(Techniques.Landing).duration(1600).playOn(view);
        new Handler().postDelayed(() -> {
            finishAndRemoveTask();
            System.exit(0);
        }, 1600);

    }

}
