package zad1;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class WeatherAppGUI {

	JFrame frame;
	private JTextField tCountry;
	private JTextField tCurrency;
	private JTextField tCity;
	String city, country, currency;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherAppGUI window = new WeatherAppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WeatherAppGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFXPanel wikiPanel;

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel dataPanel = new JPanel();
		frame.getContentPane().add(dataPanel);
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_dataPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_dataPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_dataPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		dataPanel.setLayout(gbl_dataPanel);

		JLabel lblCity = new JLabel("Podaj miasto");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.EAST;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 0;
		dataPanel.add(lblCity, gbc_lblCity);

		tCity = new JTextField();
		GridBagConstraints gbc_tCity = new GridBagConstraints();
		gbc_tCity.insets = new Insets(0, 0, 5, 0);
		gbc_tCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_tCity.gridx = 1;
		gbc_tCity.gridy = 0;
		dataPanel.add(tCity, gbc_tCity);
		tCity.setColumns(10);

		JLabel lblPodajPastwo = new JLabel("Podaj państwo");
		GridBagConstraints gbc_lblPodajPastwo = new GridBagConstraints();
		gbc_lblPodajPastwo.anchor = GridBagConstraints.EAST;
		gbc_lblPodajPastwo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPodajPastwo.gridx = 0;
		gbc_lblPodajPastwo.gridy = 1;
		dataPanel.add(lblPodajPastwo, gbc_lblPodajPastwo);

		tCountry = new JTextField();
		GridBagConstraints gbc_tCountry = new GridBagConstraints();
		gbc_tCountry.insets = new Insets(0, 0, 5, 0);
		gbc_tCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_tCountry.gridx = 1;
		gbc_tCountry.gridy = 1;
		dataPanel.add(tCountry, gbc_tCountry);
		tCountry.setColumns(10);

		JLabel lblPodajWalut = new JLabel("Podaj walutę");
		GridBagConstraints gbc_lblPodajWalut = new GridBagConstraints();
		gbc_lblPodajWalut.anchor = GridBagConstraints.EAST;
		gbc_lblPodajWalut.insets = new Insets(0, 0, 5, 5);
		gbc_lblPodajWalut.gridx = 0;
		gbc_lblPodajWalut.gridy = 2;
		dataPanel.add(lblPodajWalut, gbc_lblPodajWalut);

		tCurrency = new JTextField();
		GridBagConstraints gbc_tCurrency = new GridBagConstraints();
		gbc_tCurrency.insets = new Insets(0, 0, 5, 0);
		gbc_tCurrency.fill = GridBagConstraints.HORIZONTAL;
		gbc_tCurrency.gridx = 1;
		gbc_tCurrency.gridy = 2;
		dataPanel.add(tCurrency, gbc_tCurrency);
		tCurrency.setColumns(10);

		JLabel lTemperature2 = new JLabel("Temperatura:");
		GridBagConstraints gbc_lTemperature2 = new GridBagConstraints();
		gbc_lTemperature2.insets = new Insets(0, 0, 5, 5);
		gbc_lTemperature2.gridx = 0;
		gbc_lTemperature2.gridy = 3;
		dataPanel.add(lTemperature2, gbc_lTemperature2);

		JLabel lTemperature = new JLabel("");
		GridBagConstraints gbc_lTemperature = new GridBagConstraints();
		gbc_lTemperature.insets = new Insets(0, 0, 5, 0);
		gbc_lTemperature.gridx = 1;
		gbc_lTemperature.gridy = 3;
		dataPanel.add(lTemperature, gbc_lTemperature);

		JLabel lMinTemperature2 = new JLabel("Min. temperatura:");
		GridBagConstraints gbc_lMinTemperature2 = new GridBagConstraints();
		gbc_lMinTemperature2.insets = new Insets(0, 0, 5, 5);
		gbc_lMinTemperature2.gridx = 0;
		gbc_lMinTemperature2.gridy = 4;
		dataPanel.add(lMinTemperature2, gbc_lMinTemperature2);

		JLabel lMinTemperature = new JLabel("");
		GridBagConstraints gbc_lMinTemperature = new GridBagConstraints();
		gbc_lMinTemperature.insets = new Insets(0, 0, 5, 0);
		gbc_lMinTemperature.gridx = 1;
		gbc_lMinTemperature.gridy = 4;
		dataPanel.add(lMinTemperature, gbc_lMinTemperature);

		JLabel lMaxTemperature2 = new JLabel("Max. temperatura:");
		GridBagConstraints gbc_lMaxTemperature2 = new GridBagConstraints();
		gbc_lMaxTemperature2.insets = new Insets(0, 0, 5, 5);
		gbc_lMaxTemperature2.gridx = 0;
		gbc_lMaxTemperature2.gridy = 5;
		dataPanel.add(lMaxTemperature2, gbc_lMaxTemperature2);

		JLabel lMaxTemperature = new JLabel("");
		GridBagConstraints gbc_lMaxTemperature = new GridBagConstraints();
		gbc_lMaxTemperature.insets = new Insets(0, 0, 5, 0);
		gbc_lMaxTemperature.gridx = 1;
		gbc_lMaxTemperature.gridy = 5;
		dataPanel.add(lMaxTemperature, gbc_lMaxTemperature);

		JLabel lHumidity2 = new JLabel("Wilogotność:");
		GridBagConstraints gbc_lHumidity2 = new GridBagConstraints();
		gbc_lHumidity2.insets = new Insets(0, 0, 5, 5);
		gbc_lHumidity2.gridx = 0;
		gbc_lHumidity2.gridy = 6;
		dataPanel.add(lHumidity2, gbc_lHumidity2);

		JLabel lHumidity = new JLabel("");
		GridBagConstraints gbc_lHumidity = new GridBagConstraints();
		gbc_lHumidity.insets = new Insets(0, 0, 5, 0);
		gbc_lHumidity.gridx = 1;
		gbc_lHumidity.gridy = 6;
		dataPanel.add(lHumidity, gbc_lHumidity);

		JLabel lPressure2 = new JLabel("Ciśnienie:");
		GridBagConstraints gbc_lPressure2 = new GridBagConstraints();
		gbc_lPressure2.insets = new Insets(0, 0, 5, 5);
		gbc_lPressure2.gridx = 0;
		gbc_lPressure2.gridy = 7;
		dataPanel.add(lPressure2, gbc_lPressure2);

		JLabel lPressure = new JLabel("");
		GridBagConstraints gbc_lPressure = new GridBagConstraints();
		gbc_lPressure.insets = new Insets(0, 0, 5, 0);
		gbc_lPressure.gridx = 1;
		gbc_lPressure.gridy = 7;
		dataPanel.add(lPressure, gbc_lPressure);

		JLabel lRate1 = new JLabel("Kurs waluty:");
		GridBagConstraints gbc_lRate1 = new GridBagConstraints();
		gbc_lRate1.insets = new Insets(0, 0, 5, 5);
		gbc_lRate1.gridx = 0;
		gbc_lRate1.gridy = 8;
		dataPanel.add(lRate1, gbc_lRate1);

		JLabel lk1 = new JLabel("");
		GridBagConstraints gbc_lk1 = new GridBagConstraints();
		gbc_lk1.insets = new Insets(0, 0, 5, 0);
		gbc_lk1.gridx = 1;
		gbc_lk1.gridy = 8;
		dataPanel.add(lk1, gbc_lk1);

		JLabel lRate2 = new JLabel("Kurs NBP:");
		GridBagConstraints gbc_lRate2 = new GridBagConstraints();
		gbc_lRate2.insets = new Insets(0, 0, 5, 5);
		gbc_lRate2.gridx = 0;
		gbc_lRate2.gridy = 9;
		dataPanel.add(lRate2, gbc_lRate2);

		JLabel lk2 = new JLabel("");
		GridBagConstraints gbc_lk2 = new GridBagConstraints();
		gbc_lk2.insets = new Insets(0, 0, 5, 0);
		gbc_lk2.gridx = 1;
		gbc_lk2.gridy = 9;
		dataPanel.add(lk2, gbc_lk2);

		JPanel rightPanel = new JPanel();
		frame.getContentPane().add(rightPanel);
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		wikiPanel = new JFXPanel();
		rightPanel.add(wikiPanel);

		JButton showButton = new JButton("Sprawdz dane");
		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				country = tCountry.getText();
				Service myService = new Service(country);
				city = tCity.getText();
				myService.getWeather(city);
				double kelvin = 273.15;
				String temperature = String.format("%.1f", myService.getTemperature() - kelvin);
				String minTemperature = String.format("%.1f", myService.setMinTemperature() - kelvin);
				String maxTemperature = String.format("%.1f", myService.getMaxTemperature() - kelvin);
				lTemperature.setText(temperature + " stopni Ceslsjusza");
				lMinTemperature.setText(minTemperature + " stopni Celsjusza");
				lMaxTemperature.setText(maxTemperature + " stopni Celsjusza");
				lHumidity.setText(myService.getHumidity().toString() + "%");
				lPressure.setText(myService.getPressure().toString() + " hPa");
				currency = tCurrency.getText();
				Double rate1 = myService.getRateFor(currency);
				Double rate2 = myService.getNBPRate();
				lk1.setText(String.valueOf(rate1));
				lk2.setText(String.valueOf(rate2));

				Platform.runLater(() -> {
					WebView myWebView = new WebView();
					wikiPanel.setScene(new Scene(myWebView));
					myWebView.getEngine().load("https://en.wikipedia.org/wiki/" + city);
				});
			}
		});
		GridBagConstraints gbc_showButton = new GridBagConstraints();
		gbc_showButton.gridx = 1;
		gbc_showButton.gridy = 10;
		dataPanel.add(showButton, gbc_showButton);
	}

}
