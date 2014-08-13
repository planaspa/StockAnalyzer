package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DataInserter {
	
	 public static void main( String args[] )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:data/FullDataBase.db");
		      System.out.println("Database successfully opened");
		      
		      stmt = c.createStatement();

		      
		      File dir = new File("data/sources");
		      File[] files = dir.listFiles();
		      
		      for (int i=0; i < files.length; i++){
		    	  String name = files [i].getName();
		    	  System.out.print("Reading File: "+name);
		    	  
		    	  Scanner scan = new Scanner (new File (dir+"/"+name));
		    	  scan.useDelimiter("[,\\n]");
		    	  
		    	  String FinancialElementName = scan.nextLine().trim();
		    	  String Region = scan.nextLine().trim();
		    	 
		    	  /* 
		    	   * Read and save in the 3rd line the sectors (separated 
		    	   * by commas) in which the financial element works
		    	   */
		    	  ArrayList <String> sectors = new ArrayList<String>();
		    	  String aux = scan.nextLine();
		    	  Scanner scanSectors = new Scanner (aux);
		    	  scanSectors.useDelimiter("[,]");
		    	  while (scanSectors.hasNext()) 
		    		  if (!(aux.length()<2))
		    		  sectors.add(scanSectors.next().trim());
		    	  scanSectors.close();
		    	  
		    	  //<TICKER>,<PER>,<DTYYYYMMDD>,<TIME>,<OPEN>,<HIGH>,<LOW>,<CLOSE>,<VOL>,<OPENINT>
		    	  scan.nextLine(); //Reads and ignores the headlines
		    	  
		    	  String ticker = scan.next(); //<Ticker>
		    	  scan.next();//<PER>
		    	  int date = scan.nextInt();//<DTYYYYMMDD>
		    	  scan.next();//<TIME>
		    	  double open = scan.nextDouble();//<OPEN>
		    	  double high = scan.nextDouble();//<HIGH>
		    	  double low = scan.nextDouble();//<LOW>
		    	  double close = scan.nextDouble();//<CLOSE>
		    	  int volume = scan.nextInt();//<VOLUME>
		    	  scan.nextLine();
		    	  
		    	  /* INSERTIONS INTO DB*/  
		    	  String sql = "INSERT INTO FinancialElements ("
		    	  		+ "ticker, name, region) VALUES ('"
		    	  		+ ticker + "', '"+ FinancialElementName + "', '" + Region +
		    	  		"')";
		    	  stmt.executeUpdate(sql);
		    	  System.out.println("...OK");
		    	  
		    	  sql = "INSERT INTO Prices ("
		    	  		+ "ticker, day, maximum, minimum, open, close, volume)"
		    	  		+ " VALUES ('" +ticker+"', "+date + ", "+high+", "+low+
		    	  		", "+open+", "+close+", "+volume+")";
		    	  stmt.executeUpdate(sql);
		    	  
		    	  while (!sectors.isEmpty()){
			    	  sql = "INSERT INTO Sectors ("
			    	  		+ "ticker, sector ) VALUES ('" + ticker + "', '" +
			    			  sectors.remove(0)+"')";
			    	
			    	  stmt.executeUpdate(sql);
		    	  }
		    	  System.out.print("Inserting Prices of "+name+ ".");
		    	  int j = 0;
		    	  /* INSERTING REST OF THE FILE */
		    	  while (scan.hasNextLine()){
		    		  
			    	  ticker = scan.next(); //<Ticker>
			    	  scan.next();//<PER>
			    	  date = scan.nextInt();//<DTYYYYMMDD>
			    	  scan.next();//<TIME>
			    	  open = scan.nextDouble();//<OPEN>
			    	  high = scan.nextDouble();//<HIGH>
			    	  low = scan.nextDouble();//<LOW>
			    	  close = scan.nextDouble();//<CLOSE>
			    	  volume = scan.nextInt();//<VOLUME>
			    	  scan.nextLine();
			    	  
			    	  sql = "INSERT INTO Prices ("
				    	  		+ "ticker, day, maximum, minimum, open, close, volume)"
				    	  		+ " VALUES ('" +ticker+"', "+date + ", "+high+", "+low+
				    	  		", "+open+", "+close+", "+volume+")";
				     stmt.executeUpdate(sql);
				     
				     j++;
				     if (j%100==0)System.out.print(".");
				     
		    	  }
		    	  System.out.println("OK");
		    	  scan.close();
		      }
		      
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	  }
}
