package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import src.ManualLogin;
import src.Register;
import src.SelectionPage;
import src.Session_Log;
import admin.SelectionPage_admin;
 
public class SqlQuery {

	private JDBC_Connect connect = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;

	private Connection conn = null;

	private String user = null;
	private String pass = null;
	private String sql = null;
	private int numVar = 0;

	private int count = 0;
	private String fname = null;
	private String lname = null;
	private String password = null;
	private String email = null; 
	private String date = null;
	private String time = null;
	private int access = 0; 
	private boolean reset = false;
	private String UIN;
    private String internet,testing, tutor, studying, msOffice, printScan;
	public SqlQuery() {
		// Default Constructor
	
	}
	// Verification of the login username and password using imported sql
	public boolean ManualVerification() {
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			
			sql = new String ("SELECT * FROM REGISTERED_USER_TABLE WHERE USERNAME =? AND PASSWORD =?");
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, user);
			pst.setString(2, pass);
			// Execute query within ManualLogin line 194
			rs = pst.executeQuery();

			while (rs.next()) { // retrieve data
				count = count + 1;
				
				UIN = rs.getString("UIN");
				fname = rs.getString("F_NAME");
				lname = rs.getString("L_NAME");
				user = rs.getString("USERNAME"); 
				password = rs.getString("PASSWORD");
				email = rs.getString("EMAIL");
				access = rs.getInt("ACCESS");
			}
                        System.out.println("UIN" + " " + UIN + " " + "Fname" + " " + fname + "lname" + lname );
			if (count == 1) {// If Resultset returns one row
				System.out.println("Login Successful! Access Granted!");
				isFlaggedLoggedIn();
			
					if (access == 9) {//if admin user
						
						System.out.println("Admin Successful! Access Granted!");
                                    SelectionPage_admin admin = new SelectionPage_admin(UIN); 
                                    admin.setVisible(true);
						 }
					else{
					 new SelectionPage(UIN); 				
					 //new CardLogin();
				  
					
				//JOptionPane.showMessageDialog(null,
				//		"Login Successful! Access Granted to: " + fname + " "
				//				+ lname + "!");

				reset = true;
				//	insertReport(UIN); 
				// SET VARIABLES FOR makeReport() THEN CALL FUNCTION!!
					}
			}
				
			else if (count > 1) { // If resultset returns more then one row
				System.out.println("Access Denied! Duplicate entries!");
			} else {
				System.out.println("Access Denied! No user Found!");
				ManualLogin m_log = new ManualLogin();	
				
					int mc = JOptionPane.QUESTION_MESSAGE;
						int bc = JOptionPane.YES_NO_CANCEL_OPTION;
						int ch = JOptionPane.showConfirmDialog (null, "No Student Registered.  Would you like to Register?", "New Student?", bc, mc);
						if (ch == JOptionPane.YES_OPTION) {
							System.out.println("Yes");
							m_log.dispose();
							new Register();
							
						} 
						else{
							
							m_log.userTF.setText("");
							m_log.passTF.setText(""); 
							
						}
					
					 
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
			setCount(0);
		}
		
