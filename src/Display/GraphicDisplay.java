package Display;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Audio.TableAudio;

public class GraphicDisplay{
	
	
	public static void showGraph(String name, String XLabel, String YLabel, XYSeriesCollection data){
		
		
		JFreeChart chart = ChartFactory.createXYLineChart(name, XLabel,
				YLabel, data, PlotOrientation.VERTICAL, true, true,
				false);
		ChartFrame frame = new ChartFrame("PACT 1.3 Audio", chart);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void showGraph(String name, String XLabel, String YLabel, double[] data0){
		
		XYSeriesCollection data = new XYSeriesCollection();
		XYSeries local = new XYSeries("");
		
		for(int k=0, n=data0.length; k<n; k++){
			
			//data0[k] = (data0[k] >0)? data0[k] : 0;
			
			local.add(new XYDataItem(k,data0[k]));
		}
		
		data.addSeries(local);
		showGraph(name, XLabel, YLabel, data);
		
	}
	
	public static void showAudio(TableAudio tableAudio){
		
		double p =  (1/(double) tableAudio.getSampleRate());
		double t=0;
		double[] data = tableAudio.getAudioData();
		XYSeriesCollection series = new XYSeriesCollection();
		XYSeries local = new XYSeries("");
		
		for(int i=0, n = data.length; i<n; i++){
			local.add(new XYDataItem(t,data[i]));
			t += p;
		}
		
		series.addSeries(local);
		showGraph("Forme d'onde","Temps","Amplitude",series);
	}

}
