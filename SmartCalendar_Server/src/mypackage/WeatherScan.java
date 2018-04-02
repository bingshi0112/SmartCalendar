package mypackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherScan {
	/*
	 * get the current related weather information as an example to check the
	 * weather of beijing
	 * "http://api.openweathermap.org/data/2.5/weather?q=Beijing&WEATHER_CREDENTIAL=1a3aff4a56d078c49614644ba6f7ea37"
	 * ;
	 */
	


	static final String BASE_WEATHER_URI = "http://api.openweathermap.org/data/2.5/weather?";
	static final String WEATHER_CREDENTIAL = "APPID=1a3aff4a56d078c49614644ba6f7ea37";
	static final DecimalFormat fmt = new DecimalFormat("0.00");
	private JSONObject userJSONObject = new JSONObject();

	public WeatherScan(JSONObject _userJSONObject) {
		userJSONObject = _userJSONObject;
	}

	public JSONObject getTemperatureAndMain() throws JSONException, IOException {
		// get temperature by lat and lon
		// if ((userJSONObject.getString("lat")) != null &&
		// !(userJSONObject.getString("lat").equals(""))
		// && (userJSONObject.getString("lon")) != null &&
		// !(userJSONObject.getString("lon").equals(""))) {
		if (userJSONObject.has("lat") && userJSONObject.has("lon")) {
			double lat = userJSONObject.getDouble("lat");
			double lon = userJSONObject.getDouble("lon");
			return getWeatherByLocation(lat, lon);
		}
		// get weather by zipcode and countrycode
		else if (userJSONObject.has("zipCode")
				&& userJSONObject.has("countryCode")) {
			int zipCode = userJSONObject.getInt("zipCode");
			String countryCode = userJSONObject.getString("countryCode");
			return getWetherByZipCodeAndCountryCode(zipCode, countryCode);
		}
		// get weather by city
		else if (userJSONObject.has("location")) {
			String tempCity = userJSONObject.getString("location").trim();
			return getWeatherByCity(tempCity);
		}

		// get weather by city
		else if (userJSONObject.has("zip") && userJSONObject.has("countryCode")) {
			int zip = userJSONObject.getInt("zip");
			String countryCode = userJSONObject.getString("countryCode").trim();
			return getWetherByZipCodeAndCountryCode(zip, countryCode);
		}

		else {
			System.out.println("nothing scaned");
		}

		return new JSONObject();
	}

	// api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}
	// get the temp by zipcode and countrycode
	public JSONObject getWetherByZipCodeAndCountryCode(int zipCode,
			String countryCode) throws IOException, JSONException {
		String url = BASE_WEATHER_URI + "zip=" + zipCode + "," + countryCode
				+ "&" + WEATHER_CREDENTIAL;
		return analyseWeather(url);
	}

	// get the temp by city
	public JSONObject getWeatherByCity(String city) throws IOException,
			JSONException {
		//city="san jose state university";
		
		//city="Chase Bank, 12555 Valley View St, Garden Grove, CA 92845, USA";
		
		
		
		String[] temp = city.split(" +");
		city = appendByPlus(temp, "+");

		String url = BASE_WEATHER_URI + "q=" + city + "&" + WEATHER_CREDENTIAL;
		return analyseWeather(url);
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

	// get the temp by city
	public JSONObject getWeatherByCityAndCountryCode(String city,
			String countryCode) throws IOException, JSONException {
		String url = BASE_WEATHER_URI + "q=" + city + "," + countryCode + "&"
				+ WEATHER_CREDENTIAL;
		return analyseWeather(url);
	}

	// get the temp by city
	public JSONObject getWeatherByLocation(double lat, double lon)
			throws IOException, JSONException {
		String url = BASE_WEATHER_URI + "lat=" + lat + "&lon=" + lon + "&"
				+ WEATHER_CREDENTIAL;
		return analyseWeather(url);
	}

	// Analyse weather information
	public JSONObject analyseWeather(String url) throws IOException,
			JSONException {
		JSONObject result = new JSONObject();
		
		
		JSONObject json = readJsonFromUrl(url);
		
		if (!json.has("cod")) {
			return result;
		} else {
			 
			if (json.get("cod").toString().equals("200")) {
				JSONObject item = json.getJSONObject("main");
				if (!item.equals(null)) {
					if (!item.get("temp").equals(null)) {
						double temp = (item.getInt("temp") - 273.15); // 开氏温度转为摄氏温度
						BigDecimal bg = new BigDecimal(temp);
						double temperature = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						result.put("temperature", temperature);
					}
				}

				JSONObject weather = json.getJSONArray("weather")
						.getJSONObject(0);
				if (!weather.equals(null)) {
					if (!weather.getString("main").equals(null)) {
						String weather_main = weather.getString("main");
						result.put("main", weather_main);
					}

				}

			}

		}
		return result;

		/*
		 * if (weather_main.contains("Rain")) {
		 * System.out.println("The temperature is " + fmt.format(temp) +
		 * "/C, raining, please bring your umbrella."); } else if
		 * (weather_main.contains("Cloud")) {
		 * System.out.println("The temperature is " + fmt.format(temp) +
		 * "/C, cloud, please take more clothes."); } else if
		 * (weather_main.contains("Clear")) {
		 * System.out.println("The temperature is " + fmt.format(temp) +
		 * "/C, clear, please bring your sunglasses."); } else {
		 * System.out.println("The temperature is " + fmt.format(temp) + "/C, "
		 * + weather_main);
		 * 
		 * }
		 */

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

	 
	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		//url="http://api.openweathermap.org/data/2.5/weather?q=San+Jose&APPID=1a3aff4a56d078c49614644ba6f7ea37";
		 JSONObject json=new JSONObject();
		
		try {
			InputStream is = new URL(url).openStream();
			 
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			 json = new JSONObject(jsonText);
			 
		} catch(Exception e) {
			 
		}
		
		return json;
	}

	public static void main(String[] args) throws IOException, JSONException { // test
																				// the
																				// weather
																				// search
																				// function
																				// getWeatherByCity("LONDON");
		JSONObject jo = new JSONObject();
		String lat = "35";
		String lon = "139";
		jo.put("lat", lat);
		jo.put("lon", lon);
		WeatherScan ws = new WeatherScan(jo);
		JSONObject tt = ws.getTemperatureAndMain();
		System.out.println(tt.toString());

		// getWetherByZip(94040,"us");
		// getWeatherByLocation(35,139);

	}

}