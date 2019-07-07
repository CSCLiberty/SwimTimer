package com.example.swimtimer;

import java.util.ArrayList;

public class Stopwatch {
    private long startTimeInMillis;
    private ArrayList<Long> swimmerStartTimesInMillis;
    private int nextStartTimeToUse;
    private long stopTimeInMillis;
    private long finalTimeInMillis = 0;
    //Storing the lap times so that they can be subtracted from the next lap time
    private ArrayList<Long> lapTimesUnmodified = new ArrayList<>();

    //These are for storing and returning the modified times
    private ArrayList<Long> lapTimes = new ArrayList<Long>();
    private ArrayList<Long> swimmerTimes = new ArrayList<Long>();
    private boolean isRunning = false;
    //Has the stopwatch been restarted since the lap/swimmer times were recorded?
    private boolean hasBeenRestartedLap;
    private boolean hasBeenRestartedSwimmer;
    private int nextLapToReturn = 0;
    private int nextSwimmerToReturn = 0;


    public long getStartTimeInMillis() {
        return startTimeInMillis;
    }

    public boolean getIsRunning()
    {
        return isRunning;
    }

    public void setIsRunning(boolean running)
    {
        isRunning = running;
    }

    public long getFinalTimeInMillis() {
        return finalTimeInMillis;
    }

    public void setFinalTimeInMillis(long i)
    {
        finalTimeInMillis = i;
    }

    public void setStartTimeInMillis (long startTime)
    {
        startTimeInMillis = startTime;
        lapTimesUnmodified.add(startTime);
        swimmerStartTimesInMillis.add(startTime);

    }

    public void setStopTimeInMillis(long stopTime)
    {
        stopTimeInMillis = stopTime;
        finalTimeInMillis = stopTimeInMillis-startTimeInMillis;
    }

    public long addLapTimeInMillis (long lapTime)
    {

        long lapTimeInMillis = lapTime - lapTimesUnmodified.get(lapTimesUnmodified.size()-1);
        lapTimesUnmodified.add(lapTime);
        lapTimes.add(lapTimeInMillis);

            return lapTimeInMillis;
    }

    public long addSwimmerTimeInMillis(long swimmerTime)
    {
        if(swimmerStartTimesInMillis.get(0) > 0) {
            long currentSwimmerStartTime = swimmerStartTimesInMillis.get(nextStartTimeToUse);
            long swimmerTimeInMillis = swimmerTime - currentSwimmerStartTime;

            swimmerTimes.add(swimmerTimeInMillis);

            return swimmerTimeInMillis;
        }
        else
        {
            return 0;
        }
    }


    public long getNextLapTime ()
    {
        long lapTime = lapTimes.get(nextLapToReturn);
        if(nextLapToReturn == (lapTimes.size()-1))
        {
            nextLapToReturn = 0;
        }
        else {
            nextLapToReturn += 1;
        }
        return lapTime;
    }

    public long getNextSwimmerTime()
    {
        long swimmerTime = swimmerTimes.get(nextSwimmerToReturn);
        if(nextSwimmerToReturn == (swimmerTimes.size()-1))
        {
            nextSwimmerToReturn = 0;
        }
        else
            {
            nextSwimmerToReturn += 1;
        }
        return swimmerTime;
    }


    public void setHasBeenRestartedLap(boolean hasBeenRestartedLap) {
        this.hasBeenRestartedLap = hasBeenRestartedLap;
    }

    public void setHasBeenRestartedSwimmer(boolean hasBeenRestartedSwimmer) {
        this.hasBeenRestartedSwimmer = hasBeenRestartedSwimmer;
    }

    public boolean isHasBeenRestartedLap() {
        return hasBeenRestartedLap;
    }

    public boolean isHasBeenRestartedSwimmer() {
        return hasBeenRestartedSwimmer;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ArrayList<Long> getLapTimes() {
        return lapTimes;
    }

    public ArrayList<Long> getSwimmerTimes() {
        return swimmerTimes;
    }

    public void setLapTimes(ArrayList<Long> lapTimes) {
        this.lapTimes = lapTimes;
    }

    public void setSwimmerTimes(ArrayList<Long> swimmerTimes) {
        this.swimmerTimes = swimmerTimes;
    }

    public void setNextLapToReturn(int nextLapToReturn) {
        this.nextLapToReturn = nextLapToReturn;
    }

    public int getNextLapToReturn() {
        return nextLapToReturn;
    }

    public void setNextSwimmerToReturn(int nextSwimmerToReturn) {
        this.nextSwimmerToReturn = nextSwimmerToReturn;
    }

    public int getNextSwimmerToReturn() {
        return nextSwimmerToReturn;
    }

    public void setNextSwimmerStartTime(long nextStartTimeInMillis)
    {
        swimmerStartTimesInMillis.add(nextStartTimeInMillis);
    }

    public ArrayList<Long> getSwimmerStartTimesInMillis() {
        return swimmerStartTimesInMillis;
    }
}
