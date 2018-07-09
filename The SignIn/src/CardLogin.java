package src;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;

import backend.SqlQuery;
import backend.MagData;


public class CardLogin extends JFrame implements ActionListener, KeyListener {
	/**
	 * 
	 */

	// private JButton sB;
	// private JButton cB;
	private JButton rB;
	private JButton mlB;
	private JTextField pass;
	private int returnCounter = 0;
	private int ValidCounter = 0;

	private static final long serialVersionUID = 1L;

	public CardLogin() {
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

		URL background = getClass().getResource("/Resources/HighResCardSwipeScreen.png");
		// Added Pictures as src folder and changed to getClass().getResource() mstitt
		
		Image bg = null;
		try {
			bg = ImageIO.read(background);
			bg = bg.getScaledInstance(fullScreenWidth(),
					fullScreenHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel picture = new JLabel(new ImageIcon(bg));
		picture.setFocusable(false);

		add(picture);

		picture.setLayout(new BorderLayout());

		pass = new JTextField();
		pass.setFocusable(true);
		pass.setPreferredSize(new Dimension(0, 0));
		pass.addKeyListener(this);

		/*
		 * sB = new JButton("Submit"); sB.setPreferredSize(new
		 * Dimension(getButtonWidth(), getButtonHeight())); sB.setFont(new
		 * Font("Sans Bold", Font.BOLD, getFontSize())); sB.setFocusable(false);
		 * sB.setRolloverEnabled(false); sB.addActionListener(this);
		 * 
		 * cB = new JButton("Cancel"); cB.setFocusable(false);
		 * cB.setPreferredSize(new Dimension(getButtonWidth(),
		 * getButtonHeight())); cB.setFont(new Font("Sans Bold", Font.BOLD,
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

		mlB = new JButton("Manual Login");
		mlB.setFocusable(false);
		mlB.setPreferredSize(new Dimension(getButtonWidth(), getButtonHeight()));
		mlB.setFont(new Font("Sans Bold", Font.BOLD, getFontSize()));
		mlB.setRolloverEnabled(false);
		// mlB.setOpaque(false);
		// mlB.setContentAreaFilled(false);
		// mlB.setBorderPainted(false);
		mlB.addActionListener(this);

		JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

		pane.add(pass);

		// pane.add(sB);

		// pane.add(cB);

		pane.add(rB);

		pane.add(mlB);

		picture.add(pane, BorderLayout.SOUTH);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		
		if (source == mlB) {
			new ManualLogin();
			dispose();

		}
		if (source == rB) {
			new CardSwipeRequest();

			dispose();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();

		if (source == KeyEvent.VK_ENTER) {
			returnCounter++;
			if (returnCounter < 2) {
				// do nothing
			} else {
				String text = pass.getText();

				MagData cardData = new MagData(text.toCharArray());
				if (!cardData.getIsValid()) {
					if (ValidCounter < 2) {
						JOptionPane
								.showMessageDialog(null,
										"Card Data is not Valid. Try again, or Proceed to Manual Registration.");
						returnCounter = 0;
						pass.setText("");
						ValidCounter++;
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Card could not be read, proceeding to Manual Registration.");
						new Register();
						dispose();
					}
				} else {
					if (cardData.getMultiSwipe()) {
						JOptionPane
								.showMessageDialog(null,
										"Card swiped multiple times, please swipe your card only once.");
					} else {

						String sql = new String(
								"SELECT * FROM REGISTERED_USER_TABLE WHERE UIN =? AND L_NAME =?");
						int numVar = 1;

						SqlQuery query = new SqlQuery();
						query.setSql(sql);
						query.setNumVar(numVar);
						query.setUIN(Integer.toString(cardData.getUIN()));
						query.setLname(cardData.getLName());
						query.setFname(cardData.getFName());
						query.CardVerification(); 
											
						
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

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

		int w = (int) d.getWidth() / 2;

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
}
