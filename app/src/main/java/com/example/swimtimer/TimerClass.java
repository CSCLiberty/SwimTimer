package com.example.swimtimer;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerClass {//extends AppCompatActivity {

    private TextView countdown_textView;
    private CountDownTimer countDownTimer;
    private long timerCurrentLength = 600000; // 10 minutes
    private long timerLength = 600000;
    private long timeLeftInMilliSeconds = 0;
    private boolean timerRunning;
    private long startTime = 0;

    public TimerClass(TextView countdownTView, long timerLength) {

        timerLength = timerLength;
        timeLeftInMilliSeconds = timerLength;
        countdown_textView = countdownTView;



    }


    public void reset() {
        stopTimer();
        timeLeftInMilliSeconds = timerLength;
        timerCurrentLength = timerLength;

    }


    public void startReset() {
        if (timerRunning) {
            reset();
        } else {
            startTimer();
        }

    }

    public void startTimer() {

        countDownTimer = new CountDownTimer(timerCurrentLength, 2) {
            @Override
            public void onTick(long i) {
                timeLeftInMilliSeconds = i;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        timerRunning = true;
    }
    public void updateStopwatch()
    {
        long time = System.currentTimeMillis() - startTime;
        String displayTime = com.example.swimtimer.TimeFormat.timeFormat(time);
        countdown_textView.setText(displayTime);
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        timerCurrentLength = timeLeftInMilliSeconds;
    }
    public long getTimeLeftInMilliSeconds()
    {
        return timeLeftInMilliSeconds;
    }

    public long getTimerLength()
    {
        return timerLength;
    }

    public void updateTimer()
    {
       long currentTime = timerLength - timeLeftInMilliSeconds;
       String timeDisplayString = com.example.swimtimer.TimeFormat.timeFormat(currentTime);
       countdown_textView.setText(timeDisplayString);
    }
}
