package Model;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.BuyListener;
import View.Mag;
import View.SellListener;
import View.Window;

public class Shop extends BlockUnbreakable implements Activable{ 
	public Player active_player;
	public JFrame j = new JFrame("Shop");
	public JPanel pan = new JPanel();
	public JButton sb = new JButton("Sell");
	public JButton bb = new JButton("Buy");
	public Game g;
	public Mag mag;
	private ArrayList<GameObject> catalogue = new ArrayList<GameObject>();
	private ArrayList<Integer> stock = new ArrayList<Integer>();

	public Shop(int X, int Y) {
		super(X, Y,1,1);
		mag = new Mag(this);
	}
	
	// on crée notre magasin qui est initialement un simple objet, toutefois, dans ses attributs, ils possède une window, celle-ci est rendue visible, lorsque 
	// notre objet est activé.
	
	//Dans game, on appel SetShop, qui construit notre fenetre. Notre fenetre est constitué de mag, notre magasin
	// qui représent les différents articles ds la grille d'articles et leur pris etc..
	// Initialement, il n'y a pas d'article, c'est pourquoi on en ajoute avec SetStockandCatalogue. Mag possède une liste d'objet et une liste de stock de ces objets
	// on peut ajouter n'importe quel objet a notre guise et en mettre autant qu'on veut. SetShop et SetStockandCatalogue, sont appelés dans Game.
	// A la window, on ajoute 2 boutons, avec des listeners. Lorsqu'on appelle le bouton buy, ca identifie l'élément du catalogue séléctionné, et ca effectue buy, qui 
	// va modifié l'argent de notre joueur et ajouter l'objet a son inventaire, par la même occasion ca diminue notre stock.
	// Si l'objet est activable ou breakable, on prend soins de lui appliquer attachdeletable et update.activePlayer, il est donc nécéssaire que shop prenne connaisance de game
	// mag quant a lui prend connaissance de game en prennant connaissance de shop.
	
	public void SetShop() {	
		j.getContentPane().setLayout(new BorderLayout());
		sb.addActionListener(new SellListener(this));
	    bb.addActionListener(new BuyListener(this)); 
      	pan.add(sb, BorderLayout.LINE_START);
		pan.add(bb, BorderLayout.LINE_END);
		j.getContentPane().add(mag,BorderLayout.LINE_START);
		j.getContentPane().add(pan,BorderLayout.LINE_END);
		j.setSize(new Dimension(400,490));	
	}
	
	public void SetStockandCatalogue(GameObject a, int b) { 
		
		if(catalogue.size()<10 ) { 
			catalogue.add(a);                           
		    stock.add(b);
	      }  
		else if(catalogue.size() == 10) {
			System.out.println("Le magasin est full");
		}
		mag.repaint();
		}

	
	 // techniquement c'est nous qui allons définir le catalogue dans game, donc il n'est pas nécéssaire 
	// de traiter le cas où on veut mettre plus de 10 objets, mais il est bon de rappeler qu'on ne peut mettre
	 // plus de 10 objets, au cas où on serait distrait.
	
	
	
	
	
	public  void activate() {
		j.setVisible(true);
		
	}

	@Override
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;
		
	}
	
	
	public ArrayList<GameObject> getCatalogue(){
		return this.catalogue;
	}
	
	public ArrayList<Integer> getStock(){
		return this.stock;
	}
	
	public void Sell() {
		if(active_player.getActiveObject() instanceof SBableObject) {
			active_player.getInventory().remove(active_player.getActiveObject()); 
			active_player.setMoney(((SBableObject)active_player.getActiveObject()).getPrice()*80/100);
			if(active_player.getInventory().size() != 0) {
				active_player.setActiveObject(0,25);
			}  // car celui-ci rest un active_object sans Ãªtre ds l'invetaire...
		}
	}
	
	public void Buy() {
		int PositionSelec = mag.getPositionSelec();
		if(PositionSelec<= catalogue.size()) {                   
			if(stock.get(PositionSelec) > 0) {
				if(active_player.getMoney()>= ((SBableObject)catalogue.get(PositionSelec)).getPrice()) {
					active_player.setMoney(-(((SBableObject)catalogue.get(PositionSelec)).getPrice()));
					stock.set(PositionSelec,stock.get(PositionSelec)-1);
					j.repaint();
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
					active_player.addtoinventory(a);
					if(a instanceof Activable) {
						((Activable) a).updateActivePlayer(active_player);
		}
		}
		}                                                                  
		}
		
}
	public void newMag() {
		Mag m = new Mag(this);
	}
	/*public void setButtons() {
		JButton sb = new JButton("Sell");
		JButton bb = new JButton("Buy");
	}*/
}
