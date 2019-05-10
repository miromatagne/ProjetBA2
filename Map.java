package View;

import Model.Apple;
import Model.Bed;
import Model.Block;
import Model.Computer;
import Model.Directable;
import Model.GameObject;
import Model.Pill;
import Model.Stairs;
import Model.Toilet;
import Model.Vodka;

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

public class Map extends JPanel implements Serializable{
    private ArrayList<GameObject> objects = null; //La classe GameObject sont des objets ayant une position et une couleur
    public final int MAP_SIZE = 25;               // final permet d'empecher la modification
    private int BLOC_SIZE = 38;
    private Mouse mouseController = null;
    public int etage;
    public SourisListener souris = new SourisListener(this);

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(MAP_SIZE*BLOC_SIZE, MAP_SIZE*BLOC_SIZE));
        addMouseListener(souris);
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
        for (GameObject object : this.objects) { // dans cette boucle, on va colorer certains rectangles correspondant a des objets, on parcourt en effet une liste d'objet, 
        	// qui on le sait son caractériser par une position et une couleur(un chiffre en réalité auquel on associe une couleur ci-dessous)
        	// on parcourt notre liste d'objet, pour chaque objet on en prend la position, et on va tracer un rectangle en cette position, on attribue ensuite une couleur a ce rectangle
        	// on sait ainsi représenter des objets sur la map, cette partie du code nous permet de représenter des objets ou bien de créer des pièces.
        	int x = object.getPosX();
            int y = object.getPosY();
            int color = object.getColor();
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
            } else if (color == 9 ) {
            	g.setColor(Color.BLACK);
            }
            
            //if(object instanceof Apple) {
            g.drawImage(object.getIm(), x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE,object.getLength()*BLOC_SIZE, null);
            this.repaint();
           /*}
            else if(object instanceof Computer) {
            	g.drawImage(computer, x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE-2,object.getLength()*BLOC_SIZE-2, null);
                this.repaint();
            }
            else if(object instanceof Pill) {
            	g.drawImage(pill, x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE-2,object.getLength()*BLOC_SIZE-2, null);
                this.repaint();
            }
            else if(object instanceof Stairs) {
            	if(((Stairs) object).getEtageB() < ((Stairs) object).getEtageH()) {
            		g.drawImage(s1, x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE-2,object.getLength()*BLOC_SIZE-2, null);
            	}
            	else {
            		g.drawImage(s2, x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE-2,object.getLength()*BLOC_SIZE-2, null);
            	}
                this.repaint();
            }
            else if(object instanceof Vodka) {
            	g.drawImage(vodka, x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE-2,object.getLength()*BLOC_SIZE-2, null);
                this.repaint();
            }
            else if(object instanceof Bed) {
            	g.drawImage(bed, x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE-2,object.getLength()*BLOC_SIZE-2, null);
                this.repaint();
            }
            else if(object instanceof Toilet) {
            	g.drawImage(toilet, x*BLOC_SIZE ,y*BLOC_SIZE,object.getWidth()*BLOC_SIZE-2,object.getLength()*BLOC_SIZE-2, null);
                this.repaint();
            }
            else {
            // selon les différents cas on change le set color
            // les rectangles dessinés ci-dessous auront différentes couleurs
            	g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE - 2, BLOC_SIZE - 2);
            	g.setColor(Color.BLACK);
            	g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE - 2, BLOC_SIZE - 2);
            }*/
            // Decouper en fontions
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
}
