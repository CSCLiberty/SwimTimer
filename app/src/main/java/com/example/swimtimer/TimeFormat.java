package com.example.swimtimer;
import android.widget.TextView;

import static java.lang.Math.floorDiv;

public class TimeFormat {


    public static String timeFormat(long newTimeInMilliseconds) {
        //String[] timeStrings = new String[100];
        //for (int i = 0; i < timesInMilliseconds.length; i++) {
            int minutes = 0;
            int seconds = 0;
            long miliseconds = 0;
            long timeInMilliseconds = newTimeInMilliseconds;
            long secondsInMilliseconds = timeInMilliseconds;
            String timeString;
            minutes = floorDiv((int) timeInMilliseconds, 60000);
            timeString = minutes + ":";
            secondsInMilliseconds -= (minutes * 60000);
            seconds = floorDiv((int) secondsInMilliseconds, 1000);
            if (seconds < 10) {
                timeString += 0;
            }
            timeString += seconds + ".";
            miliseconds = secondsInMilliseconds - (seconds * 1000);
            if (miliseconds < 100) {
                if (miliseconds < 10) {
                    timeString += 0;
                }
                timeString += 0;
            }
            timeString += miliseconds;
            //timeStrings[i] = timeString;
        return timeString;
        }
        //return timeStrings;
    //}

        public static void timeDisplay (String timeString, TextView displayTView)
        {
         displayTView.setText(timeString);
        }

        public static String shortenString (String str,int charToCutOff)
        {
            String newString = str.substring(0, (str.length() - charToCutOff));
            return newString;
        }
    }










