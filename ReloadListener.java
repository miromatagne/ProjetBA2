package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JFrame;

import Controller.Mouse;
import Model.EnergyLoss;
import Model.Game;
import Model.Refresh;

public class ReloadListener implements ActionListener,Serializable{
	JFrame frame;
	public ReloadListener(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Game g = null;
		frame.setVisible(false);
	      
        
        try {
	        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream ("Fichier de Sauvegarde"));
	  
	 		g =  (Game) ois1.readObject();
	 		Mouse.setGame(g);
	 		
	 		g.getWindows().get(0).makeActive();
	 		
	 		g.getActiveWindow();
	 		
	 		Thread r1 = new Thread(new Refresh(g));
		    r1.start();
		    Thread t1 = new Thread(new EnergyLoss(g.getActivePlayer()));
		      
		    t1.start();  
	 		ois1.close();
        }
        catch (FileNotFoundException e1) {
			System.out.println("Le fichier texte n'existe pas à cette adresse.");
			e1.printStackTrace();
		} catch (IOException f) {
			f.printStackTrace();
		} catch (ClassNotFoundException t) {
			t.printStackTrace();
		}
	}
}

