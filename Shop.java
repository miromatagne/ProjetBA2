package Model;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.BuyListener;
import View.Mag;
import View.SellListener;
import View.Window;

public class Shop extends BlockUnbreakable implements Activable{ 
	public Player active_player;
	public Mag mag = new Mag();
	public JFrame j = new JFrame("Shop");
	public JPanel pan = new JPanel();
	public JButton sb = new JButton("Sell");
	public JButton bb = new JButton("Buy");
	public Game g;
	public Window w; 

	public Shop(int X, int Y, Image i) {
		super(X, Y,1,1,i);
		
	}
	
	// on cr�e notre magasin qui est initialement un simple objet, toutefois, dans ses attributs, ils poss�de une window, celle-ci est rendue visible, lorsque 
	// notre objet est activ�.
	
	//Dans game, on appel SetShop, qui construit notre fenetre. Notre fenetre est constitu� de mag, notre magasin
	// qui repr�sent les diff�rents articles ds la grille d'articles et leur pris etc..
	// Initialement, il n'y a pas d'article, c'est pourquoi on en ajoute avec SetStockandCatalogue. Mag poss�de une liste d'objet et une liste de stock de ces objets
	// on peut ajouter n'importe quel objet a notre guise et en mettre autant qu'on veut. SetShop et SetStockandCatalogue, sont appel�s dans Game.
	// A la window, on ajoute 2 boutons, avec des listeners. Lorsqu'on appelle le bouton buy, ca identifie l'�l�ment du catalogue s�l�ctionn�, et ca effectue buy, qui 
	// va modifi� l'argent de notre joueur et ajouter l'objet a son inventaire, par la m�me occasion ca diminue notre stock.
	// Si l'objet est activable ou breakable, on prend soins de lui appliquer attachdeletable et update.activePlayer, il est donc n�c�ssaire que shop prenne connaisance de game
	// mag quant a lui prend connaissance de game en prennant connaissance de shop.
	
	public void SetShop() {	
		mag.assignShop(this);
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
		
		if(mag.getCatalogue().size()<10 ) { 
			mag.getCatalogue().add(a);                           
		    mag.getStock().add(b);
	      }  
		else if(mag.getCatalogue().size() == 10) {
			System.out.println("Le magasin est full");
		}
		mag.repaint();
		}

	
	 // techniquement c'est nous qui allons d�finir le catalogue dans game, donc il n'est pas n�c�ssaire 
	// de traiter le cas o� on veut mettre plus de 10 objets, mais il est bon de rappeler qu'on ne peut mettre
	 // plus de 10 objets, au cas o� on serait distrait.
	
	
	
	
	
	public  void activate() {
		j.setVisible(true);
		
	}

	@Override
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;
		
	}
	
	public void updateActiveWindow(Window w) {
		this.w = w;
	}

}
