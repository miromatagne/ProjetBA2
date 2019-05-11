package View;

import java.awt.Event;
import sun.audio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Controller.Keyboard;
import Controller.Mouse;
import Model.Game;
import Model.ModelWindow;

public class StartListener implements ActionListener{
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
	boolean play = true;
	public StartListener(JFrame frame,JTextField textnbBebe, JTextField textnbAdulte, JTextField textnbVieux, JTextField textnbEtages) {
		this.frame = frame;
		this.textnbBebe = textnbBebe; 
		this.textnbAdulte = textnbAdulte; 
		this.textnbVieux = textnbVieux; 
		this.textnbEtages = textnbEtages;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			nbBebe = Integer.parseInt(textnbBebe.getText());
			nbAdulte = Integer.parseInt(textnbAdulte.getText());
			nbVieux = Integer.parseInt(textnbVieux.getText());
			nbEtages = Integer.parseInt(textnbEtages.getText());
		}
		catch (NumberFormatException f){
			System.out.println("Les données entrées ne sont pas des entiers.");
			frame.setVisible(false);
			play = false;
			new StartGame();
		}
		if(play) {
			ArrayList<ModelWindow> windows = new ArrayList<ModelWindow>();
			frame.setVisible(false);
			frame.dispose();
	        for (int i = 0; i < nbEtages; i++) {
	        	ModelWindow w = new ModelWindow();
	        	windows.add(w);
	        }
	        Game game = new Game(windows,nbBebe,nbAdulte,nbVieux);
	        Keyboard keyboard = new Keyboard(game);
	        Mouse mouse = new Mouse();
	        mouse.setGame(game);
	        for (ModelWindow window : windows) {
	        	 window.setKeyListener(keyboard);
	             window.setMouseListener(mouse);
	        }
		}
	}
}
