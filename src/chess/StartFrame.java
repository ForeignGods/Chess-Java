package chess;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
/**
 * 
 * @author Pascal
 *
 */
public class StartFrame extends JFrame {
	public JPanel contentPanel, contentPanel2;
	public GUI gui;
	public JFrame impressum;
	public JFrame instruction;

	/**
	 * constructs StartFrame
	 */
	public StartFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Schach - ForeignGods - Version 1.3");
		setBounds(0, 0, 375, 75);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("src\\chess\\imgs\\Logo.png");
		setIconImage(icon.getImage());
		setLayout(new BorderLayout());
		setResizable(false);
		initButton();
	}

	/*
	 * initializes Buttons
	 */
	private void initButton() {
		final JFrame frame = this;

		contentPanel = new JPanel();
		contentPanel2 = new JPanel();

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui = new GUI(new Board());
				gui.drawBoard();
				gui.drawPieces();
			}
		});
		contentPanel.add(startButton);

		JButton impressumButton = new JButton("Impressum");
		impressumButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame iframe = new JFrame();
				iframe.setVisible(true);
				iframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				iframe.setTitle("Schach - ForeignGods - Version 1.3");
				iframe.setBounds(0, 0, 500, 300);
				ImageIcon icon = new ImageIcon("src\\chess\\imgs\\Logo.png");
				iframe.setIconImage(icon.getImage());
				iframe.setLayout(new BorderLayout());
				iframe.setResizable(false);
				JLabel label1 = new JLabel();
				label1.setText("<html><h1>" + "Impressum" + "</h1>" + "<p>ForeignGods</p>" + "<p>Pascal Rössler</p>"
						+ "<p>Berneggweg 22</p>" + "<p>8055 Zürich</p>" + "<p>pascal.roessler@gmx.ch</p>" + "</html>");
				label1.setHorizontalAlignment(SwingConstants.CENTER);
				label1.setVerticalAlignment(SwingConstants.CENTER);
				iframe.add(label1);
			}
		});
		contentPanel.add(impressumButton);

		JButton anleitungButton = new JButton("Anleitung");
		anleitungButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame iframe = new JFrame();
				iframe.setVisible(true);
				iframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				iframe.setTitle("Schach - ForeignGods - Version 1.3");
				iframe.setBounds(0, 0, 1500, 1000);
				ImageIcon icon = new ImageIcon("src\\chess\\imgs\\Logo.png");
				iframe.setIconImage(icon.getImage());
				iframe.setLayout(new BorderLayout());
				iframe.setResizable(false);
				iframe.add(new JScrollPane(
						contentPanel2.add(new JLabel(new ImageIcon("src\\chess\\imgs\\Anleitung.jpg")))));
			}
		});
		contentPanel.add(anleitungButton);

		JButton exitButton = new JButton("Beenden");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(frame, "Wollen sie wirklich das Spiel beenden?", "Spiel Beenden.",
						JOptionPane.OK_CANCEL_OPTION);
				if (n == JOptionPane.OK_OPTION) {
					frame.dispose();
					if (gui != null) {
						gui.getFrame().dispose();
					}
				} else if (n == JOptionPane.CANCEL_OPTION) {
				}
			}
		});
		contentPanel.add(exitButton);
	}
}
