package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.GameObject;
import Model.Player;

public class Status extends JPanel {
	private Player p;
	private ArrayList<Player> players = null;
	private int BAR_LENGTH = 60; //Longueur de la barre d'énergie
	private int BAR_WIDTH = 20; //Largeur de la barre d'énergie (hauteur)
	private int AVATAR_SIZE = 30; //taille du carré bleu en haut à droite de l'interface graphique
	private int BLOCK_SIZE = 25;
	private ArrayList<GameObject> inventory = null;

    public Status() {
        this.setPreferredSize(new Dimension(450, 600)); //Taille de la zone avec l'énergie etc... a droite de la Map ?
        this.setBackground(Color.LIGHT_GRAY); //Couleur du background de la zone avec l'énergie etc
        this.setOpaque(true); //Pas de changement quand je met en commentaire
    }
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		// draw avatar
		int i = 0;
		int totalMoney = 0;
		if (players != null) {
		for (Player pl : players) {
			
			int color = pl.getColor();
			if (color == 0) {
                g.setColor(Color.DARK_GRAY);
            } else if (color == 1) {
                g.setColor(Color.GRAY);
            } else if (color == 2) {
                g.setColor(Color.BLUE);
            } else if (color == 3) {
                g.setColor(Color.GREEN);
            } else if (color == 4) {
                g.setColor(Color.RED);
            } else if (color == 5) {
                g.setColor(Color.ORANGE);
            } else if (color == 6) {
                g.setColor(Color.BLACK);
            }
			g.fillRect(0, 195+100*i, AVATAR_SIZE, AVATAR_SIZE); //(pos horizontale, pos verticale, longueur du cote, longueur du cote) 
			g.setColor(Color.BLACK);
			g.drawString("Energie:", 180, 200+100*i); //Ecrit le mot "Energy", avec sa position
			g.drawString("Money:"+pl.getMoney(), 80, 200+100*i);
			g.drawString("Faim:", 300, 200+100*i);
			g.setColor(Color.RED);
			g.fillRect(180, 203+100*i, BAR_LENGTH, BAR_WIDTH); //Crée une barre rouge d'une taille définie et fixée.
			g.setColor(Color.GREEN);
			int length_ok = (int) Math.round(BAR_LENGTH*pl.getEnergy()/100);
			g.fillRect(180, 203+100*i, length_ok, BAR_WIDTH);//Crée une barre verte par dessus, avec une longueur correspondant à l'énergie
			g.setColor(Color.BLACK);  
			//Quand l'énergie va diminuer, le vert va progressivement diminuer et on va voir le rouge en dessous
			totalMoney += pl.getMoney();
			
			g.setColor(Color.RED);
        	g.fillRect(300, 203+100*i, BAR_LENGTH, BAR_WIDTH);
        	g.setColor(Color.GREEN);
        	int length_ok2 = (int) Math.round(BAR_LENGTH*pl.getFaim()/100);
        	g.fillRect(300, 203+100*i, length_ok2, BAR_WIDTH);
			i += 1;
		}
		g.setColor(Color.BLACK);
	    g.drawString("Total Money : "+totalMoney, 0, 150);
	    
	    g.drawString("Inventory", 0, 595); 
	    for(int j=0; j<10; j++) {
	    	for(int k=0 ; k<10;k++) {                                             // ici on dessine l'inventaire
	    	
	    	g.setColor(Color.WHITE); 
	    	g.fillRect(j*BLOCK_SIZE,600+(k*BLOCK_SIZE),BLOCK_SIZE,BLOCK_SIZE);
	    	g.setColor(Color.BLACK);
	    	g.drawRect(j*BLOCK_SIZE,600+(k*BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE);
	    	}
	    
	    }
	    
	    int v = -1;
		int w = 0;
		
		
		for(GameObject obj:inventory) {
			
			int color=obj.getColor();
	            if (color == 0) {
	                g.setColor(Color.DARK_GRAY); 
	            } else if (color == 1) {
	                g.setColor(Color.RED);
	            } else if (color == 2) {
	                g.setColor(Color.BLUE);
	            } else if (color == 3) {
	                g.setColor(Color.GREEN);
	            } else if (color == 4) {
	                g.setColor(Color.RED);
	            } else if (color == 5) {
	                g.setColor(Color.ORANGE);
	            }
	            
	            v+=1;
	            if(v<10) {
	            	g.fillRect(v*BLOCK_SIZE+2, 602+w*BLOCK_SIZE, BLOCK_SIZE-5, BLOCK_SIZE-5);
	            	
	            }
	            else if (v==10 && w<10) {
	            	v=0;
	            	w+=1;    	
	            }          
		}
	}
}

    public void redraw() {
        this.repaint();
    }

	public void setPlayer(Player p2) {
		p = p2;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void setInventory(ArrayList<GameObject> inventory) {
		this.inventory=inventory;
}
}
