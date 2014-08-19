package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class FinancialElementSelectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> dropdown;
	
	public FinancialElementSelectPanel () {
		
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
		
		String[] content = loadFinancialElements();	
		
		dropdown = new JComboBox<String>(content);
		
		TitledBorder title = 
				BorderFactory.createTitledBorder("Financial Element Selection");
		dropdown.setBorder(title);
		
		this.add(dropdown,BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(200,50));

	}
	
	private String[] loadFinancialElements (){
		
		String [] output = null;
		
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
		
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:data/FullDataBase.db");
		      System.out.println("Database successfully opened");
		      
		      stmt = c.createStatement();
		      rs = stmt.executeQuery(
		    		  "SELECT count(ticker) FROM FinancialElements");
		      output = new String [rs.getInt(1)];
		      
		      rs = stmt.executeQuery("SELECT ticker FROM FinancialElements");
		      
		      for (int i=0; rs.next(); i++) 
		    	  output[i] = rs.getString(1);
		      
		      stmt.close();
		      c.close();
		      
	    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return output;		
	}

}
