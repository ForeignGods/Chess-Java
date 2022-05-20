package chess;

import java.awt.EventQueue;
//Hier kann das ganze Programm gestartet werden
//Hier existiert die Main Methode
/**
 * 
 * @author Pascal
 *
 */
public class MainFrame {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
					frame.setContentPane(frame.contentPanel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
