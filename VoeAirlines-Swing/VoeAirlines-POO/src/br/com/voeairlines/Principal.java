package br.com.voeairlines;

import javax.swing.SwingUtilities;

public class Principal {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Login l = new Login();
			l.setVisible(true);
		});
			
		
	}

}
