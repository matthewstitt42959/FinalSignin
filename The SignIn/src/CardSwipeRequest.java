package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.MagData;

public class CardSwipeRequest extends JFrame implements ActionListener,
		KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton mButton;
	private JButton cButton;
	private JTextField uin;
	private int returnCounter = 0;
	private int ValidCounter = 0;

	public CardSwipeRequest() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		
		getContentPane().setBackground(
				new Color(Integer.parseInt("0xAAD3E2".substring(2), 16)));

		// setLayout(new GridBagLayout());

		URL swipe = CardSwipeRequest.class.getResource("pictures/registerSwipe.png");

		Image swipeIm = null;
		try {
			swipeIm = ImageIO.read(swipe);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel image = new JLabel(new ImageIcon(swipeIm));
		image.setOpaque(false);
		image.setLayout(new BorderLayout());
		image.setFocusable(false);

		JLabel invisible = new JLabel("");
		invisible.setFocusable(false);
		invisible.setPreferredSize(new Dimension(248, 100));
		invisible.setOpaque(false);
		
		uin = new JTextField();
		uin.setFocusable(true);
		uin.setOpaque(false);
		uin.setPreferredSize(new Dimension(0, 0));// Makes the field not seen!
													// CHECK WITH CARD READER
		uin.addKeyListener(this);

		mButton = new JButton(
				"<html><center>No Card?<br>Press Here</center></html>");
		mButton.setFocusable(false);
		mButton.setPreferredSize(new Dimension(800/2, 150));
		mButton.setFont(new Font("Serif", Font.BOLD, 30));
		mButton.setRolloverEnabled(false);
		mButton.setOpaque(false);
		mButton.addActionListener(this);

		cButton = new JButton("Cancel");
		cButton.setFocusable(false);
		cButton.setPreferredSize(new Dimension(800/2, 150));
		cButton.setFont(new Font("Serif", Font.BOLD, 30));
		cButton.setRolloverEnabled(false);
		cButton.setOpaque(false);
		cButton.addActionListener(this);

		add(image);

		
		JPanel flow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		flow.setOpaque(false);
		
		flow.add(mButton);

		image.add(uin, BorderLayout.NORTH);

		//flow.add(invisible);
		flow.add(cButton);
		
		image.add(flow, BorderLayout.SOUTH);

		setSize(800, 900);
		centerWindow(this);
		setVisible(true);
	}

	private void centerWindow(Window w) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setLocation((d.width - w.getWidth()) / 2,
				(d.height - w.getHeight()) / 2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();

		if (source == KeyEvent.VK_ENTER) {
			returnCounter++;
			if (returnCounter < 2) {
				// do nothing
			} else {
				String text = uin.getText();
				
				MagData cardData = new MagData(text.toCharArray());
				if (!cardData.getIsValid()) {
					if (ValidCounter < 2) {
						JOptionPane
								.showMessageDialog(null,
										"Card Data is not Valid. Try again, or Proceed to Manual Registration.");
						returnCounter = 0;
						uin.setText("");
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
						new Register(cardData.getUIN(), cardData.getFName(),
								cardData.getLName());
						dispose();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == cButton) {
			new CardLogin();
			dispose();
		}
		if (source == mButton) {
			new Register();
			dispose();
		}
	}
}
