package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import backend.JDBC_Connect;
import backend.SqlQuery;


public class Register extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDBC_Connect connect = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private Connection conn = null;
	
	private JTextField uinTF;
	private JTextField fnTF;
	private JTextField lnTF;
	private JTextField unTF;
	private JTextField pwTF;
	private JTextField cpwTF;
	private JTextField eTF;
	private JTextField ceTF;
	
	private JButton cB;

	private JButton scB;

	private JButton smB;

	Border line = BorderFactory.createLineBorder(Color.BLACK);

	public Register() {

		getContentPane().setBackground(
				new Color(Integer.parseInt("0xAAD3E2".substring(2), 16))); // Sets
																			// to
																			// Baker
																			// Blue
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		// setSize(800,800);
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		smB = new JButton("Submit");
		smB.setPreferredSize(new Dimension(300, 150));
		smB.setFont(new Font("Serif", Font.BOLD, 30));
		smB.addActionListener(this);
		cB = new JButton("Cancel");
		cB.setPreferredSize(new Dimension(300, 150));
		cB.setFont(new Font("Serif", Font.BOLD, 30));
		cB.addActionListener(this);

		JLabel l1 = new JLabel("UIN: ");
		l1.setOpaque(false);
		l1.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l2 = new JLabel("First Name: ");
		l2.setOpaque(false);
		l2.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l3 = new JLabel("Last Name: ");
		l3.setOpaque(false);
		l3.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l4 = new JLabel("Username: ");
		l4.setOpaque(false);
		l4.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l5 = new JLabel("Password: ");
		l5.setOpaque(false);
		l5.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l6 = new JLabel("Confirm Password: ");
		l6.setOpaque(false);
		l6.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l7 = new JLabel("E-mail: ");
		l7.setOpaque(false);
		l7.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l8 = new JLabel("Confirm E-mail: ");
		l8.setOpaque(false);
		l8.setFont(new Font("Serif", Font.BOLD, 30));

		uinTF = new JTextField();
		uinTF.setPreferredSize(new Dimension(400, 60));
		uinTF.setFont(new Font("Serif", Font.BOLD, 30));
		uinTF.setOpaque(false);
		uinTF.setBorder(line);
		fnTF = new JTextField();
		fnTF.setPreferredSize(new Dimension(400, 60));
		fnTF.setFont(new Font("Serif", Font.BOLD, 30));
		fnTF.setOpaque(false);
		fnTF.setBorder(line);
		lnTF = new JTextField();
		lnTF.setPreferredSize(new Dimension(400, 60));
		lnTF.setFont(new Font("Serif", Font.BOLD, 30));
		lnTF.setOpaque(false);
		lnTF.setBorder(line);
		unTF = new JTextField();
		unTF.setPreferredSize(new Dimension(400, 60));
		unTF.setFont(new Font("Serif", Font.BOLD, 30));
		unTF.setOpaque(false);
		unTF.setBorder(line);
		pwTF = new JTextField();
		pwTF.setPreferredSize(new Dimension(400, 60));
		pwTF.setFont(new Font("Serif", Font.BOLD, 30));
		pwTF.setOpaque(false);
		pwTF.setBorder(line);
		cpwTF = new JTextField();
		cpwTF.setPreferredSize(new Dimension(400, 60));
		cpwTF.setFont(new Font("Serif", Font.BOLD, 30));
		cpwTF.setOpaque(false);
		cpwTF.setBorder(line);
		eTF = new JTextField();
		eTF.setPreferredSize(new Dimension(400, 60));
		eTF.setFont(new Font("Serif", Font.BOLD, 30));
		eTF.setOpaque(false);
		eTF.setBorder(line);
		ceTF = new JTextField();
		ceTF.setPreferredSize(new Dimension(400, 60));
		ceTF.setFont(new Font("Serif", Font.BOLD, 30));
		ceTF.setOpaque(false);
		ceTF.setBorder(line);

		c.insets = new Insets(10, 10, 10, 10);
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.WEST;

		c.gridx = 0;
		c.gridy = 0;
		add(l1, c);

		c.gridx = 2;
		c.gridy = 0;
		add(uinTF, c);

		c.gridx = 0;
		c.gridy = 1;
		add(l2, c);

		c.gridx = 2;
		c.gridy = 1;
		add(fnTF, c);

		c.gridx = 0;
		c.gridy = 2;
		add(l3, c);

		c.gridx = 2;
		c.gridy = 2;
		add(lnTF, c);

		c.gridx = 0;
		c.gridy = 3;
		add(l4, c);

		c.gridx = 2;
		c.gridy = 3;
		add(unTF, c);

		c.gridx = 0;
		c.gridy = 4;
		add(l5, c);

		c.gridx = 2;
		c.gridy = 4;
		add(pwTF, c);

		c.gridx = 0;
		c.gridy = 5;
		add(l6, c);

		c.gridx = 2;
		c.gridy = 5;
		add(cpwTF, c);

		c.gridx = 0;
		c.gridy = 6;
		add(l7, c);

		c.gridx = 2;
		c.gridy = 6;
		add(eTF, c);

		c.gridx = 0;
		c.gridy = 7;
		add(l8, c);

		c.gridx = 2;
		c.gridy = 7;
		add(ceTF, c);

		c.gridx = 0;
		c.gridy = 8;
		add(smB, c);

		c.gridx = 2;
		c.gridy = 8;
		c.anchor = GridBagConstraints.EAST;
		add(cB, c);

		pack();
		centerWindow(this);
		setVisible(true);
	}

	public Register(int uin, String fname, String lname) {
		getContentPane().setBackground(
				new Color(Integer.parseInt("0xAAD3E2".substring(2), 16))); // Sets
																			// to
																			// Baker
																			// Blue
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		// setSize(800,800);
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		scB = new JButton("Submit");
		scB.setPreferredSize(new Dimension(300, 150));
		scB.setFont(new Font("Serif", Font.BOLD, 30));
		scB.addActionListener(this);
		cB = new JButton("Cancel");
		cB.setPreferredSize(new Dimension(300, 150));
		cB.setFont(new Font("Serif", Font.BOLD, 30));
		cB.addActionListener(this);

		JLabel l1 = new JLabel("UIN: ");
		l1.setOpaque(false);
		l1.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l2 = new JLabel("First Name: ");
		l2.setOpaque(false);
		l2.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l3 = new JLabel("Last Name: ");
		l3.setOpaque(false);
		l3.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l4 = new JLabel("Username: ");
		l4.setOpaque(false);
		l4.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l5 = new JLabel("Password: ");
		l5.setOpaque(false);
		l5.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l6 = new JLabel("Confirm Password: ");
		l6.setOpaque(false);
		l6.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l7 = new JLabel("E-mail: ");
		l7.setOpaque(false);
		l7.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel l8 = new JLabel("Confirm E-mail: ");
		l8.setOpaque(false);
		l8.setFont(new Font("Serif", Font.BOLD, 30));

		uinTF = new JTextField();
		uinTF.setBorder(line);
		uinTF.setPreferredSize(new Dimension(400, 60));
		uinTF.setFont(new Font("Serif", Font.BOLD, 30));
		uinTF.setOpaque(false);
		uinTF.setBorder(line);
		uinTF.setText(Integer.toString(uin));
		uinTF.setEditable(false);
		fnTF = new JTextField();
		fnTF.setPreferredSize(new Dimension(400, 60));
		fnTF.setFont(new Font("Serif", Font.BOLD, 30));
		fnTF.setOpaque(false);
		fnTF.setBorder(line);
		fnTF.setText(fname);
		fnTF.setEditable(false);
		lnTF = new JTextField();
		lnTF.setPreferredSize(new Dimension(400, 60));
		lnTF.setFont(new Font("Serif", Font.BOLD, 30));
		lnTF.setOpaque(false);
		lnTF.setBorder(line);
		lnTF.setText(lname);
		lnTF.setEditable(false);
		unTF = new JTextField();
		unTF.setPreferredSize(new Dimension(400, 60));
		unTF.setFont(new Font("Serif", Font.BOLD, 30));
		unTF.setOpaque(false);
		unTF.setBorder(line);
		pwTF = new JTextField();
		pwTF.setPreferredSize(new Dimension(400, 60));
		pwTF.setFont(new Font("Serif", Font.BOLD, 30));
		pwTF.setOpaque(false);
		pwTF.setBorder(line);
		cpwTF = new JTextField();
		cpwTF.setPreferredSize(new Dimension(400, 60));
		cpwTF.setFont(new Font("Serif", Font.BOLD, 30));
		cpwTF.setOpaque(false);
		cpwTF.setBorder(line);
		eTF = new JTextField();
		eTF.setPreferredSize(new Dimension(400, 60));
		eTF.setFont(new Font("Serif", Font.BOLD, 30));
		eTF.setOpaque(false);
		eTF.setBorder(line);
		ceTF = new JTextField();
		ceTF.setPreferredSize(new Dimension(400, 60));
		ceTF.setFont(new Font("Serif", Font.BOLD, 30));
		ceTF.setOpaque(false);
		ceTF.setBorder(line);

		c.insets = new Insets(10, 10, 10, 10);
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.WEST;

		c.gridx = 0;
		c.gridy = 0;
		add(l1, c);

		c.gridx = 2;
		c.gridy = 0;
		add(uinTF, c);

		c.gridx = 0;
		c.gridy = 1;
		add(l2, c);

		c.gridx = 2;
		c.gridy = 1;
		add(fnTF, c);

		c.gridx = 0;
		c.gridy = 2;
		add(l3, c);

		c.gridx = 2;
		c.gridy = 2;
		add(lnTF, c);

		c.gridx = 0;
		c.gridy = 3;
		add(l4, c);

		c.gridx = 2;
		c.gridy = 3;
		add(unTF, c);

		c.gridx = 0;
		c.gridy = 4;
		add(l5, c);

		c.gridx = 2;
		c.gridy = 4;
		add(pwTF, c);

		c.gridx = 0;
		c.gridy = 5;
		add(l6, c);

		c.gridx = 2;
		c.gridy = 5;
		add(cpwTF, c);

		c.gridx = 0;
		c.gridy = 6;
		add(l7, c);

		c.gridx = 2;
		c.gridy = 6;
		add(eTF, c);

		c.gridx = 0;
		c.gridy = 7;
		add(l8, c);

		c.gridx = 2;
		c.gridy = 7;
		add(ceTF, c);

		c.gridx = 0;
		c.gridy = 8;
		add(scB, c);

		c.gridx = 2;
		c.gridy = 8;
		c.anchor = GridBagConstraints.EAST;
		add(cB, c);

		pack();
		centerWindow(this);
		setVisible(true);
	}

	private void centerWindow(Window w) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setLocation((d.width - w.getWidth()) / 2,
				(d.height - w.getHeight()) / 2);
	}

	private boolean sqlTrueOrFalse() {
		try{
		connect = new JDBC_Connect();
		conn = connect.connect(); 
		
		String sql = new String("SELECT * FROM REGISTERED_USER_TABLE WHERE UIN =?");
		pst = conn.prepareStatement(sql);
		pst.setString(1, uinTF.getText()); 
		rs = pst.executeQuery();
		int count = 0; 
		while (rs.next()) { // retrieve data

			rs.getString("UIN");
			count ++; 
		}
		
		if (count == 0) {
			return true;
		} else {
			int mc = JOptionPane.WARNING_MESSAGE;
			JOptionPane.showMessageDialog (null, "Already Registered!  Please Log in.", "Register Error", mc);
			return false;
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
		return false; 
	}

	private boolean fieldConfirmCheck() {
		if (!eTF.getText().equals(ceTF.getText())) {
			JOptionPane.showMessageDialog(null, "Emails do not match!");
			return false;
		}
		if (!pwTF.getText().equals(cpwTF.getText())) {
			JOptionPane.showMessageDialog(null, "Passwords do not match!");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == scB) {

			if (nullChecker() == true) {
				if (fieldConfirmCheck() == true) {
					if (sqlTrueOrFalse() == true) {

						insertRegistrant();

						new CardLogin();
						dispose();

					} else {
						
					}
				}
			}
		}

		if (source == smB) {

			if (nullChecker() == true) {
				if (fieldConfirmCheck() == true) {
					if (sqlTrueOrFalse() == true) {

						insertRegistrant();

						new ManualLogin();
						dispose();
					} else {
						// Already Registered!
					}
				}
			}
		}

		if (source == cB) {
			new CardLogin();
			dispose();
		}
	}

	private boolean nullChecker() {
		// TODO Auto-generated method stub
		if (uinTF.getText().isEmpty() || uinTF.getText() == " ") {
			JOptionPane.showMessageDialog(null, "UIN Field is empty!");
			return false;
		}
		if (unTF.getText().isEmpty() || unTF.getText() == " ") {
			JOptionPane.showMessageDialog(null, "Username Field is empty!");
			return false;
		}
		if (pwTF.getText().isEmpty() || pwTF.getText() == " ") {
			JOptionPane.showMessageDialog(null, "Password Field is empty!");
			return false;
		}
		if (cpwTF.getText().isEmpty() || cpwTF.getText() == " ") {
			JOptionPane.showMessageDialog(null, "Confirmed Password Field is empty!");
			return false;
		}
		if (fnTF.getText().isEmpty() || fnTF.getText() == " ") {
			JOptionPane.showMessageDialog(null, "First Name Field is empty!");
			return false;
		}
		if (lnTF.getText().isEmpty() || lnTF.getText() == " ") {
			JOptionPane.showMessageDialog(null, "Last Name Field is empty!");
			return false;
		}
		if (eTF.getText().isEmpty() || eTF.getText() == " ") {
			JOptionPane.showMessageDialog(null, "Email Field is empty!");
			return false;
		}
		if (ceTF.getText().isEmpty() || ceTF.getText() == " ") {
			JOptionPane.showMessageDialog(null, "Confirmed Email Field is empty!");
			return false;
		}
		return true;
	}

	private void insertRegistrant() {
		SqlQuery query = new SqlQuery(); 
		
		query.setUIN(uinTF.getText());
		query.setFname(fnTF.getText().toUpperCase());
		query.setLname(lnTF.getText().toUpperCase());
		query.setUser(unTF.getText());
		query.setPass(pwTF.getText());
		query.setEmail(eTF.getText());
		query.insert(); 
		dispose();
		}
}