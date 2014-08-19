package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class DataPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea text;
	
	public DataPanel(){
		
		text = new JTextArea();
		text.setEditable(false);
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
		JScrollPane pane = new JScrollPane(text);
		pane.setPreferredSize(new Dimension(200,400));
		TitledBorder title = BorderFactory.createTitledBorder("Data");
		pane.setBorder(title);
		
		this.add(pane,BorderLayout.CENTER);
	}
	
	public void setText(String t){
		text.setText(t);
	}
	
	public void updateList(){
		

		String temp = fillWindow("SELECT ticker FROM financialElements");
		
		if (temp.equals("")) temp = "No data available";

		setText(temp);
	}
	
	private String fillWindow (String sql){
		
	    Connection c = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    String output="";
		
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:data/FullDataBase.db");
		      System.out.println("Database successfully opened");
		      
		      stmt = c.createStatement();
		      
		      rs = stmt.executeQuery(sql);
		      
		      while (rs.next()) output+=rs.getString(1) + "\n";
		      
		      stmt.close();
		      c.close();
		      
	    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	    
	    return output;
	}
}
