package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controller.MagListener;
import Controller.Mouse;
import Model.*;

public class Mag extends JPanel {
	private int BLOCLENGHT = 100;
	private int BLOCHIGHT = 40;
	private int SHOPSIZE = 10;
	//private Mouse mousecontroller = new Mouse();
	private int OBJSIZE = 30;
	private static ArrayList<Image> imlist = new ArrayList<Image>();
	public int PositionSelec = 0;
	private Shop shop;
	private MagListener mouse = new MagListener(this);
	
	
	public Mag(Shop shop) {
		 this.setPreferredSize(new Dimension(SHOPSIZE*BLOCLENGHT, SHOPSIZE*BLOCHIGHT));
		 addMouseListener(mouse);
		 this.shop = shop;
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
        imlist.add(getImage("Wall3.jpg"));
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
	
	
	public void paint (Graphics g) {
		   for (int i = 0; i < SHOPSIZE; i++) { 
			   int y = i;
	            for (int j = 0; j < 2; j++) {
	                int x = j;
	                g.setColor(Color.LIGHT_GRAY);  
	                g.fillRect(x * BLOCLENGHT, y * BLOCHIGHT, BLOCLENGHT , BLOCHIGHT ); 
	                g.setColor(Color.BLACK);  
	                g.drawRect(x * BLOCLENGHT, y * BLOCHIGHT, BLOCLENGHT , BLOCHIGHT ); 
	          
	               
	            }
	        } 
		   int v = 0;
		   for(GameObject obj : shop.getCatalogue()) {     
			   //System.out.println(getIm(obj));
	            g.drawImage(getIm(obj),BLOCLENGHT+2,v*BLOCHIGHT+2,OBJSIZE,OBJSIZE,null);
	            g.setColor(Color.BLACK);
	            //g.fillRect(BLOCLENGHT+2, v*BLOCHIGHT+2, 20, 20);
	            g.drawString(((SBableObject)obj).getName(),BLOCLENGHT+40,v*BLOCHIGHT+25);
			    g.drawString("Price"+ " : " + ((SBableObject)obj).getPrice()+"€",5,v*BLOCHIGHT+15);
			    g.drawString("Stock : "+ shop.getStock().get(v),5,v*BLOCHIGHT+35);
			    v+=1;
			    shop.j.repaint();
		   }
			 g.setColor(Color.RED);
			 g.drawRect(BLOCLENGHT, PositionSelec*BLOCHIGHT, BLOCLENGHT, BLOCHIGHT);
		}
	
	public Shop getShop() {
		return this.shop;
	}
	
	public void setPositionSelec(int a) {
		PositionSelec = a;
	}
	
	public int getPositionSelec() {
		return this.PositionSelec;
		
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
}

	
	
	

