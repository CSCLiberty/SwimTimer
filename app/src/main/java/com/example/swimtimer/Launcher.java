package com.example.swimtimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launcher extends AppCompatActivity {

    private Button lapTimerLaunchButton;
    private Button swimmerTimerLaunchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        swimmerTimerLaunchButton = findViewById(R.id.launchSwimmerTimerBtn);
        lapTimerLaunchButton = findViewById(R.id.launchlapTimerBtn);

        swimmerTimerLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSwimmerTimer();
            }
        });

        lapTimerLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLapTimer();
            }
        });
    }

    public void launchLapTimer()
    {
        Intent launchLapTimerIntent = new Intent("android.intent.action.LapTimer");


        startActivity(launchLapTimerIntent);

    }

    public void launchSwimmerTimer()
    {

    }
}
