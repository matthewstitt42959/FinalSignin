package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import backend.JDBC_Connect;
import backend.SqlQuery;

public class Session_Log {
private JDBC_Connect connect = null;
private ResultSet rs = null;
private PreparedStatement pst = null;
private Connection conn = null;	
SqlQuery query = new SqlQuery();
private String UIN = "";
private String logTimeIn;
private String logTimeOut;
private String logDate;   

	public Session_Log(){
		// default Constructor
		
	}
	
	
	public void Login(String uIN2){
		try {
			connect = new JDBC_Connect();
			conn = connect.connect(); 
			// Session Logging SQL
			System.out.println("Login_ Session" +  " " + uIN2); 
			String sql = new String(
					"INSERT INTO SESSION_ACTIVE (UIN) SELECT UIN FROM REGISTERED_USER_TABLE WHERE UIN = ?");
			pst = conn.prepareStatement(sql);
			pst.setString(1, uIN2);
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
	}
	public void Logout(String uIN2){
		try {
			connect = new JDBC_Connect();
			conn = connect.connect(); 
			// Session Logging SQL
			System.out.println("Logout" +  " " + uIN2); 
			String sql = new String(
					"INSERT INTO SESSION_LOG (ACT_SESSIONID, UIN, LOGTIMEIN)(SELECT ACT_SESSIONID, UIN, LOGTIMEIN FROM SESSION_ACTIVE WHERE UIN = ?)");
			pst = conn.prepareStatement(sql);
			pst.setString(1, uIN2);
			pst.executeUpdate();
			
			setDiffTimeStamp(UIN); 
			
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
		
	}
				
	public void Delete(String uIN2){
				
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			System.out.println("Session_UIN" + " " + uIN2); 
		String sqlDelete = new String("DELETE FROM SESSION_ACTIVE WHERE UIN = ?");
			pst = conn.prepareStatement(sqlDelete);
			pst.setString(1, uIN2);
			int del = pst.executeUpdate();
			System.out.println("Number of deleted records: " + del);

		}catch (SQLException e) {
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

		}
	public void setDiffTimeStamp(String uIN2){
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			// Session Logging SQL
			
			String sql = "UPDATE SESSION_LOG SET TOTALTIME = (SELECT TIMESTAMPDIFF(MINUTE,LOGTIMEIN,LOGTIMEOUT))"
                                + "WHERE (UIN = ?) AND SESSIONID = (SELECT MAX(SESSIONID))";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uIN2);
			pst.executeUpdate();
			UpdateLogDate(uIN2); 
			
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
		
	}
	public void UpdateLogDate(String uIN2){
		try {
			
			connect = new JDBC_Connect();
			conn = connect.connect();
			// Set Date format
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date Sqldate = new Date();
            logDate = dateFormat.format(Sqldate);
            // Create date
            System.out.println(" Line 43 LOGTIMEIN" + " " + logDate); 
            
			String sql = "UPDATE SESSION_LOG SET DATE = ? WHERE UIN = ? AND SESSIONID = (SELECT MAX(SESSIONID))";
			pst = conn.prepareStatement(sql);
			pst.setString(1,logDate);
			pst.setString(2, uIN2);
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
		
	}
	
	public String getLogTimeIn() { 
        return logTimeIn;
    }

    public String getLogTimeOut() {
        return logTimeOut;
    }

    public String getLogDate() {
        return logDate;
    }

    public String getUIN() {
		System.out.println("Session_Log getUIN" + UIN);
		return UIN;
	}


	public void setUIN(String uIN2) {
		this.UIN = uIN2; 
		
	}

/*	 public void printReport(String UIN){
		try {
			connect = new JDBC_Connect();
			conn = connect.connect(); 
			// Session Logging SQL
			System.out.println("Login_ Session" +  " " + UIN); 
			String sql = new String(
					"INSERT INTO REPORT(UIN, INTERNET, PRINTSCAN, MSOFFICE, TESTING, STUDYING, TUTOR) VALUES(?, ?, ?, ?, ?,?,?)");
			pst = conn.prepareStatement(sql);
			pst.setString(1, UIN);
			pst.setString(2, internet);
			pst.setString(3, printScan);
			pst.setString(4, msOffice);
			pst.setString(5, testing);
			pst.setString(6, studying);
			pst.setString(7, tutor);
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
	} */
}
