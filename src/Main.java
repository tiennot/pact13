import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Audio.*;


public class Main {

	  public static void main(String[] args)
	   { 
		   //String[] args1 = {"Test"};
		   //WriteExample.main(args1);/*
		   
		  try{
			  XYSeries series = new XYSeries("XYGraph");
		  
			  
			  WavFile wavFile = WavFile.openWavFile(new File("Test"));
			   

				// Display information about the wav file
			  wavFile.display();

			  // Get the number of audio channels in the wav file
			  int numChannels = wavFile.getNumChannels();
			
			  // Create a buffer of 100 frames
			  double[] buffer = new double[100 * numChannels];

			  int framesRead;
			  int t=0;
			  long sr = wavFile.getSampleRate();

			  do
			  {
				  // Read frames into buffer
				  framesRead = wavFile.readFrames(buffer, 100);

				  for (int s=0 ; s<framesRead * wavFile.getNumChannels() ; s++)
				  {
					  series.add((s+100*t), buffer[s]);
					
				  }
				  t++;
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
		  }catch(Exception e){
			  e.printStackTrace();
		  }//*/
	   }
	   
 
}
