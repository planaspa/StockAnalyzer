package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChartsPanel (){
		
		XYSeries series = new XYSeries("XYGraph");
		series.add(1, 1);
		series.add(1, 2);
		series.add(2, 1);
		series.add(3, 9);
		series.add(4, 10);

		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart(
				"XY Chart", // Title
				"x-axis", // x-axis Label
				"y-axis", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs? 
		);			
		
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
		ChartPanel cp = new ChartPanel(chart);
		cp.setPreferredSize(new Dimension(600,400));
		TitledBorder title = BorderFactory.createTitledBorder("Chart");
		cp.setBorder(title);
		
		this.add(cp,BorderLayout.CENTER);
				
	}

}
