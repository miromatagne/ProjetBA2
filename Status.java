package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controller.Mouse;
import Controller.MouseForStatus;
import Model.Activable;
import Model.Apple;
import Model.Bed;
import Model.Computer;
import Model.Eldery;
import Model.GameObject;
import Model.Pill;
import Model.Player;
import Model.Stairs;
import Model.Toilet;
import Model.Vodka;

public class Status extends JPanel implements Serializable{
	private ArrayList<Player> players = null;
	private int BAR_LENGTH = 60; //Longueur de la barre d'énergie
	private int BAR_WIDTH = 20; //Largeur de la barre d'énergie (hauteur)
	private int AVATAR_SIZE = 30; //taille du carré bleu en haut à droite de l'interface graphique
	private int BLOCK_SIZE = 25;
	private int PREVIEW_SIZE = 80;
	private int a = 0;
	private int b = 25;
	private Mouse mouseController = new Mouse();
	private ArrayList<GameObject> objects;
	private GameObject active_object;
	private Player active_player;
	private MouseForStatus mouse = new MouseForStatus(this);

    public Status() {
        this.setPreferredSize(new Dimension(450, 600)); //Taille de la zone avec l'énergie etc... a droite de la Map ?
        this.setBackground(Color.LIGHT_GRAY); //Couleur du background de la zone avec l'énergie etc
        this.setOpaque(true); //Pas de changement quand je met en commentaire
        addMouseListener(mouse);
    }
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		int i = 0;
		int totalMoney = 0;
		for(Player p : players) {
			if(p.isActivePlayer()) {
				active_player = p;
			}
		}
		for(GameObject o : objects) {
			if(o.getPosX()== active_player.getFrontX() && o.getPosY() == active_player.getFrontY() && o instanceof Activable) {
	            g.drawImage(o.getIm(), 150, 25, PREVIEW_SIZE, PREVIEW_SIZE, null);
	            this.repaint();
	            }
		}
		Image player = active_player.getIm();
		g.drawImage(player, 0, 195+100*i, AVATAR_SIZE, AVATAR_SIZE,null); //(pos horizontale, pos verticale, longueur du cote, longueur du cote) 
		g.setColor(Color.BLACK);
		g.drawString("Money:"+active_player.getMoney(), 80, 200);
		g.drawString("Energie:", 80, 270); //Ecrit le mot "Energy", avec sa position
		g.drawString("Faim:", 80, 340);
		g.drawString("Vessie:", 80, 410);
		g.drawString("Hygiène:", 80, 480);
		g.setColor(Color.RED);
		g.fillRect(180, 258, BAR_LENGTH, BAR_WIDTH); //Crée une barre rouge d'une taille définie et fixée.
		g.setColor(Color.DARK_GRAY);
		g.fillRect(180, 398, BAR_LENGTH, BAR_WIDTH);
		g.setColor(Color.GREEN);
		int length_ok = (int) Math.round(BAR_LENGTH*active_player.getEnergy()/100);
		g.fillRect(180, 258, length_ok, BAR_WIDTH);//Crée une barre verte par dessus, avec une longueur correspondant à l'énergie
		g.setColor(Color.BLACK);  
		//Quand l'énergie va diminuer, le vert va progressivement diminuer et on va voir le rouge en dessous
		totalMoney += active_player.getMoney();
		
		g.setColor(Color.RED);
    	g.fillRect(180, 328, BAR_LENGTH, BAR_WIDTH);
    	g.setColor(Color.GREEN);
    	int length_ok2 = (int) Math.round(BAR_LENGTH*active_player.getFaim()/100);
    	g.fillRect(180, 328, length_ok2, BAR_WIDTH);
    	g.setColor(Color.YELLOW);
    	int length_ok3 = (int) Math.round(BAR_LENGTH*active_player.getVessie()/100);
    	g.fillRect(180, 398, length_ok3, BAR_WIDTH);
    	g.setColor(Color.RED);
		g.fillRect(180, 468, BAR_LENGTH, BAR_WIDTH);
		g.setColor(Color.GREEN);
    	int length_ok5 = (int) Math.round(BAR_LENGTH*active_player.getHygiene()/100);
    	g.fillRect(180, 468, length_ok5, BAR_WIDTH);
    	if(active_player instanceof Eldery) {
    		g.setColor(Color.BLACK);
    		g.drawString("Santé:", 80, 480);
    		g.setColor(Color.ORANGE);
    		int length_ok4 = (int) Math.round(BAR_LENGTH*((Eldery)active_player).getHealth()/100);
    		g.fillRect(180, 468, length_ok4, BAR_WIDTH);
    		g.setColor(Color.BLACK);
    		g.drawString(((Eldery)active_player).getMin() + ":" + ((Eldery)active_player).getSec(), 300, 480);
    	}
		i += 1;
		g.setColor(Color.BLACK);
	    g.drawString("Total Money : "+totalMoney, 0, 150);
	    
	    g.drawString("Inventory", 0, 595); 
	    for(int j=0; j<5; j++) {
	    	for(int k=0 ; k<5;k++) {                                             // ici on dessine l'inventaire
	    	
	    	g.setColor(Color.WHITE); 
	    	g.fillRect(j*BLOCK_SIZE,625+(k*BLOCK_SIZE),BLOCK_SIZE,BLOCK_SIZE);
	    	g.setColor(Color.BLACK);
	    	g.drawRect(j*BLOCK_SIZE,625+(k*BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE);
	    	}
	    
	    }
	    g.setColor(Color.RED);
	    g.drawRect(a*BLOCK_SIZE, b*BLOCK_SIZE , BLOCK_SIZE, BLOCK_SIZE);
	    
	    int v = -1;
		int w = 0;
		
		for(GameObject obj:active_player.getInventory()) {	            
	            v+=1;
	            if (v==5 && w<5) {
	            	v=0;
	            	w+=1;
	            }
	            g.drawImage(obj.getIm(), v*BLOCK_SIZE + 2 ,w*BLOCK_SIZE+627,BLOCK_SIZE-5,BLOCK_SIZE-5, null);
	            this.repaint();
	        } 
				
			if(active_player.getInventory().size() != 0) {
				active_object = active_player.getInventory().get(a+5*(b-25));   // on dÃ©fini l'active_object de notre inventaire apd du clic de la souris
			
			}
		}

    public void redraw() {
        this.repaint();
    }
    
    public void setObjects(ArrayList<GameObject> objects) {
    	this.objects = objects;
    }

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public Image getImage(String path) {
		Image tempImage = null; 
		try {
			URL imageurl = Map.class.getResource(path); 
    	tempImage = Toolkit.getDefaultToolkit().getImage(imageurl); 
    	}
    	catch(Exception e){
    	}
		return tempImage;
	}
	
	public GameObject getActiveObject() {
		return this.active_object;
	}
	public void setActiveObject() {
		a = 0 ;
		b = 25;
	}
	
	public Mouse getMouseController() {
		return this.mouseController;
	}
	
	public Player getActivePlayer() {
		return active_player;
	}
	public void setA(int a) {
		this.a = a;
	}
	public void setB(int b) {
		this.b = b;
	}
}
