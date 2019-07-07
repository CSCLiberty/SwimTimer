package com.example.swimtimer;

import java.util.ArrayList;

public class StopwatchManager {
    public ArrayList<com.example.swimtimer.Stopwatch> stopwatches = new ArrayList<com.example.swimtimer.Stopwatch>();
    private long startTime = 0;
    private boolean timersStarted = false;
    private boolean isFirstStart = true;

    public void initStopwatches(int numberOfStopwatches)
    {
        for (int i = 0; i < numberOfStopwatches; i++) {
            stopwatches.add(new com.example.swimtimer.Stopwatch());
        }
    }

    public long stopStopwatch(int stopwatchIndex) {

        Stopwatch currentStopwatch = stopwatches.get(stopwatchIndex);
        if (currentStopwatch.getIsRunning()) {
            long stopTime = System.currentTimeMillis();
            currentStopwatch.setStopTimeInMillis(stopTime);
            currentStopwatch.setIsRunning(false);
            //Add the final lap
            stopwatches.get(stopwatchIndex).addLapTimeInMillis(stopTime);
            stopwatches.get(stopwatchIndex).addSwimmerTimeInMillis(stopTime);
            //Set final time in lap array list to the total time
            stopwatches.get(stopwatchIndex).addLapTimeInMillis(2*stopTime-stopwatches.get(stopwatchIndex).getStartTimeInMillis());



        }
            return currentStopwatch.getFinalTimeInMillis();


    }

    public void startAllStopwatches() {
            if(timersStarted) {
                for(Stopwatch currentStopwatch: stopwatches) {
                    if(currentStopwatch.getSwimmerStartTimesInMillis().get(0) > 0)
                    {
                        currentStopwatch.getSwimmerStartTimesInMillis().add(currentStopwatch.getStartTimeInMillis());
                    }
                    currentStopwatch.setNextSwimmerStartTime(System.currentTimeMillis());

                }

            }
            else
            {
                startTime = System.currentTimeMillis();
                for (int i = 0; i < stopwatches.size(); i++) {
                    Stopwatch currentStopwatch = stopwatches.get(i);

                    currentStopwatch.setStartTimeInMillis(startTime);
                    currentStopwatch.setIsRunning(true);
                    //Ensuring that the lap/swimmer stop buttons will now serve as reset buttons
                    if (currentStopwatch.getLapTimes().size() > 0) {
                        currentStopwatch.setHasBeenRestartedLap(true);
                    }
                    if (currentStopwatch.getSwimmerTimes().size() > 0) {
                        currentStopwatch.setHasBeenRestartedSwimmer(true);
                    }

                }
                timersStarted = true;
            }
        }




    public void resetAllStopwatches()
    {
        for (int i = 0; i < stopwatches.size(); i++ )
        {
            resetStopwatch(i);
        }
        timersStarted = false;
    }

    public String getCurrentTime()
    {
        if(startTime > 0)
        {
            long currentTime = System.currentTimeMillis() - startTime;
            return com.example.swimtimer.TimeFormat.timeFormat(currentTime);

        }
        return "0:00.000";
    }

    public void resetStopwatch(int index)
    {
        stopStopwatch(index);
        stopwatches.get(index).setFinalTimeInMillis(0);
        stopwatches.get(index).getSwimmerStartTimesInMillis().clear();
    }

    public boolean isTimersStarted() {
        return timersStarted;
    }

    public void setTimersStarted(boolean timersStarted) {
        this.timersStarted = timersStarted;
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean isFirstStart() {
        return isFirstStart;
    }

    public void setFirstStart(boolean firstStart) {
        isFirstStart = firstStart;
    }

    public void resetLaps(int stopwatchIndex)
    {
        ArrayList<Long> resetArray = new ArrayList<>();
        stopwatches.get(stopwatchIndex).setLapTimes(resetArray);
        stopwatches.get(stopwatchIndex).setHasBeenRestartedLap(false);
    }

    public void resetSwimmers(int stopwatchIndex)
    {
        ArrayList<Long> resetArray = new ArrayList<>();
        stopwatches.get(stopwatchIndex).setSwimmerTimes(resetArray);
        stopwatches.get(stopwatchIndex).setHasBeenRestartedSwimmer(false);
    }

    public long lap(int stopwatchIndex)
    {
        if(stopwatches.get(stopwatchIndex).getIsRunning()) {
            long lapStopTime = System.currentTimeMillis();
            if (stopwatches.get(stopwatchIndex).isHasBeenRestartedLap()) {
                resetLaps(stopwatchIndex);
            }

            long timeToReturn = stopwatches.get(stopwatchIndex).addLapTimeInMillis(lapStopTime);
            stopwatches.get(stopwatchIndex).setNextLapToReturn(0);
            return timeToReturn;
        }
        else
        {
            return 0;
        }
    }

    public long swimmerStop (int stopwatchIndex) {
        if (stopwatches.get(stopwatchIndex).getIsRunning()) {
            long SwimmerStopTime = System.currentTimeMillis();
            if (stopwatches.get(stopwatchIndex).isHasBeenRestartedSwimmer()) {
                resetSwimmers(stopwatchIndex);
            }


            long timeToReturn = stopwatches.get(stopwatchIndex).addSwimmerTimeInMillis(SwimmerStopTime);
            stopwatches.get(stopwatchIndex).setNextSwimmerToReturn(0);
            return timeToReturn;
        }
    else
        {
            return 0;
        }
    }
}