		return reset;
	}
	
	public boolean CardVerification()
	{
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			
			sql = new String ("SELECT * FROM REGISTERED_USER_TABLE WHERE UIN =?");
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, UIN);
			// Execute query within ManualLogin line 194
			rs = pst.executeQuery();

			while (rs.next()) { // retrieve data
				count = count + 1;
				
				UIN = rs.getString("UIN");
				fname = rs.getString("F_NAME");
				lname = rs.getString("L_NAME");
				user = rs.getString("USERNAME"); 
				password = rs.getString("PASSWORD");
				email = rs.getString("EMAIL");
				access = rs.getInt("ACCESS"); 
			}
            System.out.println("UIN" + " " + UIN + " " + "Fname" + " " + fname + "lname" + lname );
			if (count == 1) {// If Resultset returns one row
				System.out.println("Login Successful! Access Granted!");
				isFlaggedLoggedIn();
			
					if (access == 9) {//if admin user
						
						System.out.println("Admin Successful! Access Granted!");
						new SelectionPage_admin(UIN);
						 }
					else{
					 new SelectionPage(UIN); 				
					 //new CardLogin();
				  
					
				//JOptionPane.showMessageDialog(null,
				//		"Login Successful! Access Granted to: " + fname + " "
				//				+ lname + "!");

				reset = true;
				//	insertReport(UIN); 
				// SET VARIABLES FOR makeReport() THEN CALL FUNCTION!!
					}
			}
				
			else if (count > 1) { // If resultset returns more then one row
				System.out.println("Access Denied! Duplicate entries!");
			} else {
				System.out.println("Access Denied! No user Found!");
				ManualLogin m_log = new ManualLogin();	
				
					int mc = JOptionPane.QUESTION_MESSAGE;
						int bc = JOptionPane.YES_NO_CANCEL_OPTION;
						int ch = JOptionPane.showConfirmDialog (null, "No Student Registered.  Would you like to Register?", "New Student?", bc, mc);
						if (ch == JOptionPane.YES_OPTION) {
							System.out.println("Yes");
							m_log.dispose();
							new Register();
							
						} 
						else{
							
							m_log.userTF.setText("");
							m_log.passTF.setText(""); 
							
						}
					
					 
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
			setCount(0);
		}
		
		return reset;
	}

	// Does select statement using imported sql
	public int selection() { 
		try {
			connect = new JDBC_Connect();
			conn = connect.connect(); 
			pst = conn.prepareStatement(sql);
			sql = new String ("SELECT * FROM REGISTERED_USER_TABLE WHERE USERNAME =? AND PASSWORD =?");
			// Possible Switch Case
				pst.setString(1, UIN);
				pst.setString(2, pass); 
			
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
				password = rs.getString("password");
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
			setPassword(pass);
			//setCount(0);
		}
		return count;
	}

	public void insert() {
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			System.out.println(UIN + " " + fname +" " + lname +" " + user+" " + pass +" " + email); 
			String sql = new String(
					"INSERT INTO REGISTERED_USER_TABLE(UIN, F_NAME, L_NAME, USERNAME, PASSWORD, EMAIL) VALUES(?, ?, ?, ?, ?,?)");
			pst = conn.prepareStatement(sql);
			pst.setString(1, UIN);
			pst.setString(2, fname);
			pst.setString(3, lname);
			pst.setString(4, user);
			pst.setString(5, pass);
			pst.setString(6, email);
			
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

	public int isFlaggedLoggedIn() { // Checks to see if student is logged in
				
		try {
			
			sql = new String("Select UIN from SESSION_ACTIVE where UIN = ?"); 					
		
			connect = new JDBC_Connect();
			conn = connect.connect(); 
			pst = conn.prepareStatement(sql);
	        
			// Possible Switch Case
				pst.setString(1, UIN);
				
			/*
			 * else if (getNumVar() == 3) { pst.setString(1, arg1);
			 * pst.setString(2, arg1); pst.setString(3, arg1); } //END HERE
			 * switch(getNumVar()){ }
			 */
	
			rs = pst.executeQuery(); 
			count = 0;
			
			while (rs.next()) { // retrieve data
				UIN = rs.getString("UIN");
				
				count++;
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
		if (count == 0) {// If 0 then student is not active
			System.out.println("Log in Student"); 
			Session_Log session_log = new Session_Log(); 
			System.out.println("SQLQuery1" + " " + UIN); 
			session_log.setUIN(UIN); // Set UIN for Session_log
			setUIN(UIN); 
			session_log.Login(UIN); 
			numVar = 0 ;
			return numVar;
			}	
		if (count == 1) { // If 1 then student is currently active
				int mc = JOptionPane.QUESTION_MESSAGE;
				int bc = JOptionPane.YES_NO_CANCEL_OPTION;
				int ch = JOptionPane.showConfirmDialog (null, "Student is Signed in.  This action will log off the account?", "Log Out?", bc, mc);
				if (ch == JOptionPane.YES_OPTION) {
					System.out.println("Yes");
					System.out.println("Log out Student");
					Session_Log session_log = new Session_Log(); 
					System.out.println("SQLQuery2" + " " + UIN); 
					session_log.setUIN(UIN); // Set UIN for Session_log 
					session_log.Logout(UIN);
					session_log.Delete(UIN);
					numVar = 1;
					return numVar; 
					 
			}
			
			/*if(count == 3) {
					System.out.println("No");
					 return 3; 
					}*/
				
				 
				 
				// Creates new Window of current Program (Switch to creating new
				// login for Administrators)
				// JFrame frame1 = new MainWindow(getFname());
				// frame1.setVisible(true);
			} else if (count > 1) {
				System.out.println("Access Denied! Duplicate entries!");
				
			} else {
				System.out.println("Access Denied! No user Found!");
			}
		
				
			
		return numVar;
	}
	
	public String getLname() {
		System.out.println("L_name" + " " + lname);
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		System.out.println("F_name" + " " + fname);
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}


	public void setCount(int count) {
		this.count = count;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		System.out.println("User" + " " + user);
		this.user = user;
	}

	public String getPass() {
		System.out.println("Password" + " " + pass);
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getNumVar() {
		return numVar;
	}

	public void setNumVar(int numVar) {
		this.numVar = numVar;
	}

	public String getPassword() {
		System.out.println("Password2" + " " + password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getUIN() { 
		System.out.println("studentId" + " " + UIN);
		return UIN;
	}
	public void setUIN(String uIN) {
		UIN = uIN;
	}
	public String getEmail() {
		System.out.println("Email" + " " + email);
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAccess() {
		return access;
	}
	public void setAccess(int access) {
		this.access = access;
	}
	
	
	public String getInternet() {
		System.out.println(internet);
		return internet;
		
	}

	public void setInternet(String internet) {
		this.internet = internet;
	}

	public String getPrintScan() {
		System.out.println(internet);
		return printScan;
	}

	public void setPrintScan(String printScan) {
		this.printScan = printScan;
	}

	public String getMsOffice() {
		System.out.println(msOffice);
		return msOffice;
	}

	public void setMsOffice(String msOffice) {
		this.msOffice = msOffice;
	}

	public String getTesting() {
		System.out.println(testing);
		return testing;
	}

	public void setTesting(String testing) {
		this.testing = testing;
	}

	public String getStudying() {
		System.out.println(studying);
		return studying;
	}

	public void setStudying(String studying) {
		this.studying = studying;
	}

	public String getTutor() {
		System.out.println(tutor);
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
}