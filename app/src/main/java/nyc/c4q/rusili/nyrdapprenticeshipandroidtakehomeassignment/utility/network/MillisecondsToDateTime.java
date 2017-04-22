package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network;

import java.util.Calendar;

public class MillisecondsToDateTime {

    public static String getDate(Calendar calendarP){
        int month = calendarP.get(Calendar.MONTH) + 1;      //month starts at 0
        int day = calendarP.get(Calendar.DAY_OF_MONTH);
        int year = calendarP.get(Calendar.YEAR);

        String date = month + "/" + day + "/" + year;

        return date;
    }

    public static String getTime(Calendar calendarP){
        int hours = calendarP.get(Calendar.HOUR_OF_DAY);
        String minutes = String.valueOf(calendarP.get(Calendar.MINUTE));
        if (minutes.equals("0")){
            minutes = "00";
        }
        String AMPM = "AM";

        if (hours < 12){
            if (hours == 0){
                hours = 12;
            }
            AMPM = "AM";
        } else {
            if (hours > 12) {
                hours = hours - 12;
            }
            AMPM = "PM";
        }

        String time = hours + ":" + minutes + AMPM;

        return time;
    }
}
