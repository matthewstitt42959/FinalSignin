package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import src.SelectionPage;
public class AdminLogin extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JToggleButton computerTB;
	private JToggleButton tutorTB;
	private JToggleButton studyTB;

	private JButton sB;// add to southPane
	private JButton cB;// add to southPane
	
	final private JCheckBox internet;
	final private JCheckBox printScan;
	final private JCheckBox microOffice;
	final private JCheckBox testing;
	final private JCheckBox studying;

	Border line = BorderFactory.createLineBorder(Color.BLACK);

	private JPanel TBwestPane;
	private JPanel TBeastPane;
	private JPanel TBcenterPane;
	private JPanel LwestPane;
	private JPanel LeastPane;
	private JPanel LcenterPane;

	//private JButton tutorsB;
	private JComboBox<String> tutorCB;

	public AdminLogin() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		//setSize(fullScreenWidth(), fullScreenHeight());
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

		URL headerLogo = SelectionPage.class.getResource("LSS_Welcome.png");
		URL unchecked = SelectionPage.class
				.getResource("RedCheck_Unchecked.png");
		URL checked = SelectionPage.class.getResource("RedCheck_Checked.png");

		Image header = null;
		Image uncheck = null;
		Image check = null;
		try {
			header = ImageIO.read(headerLogo).getScaledInstance(
					fullScreenWidth(), prefHeight()*4, Image.SCALE_DEFAULT);
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
		northPane.setPreferredSize(new Dimension(getWidth(), prefHeight()*4));
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

		//SUPER PANELS ADDED
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

		//LABELS
		JLabel headerL = new JLabel(new ImageIcon(header));

		JLabel invisibleLabel = new JLabel("");
		invisibleLabel.setFocusable(false);
		invisibleLabel.setBorder(line);
		invisibleLabel.setPreferredSize(new Dimension(fullScreenWidth()
				- (getButtonWidth() * 2), getButtonHeight()));

		JLabel selectL1 = new JLabel(
				"Select A Tutor");
		selectL1.setEnabled(false);
		selectL1.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		selectL1.setFocusable(false);
		
		JLabel selectL2 = new JLabel(
				"From The Menu Below");
		selectL2.setEnabled(false);
		selectL2.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		selectL2.setFocusable(false);

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
		//internet.addActionListener(this);

		printScan = new JCheckBox("Printing/Scanning", false);
		printScan.setFocusable(false);
		printScan.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		printScan.setOpaque(false);
		printScan.setIcon(new ImageIcon(uncheck));
		printScan.setSelectedIcon(new ImageIcon(check));
		printScan.setEnabled(false);
		//printScan.addActionListener(this);

		microOffice = new JCheckBox("Word, Excel, Access, and Powerpoint", false);
		microOffice.setFocusable(false);
		microOffice
				.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		microOffice.setOpaque(false);
		microOffice.setIcon(new ImageIcon(uncheck));
		microOffice.setSelectedIcon(new ImageIcon(check));
		microOffice.setEnabled(false);
		//microOffice.addActionListener(this);
		
		testing = new JCheckBox("Testing", false);
		testing.setFocusable(false);
		testing.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		testing.setPreferredSize(new Dimension(getButtonWidth(),
				getButtonHeight()));
		testing.setOpaque(false);
		testing.setIcon(new ImageIcon(uncheck));
		testing.setSelectedIcon(new ImageIcon(check));
		testing.setEnabled(false);
		//testing.addActionListener(this);

		studying = new JCheckBox("Studying", false);
		studying.setFocusable(false);
		studying.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		studying.setOpaque(false);
		studying.setIcon(new ImageIcon(uncheck));
		studying.setSelectedIcon(new ImageIcon(check));
		studying.setEnabled(false);
		//studying.addActionListener(this);

		/*final JCheckBox placeHolder = new JCheckBox("", false);
		microOffice.setFocusable(false);
		microOffice
				.setFont(new Font("Sans Bold", Font.BOLD, getFontSize() - 10));
		microOffice.setOpaque(false);
		microOffice.setIcon(new ImageIcon(uncheck));
		microOffice.setSelectedIcon(new ImageIcon(check));
		microOffice.setEnabled(false);
		microOffice.setText("Word, Excel, Access, and Powerpoint");
		microOffice.addActionListener(this);*/

		// BUTTONS
		computerTB = new JToggleButton("Admin Report Button");
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
		
		//Tutor Populating Button
	/*	tutorsB = new JButton("Press Here");
		tutorsB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		tutorsB.setEnabled(false);
		tutorsB.setFocusable(false);
		//tutorsB.setMaximumSize(new Dimension(getButtonWidth()/7*3, getButtonWidth()/7*3));
		//tutorsB.setMinimumSize(new Dimension(getButtonWidth()/7*3, getButtonWidth()/7*3));
		tutorsB.setPreferredSize(new Dimension(getButtonWidth()/7*3, getButtonWidth()/7*2));
		tutorsB.setRolloverEnabled(false);
		tutorsB.addActionListener(this);*/
		
		String tutors[] = new String[]{"Please Select a Tutor","1-Jeff","2-Fred","3-Lisa","4-Mandy","5-Tony","6-Cassandra",};
		
		tutorCB = new JComboBox<String>(tutors);
		tutorCB.setEnabled(false);
		tutorCB.setPreferredSize(new Dimension(getButtonWidth()/7*6, getButtonWidth()/7));
		tutorCB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		tutorCB.setFocusable(false);
		tutorCB.setSelectedIndex(0);

		sB = new JButton("Submit");
		sB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		sB.setFocusable(false);
		sB.setBorder(line);
		sB.setPreferredSize(new Dimension(getButtonWidth(), getButtonHeight()));
		sB.setRolloverEnabled(false);
		sB.addActionListener(this);

		cB = new JButton("Cancel");
		cB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		cB.setFocusable(false);
		cB.setBorder(line);
		cB.setPreferredSize(new Dimension(getButtonWidth(), getButtonHeight()));
		cB.setRolloverEnabled(false);
		cB.addActionListener(this);

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

		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		TBcenterPane.add(studyTB, c);
		
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(getButtonHeight() / 2, 0, getButtonHeight() / 2,
				0);
		LcenterPane.add(testing, c);

		c.gridy = 1;
		LcenterPane.add(studying, c);

		// EAST PANEL COMPONENTS CONSTRAINTS

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		TBeastPane.add(tutorTB, c);

		c.insets = new Insets(-(getButtonHeight()*3), 0, -getButtonHeight(), 0);
		c.anchor = GridBagConstraints.CENTER;
		LeastPane.add(selectL1, c);
		
		c.gridy = 1;
		c.insets = new Insets(-getButtonHeight()/5*3, 0, getButtonHeight(), 0);
		LeastPane.add(selectL2, c);
		/*
		c.insets = new Insets(-(getButtonHeight()*2), 0, getButtonHeight(), 0);
		c.anchor = GridBagConstraints.CENTER;
		LeastPane.add(area, c);*/

		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 0);
		LeastPane.add(tutorCB, c);
		//LeastPane.add(tutorsB, c);

		// SOUTH PANEL COMPONENTS ADDED

		southPane.add(sB);
		southPane.add(invisibleLabel);
		southPane.add(cB);

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
		
	
		//MAY NOT BE NEEDED HERE!
		/*if (source == internet){
			if (internet.isSelected()){
				//Add flag into log on submit
			} else {
				//Do not add flag
			}
		}*/

		if (source == sB) {

			// THIS NEEDS TO BE ABLE TO CLOSE THIS WINDOW!
			//JOptionPane.showMessageDialog(null, "Internet Button value: " + internet.isSelected());
			
//			new CardLogin();
			dispose();
			

		}
		if (source == cB) {
			System.exit(0);

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


}

	

