package View;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Controller.Keyboard;
import Controller.Mouse;
import Model.Game;

public class StartListener implements ActionListener {
	JTextField textnbBebe;
	JTextField textnbAdulte;
	JTextField textnbVieux;
	JTextField textnbEtages;
	StartGame start;
	JFrame frame;
	int nbBebe;
	int nbAdulte;
	int nbVieux;
	int nbEtages;
	public StartListener(JFrame frame,JTextField textnbBebe, JTextField textnbAdulte, JTextField textnbVieux, JTextField textnbEtages) {
		this.frame = frame;
		this.textnbBebe = textnbBebe; 
		this.textnbAdulte = textnbAdulte; 
		this.textnbVieux = textnbVieux; 
		this.textnbEtages = textnbEtages;
	}
	
	public void actionPerformed(ActionEvent e) {
		nbBebe = Integer.parseInt(textnbBebe.getText());
		nbAdulte = Integer.parseInt(textnbAdulte.getText());
		nbVieux = Integer.parseInt(textnbVieux.getText());
		nbEtages = Integer.parseInt(textnbEtages.getText());
		ArrayList<Window> windows = new ArrayList<Window>();
		frame.setVisible(false);
		frame.dispose();
        for (int i = 0; i < nbEtages; i++) {
        	Window w = new Window("Etage " + i);
        	windows.add(w);
        }
        Game game = new Game(windows,nbBebe,nbAdulte,nbVieux);
        Keyboard keyboard = new Keyboard(game);
        Mouse mouse = new Mouse();
        mouse.setGame(game);
        for (Window window : windows) {
        	 window.setKeyListener(keyboard);
             window.setMouseListener(mouse);
        }
	}
}
