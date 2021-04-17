package rolling_averages;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//LineChart class created to display data in line graph, using jFreechart 

public class LineChart extends JFrame {
	
	private static final long serialVersionUID = 6783576247711246810L;
	
	//ArrayLists of our data (moving averages and current data) is passed into the LineChart to create the graphs

	public LineChart(ArrayList<InputData> dataFromFile, ArrayList<RollingAverage> rollingAverage) {
	        super("Rolling Averages");
	 
	        JPanel chartPanel = createChartPanel(dataFromFile, rollingAverage);
	        add(chartPanel, BorderLayout.CENTER);
	
	        setSize(1000, 800);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	    }
	 
	
		//Chart Panel is created using the title, x & y axis and dataset of our ArrayLists 
		//(a Java 2D API to plot the line chart.)
	
	    public JPanel createChartPanel(ArrayList<InputData> dataFromFile, ArrayList<RollingAverage> rollingAverage) {
	    	    String chartTitle = "Rolling Averages Chart";
	    	    String xAxisLabel = "Time";
	    	    String yAxisLabel = "Input";
	    	 
	    	    XYDataset dataset = createDataset(dataFromFile, rollingAverage);
	    	 
	    	    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);
	    	 
	    	    return new ChartPanel(chart);
	    }
	 
	    public static XYDataset createDataset(ArrayList<InputData> dataFromFile, ArrayList<RollingAverage> rollingAverage) {
	    	    XYSeriesCollection dataset = new XYSeriesCollection();
	    	    XYSeries currentData = new XYSeries("Values");
	    	    XYSeries rollingAverages = new XYSeries("Rolling average");
	    	    
	    	    //add the data to the relevant series for the X and Y axis on the line graph
	    	    
	    	    for(InputData data : dataFromFile) {
	    	    	currentData.add(data.getTime(), data.getInput());
	    	    }
	    	    
	    	    for(RollingAverage data : rollingAverage) {
	    	    	rollingAverages.add(data.getTime(), data.getAverageValue());
	    	    }
	    	 
	    	    dataset.addSeries(currentData);
	    	    dataset.addSeries(rollingAverages);
	    	    
	    	    return dataset;
	    }
	 
	    public static void main(String[] args) {
	      
	    }

		public static void draw(ArrayList<InputData> dataFromFile, ArrayList<RollingAverage> rollingAverage) {
			  SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                new LineChart(dataFromFile, rollingAverage).setVisible(true);
		              
		            }
		        });
			  
		
			
		}
}
