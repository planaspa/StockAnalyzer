package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseCreator {

	 public static void main( String args[] )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:data/FullDataBase.db");
	      System.out.println("Database successfully created and opened");
	      
	      stmt = c.createStatement();
	      
	      /* FinancialElements in the stock market */
	      String sql =	"CREATE TABLE FinancialElements( " +
	    		  		"ticker varchar (20), " +
	    		  		"name varchar (100) NOT NULL, " +
	    		  		"region varchar (100) NOT NULL, " +
	    		  		"PRIMARY KEY (ticker))";
	      stmt.executeUpdate(sql);
	      System.out.println("FinancialElements table successfully created");
	      
	      /* Prices per day of a ticker */
	      sql = "CREATE TABLE Prices( " +
		  		"ticker varchar (20), " +
		  		"day int, " +
		  		"minimum float NOT NULL, " +
		  		"maximum float NOT NULL, " +
		  		"open float NOT NULL, " +
		  		"close float NOT NULL, " +
		  		"volume int, " +
		  		"FOREIGN KEY (ticker) REFERENCES FinancialElements(ticker), " +
		  		"PRIMARY KEY (ticker,day)) ";
	      stmt.executeUpdate(sql);
	      System.out.println("Prices table successfully created");

	      
	      /* Relation between Sectors and tickers, e.g. Banking and SAN.MC */
	      sql =	"CREATE TABLE Sectors( " +
	    		"ticker varchar (20), " +
	    		"sector varchar (100), " +
	    		"FOREIGN KEY (ticker) REFERENCES FinancialElements(ticker), " +
	    		"PRIMARY KEY (ticker,sector))";
	      stmt.executeUpdate(sql);
	      System.out.println("Sectors table successfully created");

	      
	      /* Relation between companies and indexes */
	      sql = "CREATE TABLE Indexes( " +
	    		"tickerCompany varchar (20), " +
	    		"tickerIndex varchar (20), " +
	    		"FOREIGN KEY (tickerCompany) REFERENCES FinancialElements(ticker), " +
	    		"FOREIGN KEY (tickerIndex) REFERENCES FinancialElements(ticker), " +
	    		"PRIMARY KEY (tickerCompany,tickerIndex))";
	      stmt.executeUpdate(sql);
	      System.out.println("Indexes table successfully created");

	      
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
	  }
	}