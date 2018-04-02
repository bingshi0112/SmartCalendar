package edu.sjsu.cmpe281.smartcalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONObject;

import java.util.Calendar;

public class AddCalendarEventActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText nameEditText;
    private TextView startDateText;
    private TextView startTimeText;
    private TextView endDateText;
    private TextView endTimeText;
    private TextView repeatEndDate;

    private Button startDateBtn;
    private Button startTimeBtn;
    private Button endDateBtn;
    private Button endTimeBtn;
    private Button repeatDateBtn;

    private Spinner eventRepeatSpinner;
    private CheckBox allDayEvent;
    private CheckBox alertCheck;
    private CheckBox trafficCheck;
    private EditText locationText;
    private EditText descText;

    private static final String START_DATE_TAG = "startDatePicker";
    private static final String END_DATE_TAG = "endDatePicker";
    private static final String START_TIME_TAG = "startTimePicker";
    private static final String END_TIME_TAG = "endTimePicker";
    private static final String REPEAT_DATE_TAG = "repeatDatePicker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        nameEditText = (EditText) findViewById(R.id.input_name);
        startDateText = (TextView) findViewById(R.id.input_start_date);
        startTimeText = (TextView) findViewById(R.id.input_start_time);
        endDateText = (TextView) findViewById(R.id.input_end_date);
        endTimeText = (TextView) findViewById(R.id.input_end_time);
        startDateBtn = (Button) findViewById(R.id.start_date_button);
        startTimeBtn = (Button) findViewById(R.id.start_time_button);
        endDateBtn = (Button) findViewById(R.id.end_date_button);
        endTimeBtn = (Button) findViewById(R.id.end_time_button);
        repeatDateBtn = (Button) findViewById(R.id.repeat_time_button);
        repeatEndDate = (TextView) findViewById(R.id.repeat_end_date);
        eventRepeatSpinner = (Spinner) findViewById(R.id.event_repeat_spinner);
        allDayEvent = (CheckBox) findViewById(R.id.all_day_checkbox);
        alertCheck = (CheckBox) findViewById(R.id.alert_checkbox);
        trafficCheck = (CheckBox) findViewById(R.id.traffic_checkbox);
        locationText = (EditText) findViewById(R.id.input_location);
        descText = (EditText) findViewById(R.id.input_desc);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Add New Event");

        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), START_DATE_TAG);
            }
        });

        startTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), START_TIME_TAG);
            }
        });

        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), END_DATE_TAG);
            }
        });

        endTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), END_TIME_TAG);
            }
        });

        repeatDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), REPEAT_DATE_TAG);
            }
        });

        eventRepeatSpinner.setAdapter(new ArrayAdapter<CalendarEvent.Repeat>(this,
                android.R.layout.simple_list_item_1, CalendarEvent.Repeat.values()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            boolean isError = false;

            if (nameEditText.getText().equals("")) {
                nameEditText.setError("Please enter a name for your event");
                isError = true;
            }
            if (startDateText.getText().equals(getString(R.string.emptyDate))) {
                startDateText.setError("Please set a start date");
                isError = true;
            }
            if (startTimeText.getText().equals(getString(R.string.emptyTime))) {
                startTimeText.setError("Please set a start time");
                isError = true;
            }

            if (endDateText.getText().equals(getString(R.string.emptyDate))) {
                endDateText.setError("Please set an end date");
                isError = true;
            }
            if (endTimeText.getText().equals(getString(R.string.emptyTime))) {
                endTimeText.setError("Please set an end time");
                isError = true;
            }

            if (!isError) {
                try {

                    Intent intent = new Intent();

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("eventName", nameEditText.getText().toString());
                    jsonObject.put("allDayEvent", allDayEvent.isChecked() ? 1 : 0);
                    jsonObject.put("startTime", DateUtil.FormatDateTime(startDateText.getText().toString(),
                            startTimeText.getText().toString()));
                    jsonObject.put("endTime", DateUtil.FormatDateTime(endDateText.getText().toString(),
                            endTimeText.getText().toString()));
                    jsonObject.put("eventRepeat", eventRepeatSpinner.getSelectedItem().toString());
                    jsonObject.put("repeatEndTime", DateUtil.FormatDateTime(repeatEndDate.getText().toString(),
                            endTimeText.getText().toString()));
                    jsonObject.put("travelTime", 30); //TODO: this should be calculated on server
                    jsonObject.put("location", locationText.getText().toString());
                    jsonObject.put("alert", alertCheck.isChecked() ? 1 : 0);
                    jsonObject.put("trafficCheck", trafficCheck.isChecked() ? 1 : 0);
                    jsonObject.put("description", descText.getText().toString());
                    intent.putExtra(MainActivity.ADD_EVENT_JSON, jsonObject.toString());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            int resId;

            switch (getTag()) {
                case REPEAT_DATE_TAG:
                    resId = R.id.repeat_end_date;
                    break;
                case START_DATE_TAG:
                    resId = R.id.input_start_date;
                    break;
                case END_DATE_TAG:
                    resId = R.id.input_end_date;
                    break;
                default:
                    resId = 0;
            }
            month++;

            String monthStr = Integer.toString(month);
            if (month < 10) {
                monthStr = "0" + month;
            }

            String dayStr = Integer.toString(day);
            if (day < 10) {
                dayStr = "0" + day;
            }

            TextView dateText = (TextView) getActivity().findViewById(resId);
            dateText.setText(monthStr + "/" + dayStr + "/" + year);
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int resId;

            if (getTag().equals(START_TIME_TAG)) {
                resId = R.id.input_start_time;
            } else {
                resId = R.id.input_end_time;
            }

            TextView timeText = (TextView) getActivity().findViewById(resId);
            String hourStr = Integer.toString(hourOfDay);
            if (hourOfDay < 10) {
                hourStr = "0" + hourOfDay;
            }
            String minuteStr = Integer.toString(minute);
            if (minute < 10) {
                minuteStr = "0" + minute;
            }
            timeText.setText(hourStr + ":" + minuteStr);
        }
    }
}
