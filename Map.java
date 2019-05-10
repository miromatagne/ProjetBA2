package View;

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
import Model.Directable;
import Model.Eldery;
import Model.Fridge;
import Model.FurnitureStore;
import Model.GameObject;
import Model.Gun;
import Model.Mur;
import Model.Panade;
import Model.Pill;
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

import java.awt.AlphaComposite;
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
import Controller.SourisListener;

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null; //La classe GameObject sont des objets ayant une position et une couleur
    public final int MAP_SIZE = 25;               // final permet d'empecher la modification
    private int BLOC_SIZE = 38;
    private Mouse mouseController = null;
    public int etage;
    private static ArrayList<Image> imlist = new ArrayList<Image>();
    public SourisListener souris = new SourisListener(this);

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(MAP_SIZE*BLOC_SIZE, MAP_SIZE*BLOC_SIZE));
        addMouseListener(souris);
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
    
    static Image parquet = getImage("parquet3.PNG");

    public void paint(Graphics g) {
    	//float alpha = 0.5F;
    	//AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
    	//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(parquet, 0, 0, MAP_SIZE*BLOC_SIZE, MAP_SIZE*BLOC_SIZE,null);        
        	
        // dans cette boucle on défini la map en dessinant plein de rectangle blanc
        
        // que représente le g. et comment sait-on de quoi on définit la couleur ? lorsqu'on définit g.setcolor(couleur) tout les dessin qui suivent se font dans la couleur définie
        if(objects != null) {
        for (GameObject object : this.objects) {
        	int x = object.getPosX();
            int y = object.getPosY();
            g.drawImage(getIm(object), x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE,object.getLength()*BLOC_SIZE, null);
            this.repaint();
            if(object instanceof Directable) {
                int direction = ((Directable) object).getDirection();
                
                int deltaX = 0;
                int deltaY = 0;
                
                switch (direction) {
                case Directable.EAST:
                    deltaX = +(BLOC_SIZE-2)/2;
                    break;
                case Directable.NORTH:
                    deltaY = -(BLOC_SIZE-2)/2;
                    break;
                case Directable.WEST:
                    deltaX = -(BLOC_SIZE-2)/2;
                    break;
                case Directable.SOUTH:
                    deltaY = (BLOC_SIZE-2)/2;
                    break;
                }

                int xCenter = x * BLOC_SIZE + (BLOC_SIZE-2)/2;
                int yCenter = y * BLOC_SIZE + (BLOC_SIZE-2)/2;
                g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY);
            }
            // cette partie du code dessine la petit ligne présente sur notre personnage et indiquant l'orientation du personnage
        }
        }
    }

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    public void redraw() {
        this.repaint();
    }

	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
	
	public static Image getImage(String path) {
		Image tempImage = null; 
		try {
			URL imageurl = Block.class.getResource(path); 
    	tempImage = Toolkit.getDefaultToolkit().getImage(imageurl); 
    	}
    	catch(Exception e){
    	}
		return tempImage;
	}
	public void setEtage(int i) {
		this.etage= i;
	}
	public Mouse getMouseController() {
		return this.mouseController;
	}
	public void setImages(ArrayList<Image> i) {
		Map.imlist = i;
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
