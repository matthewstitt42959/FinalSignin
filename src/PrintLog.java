package src;

/*
 * Create an array from Database that collects log information and prints to .log
 -UIN
 -Name
 -Time in/out
 -What service they came in for
 -tutor # or test ID
 * 
 */

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;

import backend.JDBC_Connect;

public final class PrintLog {

    private Connection conn;
    private String sql;
    private PreparedStatement pst;
    private ResultSet rs;
    // Print
    PrintWriter pw = null; 
    FileOutputStream fileOut = null;
    //XSSFWorkbook workbook;
    //Workbook wb;
        
    private String startDate = "";
    private final String computer = "";
    private String totaltime;
    private String selection = "";  
    
    public String getStartDate() {
        return startDate;
    }
    private String currDate;
    private String reportStr;
    private JDBC_Connect connect;
	private Timestamp logtimein;
	private String internet, printscan, msoffice, studying, testing, tutor;
	ArrayList<Object> record = new ArrayList<Object>();
	private Timestamp logtimeout;
	private String reportDate;
	private Object logStr;

      
	public void PrinttoLog(){
        // Get all Data from the Report table
		CurrentDate(currDate);
		GetFromReport(); 
		GetFromSessionLog();
		printReport2CSV(); 
    }
	
	
private void GetFromReport() {
	 try {
         
     	connect = new JDBC_Connect();
			conn = connect.connect();

         sql = "SELECT LOGTIMEIN,INTERNET,PRINTSCAN,MSOFFICE,TESTING,STUDYING,TUTOR FROM REPORT";
         pst = conn.prepareStatement(sql);
   		//	pst.setInt(1, access);
   			
   			rs = pst.executeQuery();                  
    
                         java.sql.ResultSetMetaData metaData = rs.getMetaData();
                               int columns = metaData.getColumnCount();
                               
                         for (int i = 0; i <= columns; i++) { 
                           while (rs.next()) { // retrieve data
                               logtimein = rs.getTimestamp("LOGTIMEIN");
                               internet = rs.getString("INTERNET");
                               printscan = rs.getString("PRINTSCAN");
                               msoffice = rs.getString("MSOFFICE");
                               testing = rs.getString("TESTING");
                               studying = rs.getString("STUDYING");
                               tutor = rs.getString("TUTOR");
                       
                           reportStr = logtimein + " " + internet + " " + printscan + " " + msoffice + " " + testing + " " + studying + " " + tutor; 
                              record.add(reportStr);
                           }                           
         
                       }
   		} catch (SQLException e) {
                       // Add catch
                   }
                    System.out.println("Line 107: Report Array" + " " + reportStr);
     
	}

private void GetFromSessionLog() {
	 try {
        
    	connect = new JDBC_Connect();
			conn = connect.connect();

        sql = "SELECT LOGTIMEOUT,DATE,TOTALTIME FROM SESSION_LOG";
        pst = conn.prepareStatement(sql);
  		//	pst.setInt(1, access);
  			
  			rs = pst.executeQuery();                  
   
                        java.sql.ResultSetMetaData metaData = rs.getMetaData();
                              int columns = metaData.getColumnCount();
                              
                        for (int i = 0; i <= columns; i++) { 
                          while (rs.next()) { // retrieve data
                              logtimeout = rs.getTimestamp("LOGTIMEOUT");
                              reportDate = rs.getString("DATE");
                              totaltime = rs.getString("TOTALTIME");
                              
                          logStr = logtimeout + " " + reportDate + " " + totaltime; 
                          
                          }                           
        
                      }
  		} catch (SQLException e) {
                      // Add catch
                  }
                   System.out.println("Line 143: Log Array" + " " + logStr);
    
	}

private void printReport2CSV() {
	
        try {
         
        //	reportArr.add(waiverStr);
            System.out.println(" Report Log Line 152 " + " " + logtimein + " " + logtimeout + " " + reportDate + " " + totaltime);
            
            pw = new PrintWriter(new FileWriter("LearningCtr_history.csv", true));
            pw.println("Date"+ "  TimeStamp    " + "Date" + " " + "User" + " " + "Computer" + " " + "Testing" + " " + "Tutor" + " " + "Time Logged" ); // Print Heading  
            if (computer != null){
            	selection = "Computer";
            }
            if (testing != null){
            	selection = "Testing";
            }
            if (tutor != null){
            	selection = "Tutor";
            }
            
            
            pw.println(reportDate + ", " + logtimein + ", " + logtimeout + ", " + selection + ", " + totaltime + ";"); // Print data
 
    pw.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
   
private String CurrentDate(String currDate2) {
    
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date Sqldate = new java.util.Date();
             currDate = dateFormat.format(Sqldate);
             // Create date
             System.out.println(" Line 309 Date" + " " + currDate); 
    
     return currDate;
 }


public Timestamp getLogtimein() {
	return logtimein;
}


public void setLogtimein(Timestamp logtimein) {
	this.logtimein = logtimein;
}


public String getPrintscan() {
	return printscan;
}


public void setPrintscan(String printscan) {
	this.printscan = printscan;
}


public String getMsoffice() {
	return msoffice;
}


public void setMsoffice(String msoffice) {
	this.msoffice = msoffice;
}


public String getStudying() {
	return studying;
}


public void setStudying(String studying) {
	this.studying = studying;
}


public String getTesting() {
	return testing;
}


public String getTutor() {
	return tutor;
}


public String getTotaltime() {
	return totaltime;
}


public void setTotaltime(String totaltime) {
	this.totaltime = totaltime;
}


public Timestamp getLogtimeout() {
	return logtimeout;
}


public void setLogtimeout(Timestamp logtimeout) {
	this.logtimeout = logtimeout;
}


public String getReportDate() {
	return reportDate;
}


public void setReportDate(String reportDate) {
	this.reportDate = reportDate;
}


public String getSelection() {
	return selection;
}


public void setSelection(String selection) {
	this.selection = selection;
}


}

