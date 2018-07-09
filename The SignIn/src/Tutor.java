package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComponent;

import backend.JDBC_Connect;

public class Tutor extends JComponent {
 
	private static final long serialVersionUID = 1L;
	private JDBC_Connect connect = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private Connection conn = null;
	private String sql = null;
	private final int tutorNum = 0; 
    private final String user = null;
    private final String fname = null;    
    private final int access = 2;
    private final String lname = null; 
    public String tutorStr = null;  	
	ArrayList<Object> record = new ArrayList<Object>();
	
    public Tutor(){
       selectTutor(tutorNum, fname, lname, tutorStr); 
       
    }

    public int getTutorNum() {
        return tutorNum;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
    

    public ArrayList<Object> getRecord() {
        return record;
    }
   
                 
    public int getAccess() {
        return access;
    }


    public String getUser() {
        return user;
    }

    public Connection getConn() {
        return conn;
    }


    public void selectTutor(int tutorNum, String fname, String lname, String tutorStr) {
      try {
		
    	  connect = new JDBC_Connect();
			conn = connect.connect();
			

			sql = "SELECT TUTOR.TUTORNUM, TUTOR.F_NAME, TUTOR.L_NAME FROM TUTOR, REGISTERED_USER_TABLE USER WHERE (USER.ACCESS =?) AND (TUTOR.USERNAME = USER.USERNAME)";
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, access);
			
			rs = pst.executeQuery();                  
                      
                      
                    
                      java.sql.ResultSetMetaData metaData = rs.getMetaData();
                            int columns = metaData.getColumnCount();
                            
                      for (int i = 0; i <= columns; i++) { 
                        while (rs.next()) { // retrieve data
                            tutorNum = rs.getInt("TUTORNUM");
                            fname = rs.getString("F_NAME");
                            lname = rs.getString("L_NAME");
                    
                        tutorStr = tutorNum + " " + fname + " " + lname; 
                           record.add(tutorStr);
                        }                           
      
                    }
		} catch (SQLException e) {
                    // Add catch
                }
                 System.out.println("Line 95: Tutor Array" + " " + record);
            }
    
    public String tutorStr(String tutorStr){
        // Converting ArrayList to String using Spring API , result is comma separated String      
        
        for (Object element : record) {
        
        String temp = element.toString(); 
        
          String substring = temp.substring(1, temp.length()-1); 
        String temp2 = element.toString(); 
           String substring2 = temp2.substring(1, temp.length()-1);
        System.out.println("Tutor Names Line 109" + " " + substring + " " + substring2); 
        
       tutorStr =  (substring + " " + substring2);
       System.out.println("Tutor Names Line 112" + " " + tutorStr); 
       
        }
        return tutorStr; 
    }

    

    public String getTutorStr() {
        return tutorStr;
    }

  
}
