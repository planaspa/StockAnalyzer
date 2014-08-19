package gui;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChartsPanel (){
		
		XYSeries series = loadPrices("ABE.MC","20140700","20140731");


		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Abertis Chart", // Title
				"Days", // x-axis Label
				"Prices", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs? 
		);			
		
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
		ChartPanel cp = new ChartPanel(chart);
		TitledBorder title = BorderFactory.createTitledBorder("Chart");
		cp.setBorder(title);
		
		this.add(cp,BorderLayout.CENTER);
				
	}
	
	private XYSeries loadPrices (String ticker, String dayStart, String dayEnd){
		
		XYSeries series = new XYSeries("XYGraph");
	    Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT day,close FROM Prices WHERE ticker=\""+ticker+"\" AND day >= "+dayStart+" AND day <="+dayEnd;
		
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:data/FullDataBase.db");
		      System.out.println("Database successfully opened");
		      
		      stmt = c.createStatement();
		      rs = stmt.executeQuery(sql);
		      
		      while (rs.next())
		    	  series.add(rs.getInt(1),rs.getDouble(2));
		      
		      stmt.close();
		      c.close();
		      
	    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		return series;
	}

}
