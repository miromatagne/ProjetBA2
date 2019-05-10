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

public class StartListener implements ActionListener,Serializable {
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
			System.out.println("Les donn�es entr�es ne sont pas des entiers.");
			frame.setVisible(false);
			play = false;
			new StartGame();
		}
		if(play) {
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
	        music();
		}
	}
	public void music() {
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		try {
			InputStream test = new FileInputStream("C:\\Users\\Miro-Manuel\\Dropbox\\ULB\\BA2\\Projet Info\\View\\Son.wav");
			BGM = new AudioStream(test);
			AudioPlayer.player.start(BGM);
			//MD = BGM.getData();
			//loop = new ContinuousAudioDataStream(MD);
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas � cette adresse.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		MGP.start(loop);
		
	}
}
