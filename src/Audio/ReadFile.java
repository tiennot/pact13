package Audio;

import java.io.*;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public final class ReadFile {
	
	public static void fileDisplay(String fileName) {
		
		WavFile wavFile = null;

		try {
			wavFile = WavFile.openWavFile(new File(fileName));

		} catch (WavFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Get the number of audio channels in the wav file
		int numChannels = wavFile.getNumChannels();
		double t = 0; // Curseur temporel
		double T = 1 / (double) wavFile.getSampleRate(); // Période
															// d'acquisition

		ArrayList<XYSeries> series = new ArrayList<XYSeries>(numChannels);
		for (int i = 0; i < numChannels; i++) {
			series.add(new XYSeries("Voie n°" + String.valueOf(i)));
		}

		// Display information about the wav file
		wavFile.display();

		// Create a buffer of 100 frames * numChannels
		double[] buffer = new double[100 * numChannels];

		int framesRead = 0;

		do {
			// Read frames into buffer
			try {
				framesRead = wavFile.readFrames(buffer, 100);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WavFileException e) {
				e.printStackTrace();
			}

			for (int s = 0; s < framesRead * wavFile.getNumChannels(); s += numChannels) {
				for (int i = 0; i < numChannels; i++) {
					series.get(i).add(t, buffer[s + i]);

				}

				t += T;

			}

		} while (framesRead != 0);

		// Close the wavFile
		try {
			wavFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		for (int i = 0; i < numChannels; i++) {
			dataset.addSeries(series.get(i));
		}
		JFreeChart chart = ChartFactory.createXYLineChart(fileName, "temps",
				"amplitude", dataset, PlotOrientation.VERTICAL, true, true,
				false);
		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		frame.setVisible(true);
	}

	public static TableAudio getData(String fileName) {
		
		WavFile wavFile = null;

		try {
			wavFile = WavFile.openWavFile(new File(fileName));

		} catch (WavFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Get the number of audio channels in the wav file
		int numChannels = wavFile.getNumChannels();
		double t = 0; // Curseur temporel
		double T = 1 / (double) wavFile.getSampleRate(); // Période
															// d'acquisition

		ArrayList<XYSeries> series = new ArrayList<XYSeries>(numChannels);
		for (int i = 0; i < numChannels; i++) {
			series.add(new XYSeries("Voie n°" + String.valueOf(i)));
		}


		// Create a buffer of 100 frames * numChannels
		double[] buffer = new double[100 * numChannels];

		int framesRead = 0;

		do {
			// Read frames into buffer
			try {
				framesRead = wavFile.readFrames(buffer, 100);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WavFileException e) {
				e.printStackTrace();
			}

			for (int s = 0; s < framesRead * wavFile.getNumChannels(); s += numChannels) {
				for (int i = 0; i < numChannels; i++) {
					series.get(i).add(t, buffer[s + i]);

				}

				t += T;

			}

		} while (framesRead != 0);

		// Close the wavFile
		try {
			wavFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		for (int i = 0; i < numChannels; i++) {
			dataset.addSeries(series.get(i));
		}

		TableAudio tableAudio = new TableAudio(dataset,
				(int) wavFile.getSampleRate(), (int) wavFile.getValidBits(),
				(int) wavFile.getNumChannels());

		// Close the wavFile
		try {
			wavFile.close();
		} catch (IOException e) {
			System.out.println("Erreur lors de l'accès au fichier : vérifiez l'emplacement");

		}
		return tableAudio;

	}
	
	public static void showGraph(String name, XYSeriesCollection data){
		JFreeChart chart = ChartFactory.createXYLineChart(name, "temps",
				"amplitude", data, PlotOrientation.VERTICAL, true, true,
				false);
		ChartFrame frame = new ChartFrame("PACT 1.3 Audio", chart);
		frame.pack();
		frame.setVisible(true);
		
	}
}
