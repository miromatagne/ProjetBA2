package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JFrame;

public class NewListener implements ActionListener{
	JFrame frame;
	public NewListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.setVisible(false);
		new StartGame();
	}
}
