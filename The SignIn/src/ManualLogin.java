package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;

import backend.SqlQuery;

public class ManualLogin extends JFrame implements ActionListener,
		WindowListener {
	/**
	 * 
	 */

	JButton sB;
	JButton cB;
	JButton rB;
	JButton clB;
	public JTextField passTF;
	public JTextField userTF;
	SqlQuery query = null; 
	private static final long serialVersionUID = 1L;
    // MenuBar
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
    
	public ManualLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		/*
		 * try {
		 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 * } catch (ClassNotFoundException | InstantiationException |
		 * IllegalAccessException | UnsupportedLookAndFeelException e) {
		 * e.printStackTrace(); }//Sets to computer's default look and feel
		 */

		// setSize(fullScreenWidth(), fullScreenHeight());

		setExtendedState(Frame.MAXIMIZED_BOTH);

		// getContentPane().setBackground(Color.GREEN.darker());

		getContentPane().setBackground(
				new Color(Integer.parseInt("0xAAD3E2".substring(2), 16))); // Sets
																			// to
																			// Baker
																			// Blue

		setLayout(new BorderLayout());

		URL background = ManualLogin.class
				.getResource("/Resources/HighResManualLoginScreen.png");

		Image bg = null;
		try {
			bg = ImageIO.read(background).getScaledInstance(fullScreenWidth(),
					fullScreenHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		JLabel picture = new JLabel(new ImageIcon(bg));

		add(picture);

		picture.setLayout(new BorderLayout());

		JLabel userL = new JLabel("Username: ");
		userL.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() * 4 / 3));

		JLabel passL = new JLabel("Password: ");
		passL.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() * 4 / 3));

		userTF = new JTextField();
		userTF.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		userTF.setPreferredSize(new Dimension(getButtonWidth(),
				getButtonHeight() / 3));

		passTF = new JPasswordField();
		passTF.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		passTF.setPreferredSize(new Dimension(getButtonWidth(),
				getButtonHeight() / 3));

		sB = new JButton("Submit");
		sB.setPreferredSize(new Dimension(getButtonWidth(), getButtonHeight()));
		sB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		sB.setFocusable(false);
		sB.setRolloverEnabled(false);
		sB.addActionListener(this);

		/*
		 * cB = new JButton("Clear"); cB.setFocusable(false);
		 * cB.setPreferredSize(new Dimension(fullScreenWidth(),
		 * getButtonHeight()/2)); cB.setFont(new Font("Sans Bold", Font.BOLD,
		 * getFontSize())); cB.setRolloverEnabled(false); //
		 * cB.setContentAreaFilled(false); // cB.setBackground(Color.BLUE); //
		 * cB.setBorderPainted(false); cB.addActionListener(this);
		 */

		rB = new JButton("Register");
		rB.setFocusable(false);
		rB.setPreferredSize(new Dimension(getButtonWidth(), getButtonHeight()));
		rB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		rB.setRolloverEnabled(false);
		rB.addActionListener(this);

		clB = new JButton("Card Swipe Login");
		clB.setFocusable(false);
		clB.setPreferredSize(new Dimension(getButtonWidth(), getButtonHeight()));
		clB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		clB.setRolloverEnabled(false);
		// clB.setOpaque(false);
		// clB.setContentAreaFilled(false);
		// clB.setBorderPainted(false);
		clB.addActionListener(this);

		JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

		pane.add(sB);

		pane.add(rB);

		pane.add(clB);

		// pane.add(cB);

		JPanel pane2 = new JPanel(new GridBagLayout());

		pane2.setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(fullScreenHeight() / 200 * 20, 0, 0, 0);
		pane2.add(userL, c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		pane2.add(passL, c);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(fullScreenHeight() / 200 * 20, 0, 5,
				getButtonWidth() / 7);
		pane2.add(userTF, c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 5, getButtonWidth() / 7);
		pane2.add(passTF, c);

		picture.add(pane, BorderLayout.SOUTH);
		picture.add(pane2, BorderLayout.EAST);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == sB) {
			String user = userTF.getText().trim();
			String pass = passTF.getText().trim();
			
			query = new SqlQuery();
			query.setUser(user);
			query.setPass(pass);			
			query.ManualVerification();
			dispose(); 
		}
		if (source == clB) {
			new CardLogin();
			dispose();
		}
		if (source == cB) {
			System.exit(0);
		}
		if (source == rB) {
			new CardSwipeRequest();	
			dispose();
		}
		
	}
	/*private void Validation(){
		int numVar = 1;
		query = new SqlQuery_admin(); 
		query.setNumVar(numVar);
		query.ManualVerification(); 
		
		while (query.ManualVerification() == true && numVar == 1){ 
		
		// Validate if student is logged in already
			if (query.isFlaggedLoggedIn() == 1){ // Student is active and will be logged out
				numVar=0;
				new CardLogin(); 
				dispose();
			}
			if (query.isFlaggedLoggedIn() == 0){ // Student is not currently active
				numVar=0;
			new SelectionPage_admin(); 
			dispose();
		}
		
		}
		
		// Manual validation is not true
		if(query.ManualVerification()== false){
			int mc = JOptionPane.WARNING_MESSAGE;
		JOptionPane.showMessageDialog (null, "No Student found!  Please Register.", "Register", mc);
		new CardLogin(); 
		dispose();
		}
	} */
	public int fullScreenWidth() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		return (int) d.getWidth();
	}

	public int fullScreenHeight() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		return (int) d.getHeight();
	}

	public int getButtonWidth() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		int w = (int) d.getWidth() / 3;

		return w;
	}

	public int getButtonHeight() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		int h = (int) d.getHeight() / 7;

		return h;
	}

	public int getFontSize() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		int s = (int) d.getHeight() / 30;

		return s;
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

}
