package com.example.swimtimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TextView currentTimeTextView;
    private TextView lane1TView;
    private TextView lane2TView;
    private TextView lane3TView;
    private TextView lane4TView;
    private TextView lane5TView;
    private TextView lane6TView;
    private Button lane1Stop;
    private Button lane2Stop;
    private Button lane3Stop;
    private Button lane4Stop;
    private Button lane5Stop;
    private Button lane6Stop;
    private Button startButton;
    private Button lane1LapButton;
    private Button lane2LapButton;
    private Button lane3LapButton;
    private Button lane4LapButton;
    private Button lane5LapButton;
    private Button lane6LapButton;
    private Button lane1LapRecallButton;
    private Button lane2LapRecallButton;
    private Button lane3LapRecallButton;
    private Button lane4LapRecallButton;
    private Button lane5LapRecallButton;
    private Button lane6LapRecallButton;
    private Button lane1SwimmerStopButton;
    private Button lane2SwimmerStopButton;
    private Button lane3SwimmerStopButton;
    private Button lane4SwimmerStopButton;
    private Button lane5SwimmerStopButton;
    private Button lane6SwimmerStopButton;
    private Button lane1SwimmerRecallButton;
    private Button lane2SwimmerRecallButton;
    private Button lane3SwimmerRecallButton;
    private Button lane4SwimmerRecallButton;
    private Button lane5SwimmerRecallButton;
    private Button lane6SwimmerRecallButton;
    private ArrayList<Button> stopButtons = new ArrayList<>();
    private ArrayList<TextView> displayTextViews = new ArrayList<>();
    private ArrayList<Button> lapButtons = new ArrayList<>();
    private ArrayList<Button> lapRecallButtons = new ArrayList<>();
    private ArrayList<Button> swimmerStopButtons = new ArrayList<>();
    private ArrayList<Button> swimmerRecallButtons = new ArrayList<>();
    private com.example.swimtimer.StopwatchManager myManager = new StopwatchManager();
    private TimerClass myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        currentTimeTextView = findViewById(R.id.currentTimeTextView);
        lane1TView = findViewById(R.id.lane1TView);
        lane2TView = findViewById(R.id.lane2TView);
        lane3TView = findViewById(R.id.lane3TView);
        lane4TView = findViewById(R.id.lane4TView);
        lane5TView = findViewById(R.id.lane5TView);
        lane6TView = findViewById(R.id.lane6TView);
        lane1Stop = findViewById(R.id.lane1StopButton);
        lane2Stop = findViewById(R.id.lane2Button);
        lane3Stop = findViewById(R.id.lane3Button);
        lane4Stop = findViewById(R.id.lane4Button);
        lane5Stop = findViewById(R.id.lane5StopButton);
        lane6Stop = findViewById(R.id.lane6StopButton);
        startButton = findViewById(R.id.startButton);
        lane1LapButton = findViewById(R.id.lane1LapButton);
        lane2LapButton = findViewById(R.id.lane2LapButton);
        lane3LapButton = findViewById(R.id.lane3LapButton);
        lane4LapButton = findViewById(R.id.lane4LapButton);
        lane5LapButton = findViewById(R.id.lane5LapButton);
        lane6LapButton = findViewById(R.id.lane6LapButton);
        lane1LapRecallButton = findViewById(R.id.lane1LapRecallButton);
        lane2LapRecallButton = findViewById(R.id.lane2LapRecallButton);
        lane3LapRecallButton = findViewById(R.id.lane3LapRecallButton);
        lane4LapRecallButton = findViewById(R.id.lane4LapRecallButton);
        lane5LapRecallButton = findViewById(R.id.lane5LapRecallButton);
        lane6LapRecallButton = findViewById(R.id.lane6LapRecallButton);
        myTimer = new TimerClass(currentTimeTextView,10000000);
        stopButtons.add(lane1Stop);
        stopButtons.add(lane2Stop);
        stopButtons.add(lane3Stop);
        stopButtons.add(lane4Stop);
        stopButtons.add(lane5Stop);
        stopButtons.add(lane6Stop);
        displayTextViews.add(lane1TView);
        displayTextViews.add(lane2TView);
        displayTextViews.add(lane3TView);
        displayTextViews.add(lane4TView);
        displayTextViews.add(lane5TView);
        displayTextViews.add(lane6TView);
        lapButtons.add(lane1LapButton);
        lapButtons.add(lane2LapButton);
        lapButtons.add(lane3LapButton);
        lapButtons.add(lane4LapButton);
        lapButtons.add(lane5LapButton);
        lapButtons.add(lane6LapButton);
        lapRecallButtons.add(lane1LapRecallButton);
        lapRecallButtons.add(lane2LapRecallButton);
        lapRecallButtons.add(lane3LapRecallButton);
        lapRecallButtons.add(lane4LapRecallButton);
        lapRecallButtons.add(lane5LapRecallButton);
        lapRecallButtons.add(lane6LapRecallButton);
        myManager.initStopwatches(6);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartClick();

            }
        });

        //Configure Stop Buttons
        for (int i = 0; i < myManager.stopwatches.size(); i++)
        {
            final int stopwatchNum = i;
            stopButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String timeString = com.example.swimtimer.TimeFormat.timeFormat(myManager.stopStopwatch(stopwatchNum));
                    displayTextViews.get(stopwatchNum).setText(timeString);
                   int numStopwatchesRunning = 0;
                   for(int i = 0; i < myManager.stopwatches.size(); i++)
                   {
                       if(myManager.stopwatches.get(i).getIsRunning())
                       {
                           numStopwatchesRunning += 1;
                       }
                   }
                   if (numStopwatchesRunning == 0)
                   {
                       myTimer.stopTimer();
                       currentTimeTextView.setText(timeString); //making sure overall stoppage time is equal to final lane stopped time
                   }

                }
            });
        }

        //Configure Lap, Swimmer Stop, & Recall Buttons
        for (int i = 0; i < lapButtons.size(); i++)
        {
            final int stopwatchNum = i;
            lapButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayTextViews.get(stopwatchNum).setText(com.example.swimtimer.TimeFormat.timeFormat(myManager.lap(stopwatchNum)));
                }
            });

            lapRecallButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayTextViews.get(stopwatchNum).setText(com.example.swimtimer.TimeFormat.timeFormat(myManager.stopwatches.get(stopwatchNum).getNextLapTime()));
                }
            });

