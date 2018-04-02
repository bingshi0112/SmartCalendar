package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TransportationScan {
	/*
	 * Google Maps
	 * APIs----https://developers.google.com/maps/documentation/distance-matrix/
	 * start Get the time and distance from origins to destinations
	 * https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&
	 * origins=Washington,DC&destinations=New+York+City,NY&key=
	 * AIzaSyAZYK9iKplQ9KYC8yUkHMYr8YCjJRZUeyQ
	 */
	static final String BASE_TRANSPORTATION_URI = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial";
	static final String TRANSPORTATION_CREDENTIAL = "&key=AIzaSyAZYK9iKplQ9KYC8yUkHMYr8YCjJRZUeyQ";
	private JSONObject userJSONObject;
	private JSONObject result;

	// constructor
	public TransportationScan(JSONObject _userJSONObject) {
		userJSONObject = _userJSONObject;
	}

	// change the array str to string, appended by string p
	public String appendByPlus(String[] str, String p) {
		StringBuffer sb = new StringBuffer();
		if (str.length != 0 && !str.equals(null)) {
			int j = 0;
			for (int i = 0; i < str.length; i++) {
				if (j < str.length - 1) {
					sb.append(str[i]).append(p);
					j++;
				} else {
					sb.append(str[i]);
				}
			}
		}
		return sb.toString();
	}

	// get the time and distance
	public JSONObject getTimeAndDistance() throws JSONException, IOException {
		String origins = null;
		String destinations = null;
		JSONObject tempObj = new JSONObject();

		if (userJSONObject.has("originalLocation")) {
			String[] temp = userJSONObject.getString("originalLocation").split(
					" +");
			origins = appendByPlus(temp, "+");
		}

		if (userJSONObject.has("location")) {
			String[] temp = userJSONObject.getString("location").split(" +");
			destinations = appendByPlus(temp, "+");
		}

		if (origins != null && destinations != null) {
			try {
				tempObj = getTimeAndDistanceFromGoogleMap(origins, destinations);
			} catch (Exception e) {
				return tempObj;
			}
		}

		System.out.println("search result:" + tempObj.toString());
		return tempObj;

	}

	// get the time And Distance from origins to destinations
	public JSONObject getTimeAndDistanceFromGoogleMap(String origins,
			String destinations) throws IOException, JSONException {
		String url = BASE_TRANSPORTATION_URI + "&origins=" + origins
				+ "&destinations=" + destinations + TRANSPORTATION_CREDENTIAL;
		String destination_addresses = "";
		String origin_addresses = "";
		String distance = "";
		String duration = "";
		JSONObject obj = new JSONObject();

		try {
			JSONObject json = readJsonFromUrl(url);

			// find the distance and duration
			JSONArray rows = json.getJSONArray("rows");
			if (rows.length() > 0) {
				JSONArray elements = rows.getJSONObject(0).getJSONArray("elements");
				if (elements.length() > 0) {
					if (elements.getJSONObject(0).getString("status").equals("OK")) {
						JSONObject distances = elements.getJSONObject(0).getJSONObject("distance");
						JSONObject durations = elements.getJSONObject(0).getJSONObject("duration");
						if (distances.length() > 0) {
							String distance_text = distances.getString("text");
							String duration_text = durations.getString("text");
							if (distance_text != null) {
								distance = distance_text;
								obj.put("distance", distance);
							}
							if (duration_text != null) {
								duration = duration_text;
								obj.put("duration", duration);
							}
						}

					} else {
						return new JSONObject();

					}

				}

			}

			// find the origin_address
			JSONArray oa = json.getJSONArray("origin_addresses");
			if (oa.length() > 0) {
				origin_addresses = oa.get(0).toString();
				obj.put("origin", origin_addresses);
			}
			// find the destination_address
			JSONArray da = json.getJSONArray("destination_addresses");
			if (da.length() > 0) {
				destination_addresses = da.get(0).toString();
				obj.put("destination", destination_addresses);
			}
		} catch (Exception e) {

		}

		return obj;
	}

	// parse the information get from the api
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	// get the JSONObject from the URL
	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	/*
	 * public static void main(String[] args) throws IOException, JSONException
	 * { // test the time and distance test from origin to destination
	 * JSONObject obj=new JSONObject(); String origin = "Washington,DC,USA";
	 * String destination = "New York City,NY,USA"; obj.put("origin", origin);
	 * obj.put("destination", destination); TransportationScan ts=new
	 * TransportationScan(obj); ts.getTimeAndDistance(); }
	 */

}