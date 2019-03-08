//package AnimalsTeamweek5;


import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Animals implements Runnable{
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String framework = "embedded";
    private int namet;
    private int duration;
    static int runsometimes;
	private String name;
	private String color;
	private String type;
	private String swim;
	
	
	public String getname(){
		return this.name;
	}
	
	public String getcolor(){
		return this.color;
	}
	
	public String gettype(){
		return this.type;
	}
		public String getswim(){
		return this.swim;
	}
	
//Sets values
		public String setname(String name){
			return this.name=name;
		}
		
		public String setcolor(String color){
			return this.color=color;
		}
		
		public String settype(String type){
			return this.type=type;
		}
			public String setswim(String swim){
			return this.swim=swim;
		}
		
	public static void main(String[] args)throws 
	 UnsupportedEncodingException, FileNotFoundException, 
	 IOException{

		String createTableSQL = "CREATE TABLE  Animals (Name VARCHAR(10),Color VARCHAR(10),Type VARCHAR(10),Swim VARCHAR(10))";
		String optionmenu;
		String dbName = "Animals Database";
		ResultSet rs = null;
		PreparedStatement psInsert = null;
		PreparedStatement preparedStatement = null;
		
		Scanner user_input = new Scanner( System.in );	
		
		
		// load the desired JDBC driver
		
		loadDriver();
				

       	
       	// ---------------Connection with MySQL
       	Connection conn = null;
           Scanner scn = new Scanner(System.in);
           String name = null, type = null, color = null, swim = null;
           

   		try{
   			final String DB_URL = "jdbc:derby:" + dbName + ";create = true";

   			//connect to database
   			conn = DriverManager.getConnection(DB_URL);
   			System.out.println("Connected to and created database " + dbName);

   			//Create a statement object
   			Statement s = conn.createStatement();
   			
   			//Check if animals table already exists in database
   			DatabaseMetaData dbm = conn.getMetaData();
   			// check if animals table already exists
   			ResultSet tables = dbm.getTables(null, null, "animals", null);
   			if (tables.next()) {
   			  // Table exists
   				// delete the table
   				s.execute("drop table animals");
   				System.out.println("Dropped table Animals");
   			}
   			else {
   			  // Table does not exist
   				//Create an Animals table
   				s.execute(createTableSQL);
   				System.out.println("Created table Animals");
   			}
   			
   			boolean choice = true;  
   			Random randomize = new Random();
   	        
   			// wait 2 seconds to display menu with one thread    	     
   	        new Thread(new Animals(0, randomize.nextInt(2000))).start();
   			
 			while (choice) {
 				
 				
 		        System.out.println("          Animals Menu");
 		        System.out.println("--------------------------------------");
 		        System.out.println("1 - List one animal");
 		        System.out.println("2  Add animal");
 		        System.out.println("3  Exit Program");
 		        System.out.print("Select a Menu Option: ");
 		        optionmenu = user_input.next( );
 		        
 		        switch(optionmenu) {
 		        case "1": // 


					System.out.print("Please enter animal name: ");
					//get user input
					name = scn.next();
					//get the selected row from the table
					rs = s.executeQuery("SELECT * FROM Animals WHERE name = '" + name + "'");
					if(rs.next()){	//check if animal is in table
						//display row
						ResultSetMetaData rsmd = rs.getMetaData();
						int columnsNumber = rsmd.getColumnCount();
							for(int i = 1; i <= columnsNumber; i++)
								System.out.print(" " + rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " ");
							System.out.println();
					} else {	//animal not found in table
						System.out.println("Animal does not exist in table");
					}
 		            break;
 		       
 		        case "2": // 
 		    

 		                System.out.print("Enter name: ");
 		                name = scn.nextLine();

 		                System.out.print("Enter color: ");
 		                color = scn.nextLine();

 		                System.out.print("Enter type of animal: ");
 		                type = scn.nextLine();
 		                
 		                System.out.print("Swim? (Yes/No): ");
 		                swim = scn.nextLine();
 						//prepare insert statement
 						psInsert = conn.prepareStatement("insert into animals values (?, ?, ?, ?)");
 						
 						//insert animal traits into table
 						psInsert.setString(1, name);
 						psInsert.setString(2, color);
 						psInsert.setString(3, type);
 						psInsert.setString(4, swim);
 						
 						//update table
 						psInsert.executeUpdate();
 						
 						//display updated table
 						rs = s.executeQuery("SELECT * FROM Animals");
 						ResultSetMetaData rsmd = rs.getMetaData();
 						int columnsNumber = rsmd.getColumnCount();
 						while (rs.next()) {
 							for(int i = 1; i <= columnsNumber; i++)
 								System.out.print(" " + rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " ");
 							System.out.println();
 						}
 						System.out.println("Record has been added");
 		           
 		            break;
 		        case "3": 
 		        	choice = false;
 		        	break;
 		        
 		        default:
 		            System.out.print("The entered value is unrecognized!");
 		            break;
 		    }
 			}
 			
   			

			// delete the table
			s.execute("drop table animals");
			System.out.println("Dropped table Animals");

			/*
            We commit the transaction. Any changes will be persisted to
            the database now.
			 */
			conn.commit();

			//shutdown database

			if (framework.equals("embedded"))
			{
				try
				{
					// the shutdown=true attribute shuts down Derby
					DriverManager.getConnection("jdbc:derby:;shutdown=true");

					// To shut down a specific database only, but keep the
					// engine running (for example for connecting to other
					// databases), specify a database in the connection URL:
					//DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
				}
				catch (SQLException se)
				{
					if (( (se.getErrorCode() == 50000)
							&& ("XJ015".equals(se.getSQLState()) ))) {
						// we got the expected exception
						System.out.println("Derby shut down normally");
						// Note that for single database shutdown, the expected
						// SQL state is "08006", and the error code is 45000.
					} else {
						// if the error code or SQLState is different, we have
						// an unexpected exception (shutdown failed)
						System.err.println("Derby did not shut down normally");
						printSQLException(se);
					}
				}
			}



		}
		catch (Exception sqle)
		{
			System.out.println("Error"+ sqle.getMessage());
		} finally {
			// release all open resources to avoid unnecessary memory usage

			// ResultSet
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			// Statements and PreparedStatements
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
			//Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}


}
	private static void loadDriver() {
		/*
		 *  The JDBC driver is loaded by loading its class.
		 *  If you are using JDBC 4.0 (Java SE 6) or newer, JDBC drivers may
		 *  be automatically loaded, making this code optional.
		 *
		 *  In an embedded environment, this will also start up the Derby
		 *  engine (though not any databases), since it is not already
		 *  running. In a client environment, the Derby engine is being run
		 *  by the network server framework.
		 *
		 *  In an embedded environment, any static Derby system properties
		 *  must be set before loading the driver to take effect.
		 */
		try {
			Class.forName(driver).newInstance();
			System.out.println("Loaded the appropriate driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("\nUnable to load the JDBC driver " + driver);
			System.err.println("Please check your CLASSPATH.");
			cnfe.printStackTrace(System.err);
		} catch (InstantiationException ie) {
			System.err.println(
					"\nUnable to instantiate the JDBC driver " + driver);
			ie.printStackTrace(System.err);
		} catch (IllegalAccessException iae) {
			System.err.println(
					"\nNot allowed to access the JDBC driver " + driver);
			iae.printStackTrace(System.err);
		}
	}
	public static void printSQLException (SQLException e)
	{
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null)
		{
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State:  " + e.getSQLState());
			System.err.println("  Error Code: " + e.getErrorCode());
			System.err.println("  Message:    " + e.getMessage());
			// for stack traces, refer to derby.log or uncomment this:
			//e.printStackTrace(System.err);
			e = e.getNextException();
		}
		
		
	}
	
	
	/**
     * Method to run the actions when the thread will be run.
    */
    public Animals(int namet, int duration) {
        this.namet = namet;
        this.duration = duration;
    }

    @Override
    public void run() {
        System.out.println("Thread "+this.namet+" right now is running.");
        System.out.println("Thread "+this.namet+" and I will stop "+this.duration+" ms.");
        try {
            Thread.sleep(this.duration);
        } catch (InterruptedException ex) { // Sleep can lunch a exception to abort the thread.
            Logger.getLogger(Animals.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thread "+this.name+" is running.");
        System.out.println("Thread "+this.name+" has been finished.");
    }	
}
