package edu.sjsu.cmpe281.smartcalendar;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by starn on 11/29/2016.
 */

public class DateUtil {
    private static final List<String> monthList = Arrays.asList("January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December");

    public static String FormatDateView(String date) {
        //2016-10-30 17:20:00.0000
        String[] dateTime = date.split(" ");
        String[] dateArr = dateTime[0].split("-");

        return monthList.get(Integer.parseInt(dateArr[1]) - 1) + " " + dateArr[2] + ", " + dateArr[0];
    }

    public static String FormatDateTime(String date, String time) {
        String[] dateStr = date.split("/");

        return dateStr[2] + "-" + dateStr[0] + "-" + dateStr[1] + " " + time + ":00.0000";
    }
}
