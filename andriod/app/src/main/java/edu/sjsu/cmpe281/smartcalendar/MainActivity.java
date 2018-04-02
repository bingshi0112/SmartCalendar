package edu.sjsu.cmpe281.smartcalendar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private Toolbar mToolbar;
    private CalendarListAdapter mAdapter;
    private RecyclerView recList;
    private RequestQueue queue;

    public static final int ADD_NEW_CALENDAR_EVENT = 11;
    public static final String ADD_EVENT_JSON = "addEventJson";

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        Toast.makeText(this, "Failed to connect...", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnected(Bundle arg0) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null) {
            System.out.println(mLastLocation.getLatitude());
        }

    }

    @Override
    public void onConnectionSuspended(int arg0) {
        Toast.makeText(this, "Connection suspended...", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        queue = Volley.newRequestQueue(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

//        buildGoogleApiClient();
//
//        if(mGoogleApiClient!= null){
//            mGoogleApiClient.connect();
//        }
//        else
//            Toast.makeText(this, "Not connected...", Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(MainActivity.this, AddCalendarEventActivity.class);
                startActivityForResult(intent, ADD_NEW_CALENDAR_EVENT);
            }
        });

//        getEvents();



        Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {

                getEvents();
            }
        };
// schedule the task to run starting now and then every hour...
//        timer.schedule (hourlyTask, 0l, 1000*60*60);
        timer.schedule (hourlyTask, 0l, 1000*60);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == ADD_NEW_CALENDAR_EVENT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String jsonStr = data.getStringExtra(ADD_EVENT_JSON);
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    CalendarEvent event = new CalendarEvent();
                    event.name = jsonObject.getString("eventName");
                    event.startTime = jsonObject.getString("startTime");
                    event.endTime = jsonObject.getString("endTime");
                    mAdapter.addItem(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                postRequest(getString(R.string.postEventUrl), jsonStr);
            }
        }
    }

    private List<CalendarEvent> createList(int size) {
        List<CalendarEvent> result = new ArrayList<CalendarEvent>();
        for (int i=1; i <= size; i++) {
            CalendarEvent ci = new CalendarEvent();
            ci.name = CalendarEvent.NAME_PREFIX + i;
            Random r = new Random();
            ci.startTime = "2016-10-" + (r.nextInt(32 - 10) + 10) + " 17:20:00.0000";
            ci.endTime = "2016-11-" + (r.nextInt(32 - 10) + 10) + " 17:20:00.0000";
            result.add(ci);
        }

        return result;
    }

    //REST CAll to get events
    private void getEvents() {
        String url = getString(R.string.getEventsUrl);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        //events[]
                        //eventName: "Bing's Event"
                        //startTime: "asdasda"
                        //endTime: "asdad"

                        try {

                            ArrayList<CalendarEvent> eventList = new ArrayList<>();

                            //TODO: FOR LOOP START
                            JSONArray jsonArray=response.getJSONArray("events");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                CalendarEvent event = new CalendarEvent();

                                event.name = jsonObject.getString("eventName");
                                //String sss=DateUtil.FormatDateView(jsonObject.getString("startTime"));
                                event.startTime = jsonObject.getString("startTime");
                                event.endTime = jsonObject.getString("endTime");
                                if(jsonObject.getString("myMessage").trim().equals("")) {
                                    event.mymessage = "Enjoy your day!";
                                }
                                else{
                                    event.mymessage = jsonObject.getString("myMessage");
                                }
                                eventList.add(event);
                            }

//                            while(!response.getString("eventName").equals(null))  {
//                                CalendarEvent event = new CalendarEvent();
//
//                                event.name = response.getString("eventName");
//                                event.startTime = DateUtil.FormatDateView(response.getString("startTime"));
//                                event.endTime = DateUtil.FormatDateView(response.getString("endTime"));
//                                eventList.add(event);
//                            }
//                            //FOR LOOP END

                            mAdapter = new CalendarListAdapter(eventList);
                            recList.setAdapter(mAdapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
                //TODO: it crashed after this, it went into looper.class and keeps looping, andriod crash
        );

        queue.add(getRequest);
    }

    private void postRequest(final String url, final String requestBody) {
        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.getMessage());
                        }
                    }
            ) {
                @Override
                public String getBodyContentType() {
                   return String.format("application/json; charset=utf-8");
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody, "utf-8");
                        return null;
                    }
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {

        }
    }


    protected synchronized void buildGoogleApiClient() {
         mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void getLocaton(){
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
//                makeUseOfNewLocation(location);
                System.out.print(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

}