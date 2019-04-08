package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Service {
	static double kelvin = 273.15;
	static String currencyCode;
	static String myAppID = "7bc479f79e2bb15a2299f4acfac1a78b";
	String countryName, currency, currencyToCheck, myNBPRate;
	Double temperature, minTemperature, maxTemperature, pressure, humidity;
	String rate = "";

	public Service(String countryName) {
		this.countryName = countryName;
		currency = Service.getCurrency(countryName);
	}

	static String getCurrency(String countryName) {
		String currency = "";
		Locale defaultLocale = new Locale("United States");
		Locale.setDefault(defaultLocale);
		String[] countriesShort = Locale.getISOCountries();
		for (String country : countriesShort) {
			Locale currentLocale = new Locale("en", country);
			String myCountryCode = currentLocale.getCountry();
			boolean nameConfirmed = currentLocale.getDisplayCountry().startsWith(countryName);
			if (nameConfirmed == true) {
				setCode(myCountryCode);
				Currency myCurrency = Currency.getInstance(currentLocale);
				currency = myCurrency.toString();
			}
		}
		return currency;
	}

	public String getWeather(String city) {
		byte element = 1;
		String link = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + currencyCode + "&appid="
				+ myAppID + "";
		String weather = connect(link, element);
		try {
			ParseResult(weather);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weather;
	}

	public String weather() {
		String temperature = String.format("%.1f", getTemperature() - kelvin);
		String minTemperature = String.format("%.1f", setMinTemperature() - kelvin);
		String maxTemperature = String.format("%.1f", getMaxTemperature() - kelvin);
		String pressure = String.format("%.1f", getPressure());
		String humidity = String.format("%.1f", getHumidity());
		return "Temperatura: " + temperature + " stopni Celsjusza." + "\n" + "Temeperatura min: " + minTemperature
				+ " stopni Celsjusza." + "\n" + "Temperatura max: " + maxTemperature + " stopni Celsjusza." + "\n"
				+ "Cisnienie: " + pressure + " hPa" + "\n" + "Wilgotnosc: " + humidity + "%";
	}

	public String connect(String link, byte element) {
		String myConnectionString = "";
		URL weatherURL = null;
		try {
			weatherURL = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection myHttpURLConnection = null;
		try {
			myHttpURLConnection = (HttpURLConnection) weatherURL.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (myHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader myBufferedReader = new BufferedReader(
						new InputStreamReader(myHttpURLConnection.getInputStream()));
				String line = null;
				while ((line = myBufferedReader.readLine()) != null) {
					myConnectionString += line;
				}
				myBufferedReader.close();
				if (element == 1) {
					System.out.println("Połączenie z api pogodowym poprawne");
				} else if (element == 4) {
					System.out.println("Sprawdzono kurs wobec NBP.");
					try {
						ParseNBP(myConnectionString);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Sprawdzono zależność kursów wobec siebie.");
					parseResultCurrency(myConnectionString);
				}
			} else {
				System.err.print("Nie udało się odczytać danych.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myConnectionString;
	}

	public Double getRateFor(String currencyToCheck) {
		this.currencyToCheck = currencyToCheck;
		byte element = 0;
		connect("https://api.exchangeratesapi.io/latest?base=" + currencyToCheck, element);
		double value = Double.parseDouble(getRate());
		return value;
	}

	String parseResultCurrency(String json) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject JSON_rates = null;
		try {
			JSON_rates = jsonObject.getJSONObject("rates");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currencyToSet = "";
		try {
			if (currency.equals(currencyToCheck)) {
				currencyToSet = "1";
			} else {
				currencyToSet = JSON_rates.getString(currency);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.err.println("Ustalanie kursu nie powiodło się.");
		}
		setRate(currencyToSet);
		return json;
	}

	public Double getNBPRate() {
		byte element = 4;
		if (currency.equals("PLN")) {
			return 1.0;
		} else {
			connect("http://api.nbp.pl/api/exchangerates/rates/a/" + currency, element);
			double valueNBP = Double.parseDouble(getMyNBPRate());
			return valueNBP;
		}
	}

	String ParseNBP(String json) throws JSONException {
		JSONObject myJsonObject = new JSONObject(json);
		JSONArray nbp = myJsonObject.getJSONArray("rates");
		if (nbp.length() > 0) {
			JSONObject myJsonObject2 = nbp.getJSONObject(0);
			setMyNBPRate(myJsonObject2.getString("mid"));
		}
		return json;
	}

	String ParseResult(String json) throws JSONException {
		JSONObject myJsonObject = new JSONObject(json);
		JSONObject JSONObject_main = myJsonObject.getJSONObject("main");
		Double jsonMyTemperature = JSONObject_main.getDouble("temp");
		Double jsonMyMinTemp = JSONObject_main.getDouble("temp_min");
		setMinTemperature(jsonMyMinTemp);
		Double jsonMyMaxTemp = JSONObject_main.getDouble("temp_max");
		setMaxTemperature(jsonMyMaxTemp);
		setTemperature(jsonMyTemperature);
		Double jsonMyPressure = JSONObject_main.getDouble("pressure");
		setPressure(jsonMyPressure);
		Double jsonMyHumidity = JSONObject_main.getDouble("humidity");
		setHumidity(jsonMyHumidity);
		return json;
	}

	public static String getCode() {
		return currencyCode;
	}

	public static void setCode(String code) {
		Service.currencyCode = code;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getMyNBPRate() {
		return myNBPRate;
	}

	public void setMyNBPRate(String rate) {
		this.myNBPRate = rate;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temp) {
		this.temperature = temp;
	}

	public Double setMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(Double mintemp) {
		this.minTemperature = mintemp;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Double maxtemp) {
		this.maxTemperature = maxtemp;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
}
