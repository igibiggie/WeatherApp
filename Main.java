/**
 *
 *  @author Leleń Igor S15833
 *
 */

package zad1;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		Service s = new Service("Poland");
		String weatherJson = s.getWeather("Warsaw");
		Double rate1 = s.getRateFor("USD");
		Double rate2 = s.getNBPRate();
		// ...
		// część uruchamiająca GUI

		System.out.println(s.weather());
		System.out.println(weatherJson);
		System.out.println(rate1);
		System.out.println(rate2);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherAppGUI window = new WeatherAppGUI();
					window.frame.setVisible(true);
					// window.frame.setExtendedState(window.frame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
