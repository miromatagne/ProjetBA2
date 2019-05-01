package View;

import Model.Apple;
import Model.Directable;
import Model.GameObject;
import Model.Toilet;
import Model.Vodka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controller.Mouse;

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null; //La classe GameObject sont des objets ayant une position et une couleur
    public final int MAP_SIZE = 40;               // final permet d'empecher la modification
    private int BLOC_SIZE = 20;
    private Mouse mouseController = null;

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(MAP_SIZE*BLOC_SIZE, MAP_SIZE*BLOC_SIZE));
        addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/BLOC_SIZE;           // on divise la position x et y par la taille du bloc ce qui renvoie un double
				int y = e.getY()/BLOC_SIZE;           // on prend ensuite l'entier le plus proche ce qui renvoie enft le numéro de notre block 
				                                      // sur la map
				mouseController.mapEvent(x, y);       // on attribue a x et y l'endroit de la position de la souris
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
    }

    public void paint(Graphics g) {
        for (int i = 0; i < MAP_SIZE; i++) { 
            for (int j = 0; j < MAP_SIZE; j++) {
                int x = i;
                int y = j;
                g.setColor(Color.GREEN);  // tout ce qui est dessiné par la suite est blanc
                g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE , BLOC_SIZE ); // ( localisation en x, en y, dimension en x, en y)
                // on multiplie x et y par la taille des blocks de facon a ce que les blocks ne se superposent pas.
                
                // ici ondessine des rectangles, tehcniquement cette ligne-ci est inutile
                //(à moins qu'on change de fond) car elle dessine des rectangles
                //blancs sur un fond blanc, d'autres part a la ligne suivante on trace le contour de ces rectangles 
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE , BLOC_SIZE ); // ici on dessine les contours des rectangles
            }
         
        }
        
        for( int i = 5 ; i<36; i++) {
        	for(int j = 5; j<36; j++) {
        		int x = i;
        		int y = j;
        		g.setColor(Color.LIGHT_GRAY);
        		g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE , BLOC_SIZE );
        		g.setColor(Color.DARK_GRAY);
        		g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE , BLOC_SIZE );
        				
        	}
        		
        }
        	
        // dans cette boucle on défini la map en dessinant plein de rectangle blanc
        
        // que représente le g. et comment sait-on de quoi on définit la couleur ? lorsqu'on définit g.setcolor(couleur) tout les dessin qui suivent se font dans la couleur définie

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
            
            Image apple = getImage("Apple.PNG");
            Image vodka = getImage("Vodka.PNG");
            Image toilet = getImage("toilet.PNG");
            if(object instanceof Apple) {
            	g.drawImage(apple, x*BLOC_SIZE ,y*BLOC_SIZE,BLOC_SIZE-2,BLOC_SIZE-2, null);
                this.repaint();
            }
            else if(object instanceof Vodka) {
            	g.drawImage(vodka, x*BLOC_SIZE ,y*BLOC_SIZE,BLOC_SIZE-2,BLOC_SIZE-2, null);
                this.repaint();
            }
            else if(object instanceof Toilet) {
            	g.drawImage(toilet, x*BLOC_SIZE ,y*BLOC_SIZE,BLOC_SIZE-2,BLOC_SIZE-2, null);
                this.repaint();
            }
            else {
            // selon les différents cas on change le set color
            // les rectangles dessinés ci-dessous auront différentes couleurs
            	g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE - 2, BLOC_SIZE - 2);
            	g.setColor(Color.BLACK);
            	g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE - 2, BLOC_SIZE - 2);
            }
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

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    public void redraw() {
        this.repaint();
    }

	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
	
	public Image getImage(String path) {
		Image tempImage = null; 
		try {
			URL imageurl = Map.class.getResource(path); 
    	tempImage = Toolkit.getDefaultToolkit().getImage(imageurl); 
    	}
    	catch(Exception e){
    		System.out.println(""+e.getMessage()); 
    		
    	}
		return tempImage;
	}
}