/*            swimmerStopButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayTextViews.get(stopwatchNum).setText(com.example.swimtimer.TimeFormat.timeFormat(myManager.swimmerStop(stopwatchNum)));
                }
            });

            swimmerRecallButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayTextViews.get(stopwatchNum).setText(com.example.swimtimer.TimeFormat.timeFormat(myManager.stopwatches.get(stopwatchNum).getNextSwimmerTime()));
                }
            });
*/

        }



    }

    protected void onStartClick()
    {
        if (myManager.isFirstStart() == true) {
            for (TextView textView : displayTextViews) {
                textView.setText("0:00.000");
            }

            myManager.setFirstStart(false);
            currentTimeTextView.setText("0:00.000");
        }
        if (myManager.isTimersStarted() == false) { //Start
            //final StopwatchManager myCurrentManager = myManager;
            //final TextView currentTimeTextView = currentTimeTView;
            myManager.startResetAll();
            myTimer.startTimer();
            /*CountDownTimer myTimer = new CountDownTimer(1000000, 10) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String displayTimeString = myManager.getCurrentTime();
                    currentTimeTextView.setText(displayTimeString);
                }

                @Override
                public void onFinish() {

                }
            };
            */

            startButton.setText("Reset");

        }
        else //Reset
            {
            myManager.startResetAll();
            /*for (TextView textView : displayTextViews) {
                textView.setText("0:00.000");
            }
            */
            currentTimeTextView.setText("0:00.000");
            startButton.setText("Start");
            myTimer.reset();
            }

    }

}

