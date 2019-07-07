package com.example.swimtimer;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SplitsDisplay extends AppCompatActivity {
    private Button returnButton;
    private ListView lane1ListView;
    private ListView lane2ListView;
    private ListView lane3ListView;
    private ListView lane4ListView;
    private ListView lane5ListView;
    private ListView lane6ListView;
    private ArrayList<ListView> lapDisplayListViews = new ArrayList<>();
    private StopwatchManager myManager;
    private Adapter lane1Adapter;
    private Adapter lane2Adapter;
    private Adapter lane3Adapter;
    private Adapter lane4Adapter;
    private Adapter lane5Adapter;
    private Adapter lane6Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splits_display);
        myManager = new StopwatchManager();// TimingService.getMyManager();
        myManager.initStopwatches(6);//
        returnButton = (Button) findViewById(R.id.returnButton);
        lane1ListView = findViewById(R.id.lane1ListView);
        lane2ListView = findViewById(R.id.lane2ListView);
        lane3ListView = findViewById(R.id.lane3ListView);
        lane4ListView = findViewById(R.id.lane4ListView);
        lane5ListView = findViewById(R.id.lane5ListView);
        lane6ListView = findViewById(R.id.lane6ListView);
        lapDisplayListViews.add(lane1ListView);
        lapDisplayListViews.add(lane2ListView);
        lapDisplayListViews.add(lane3ListView);
        lapDisplayListViews.add(lane4ListView);
        lapDisplayListViews.add(lane5ListView);
        lapDisplayListViews.add(lane6ListView);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnClicked();
            }
        });

        ArrayList<Long> lane1LapTimes = myManager.stopwatches.get(0).getLapTimes();
        ArrayList<Long> lane2LapTimes = myManager.stopwatches.get(1).getLapTimes();
        ArrayList<Long> lane3LapTimes = myManager.stopwatches.get(2).getLapTimes();
        ArrayList<Long> lane4LapTimes = myManager.stopwatches.get(3).getLapTimes();
        ArrayList<Long> lane5LapTimes = myManager.stopwatches.get(4).getLapTimes();
        ArrayList<Long> lane6LapTimes = myManager.stopwatches.get(5).getLapTimes();

        lane1Adapter = new ArrayAdapter<Long>(this,R.layout.activity_splits_display,lane1LapTimes);
        lane2Adapter = new ArrayAdapter<Long>(this,R.layout.activity_splits_display,lane2LapTimes);
        lane3Adapter = new ArrayAdapter<Long>(this,R.layout.activity_splits_display,lane3LapTimes);
        lane4Adapter = new ArrayAdapter<Long>(this,R.layout.activity_splits_display,lane4LapTimes);
        lane5Adapter = new ArrayAdapter<Long>(this,R.layout.activity_splits_display,lane5LapTimes);
        lane6Adapter = new ArrayAdapter<Long>(this,R.layout.activity_splits_display,lane6LapTimes);
    }


    public void returnClicked()
    {
        finish();
    }
}

