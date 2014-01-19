package Audio;

import java.util.Observable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphicDisplay extends Observable{
	
	public GraphicDisplay(){
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		JFreeChart chart = ChartFactory.createXYLineChart("Audio", "temps",
				"amplitude", dataset, PlotOrientation.VERTICAL, true, true,
				false);
		
		
		
		ChartFrame frame = new ChartFrame("First", chart);
		for(int i=0; i<frame.getContentPane().getComponents().length; i++){
		System.out.println(frame.getContentPane().getComponents()[i].toString());
		}
		System.out.println(frame.getLayout().toString());
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
