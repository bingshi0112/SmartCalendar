package mypackage;

public class AlertInfo {
	private double temperature;
	private String main;
	private String dur;
	private String distance;

	public AlertInfo() {
		temperature = 10000;
		main = "";
		dur = "";
		distance = "";

	}

	public void setTemperature(double _temperature) {
		temperature = _temperature;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setMain(String _main) {
		main = _main;
	}

	public String getMain() {
		return main;
	}

	public void setDur(String _dur) {
		dur = _dur;
	}

	public String getDur() {
		return dur;
	}

	public void setDistance(String _distance) {
		distance = _distance;
	}

	public String getDistance() {
		return distance;
	}

	public String getMyMessage() {
		StringBuilder message = new StringBuilder();
		if (!dur.equals("")) {message.append("Distance is " + distance + ". Drive time " + dur+ ".");
		}
		if (!main.equals("")) {
			if (main.equals("Clear")) {
				message.append("Weather is Clear. Temperature is " + temperature
						+ "C . Please have fun!");
			} else if (main.equals("Haze")) {
				message.append("Weather is Haze. Temperature is " + temperature
						+ "C . Please wear mask!");
			} else if (main.equals("Clouds")) {
				message.append("The weather is Clouds.Temperature is " + temperature
						+ "C . Please wear more!");
			} else if (main.equals("Rain")) {
				message.append("The weather is Rain.Temperature is " + temperature
						+ "C . Please wear more!");
			} else {
				message.append("The weather is " +main+ ". Temperature is " + temperature
						+ "C . Please enjoy your day!");
			}
		}

		return message.toString();

	}

}
