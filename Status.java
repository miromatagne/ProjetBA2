package View;

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
import Model.Activable;
import Model.Apple;
import Model.Eldery;
import Model.GameObject;
import Model.Player;
import Model.Toilet;
import Model.Vodka;

public class Status extends JPanel {
	private ArrayList<Player> players = null;
	private int BAR_LENGTH = 60; //Longueur de la barre d'énergie
	private int BAR_WIDTH = 20; //Largeur de la barre d'énergie (hauteur)
	private int AVATAR_SIZE = 30; //taille du carré bleu en haut à droite de l'interface graphique
	private int BLOCK_SIZE = 25;
	private int PREVIEW_SIZE = 80;
	private ArrayList<GameObject> inventory = null;
	private Mouse mouseController = new Mouse();
	private ArrayList<GameObject> objects;
	private Player active_player;

    public Status() {
        this.setPreferredSize(new Dimension(450, 600)); //Taille de la zone avec l'énergie etc... a droite de la Map ?
        this.setBackground(Color.LIGHT_GRAY); //Couleur du background de la zone avec l'énergie etc
        this.setOpaque(true); //Pas de changement quand je met en commentaire
        addMouseListener(new MouseListener() {
 	       
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/BLOCK_SIZE;           // on divise la position x et y par la taille du bloc ce qui renvoie un double
				int y = e.getY()/BLOCK_SIZE;           // on prend ensuite l'entier le plus proche ce qui renvoie enft le numÃ©ro de notre block 
				                                      // sur la map
			                                          // on attribue a x et y l'endroit de la position de la souris
				
				mouseController.statusEvent(x,y);
				
			}
			
		
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
    }
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		int i = 0;
		int totalMoney = 0;
		Image apple = getImage("Apple.PNG");
        Image vodka = getImage("Vodka.PNG");
        Image toilet = getImage("toilet.PNG");
		for(Player p : players) {
			if(p.isActivePlayer()) {
				active_player = p;
			}
		}
		for(GameObject o : objects) {
			if(o.getPosX()== active_player.getFrontX() && o.getPosY() == active_player.getFrontY() && o instanceof Activable) {
				if(o instanceof Apple) {
	            	g.drawImage(apple, 150, 25, PREVIEW_SIZE, PREVIEW_SIZE, null);
	                this.repaint();
	            }
	            else if(o instanceof Vodka) {
	            	g.drawImage(vodka, 150, 25, PREVIEW_SIZE + 30, PREVIEW_SIZE + 30, null);
	                this.repaint();
	            }
	            else if(o instanceof Toilet) {
	            	g.drawImage(toilet, 150, 25, PREVIEW_SIZE, PREVIEW_SIZE, null);
	                this.repaint();
	            }
	            else {
	            // selon les diffÃ©rents cas on change le set color
	            // les rectangles dessinÃ©s ci-dessous auront diffÃ©rentes couleurs
	            	g.setColor(Color.RED);
	            	g.fillRect(150, 25, PREVIEW_SIZE, PREVIEW_SIZE);
	            	g.setColor(Color.BLACK);
	            	g.drawRect(150, 25, PREVIEW_SIZE, PREVIEW_SIZE);
	            }
			}
		}
		int color = active_player.getColor();
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
	    
	    int v = -1;
		int w = 0;
		
		for(GameObject obj:inventory) {	
			color=obj.getColor();
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
	            if(v<5) {
	            	if(obj instanceof Apple) {
	            		g.drawImage(apple, v*BLOCK_SIZE + 2 ,w*BLOCK_SIZE+627,BLOCK_SIZE-5,BLOCK_SIZE-5, null);
	                    this.repaint();
	            	}
	            	else if(obj instanceof Vodka) {
	            		g.drawImage(vodka, v*BLOCK_SIZE + 2 ,w*BLOCK_SIZE+627,BLOCK_SIZE-5,BLOCK_SIZE-5, null);
	                    this.repaint();
	            	}
	            	else {
	            	g.fillRect(v*BLOCK_SIZE+2, 627+w*BLOCK_SIZE, BLOCK_SIZE-5, BLOCK_SIZE-5);
	            	}
	            }
	            else if (v==5 && w<5) {
	            	v=0;
	            	w+=1;
	            	g.fillRect(v*BLOCK_SIZE+2,627+w*BLOCK_SIZE, BLOCK_SIZE-5, BLOCK_SIZE-5);
	            }          
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
	
	public void setInventory(ArrayList<GameObject> inventory) {
		this.inventory=inventory;
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
