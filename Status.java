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
import Model.Adult;
import Model.Apple;
import Model.Avocado;
import Model.Baby;
import Model.Bed;
import Model.Block;
import Model.Boisson;
import Model.Bread;
import Model.Bullet;
import Model.Chair;
import Model.Cheese;
import Model.Cherry;
import Model.Chicken;
import Model.Computer;
import Model.Cookie;
import Model.Couch;
import Model.Eldery;
import Model.Fridge;
import Model.FurnitureStore;
import Model.GameObject;
import Model.Gun;
import Model.Mur;
import Model.Panade;
import Model.Pill;
import Model.Player;
import Model.Shower;
import Model.Shrimp;
import Model.Soda;
import Model.Stairs;
import Model.Strawberry;
import Model.Table;
import Model.Target;
import Model.Toilet;
import Model.Tomato;
import Model.Tree;
import Model.Vodka;
import Model.Watermelon;

public class Status extends JPanel {
	private ArrayList<Player> players = null;
	private int BAR_LENGTH = 60; //Longueur de la barre d'énergie
	private int BAR_WIDTH = 20; //Largeur de la barre d'énergie (hauteur)
	private int AVATAR_SIZE = 30; //taille du carré bleu en haut à droite de l'interface graphique
	private int BLOCK_SIZE = 25;
	private int PREVIEW_SIZE = 80;
	private static ArrayList<Image> imlist = new ArrayList<Image>();
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
        imlist.add(getImage("Apple.PNG"));
        imlist.add(getImage("Adult1.PNG"));
        imlist.add(getImage("Avocado.PNG"));
        imlist.add(getImage("Baby.PNG"));
        imlist.add(getImage("Bed.PNG"));
        imlist.add(getImage("Boisson.PNG"));
        imlist.add(getImage("Bread.PNG"));
        imlist.add(getImage("Bullet.PNG"));
        imlist.add(getImage("Chair.PNG"));
        imlist.add(getImage("Cheese.PNG"));
        imlist.add(getImage("Cherry.PNG"));
        imlist.add(getImage("Chicken.PNG"));
        imlist.add(getImage("Computer.PNG"));
        imlist.add(getImage("Cookie.PNG"));
        imlist.add(getImage("Couch.PNG"));
        imlist.add(getImage("Eldery.PNG"));
        imlist.add(getImage("Fridge.PNG"));
        imlist.add(getImage("Gun.PNG"));
        imlist.add(getImage("Wall3.PNG"));
        imlist.add(getImage("Panade.PNG"));
        imlist.add(getImage("Pill.PNG"));
        imlist.add(getImage("FurnitureShop.PNG"));
        imlist.add(getImage("Shower2.PNG"));
        imlist.add(getImage("Shrimp.PNG"));
        imlist.add(getImage("Soda.PNG"));
        imlist.add(getImage("Stairs.PNG"));
        imlist.add(getImage("Strawberry.PNG"));
        imlist.add(getImage("Table.PNG"));
        imlist.add(getImage("Target.PNG"));
        imlist.add(getImage("Toilet.PNG"));
        imlist.add(getImage("Tomato.PNG"));
        imlist.add(getImage("Tree.PNG"));
        imlist.add(getImage("Vodka.PNG"));
        imlist.add(getImage("MelonWater.PNG"));
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
	            g.drawImage(getIm(o), 150, 25, PREVIEW_SIZE, PREVIEW_SIZE, null);
	            this.repaint();
	            }
		}
		Image player = null;
		if(active_player instanceof Eldery) {
			player = getIm(new Eldery(1,1));
		}
		else if(active_player instanceof Baby) {
			player = getIm(new Baby(1,1));
		}
		if(active_player instanceof Adult) {
			player = getIm(new Adult(1,1));
		}
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
	            g.drawImage(getIm(obj), v*BLOCK_SIZE + 2 ,w*BLOCK_SIZE+627,BLOCK_SIZE-5,BLOCK_SIZE-5, null);
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
	
	public void setImages(ArrayList<Image> i) {
		this.imlist = i;
	}
	
	public Image getIm(GameObject o) {
    	if (o instanceof Apple) {
    		return imlist.get(0);
    	}
    	else if (o instanceof Adult) {
    		return imlist.get(1);
    	}
    	else if (o instanceof Avocado) {
    		return imlist.get(2);
    	}
    	else if (o instanceof Baby) {
    		return imlist.get(3);
    	}
    	else if (o instanceof Bed) {
    		return imlist.get(4);
    	}
    	else if (o instanceof Boisson) {
    		return imlist.get(5);
    	}
    	else if (o instanceof Bread) {
    		return imlist.get(6);
    	}
    	else if (o instanceof Bullet) {
    		return imlist.get(7);
    	}
    	else if (o instanceof Chair) {
    		return imlist.get(8);
    	}
    	else if (o instanceof Cheese) {
    		return imlist.get(9);
    	}
    	else if (o instanceof Cherry) {
    		return imlist.get(10);
    	}
    	else if (o instanceof Chicken) {
    		return imlist.get(11);
    	}
    	else if (o instanceof Computer) {
    		return imlist.get(12);
    	}
    	else if (o instanceof Cookie) {
    		return imlist.get(13);
    	}
    	else if (o instanceof Couch) {
    		return imlist.get(14);
    	}
    	else if (o instanceof Eldery) {
    		return imlist.get(15);
    	}
    	else if (o instanceof Fridge) {
    		return imlist.get(16);
    	}
    	else if (o instanceof Gun) {
    		return imlist.get(17);
    	}
    	else if (o instanceof Mur) {
    		return imlist.get(18);
    	}
    	else if (o instanceof Panade) {
    		return imlist.get(19);
    	}
    	else if (o instanceof Pill) {
    		return imlist.get(20);
    	}
    	else if (o instanceof FurnitureStore) {
    		return imlist.get(21);
    	}
    	else if (o instanceof Shower) {
    		return imlist.get(22);
    	}
    	else if (o instanceof Shrimp) {
    		return imlist.get(23);
    	}
    	else if (o instanceof Soda) {
    		return imlist.get(24);
    	}
    	else if (o instanceof Stairs) {
    		return imlist.get(25);
    	}
    	else if (o instanceof Strawberry) {
    		return imlist.get(26);
    	}
    	else if (o instanceof Table) {
    		return imlist.get(27);
    	}
    	else if (o instanceof Target) {
    		return imlist.get(28);
    	}
    	else if (o instanceof Toilet) {
    		return imlist.get(29);
    	}
    	else if (o instanceof Tomato) {
    		return imlist.get(30);
    	}
    	else if (o instanceof Tree) {
    		return imlist.get(31);
    	}
    	else if (o instanceof Vodka) {
    		return imlist.get(32);
    	}
    	else if (o instanceof Watermelon) {
    		return imlist.get(33);
    	}
    	else {
    		return null;
    	}
    }
	public Image getImage(String path) {
		Image tempImage = null; 
		try {
			URL imageurl = Block.class.getResource(path); 
    	tempImage = Toolkit.getDefaultToolkit().getImage(imageurl); 
    	}
    	catch(Exception e){
    	}
		return tempImage;
	}
}
