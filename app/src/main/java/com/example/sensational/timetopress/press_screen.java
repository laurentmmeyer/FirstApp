package com.example.sensational.timetopress;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class press_screen extends ActionBarActivity {

    private int time_left;
    private int amountOfTapsNumber;
    private int bestScore;

    TextView amountOfTaps;
    TextView timeLeftNumber;
    TextView time_left_tv;

    public static final String PREFS = "SHARED_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_screen);
        getSupportActionBar().hide();

        SharedPreferences prefs = getSharedPreferences(PREFS, 0);

        amountOfTaps = (TextView)findViewById(R.id.amount_of_taps);
        timeLeftNumber = (TextView)findViewById(R.id.time_left_number_tv);
        time_left_tv = (TextView)findViewById(R.id.time_left_tv);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/LemonMilk.otf");
        amountOfTaps.setTypeface(myCustomFont);
        timeLeftNumber.setTypeface(myCustomFont);
        time_left_tv.setTypeface(myCustomFont);

        timer.start();

    }

    //Create Timer


    CountDownTimer timer = new CountDownTimer(21000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            timeLeftNumber = (TextView) findViewById(R.id.time_left_number_tv);
            time_left = Integer.valueOf(timeLeftNumber.getText().toString()) - 1;
            timeLeftNumber.setText(time_left + "");
        }


        @Override
        public void onFinish() {

            Intent goBackToMainActivity = new Intent(press_screen.this, Main_Screen.class);
            goBackToMainActivity.putExtra("lastScore", amountOfTapsNumber);
            startActivity(goBackToMainActivity);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_press_screen, menu);
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

    public void triggerTapOnMainButton(View view) {
        amountOfTaps = (TextView) findViewById(R.id.amount_of_taps);
        amountOfTapsNumber = Integer.valueOf(amountOfTaps.getText().toString()) + 1;
        amountOfTaps.setText(amountOfTapsNumber + "");
    }

 }



