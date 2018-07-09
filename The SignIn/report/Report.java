package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.PrintLog;
import backend.JDBC_Connect;
import backend.SqlQuery;

public class Report {

	
	// Connection Variables
	private JDBC_Connect connect = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;

	private Connection conn = null;
	private int internet = 0;
	private int printScan = 0;
	private int msOffice = 0;

	private int testing = 0;
	private int studying = 0;
	private int tutor = 0;
	private String UIN;
	SqlQuery query = null;
	private String reportDate; 
	
	Report(){ 
		 // Default Constructor
		}
	
	public void insertReport(){
		CreateDate(reportDate); 
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			String sql = new String(
					"INSERT INTO REPORT(UIN, INTERNET, PRINTSCAN, MSOFFICE, TESTING, STUDYING, TUTOR, DATE) VALUES(?, ?, ?, ?, ?,?,?, ?)");
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, getUIN());
			pst.setInt(2, getInternet());
			pst.setInt(3, getPrintScan());
			pst.setInt(4, getMsOffice());
			pst.setInt(5, getTesting());
			pst.setInt(6, getStudying());
			pst.setInt(7, getTutor());
			pst.setString(8, CreateDate(reportDate));
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//To Log
		PrintLog log = new PrintLog();
		log.PrinttoLog();
	}
	
	public String CreateDate(String reportDate){

			// Set Date format
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date Sqldate = new Date();
            reportDate = dateFormat.format(Sqldate);
            // Create date
            System.out.println(" Line 91 report Date" + " " + reportDate);
			return reportDate;
            
			
	}
	
	
	public int getInternet() {
		System.out.println(internet);
		return internet;
		
	}

	public void setInternet(int internet) {
		this.internet = internet;
	}

	public int getPrintScan() {
		System.out.println(internet);
		return printScan;
	}

	public void setPrintScan(int printScan) {
		this.printScan = printScan;
	}

	public int getMsOffice() {
		System.out.println(msOffice);
		return msOffice;
	}

	public void setMsOffice(int msOffice) {
		this.msOffice = msOffice;
	}

	public int getTesting() {
		System.out.println(testing);
		return testing;
	}

	public void setTesting(int testing) {
		this.testing = testing;
	}

	public int getStudying() {
		System.out.println(studying);
		return studying;
	}

	public void setStudying(int studying) {
		this.studying = studying;
	}

	public int getTutor() {
		System.out.println(tutor);
		return tutor;
	}

	public void setTutor(int tutor) {
		this.tutor = tutor;
	}
	public String getUIN() {
		System.out.println("Report UIN" + " " + UIN);
		return UIN;
	}
	public void setUIN(String uIN) {
		query.selection(); 
		uIN = query.getUIN();
		this.UIN = uIN;
	}

}
