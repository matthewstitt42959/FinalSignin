package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Background extends JFrame {

	private static final long serialVersionUID = 1L;

	public Background() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setFocusable(false);
		setEnabled(false);
		/*
		 * try {
		 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 * } catch (ClassNotFoundException | InstantiationException |
		 * IllegalAccessException | UnsupportedLookAndFeelException e) {
		 * e.printStackTrace(); }//Sets to computer's default look and feel
		 */

		//setSize(fullScreenWidth(), fullScreenHeight());

		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		// getContentPane().setBackground(Color.GREEN.darker());

		getContentPane().setBackground(
				new Color(Integer.parseInt("0xAAD3E2".substring(2), 16))); // Sets
																			// to
																			// Baker
																			// Blue
		setVisible(true);
	}

	public int fullScreenWidth() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		return (int) d.getWidth();
	}

	public int fullScreenHeight() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		return (int) d.getHeight();
	}

}
