package src;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import backend.JDBC_Connect;



public class SelectionPage extends JFrame implements ActionListener,
		WindowListener {

	/**
	 * 
	 */
	// Connection var..
	private JDBC_Connect connect = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private Connection conn = null;
	private static final long serialVersionUID = 1L;
	private String selection = "";
	private JToggleButton computerTB;
	private JToggleButton tutorTB;
	private JToggleButton studyTB;

	private JButton submitBtn;// add to southPane
	private JButton cancelBtn;// add to southPane

	private JCheckBox internet;
	private JCheckBox printScan;
	private JCheckBox microOffice;
	private JCheckBox testing;
	private JCheckBox studying;
	private JCheckBox tutor;

	Border line = BorderFactory.createLineBorder(Color.BLACK);

	private JPanel TBwestPane;
	private JPanel TBeastPane;
	private JPanel TBcenterPane;
	private JPanel LwestPane;
	private JPanel LeastPane;
	private JPanel LcenterPane;

	// private JButton tutorsB;
	
	private String selectUIN;
	ArrayList<Object> tutorArr;
	private String tutorName;
	private ArrayList<Object> testArr;
	private String testName;
	@SuppressWarnings("rawtypes")
	public SelectionPage(String UIN) {
		System.out.println("Line 57 " + UIN);
		this.selectUIN = UIN;
		
		setExtendedState(Frame.MAXIMIZED_BOTH);

		// setSize(fullScreenWidth(), fullScreenHeight());
		setResizable(false);
		setUndecorated(true);
		/*
		 * try {
		 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 * } catch (ClassNotFoundException | InstantiationException |
		 * IllegalAccessException | UnsupportedLookAndFeelException e) {
		 * e.printStackTrace(); }// Sets to computer's default look and feel
		 */

		getContentPane().setBackground(
				new Color(Integer.parseInt("0xAAD3E2".substring(2), 16)));
		// Above Sets to Baker Blue
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		URL headerLogo = SelectionPage.class.getResource("/Resources/LSS_Welcome.png");
		URL unchecked = SelectionPage.class
				.getResource("/Resources/RedCheck_Unchecked.png");
		URL checked = SelectionPage.class.getResource("/Resources/RedCheck_Checked.png");

		Image header = null;
		Image uncheck = null;
		Image check = null;
		try {
			header = ImageIO.read(headerLogo).getScaledInstance(
					fullScreenWidth(), prefHeight() * 4, Image.SCALE_DEFAULT);
			// fullScreenHeight()
			check = ImageIO.read(checked).getScaledInstance(
					fullScreenWidth() / 400 * 25,
					fullScreenHeight() / 300 * 20, Image.SCALE_DEFAULT);
			uncheck = ImageIO.read(unchecked).getScaledInstance(
					fullScreenWidth() / 400 * 25,
					fullScreenHeight() / 300 * 20, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JPanel northPane = new JPanel(new FlowLayout());
		northPane.setPreferredSize(new Dimension(getWidth(), prefHeight() * 4));
		northPane.setBorder(line);
		northPane.setOpaque(false);

		JPanel southPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		southPane.setOpaque(false);

		// TOGGLE BUTTON PANELS
		TBwestPane = new JPanel(new GridBagLayout());
		TBwestPane.setOpaque(false);

		TBeastPane = new JPanel(new GridBagLayout());
		TBeastPane.setOpaque(false);

		TBcenterPane = new JPanel(new GridBagLayout());
		TBcenterPane.setOpaque(false);

		// LIST PANELS
		LwestPane = new JPanel(new GridBagLayout());
		LwestPane.setOpaque(false);
		LwestPane.setBorder(line);

		LeastPane = new JPanel(new GridBagLayout());
		LeastPane.setOpaque(false);
		LeastPane.setBorder(line);

		LcenterPane = new JPanel(new GridBagLayout());
		LcenterPane.setOpaque(false);
		LcenterPane.setBorder(line);

		// SUPER PANELS ADDED
		JPanel westPane = superPane(TBwestPane, LwestPane);
		westPane.setOpaque(false);
		JPanel centerPane = superPane(TBcenterPane, LcenterPane);
		centerPane.setOpaque(false);
		JPanel eastPane = superPane(TBeastPane, LeastPane);
		eastPane.setOpaque(false);

		westPane.setPreferredSize(new Dimension(prefWidth(), prefHeight() * 8));
		eastPane.setPreferredSize(new Dimension(prefWidth(), prefHeight() * 8));
		centerPane
				.setPreferredSize(new Dimension(prefWidth(), prefHeight() * 8));

		// LABELS
		JLabel headerL = new JLabel(new ImageIcon(header));

		JLabel invisibleLabel = new JLabel("");
		invisibleLabel.setFocusable(false);
		invisibleLabel.setBorder(line);
		invisibleLabel.setPreferredSize(new Dimension(fullScreenWidth()
				- (getButtonWidth() * 2), getButtonHeight()));

		JLabel selectL1 = new JLabel("Select A Tutor");
		selectL1.setEnabled(false);
		selectL1.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		selectL1.setFocusable(false);

		JLabel selectL2 = new JLabel("From The Menu Below");
		selectL2.setEnabled(false);
		selectL2.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		selectL2.setFocusable(false);
		// testing
		JLabel selectL3 = new JLabel("Course" + " " + "Time" + " " + "Fee");
		selectL3.setEnabled(false);
		selectL3.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		selectL3.setFocusable(false);
		// CHECKBOXES
		internet = new JCheckBox("Internet", false);
		internet.setFocusable(false);
		internet.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		internet.setPreferredSize(new Dimension(getButtonWidth(),
				getButtonHeight()));
		internet.setOpaque(false);
		internet.setIcon(new ImageIcon(uncheck));
		internet.setSelectedIcon(new ImageIcon(check));
		internet.setEnabled(false);
		internet.addActionListener(this);

		printScan = new JCheckBox("Printing/Scanning", false);
		printScan.setFocusable(false);
		printScan.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		printScan.setOpaque(false);
		printScan.setIcon(new ImageIcon(uncheck));
		printScan.setSelectedIcon(new ImageIcon(check));
		printScan.setEnabled(false);
		printScan.addActionListener(this);

		microOffice = new JCheckBox("Word, Excel, Access, and Powerpoint",
				false);
		microOffice.setFocusable(false);
		microOffice
				.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		microOffice.setOpaque(false);
		microOffice.setIcon(new ImageIcon(uncheck));
		microOffice.setSelectedIcon(new ImageIcon(check));
		microOffice.setEnabled(false);
		microOffice.addActionListener(this);

		testing = new JCheckBox("Testing", false);
		testing.setFocusable(false);
		testing.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		testing.setPreferredSize(new Dimension(getButtonWidth(),
				getButtonHeight()));
		testing.setOpaque(false);
		testing.setIcon(new ImageIcon(uncheck));
		testing.setSelectedIcon(new ImageIcon(check));
		testing.setEnabled(false);
		testing.addActionListener(this);

		studying = new JCheckBox("Studying", false);
		studying.setFocusable(false);
		studying.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		studying.setOpaque(false);
		studying.setIcon(new ImageIcon(uncheck));
		studying.setSelectedIcon(new ImageIcon(check));
		studying.setEnabled(false);
		studying.addActionListener(this);

		tutor = new JCheckBox("Tutor", false);
		tutor.setFocusable(false);
		tutor.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		tutor.setOpaque(false);
		tutor.setIcon(new ImageIcon(uncheck));
		tutor.setSelectedIcon(new ImageIcon(check));
		tutor.setEnabled(false);
		tutor.addActionListener(this);
		tutor.setVisible(false); 
		/*
		 * final JCheckBox placeHolder = new JCheckBox("", false);
		 * microOffice.setFocusable(false); microOffice .setFont(new
		 * Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		 * microOffice.setOpaque(false); microOffice.setIcon(new
		 * ImageIcon(uncheck)); microOffice.setSelectedIcon(new
		 * ImageIcon(check)); microOffice.setEnabled(false);
		 * microOffice.setText("Word, Excel, Access, and Powerpoint");
		 * microOffice.addActionListener(this);
		 */

		// BUTTONS
		computerTB = new JToggleButton("Computer");
		computerTB.setSelected(false);
		computerTB.setFocusable(false);
		computerTB.setBorder(line);
		computerTB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		computerTB.setPreferredSize(new Dimension(getButtonWidth(),
				getButtonHeight()));
		computerTB.setRolloverEnabled(false);
		computerTB.addActionListener(this);

		studyTB = new JToggleButton("Testing/Studying");
		studyTB.setSelected(false);
		studyTB.setFocusable(false);
		studyTB.setBorder(line);
		studyTB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		studyTB.setPreferredSize(new Dimension(fullScreenWidth()
				- (getButtonWidth() * 2) - 2, getButtonHeight()));
		studyTB.setRolloverEnabled(false);
		studyTB.addActionListener(this);

		tutorTB = new JToggleButton("Tutor");
		tutorTB.setSelected(false);
		tutorTB.setFocusable(false);
		tutorTB.setBorder(line);
		tutorTB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		tutorTB.setPreferredSize(new Dimension(getButtonWidth(),
				getButtonHeight()));
		tutorTB.setRolloverEnabled(false);
		tutorTB.addActionListener(this);

		// Tutor Populating Button
		/*
		 * tutorsB = new JButton("Press Here"); tutorsB.setFont(new
		 * Font("Sans Bold", Font.BOLD, getFontSize()));
		 * tutorsB.setEnabled(false); tutorsB.setFocusable(false);
		 * //tutorsB.setMaximumSize(new Dimension(getButtonWidth()/7*3,
		 * getButtonWidth()/7*3)); //tutorsB.setMinimumSize(new
		 * Dimension(getButtonWidth()/7*3, getButtonWidth()/7*3));
		 * tutorsB.setPreferredSize(new Dimension(getButtonWidth()/7*3,
		 * getButtonWidth()/7*2)); tutorsB.setRolloverEnabled(false);
		 * tutorsB.addActionListener(this);
		 */
		Tutor tutors = new Tutor(); //creating the instance of the class
	    	tutorArr = new ArrayList<Object>();
	    	tutorArr = tutors.getRecord();

		
		System.out.println("Line 299 - SelectionPage_admin " + " " + tutorArr); 
		 final JComboBox<Object> tutorCB = new JComboBox<Object>(tutorArr.toArray()); 	
		tutorCB.setEnabled(false);
		tutorCB.setPreferredSize(new Dimension(getButtonWidth() / 7 * 6,
		getButtonWidth() / 7));
		tutorCB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		tutorCB.setFocusable(false);
		tutorCB.addActionListener(this);
		
		tutorCB.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    		
			
					tutorName = (String)tutorCB.getSelectedItem();
					selection = tutorName;
					
					System.out.println("Line 453: " + selection + " button pressed " + selectUIN);
					selectTutor(selectUIN, selection);
		    }
		});
		// Get Waiver array
		Testing test = new Testing(); //creating the instance of the class
		testArr = new ArrayList<Object>(); 
		testArr = test.getWaiverArr();
	   		 
	    new javax.swing.JComboBox();       		
			    //create JComboBox and assign it to the comboBox
			    final JComboBox<Object> testCB = new JComboBox<Object>(testArr.toArray());    
				testCB.setEnabled(false);
				testCB.setPreferredSize(new Dimension(getButtonWidth() / 7 * 6,
				getButtonWidth() / 7));
				testCB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
				testCB.setFocusable(false);
				testCB.addActionListener(this);
				
				testCB.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				    	
							testName = (String)testCB.getSelectedItem();
							selection = testName;
							
							System.out.println("Line 453: " + selection + " button pressed " + selectUIN);
							selectTesting(selectUIN, selection);
						
				    }
				});		
				
		submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		submitBtn.setFocusable(false);
		submitBtn.setBorder(line);
		submitBtn.setPreferredSize(new Dimension(getButtonWidth(), getButtonHeight()));
		submitBtn.setRolloverEnabled(false);
		submitBtn.addActionListener(this);

		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		cancelBtn.setFocusable(false);
		cancelBtn.setBorder(line);
		cancelBtn.setPreferredSize(new Dimension(getButtonWidth(), getButtonHeight()));
		cancelBtn.setRolloverEnabled(false);
		cancelBtn.addActionListener(this);

		// ADDING EACH COMPONENT WITH CONSTRAINTS

		GridBagConstraints c = new GridBagConstraints();

		// NORTH PANEL COMPONENTS CONSTRAINTS
		northPane.add(headerL);

		// WEST PANEL COMPONENTS CONSTRAINTS

		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		TBwestPane.add(computerTB, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(getButtonHeight() / 2, 0, getButtonHeight() / 2,
				0);
		LwestPane.add(internet, c);

		c.gridx = 0;
		c.gridy = 1;
		LwestPane.add(printScan, c);

		c.gridx = 0;
		c.gridy = 2;
		LwestPane.add(microOffice, c);

		// CENTER PANEL COMPONENTS CONSTRAINTS
		c.gridy = 0;
		LcenterPane.add(selectL3, c);
		
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		TBcenterPane.add(studyTB, c);

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(getButtonHeight() / 2, 0, getButtonHeight() / 2,
				0);
		LcenterPane.add(testCB, c);
			
		c.gridy = 2;
		LcenterPane.add(studying, c);

		// EAST PANEL COMPONENTS CONSTRAINTS

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		TBeastPane.add(tutorTB, c);

		c.insets = new Insets(-(getButtonHeight() * 3), 0, -getButtonHeight(),
				0);
		c.anchor = GridBagConstraints.CENTER;
		LeastPane.add(selectL1, c);

		c.gridy = 1;
		c.insets = new Insets(-getButtonHeight() / 5 * 3, 0, getButtonHeight(),
				0);
		LeastPane.add(selectL2, c);
		/*
		 * c.insets = new Insets(-(getButtonHeight()*2), 0, getButtonHeight(),
		 * 0); c.anchor = GridBagConstraints.CENTER; LeastPane.add(area, c);
		 */	
		
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		LeastPane.add(tutorCB, c);
		
		// LeastPane.add(tutorsB, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridy = 3;
		LeastPane.add(tutor, c);
		
		// SOUTH PANEL COMPONENTS ADDED

		southPane.add(submitBtn);
		southPane.add(invisibleLabel);
		southPane.add(cancelBtn);

		/*
		 * Adding panels to frame
		 */

		add(northPane, BorderLayout.NORTH);
		add(southPane, BorderLayout.SOUTH);
		add(westPane, BorderLayout.WEST);
		add(centerPane, BorderLayout.CENTER);
		add(eastPane, BorderLayout.EAST);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == computerTB) {

			if (computerTB.isSelected()) {
				enableComponents(LwestPane, true);

			} else {
				enableComponents(LwestPane, false);
			}
		}
		
		if (source == studyTB) {
			if (studyTB.isSelected()) {
				enableComponents(LcenterPane, true);
				

			} else {
				enableComponents(LcenterPane, false);
			}
		}
		if (source == tutorTB) {
			if (tutorTB.isSelected()) {
				enableComponents(LeastPane, true);
				
			} else {
				enableComponents(LeastPane, false);
			}
			
		}
			if (source == submitBtn) { // Submit button
	
				if (internet.isSelected()) {
					selection = "Internet";
					System.out.println("Line 421: " + selection + " button pressed " + selectUIN);
					selectInternet(selectUIN, selection);
					}
				if (printScan.isSelected()) {
					selection = "PrintScan";
					System.out.println("Line 426: " + selection + " button pressed " + selectUIN);
					selectprintScan(selectUIN, selection);
					
				}
				if (microOffice.isSelected()) {
					selection = "MSOffice";
					System.out.println("Line 432: " + selection + " button pressed " + selectUIN);
					selectMSOffice(selectUIN, selection);
					
				}
				

				if (studying.isSelected()) {
					selection = "Studying";
					System.out.println("Line 447: " + selection + " button pressed " + selectUIN);
					selectStudying(selectUIN, selection);
						
				}

			
				if (source != null){
					new CardLogin();
					this.dispose();
				}else{
						JOptionPane.showMessageDialog(null,
						"	Please make a selection ");
						}	
				// Add to Log
				PrintLog printLog = new PrintLog();
				printLog.PrinttoLog();
				
			}
				if (source == cancelBtn) { // Cancel button
					selection = "";
					this.dispose(); 
				}
	}
	
	// Print to Database session active table
	private void selectInternet(String intrUIN, String intrSelection) {
		
		try {
			
			connect = new JDBC_Connect();
			conn = connect.connect();
			String sql = "UPDATE SESSION_ACTIVE ACT, REGISTERED_USER_TABLE USER SET INTERNET = (?) WHERE ACT.UIN = USER.UIN AND USER.UIN = (?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, intrSelection);
			pst.setString(2, intrUIN);
			pst.executeUpdate();
			}catch (SQLException e_internet) {
				e_internet.printStackTrace();
			} finally {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e_internet) {
						e_internet.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e_internet) {
						e_internet.printStackTrace();
					}
				}

			}
	}

	private void selectprintScan(String psUIN, String psSelection) {
		try {
			
			connect = new JDBC_Connect();
			conn = connect.connect();
			String sql = "UPDATE SESSION_ACTIVE ACT, REGISTERED_USER_TABLE USER SET PRINTSCAN = (?) WHERE ACT.UIN = USER.UIN AND USER.UIN = (?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, selection);
			pst.setString(2, psUIN);
			pst.executeUpdate();
			}catch (SQLException e_printscan) {
				e_printscan.printStackTrace();
			} finally {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e_printscan) {
						e_printscan.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e_printscan) {
						e_printscan.printStackTrace();
					}
				}

			}
	}
	private void selectMSOffice(String msUIN, String msSelection) {
		try {
			
			connect = new JDBC_Connect();
			conn = connect.connect();
			String sql = "UPDATE SESSION_ACTIVE ACT, REGISTERED_USER_TABLE USER SET MSOFFICE = (?) WHERE ACT.UIN = USER.UIN AND USER.UIN = (?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, msSelection);
			pst.setString(2, msUIN);
			pst.executeUpdate();
			}catch (SQLException e_msoffice) {
				e_msoffice.printStackTrace();
			} finally {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e_msoffice) {
						e_msoffice.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e_msoffice) {
						e_msoffice.printStackTrace();
					}
				}

			}
		
	}
	
	private void selectTesting(String testUIN, String testSelection) {
		try {
			
			connect = new JDBC_Connect();
			conn = connect.connect();
			String sql = "UPDATE SESSION_ACTIVE ACT, REGISTERED_USER_TABLE USER SET TESTING = (?) WHERE ACT.UIN = USER.UIN AND USER.UIN = (?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, testSelection);
			pst.setString(2, testUIN);
			pst.executeUpdate();
			}catch (SQLException e_testing) {
				e_testing.printStackTrace();
			} finally {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e_testing) {
						e_testing.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e_testing) {
						e_testing.printStackTrace();
					}
				}

			}
		
	}

	private void selectStudying(String studUIN, String studSelection) {
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			String sql = "UPDATE SESSION_ACTIVE ACT, REGISTERED_USER_TABLE USER SET STUDYING = (?) WHERE ACT.UIN = USER.UIN AND USER.UIN = (?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, studSelection);
			pst.setString(2, studUIN);
			pst.executeUpdate();
			}catch (SQLException e_studying) {
				e_studying.printStackTrace();
			} finally {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e_studying) {
						e_studying.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e_studying) {
						e_studying.printStackTrace();
					}
				}

			}
		
	}

	private void selectTutor(String tutorUIN, String tutSelection) {
		try {
			connect = new JDBC_Connect();
			conn = connect.connect();
			String sql = "UPDATE SESSION_ACTIVE ACT, REGISTERED_USER_TABLE USER SET TUTOR = (?) WHERE ACT.UIN = USER.UIN AND USER.UIN = (?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, tutSelection);
			pst.setString(2, tutorUIN);
			pst.executeUpdate();
			}catch (SQLException e_studying) {
				e_studying.printStackTrace();
			} finally {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e_studying) {
						e_studying.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e_studying) {
						e_studying.printStackTrace();
					}
				}

			}
		
	}
		
	public int fullScreenWidth() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		return (int) d.getWidth();
	}

	public int fullScreenHeight() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		return (int) d.getHeight();
	}

	public int prefWidth() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		double p = d.getWidth() / 3;

		int w = (int) Math.round(p);

		return w;
	}

	public int prefHeight() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		double p = d.getHeight() / 21;

		int w = (int) Math.round(p);

		return w;
	}

	public JPanel superPane(JPanel n, JPanel c) {

		JPanel sp = new JPanel(new BorderLayout());

		sp.add(n, BorderLayout.NORTH);
		sp.add(c, BorderLayout.CENTER);

		return sp;

	}

	public void enableComponents(Container container, boolean enable) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			component.setEnabled(enable);
			if (component instanceof Container) {
				enableComponents((Container) component, enable);
			}
		}
	}

	public int getFontSize() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		int s = (int) d.getHeight() / 30;

		return s;
	}

	public int getButtonWidth() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		int w = (int) d.getWidth() / 3;

		return w;
	}

	public int getButtonHeight() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		int h = (int) d.getHeight() / 8;

		return h;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		setFocusableWindowState(true);

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		setFocusableWindowState(false);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		setFocusableWindowState(false);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		setFocusableWindowState(false);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		setFocusableWindowState(true);
	}

	public void pause() {
		System.out.println("Pausing...");
		// Pause for 4 seconds
		try {
			Thread.sleep(4000);
			dispose();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

/* Get Report 
public void insertReport(String UIN, String internet, String printScan, String msOffice, String testing, String studying, String tutor){ 
	try {
		
		connect = new JDBC_Connect();
		conn = connect.connect();
		String sql = new String(
				"UPDATE REPORT SET (selectUIN, INTERNET, PRINTSCAN, MSOFFICE, TESTING, STUDYING, TUTOR) VALUES(?, ?, ?, ?, ?,?,?) WHERE ACT.ACT_SESSIONID = REPORTID");
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

public String getUIN() {
	return selectUIN;
}


}
