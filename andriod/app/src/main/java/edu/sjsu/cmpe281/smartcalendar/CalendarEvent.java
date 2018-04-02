package edu.sjsu.cmpe281.smartcalendar;

/**
 * Created by starn on 10/30/2016.
 */

public class CalendarEvent {
    protected String name;
    protected String date;
    protected String startTime;
    protected String endTime;
    protected Repeat eventRepeat = Repeat.NONE;
    protected String repeatEndTime;
    protected int travelTime;
    protected String location;
    protected boolean alert = true;
    protected boolean trafficCheck = false;
    protected boolean allDayEvent = false;
    protected String description;
    protected String mymessage;

    protected static final String NAME_PREFIX = "Name_";
    protected static final String SURNAME_PREFIX = "Date_";
    protected static final String EMAIL_PREFIX = "Time_";

    public enum Repeat {
        NONE("None"), WEEKLY("Weekly"), MONTHLY("Monthly"), YEARLY("Yearly");

        private String value;

        Repeat(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }
}
