package src;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import backend.JDBC_Connect;


/**
 *
 * @author matthew
 */
public final class Testing extends JComponent {
  
	private static final long serialVersionUID = 1L;
	private JDBC_Connect connect = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private Connection conn = null;
	private String sql = null;
        
    private final int access = 2;
    private final String user = null;
    ArrayList<Object> waiverArr = new ArrayList<Object>(); 	
    private Object waiverStr;
    private String course;
    private String time;
    private String fee;
                 
    public int getAccess() {
        return access;
    }

    public ArrayList<Object> getWaiverArr() {
        return waiverArr;
    }

    public String getUser() {
        return user;
    }

    public Connection getConn() {
        return conn;
    }

    public Testing(){
       selectWaiver(course, time, fee, waiverStr);  
    }
    
    
    
    
    public void selectWaiver(String course, String time, String fee, Object waiverStr) {
      try {
		
    	  	connect = new JDBC_Connect();
			conn = connect.connect();
			
			sql = "SELECT COURSE, DESCRIPTION, TIME, FEE FROM WAIVERS";
			
			pst = conn.prepareStatement(sql);
			
			
			rs = pst.executeQuery();
                   ResultSetMetaData metaData = rs.getMetaData();
                            int columns = metaData.getColumnCount();
                            
                      for (int i = 0; i <= columns; i++) { 
                        while (rs.next()) { // retrieve data
                           course = rs.getString("COURSE");
                         //   descrip = rs.getString("DESCRIPTION");
                        time = rs.getString("TIME");
                        fee = rs.getString("FEE"); 
                        
                            waiverStr = course + " " + " " + time + " " + fee; 
                           waiverArr.add(waiverStr);
                    }                           

                    }
   
		} catch (SQLException e) {
		} 
        
                System.out.println("Waiver Array Line 92" + waiverArr);  
            }
}
