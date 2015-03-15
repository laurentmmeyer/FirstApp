package com.example.sensational.timetopress;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Main_Screen extends ActionBarActivity {

    int best_Score;
    int lastScore;
    public static final String PREFS = "SHARED_PREFS";
    TextView best_score_number_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide();

        //SETS CUSTOM FONT
        TextView main_screen_titleone = (TextView) findViewById(R.id.main_screen_titleone);
        TextView main_screen_titletwo = (TextView) findViewById(R.id.main_screen_titletwo);
        TextView best_score_tv = (TextView) findViewById(R.id.best_score_tv);
        best_score_number_tv = (TextView) findViewById(R.id.best_score_number_tv);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/LemonMilk.otf");
        main_screen_titleone.setTypeface(myCustomFont);
        main_screen_titletwo.setTypeface(myCustomFont);
        best_score_tv.setTypeface(myCustomFont);
        best_score_number_tv.setTypeface(myCustomFont);
        //******************

        best_Score = Integer.valueOf(best_score_number_tv.getText().toString());

        Bundle extras = getIntent().getExtras();

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        best_Score = prefs.getInt("BEST_SCORE", 0); //0 is the default value.

        best_score_number_tv.setText(String.valueOf(best_Score));

    }


    public void startTheGame(View view) {
        Intent intent = new Intent(this, press_screen.class);
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode== Activity.RESULT_OK){
            lastScore = data.getIntExtra("lastScore", 0);
            if (lastScore > best_Score) {
                best_Score = lastScore;
                best_score_number_tv.setText(String.valueOf(lastScore));
                SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
                editor.putInt("BEST_SCORE", best_Score);
                editor.commit();
            } else {
                best_score_number_tv.setText(String.valueOf(best_Score));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main__screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
