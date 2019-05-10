package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controller.MagListener;
import Controller.Mouse;
import Model.BlockBreakable;
import Model.Bread;
import Model.GameObject;
import Model.Computer;
import Model.*;
import Model.Avocado;
import Model.SBableObject;
import Model.Shop;

public class Mag extends JPanel implements Serializable {
	private ArrayList<GameObject> catalogue = new ArrayList<GameObject>();
	private int BLOCLENGHT = 100;
	private int BLOCHIGHT = 40;
	private int SHOPSIZE = 10;
	//private Mouse mousecontroller = new Mouse();
	private int OBJSIZE = 30;
	private ArrayList<Integer> stock = new ArrayList<Integer>();
	public int PositionSelec = 0;
	private Shop shop;
	private MagListener mouse = new MagListener(this);
	
	
	public Mag() {
		 this.setPreferredSize(new Dimension(SHOPSIZE*BLOCLENGHT, SHOPSIZE*BLOCHIGHT));
		 addMouseListener(mouse);
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
		   for(GameObject obj : catalogue) {       
	            g.drawImage(obj.getIm(),BLOCLENGHT+2,v*BLOCHIGHT+2,OBJSIZE,OBJSIZE,null);
	            g.setColor(Color.BLACK);
	            //g.fillRect(BLOCLENGHT+2, v*BLOCHIGHT+2, 20, 20);
	            g.drawString(((SBableObject)obj).getName(),BLOCLENGHT+40,v*BLOCHIGHT+25);
			    g.drawString("Price"+ " : " + ((SBableObject)obj).getPrice()+"€",5,v*BLOCHIGHT+15);
			    g.drawString("Stock : "+ stock.get(v),5,v*BLOCHIGHT+35);
			    v+=1;
			    shop.j.repaint();
		   }
			 g.setColor(Color.RED);
			 g.drawRect(BLOCLENGHT, PositionSelec*BLOCHIGHT, BLOCLENGHT, BLOCHIGHT);
		}
	
	public void assignShop(Shop sh) {
		this.shop = sh;
	}
	
	public  ArrayList<GameObject> getCatalogue(){
		return this.catalogue;
	}
	
	public ArrayList<Integer> getStock(){ 
		return this.stock;
	}
	public void Sell() {
		//if(shop.w.getStatus() != null) {
			GameObject a = shop.w.getStatus().getActiveObject();
			if(a instanceof SBableObject) {
				shop.w.getStatus().setActiveObject();
				shop.active_player.getInventory().remove(a);  // a mon avis on rencotre un problème lorsqu'on retire l'objet de l'invetaire
				shop.active_player.setMoney(((SBableObject)a).getPrice()*80/100);  // car celui-ci rest un active_object sans être ds l'invetaire...
			}
		//}
	}
	public void Buy() {
		if(PositionSelec<= catalogue.size()) {                   
		if(stock.get(PositionSelec) > 0) {
		if(shop.active_player.getMoney()>= ((SBableObject)catalogue.get(PositionSelec)).getPrice()) {
		shop.active_player.setMoney(-(((SBableObject)catalogue.get(PositionSelec)).getPrice()));
		stock.set(PositionSelec,stock.get(PositionSelec)-1);
		shop.j.repaint();
		String ObjType = ((SBableObject)catalogue.get(PositionSelec)).getName();                   
		GameObject a = null;                                                               
		switch(ObjType) {                                                            
		case "Apple" : a = new Apple(1,1);break;
		case "Avocado" : a = new Avocado(1,1);break;
		case "Bed" : a = new Bed(1,1);break;
		case "Bread" : a = new Bread(1,1);break;
		case "Bullet" : a = new Bullet(1,1);break;
		case "Cheese" : a = new Cheese(1,1);break;
		case "Cherry" : a = new Cherry(1,1);break;
		case "Chair" : a = new Chair(1,1);break;
		case "Couch" : a = new Couch(1,1);break;
		case "Chicken" : a = new Chicken(1,1);break;
		case "Computer" : a = new Computer(1,1);break;
		case "Cookie" : a = new Cookie(1,1);break;
		case "Gun" : a = new Gun(1,1);break;
		case "Panade" : a = new Panade(1,1);break;
		case "Pill" : a = new Pill(1,1);break;
		case "Shrimp" : a = new Shrimp(1,1);break;
		case "Strawberry" : a = new Strawberry(1,1);break;
		case "Table" : a = new Table(1,1);break;
		case "Tomato" : a = new Tomato(1,1);break;
		case "Vodka" : a = new Vodka(1,1,10);break;
		case "Watermelon" : a = new Watermelon(1,1);break;
		}  
		shop.active_player.addtoinventory(a);
		if(a instanceof Activable) {
			((Activable) a).updateActivePlayer(shop.active_player);
		}
		}
		}                                                                  
		}
		
}
	public Shop getShop() {
		return this.shop;
	}
	
	public void setPositionSelec(int a) {
		PositionSelec = a;
	}
}

	
	
	

