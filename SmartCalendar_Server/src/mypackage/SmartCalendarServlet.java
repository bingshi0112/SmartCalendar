package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



@SuppressWarnings("serial")
public class SmartCalendarServlet extends HttpServlet {

	/*
	 * GET Method for project
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		SmartCalendar sc = new SmartCalendar();
		Connection conn = sc.connect();
		JSONObject object = SmartCalendar.getUserCalendar("smartcalendartestuser@gmail.com", conn);
		//resp.setContentType("application/json");
		//resp.getWriter().println(object);
		/*try {
			JSONArray jsonArray = object.getJSONArray("events");
			resp.getWriter().println("eventNumber:" + jsonArray.length());

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				TransportationScan ts = new TransportationScan(jsonObject);
				WeatherScan ws = new WeatherScan(jsonObject);
				JSONObject transportation = ts.getTimeAndDistance();
				resp.getWriter().println(i + "transportation" + transportation);
				JSONObject weather = ws.getTemperatureAndMain();
				resp.getWriter().println(i + "weather:" + weather);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0000").format(Calendar.getInstance().getTime());
		Date previous_time = Calendar.getInstance().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 3);
		previous_time = calendar.getTime();
		/*
		 * get weather and transportation
		 */
		//String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0000").format(previous_time);
		//JSONObject currentObject = SmartCalendar.getUserCalendarInTimePeriod("smartcalendartestuser@gmail.com", curTime, endTime, conn);
		
		//System.out.println(currentObject.toString());
		try {
			JSONArray jsonArray = object.getJSONArray("events");
						
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject=jsonArray.getJSONObject(i);
				System.out.println("local:"+jsonObject.toString());
				
				TransportationScan ts=new TransportationScan(jsonObject);
				WeatherScan ws=new WeatherScan(jsonObject);
				JSONObject transportation=ts.getTimeAndDistance();
				System.out.println(i+"transportation"+transportation);
				JSONObject weather=ws.getTemperatureAndMain();
				System.out.println(i+"weather:"+weather);
				
				AlertInfo alert=new AlertInfo();
				double wea=10000;
				String main="";
				String dur="";
				String distance="";
				if(weather.has("temperature")){
					wea=weather.getDouble("temperature");
					main=weather.getString("main");
					alert.setTemperature(wea);
					alert.setMain(main);
				}
				if(transportation.has("duration")){
					dur=transportation.getString("duration");
					distance=transportation.getString("distance");
					alert.setDur(dur);
					alert.setDistance(distance);
				}
				jsonObject.put("myMessage",alert.getMyMessage());	
				System.out.println("myMessages"+jsonObject.toString());
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.setContentType("application/json");
		resp.getWriter().println(object);
		
		

	}

	// merge the information from transportation and weather

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		StringBuffer buffer = new StringBuffer();
		String rl = null;
		BufferedReader breader = req.getReader();
		while ((rl = breader.readLine()) != null) {
			buffer.append(rl);
		}

		if (buffer.toString().length() == 0) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN,
					"there is no input");
		} else {
			try {

				JSONObject json = new JSONObject(buffer.toString());
				System.out.println("json:"+json.toString());
				JSONObject jsonObject=new JSONObject();

				if (json.has("eventName")) {
					jsonObject.put("eventName", json.getString("eventName"));
				} else {
					jsonObject.put("eventName", "");
				}

				if (json.has("allDayEvent")) {
					jsonObject.put("allDayEvent", json.getInt("allDayEvent"));
				} else {
					jsonObject.put("allDayEvent", 0);
				}

				if (json.has("startTime")) {
					jsonObject.put("startTime", json.getString("startTime"));
				} else {
					jsonObject.put("startTime", "5000-01-01 00:00:00.0000");
				}

				if (json.has("endTime")) {
					jsonObject.put("endTime", json.getString("endTime"));
				} else {
					jsonObject.put("endTime", "5000-01-01 00:00:00.0000");
				}

				if (json.has("eventRepeat")) {
					jsonObject.put("eventRepeat", json.getString("eventRepeat"));
				} else {
					jsonObject.put("eventRepeat", "Weekly");
				}


				if (json.has("repeatEndTime")) {
					jsonObject.put("repeatEndTime", json.getString("repeatEndTime"));
				} else {
					jsonObject.put("repeatEndTime", "5000-01-01 00:00:00.0000");
				}
				
				if (json.has("travelTime")) {
					jsonObject.put("travelTime", json.getInt("travelTime"));
				} else {
					jsonObject.put("travelTime", 0);
				}


				if (json.has("location")) {
					jsonObject.put("location", json.getString("location"));
				} else {
					jsonObject.put("location", "San Jose State University");
				}


				if (json.has("alert")) {
					jsonObject.put("alert",json.getInt("alert"));
				} else {
					jsonObject.put("alert", 0);
				}
				

				if (json.has("trafficCheck")) {
					jsonObject.put("trafficCheck",json.getInt("trafficCheck"));
				} else {
					jsonObject.put("trafficCheck", 0);
				}


				if (json.has("description")) {
					jsonObject.put("description", json.getString("description"));
				} else {
					jsonObject.put("description", "None");
				}


				if (json.has("originalLocation")) {
					jsonObject.put("originalLocation",json.getString("originalLocation"));
				} else {
					jsonObject.put("originalLocation", "Washington,DC,USA");
				}


				SmartCalendar sc = new SmartCalendar();
				Connection conn = sc.connect();

				SmartCalendar.postFullUserCalendar("smartcalendartestuser@gmail.com", jsonObject, conn);

				resp.getWriter().println("13"+jsonObject.toString());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
