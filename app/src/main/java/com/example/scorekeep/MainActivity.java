package com.example.scorekeep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgIcon;
    private long mCountTimer = 45000; //Time in MilliSecond
    private boolean timerRunning;
    private CountDownTimer countDownTimer;
    TextView timeCounter;
    TextView teamAThrow, teamATouchDown, teamAFoul;
    TextView teamBThrow, teamBTouchDown, teamBFoul;
    TextView reset;
    TextView teamAScore, teamBScore;

    //Making Global variable to update the score of both team..
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgIcon = findViewById(R.id.imgIcon);
        timeCounter = findViewById(R.id.timeCounter);

        //Assigning Id's to Team A..
        teamAThrow = findViewById(R.id.team_A_Throw);
        teamATouchDown = findViewById(R.id.team_A_Touchdown);
        teamAFoul = findViewById(R.id.team_A_foul);

        //Assigning Id's to Team B..
        teamBTouchDown = findViewById(R.id.team_B_touchdown);
        teamBThrow = findViewById(R.id.team_B_throw);
        teamBFoul = findViewById(R.id.team_B_foul);

        //Disable all the score button until the game started.
        teamATouchDown.setEnabled(false);
        teamAThrow.setEnabled(false);
        teamAFoul.setEnabled(false);
        teamBTouchDown.setEnabled(false);
        teamBFoul.setEnabled(false);
        teamBThrow.setEnabled(false);

        teamAScore = findViewById(R.id.team_A_Score);
        teamBScore = findViewById(R.id.team_B_Score);

        reset = findViewById(R.id.reset);

        //Making method to display image to score board..
        displayForTeamA(0);
        displayFoTeamB(0);


        teamATouchDown.setOnClickListener(this);
        teamAThrow.setOnClickListener(this);
        teamAFoul.setOnClickListener(this);

        teamBTouchDown.setOnClickListener(this);
        teamBThrow.setOnClickListener(this);
        teamBFoul.setOnClickListener(this);
        reset.setOnClickListener(this);

        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
    }

    //Implementing the Logic of Countdown timer..
    public void startStop() {
        if (timerRunning) {
            stopTimer();
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_anti_clockwise);
            Toast.makeText(MainActivity.this, R.string.game_pause, Toast.LENGTH_SHORT).show();
            imgIcon.startAnimation(animation);
        } else {
            startTimer();
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_clockwise);
            imgIcon.startAnimation(animation);
            Toast.makeText(MainActivity.this, R.string.game_start, Toast.LENGTH_SHORT).show();
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(mCountTimer, 1000) {
            @Override
            public void onTick(long l) {
                mCountTimer = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                stopTimer();
                imgIcon.setClickable(false);
                Toast.makeText(MainActivity.this, R.string.time_over, Toast.LENGTH_SHORT).show();
            }
        }.start();
        timerRunning = true;

        teamATouchDown.setEnabled(true);
        teamATouchDown.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button));
        teamATouchDown.setTextColor(getResources().getColor(R.color.white));

        teamAThrow.setEnabled(true);
        teamAThrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button));
        teamAThrow.setTextColor(getResources().getColor(R.color.white));

        teamAFoul.setEnabled(true);
        teamAFoul.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button));
        teamAFoul.setTextColor(getResources().getColor(R.color.white));

        teamBTouchDown.setEnabled(true);
        teamBTouchDown.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button));
        teamBTouchDown.setTextColor(getResources().getColor(R.color.white));

        teamBThrow.setEnabled(true);
        teamBThrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button));
        teamBThrow.setTextColor(getResources().getColor(R.color.white));

        teamBFoul.setEnabled(true);
        teamBFoul.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button));
        teamBFoul.setTextColor(getResources().getColor(R.color.white));
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;

        teamATouchDown.setEnabled(false);
        teamATouchDown.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button_disabled));
        teamATouchDown.setTextColor(getResources().getColor(R.color.red));

        teamAThrow.setEnabled(false);
        teamAThrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button_disabled));
        teamAThrow.setTextColor(getResources().getColor(R.color.red));

        teamAFoul.setEnabled(false);
        teamAFoul.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button_disabled));
        teamAFoul.setTextColor(getResources().getColor(R.color.red));

        teamBTouchDown.setEnabled(false);
        teamBTouchDown.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button_disabled));
        teamBTouchDown.setTextColor(getResources().getColor(R.color.red));

        teamBThrow.setEnabled(false);
        teamBThrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button_disabled));
        teamBThrow.setTextColor(getResources().getColor(R.color.red));

        teamBFoul.setEnabled(false);
        teamBFoul.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button_disabled));
        teamBFoul.setTextColor(getResources().getColor(R.color.red));

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_anti_clockwise);
        imgIcon.startAnimation(animation);
    }

    public void updateTimer() {
        int minute = (int) mCountTimer / 60000;
        int second = (int) mCountTimer % 60000 / 1000;

        String timeLeft;
        timeLeft = "" + minute;
        timeLeft += ":";
        if (second < 10)
            timeLeft += "0";
        timeLeft += second;
        timeCounter.setText(timeLeft);
    }

    public void displayForTeamA(int score) {
        teamAScore.setText(String.valueOf(score));

    }

    public void displayFoTeamB(int score) {
        teamBScore.setText(String.valueOf(score));
    }


    @Override
    public void onClick(View view) {

        Button bt = (Button) view;
        switch (bt.getId()) {
            case R.id.team_A_Throw: {
                scoreTeamA += 2;
                displayForTeamA(scoreTeamA);
                break;
            }
            case R.id.team_A_Touchdown: {
                scoreTeamA += 3;
                displayForTeamA(scoreTeamA);
                break;
            }
            case R.id.team_A_foul:
            case R.id.team_B_foul: {
                Toast.makeText(this, R.string.score_point, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.team_B_touchdown: {
                scoreTeamB += 3;
                displayFoTeamB(scoreTeamB);
                break;
            }
            case R.id.team_B_throw: {
                scoreTeamB += 2;
                displayFoTeamB(scoreTeamB);
                break;
            }
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA = 0;
                scoreTeamB = 0;
                displayForTeamA(0);
                displayFoTeamB(0);
                Toast.makeText(MainActivity.this, R.string.score_reset, Toast.LENGTH_SHORT).show();

            }
        });
    }
}




