package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import backend.JDBC_Connect;

import src.ManualLogin;
import src.Register;
import src.SelectionPage;
import src.Session_Log;
import admin.Add_Tutor;
public class SqlQuery_admin {

	private JDBC_Connect connect = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private Connection conn = null;
	private String sql = null;
	private int numVar = 0;
	private int count = 0;
	private String user, UIN, fname,lname, tutorStr, email = null;
	private String pass;
	private int access = 0; 
	ArrayList<Object> record = new ArrayList<Object>();
    
	private boolean exists;
	
	public SqlQuery_admin() {
		// Default Constructor
	
	}
	// Verification of the login username and password using imported sql
	public void doesStudentExist(boolean b, String uin2) {
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			
			sql = new String ("SELECT * FROM REGISTERED_USER_TABLE WHERE UIN =?");
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, uin2);
			
			rs = pst.executeQuery();
		     
                java.sql.ResultSetMetaData metaData = rs.getMetaData();
                      int columns = metaData.getColumnCount();
                      
                for (int i = 0; i <= columns; i++) { 
                  while (rs.next()) { // retrieve data
                      uin2 = rs.getString("UIN");
                      fname = rs.getString("F_NAME");
                      lname = rs.getString("L_NAME");
                      email = rs.getString("EMAIL"); 
                      
              
                  tutorStr = uin2 + " " + fname + " " + lname + " " + email; 
                     record.add(tutorStr);
                  }     	
			}
                 
			if (count == 1) {// If Resultset returns one row
				exists = true; 
				System.out.println("Student Exists");
				selection(uin2); 
				
			}else{
				exists = false; 
				System.out.println("Student does not Exist");
				
		    }
			
			if (count > 1) { // If resultset returns more then one row
				System.out.println("Duplicate entries!");
				}
			
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
	// Does select statement using imported sql
		public int selection(String UIN) { 
			try {
				connect = new JDBC_Connect();
				conn = connect.connect(); 
				pst = conn.prepareStatement(sql);
				sql = new String ("SELECT * FROM REGISTERED_USER_TABLE WHERE USERNAME =?");
				// Possible Switch Case
					pst.setString(1, UIN);
					
				
				 /*else if (getNumVar() == 3) { pst.setString(1, arg1);
				  pst.setString(2, arg1); pst.setString(3, arg1); } //END HERE
				  switch(getNumVar()){ }*/
				 

				rs = pst.executeQuery();

				int count = 0;
				
				
				while (rs.next()) { // retrieve data
					UIN = rs.getString("UIN");
					fname = rs.getString("fname");
					lname = rs.getString("lname");
					user = rs.getString("uname"); 
					pass = rs.getString("password");
					email = rs.getString(email); 
					count++;
				}
				if (count == 1) {
					System.out.println(getFname());
					JOptionPane.showMessageDialog(null, "Student Found!\n"
							+ getFname() + " " + getLname() + "\n" + "StudentID: "
							+ getUIN() + "\nPassword: " + getPassword()
							+ "\n" + "Date: " + getDate() + "\nTime: " + getTime()
							+ "");
					// NEED TO CREATE METHOD TO SAVE TO LOG FILE
				} else if (count > 1) {
					System.out.println("Multiple Students found!");
					System.out.println("Number of Records Found: " + count);
					// make above while statement store each into array named
					// student then create a panel with a list in it of all the
					// student
					// names (For use with multi-student query)
				} else {
					System.out.println("No Results Found!");
					JOptionPane.showMessageDialog(null, "No Results Found!");
				}
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
				
				setUIN(UIN);
				setFname(fname);
				setLname(lname);
				setUser(user); 
				setEmail(email);
				setPass(pass);
				//setCount(0);
			}
			return count;
		}
	private String getTime() {
			// TODO Auto-generated method stub
			return null;
		}
	private String getDate() {
			// TODO Auto-generated method stub
			return null;
		}
	private String getPassword() {
			// TODO Auto-generated method stub
			return null;
		}
	private String getUIN() {
			// TODO Auto-generated method stub
			return null;
		}
	private String getLname() {
			// TODO Auto-generated method stub
			return null;
		}
	private char[] getFname() {
			// TODO Auto-generated method stub
			return null;
		}
	public void insertStudent() {

			try {
				connect = new JDBC_Connect();
				conn = connect.connect();
				System.out.println(UIN + " " + fname +" " + lname +" " + user+" " + pass +" " + email); 
				String sql = new String(
						"INSERT INTO REGISTERED_USER_TABLE(UIN, F_NAME, L_NAME, USERNAME, PASSWORD, EMAIL, ACCESS) VALUES(?, ?, ?, ?, ?,?,?)");
				pst = conn.prepareStatement(sql);
				pst.setString(1, UIN);
				pst.setString(2, fname);
				pst.setString(3, lname);
				pst.setString(4, user);
				pst.setString(5, pass);
				pst.setString(6, email);
				pst.setInt(7, 2); 
				
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
	public void removeStudent() {

		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			System.out.println(UIN + " " + fname +" " + lname +" " + user+" " + pass +" " + email); 
			String sql = new String("DELETE FROM REGISTERED_USER_TABLE WHERE UIN = ?");
			pst = conn.prepareStatement(sql);
			pst.setString(1, UIN);
					
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
	public void setUser(String user) {
		this.user = user;
	}
	public void setUIN(String uIN) {
		UIN = uIN;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAccess(int access) {
		this.access = access;
	}
	public void setExists(boolean exists) {
		this.exists = exists;
	}
	
}