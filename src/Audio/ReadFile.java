package Audio;
import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ReadFile
{
	public static void readFile(String fileName)
	{
		try{
			  XYSeries series = new XYSeries("XYGraph");
		  
			  
			  WavFile wavFile = WavFile.openWavFile(new File(fileName));
			   

			// Display information about the wav file
			  wavFile.display();

			  // Get the number of audio channels in the wav file
			  int numChannels = wavFile.getNumChannels();
			
			  // Create a buffer of 100 frames
			  double[] buffer = new double[100 * numChannels];

			  int framesRead;
			  double t=0;
			  double sr = (double) wavFile.getSampleRate();
			  double T = 1/sr;

			  do
			  {
				  // Read frames into buffer
				  framesRead = wavFile.readFrames(buffer, 100);

				  for (int s=0 ; s<framesRead * wavFile.getNumChannels() ; s++)
				  {
					  series.add(t, buffer[s]);
					  t += T;
					  
				  }
				  
			  }
			  while (framesRead != 0);

			  // Close the wavFile
			  wavFile.close();
		  
			  XYSeriesCollection dataset = new XYSeriesCollection();
			  dataset.addSeries(series);
			  JFreeChart chart = ChartFactory.createXYLineChart("XY Chart","x-axis",
				  "y-axis", dataset,PlotOrientation.VERTICAL,true,true,false);
			  ChartFrame frame=new ChartFrame("First",chart);
			  frame.pack();
			  frame.setVisible(true);
		  }catch(IOException e){
			  System.out.println("Erreur lors de l'accès au fichier : vérifiez l'emplacement");
		  }catch (WavFileException e){
			e.printStackTrace();
		  }
	}
}
