package Model;

import View.Window;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver, Observer {
    private Player active_player = null; 
    private static ArrayList<Window> windows;
    private int size;
    //private int numberOfBreakableBlocks = 40;
    public Game(ArrayList<Window> windows) {
        this.windows = windows;
        Ordi o1 = new Ordi(20,20,5);
        Three T1 = new Three(10,13);
        T1.attachDeletable(this);
        Pomme po = new Pomme(10,14,1);
        po.attachDeletable(this);
        Window windowInit = windows.get(0);
        
        windowInit.objects.add(o1);
        windowInit.objects.add(T1);
        windowInit.objects.add(po);

        size = windows.get(0).getMapSize();
        // Creating one Player at position (10,10)
        int numberOfPlayers = 4;
        
        for (int i = 0; i < numberOfPlayers; i++) {
        	Player p = new Player(10+i, 10, 2+i);
        	windowInit.objects.add(p);
        	windowInit.players.add(p);
        	//window.setPlayer(p);
        }
        //active_window = window;
        windowInit.setPlayers(windowInit.players);
        windowInit.setActiveWindow();
        //players.get(0); //obtenir le premier joueur
        
        active_player = windowInit.players.get(0);
        active_player.setActivePlayer();//premier joueur actif par défaut
        this.notifyObservers();
        Thread t1 = new Thread(new EnergyLoss(active_player,windowInit));
		t1.start();

        // Map building
        for (int i = 0; i < size; i++) { //crée le contour de blocs gris foncé, incassables
        	for (Window window : windows) {
            window.objects.add(new BlockUnbreakable(i, 0));
            window.objects.add(new BlockUnbreakable(0, i));
            window.objects.add(new BlockUnbreakable(i, size - 1));
            window.objects.add(new BlockUnbreakable(size - 1, i));
        	}
        }      
        for (int i = 5; i < 36; i++) {
            windows.get(0).objects.add(new BlockUnbreakable(i, 5));
            windows.get(0).objects.add(new BlockUnbreakable(5, i));
            windows.get(0).objects.add(new BlockUnbreakable(35, i));  // ici on construit le contour de la maison les 3 faces fermées
        }
        
        for (int i = 5; i < 36; i++) {
        	if (i == 20) {
        	}
        	else {
        		windows.get(0).objects.add(new BlockUnbreakable(i,35));  // ici on construit la face de la maison avec l'entrée
        	}
        	
        }
        windowInit.objects.add(new Stairs(12, 8,windows, 0, 1, this));
        windows.get(1).objects.add(new Stairs(12, 8,windows, 1, 0, this));
        windows.get(1).objects.add(new Stairs(20, 4,windows, 1, 2, this));
        windows.get(2).objects.add(new Stairs(20, 4,windows, 2, 1, this));
        Random rand = new Random();
        //for (int i = 0; i < 40; i++) {
            //int x = rand.nextInt(size-4) + 2; //Construit les blocs cassables au sein de la map, +2 pour pas avoir de blocs le long des bords.
            //int y = rand.nextInt(size-4) + 2;
            //int lifepoints = rand.nextInt(5) + 1;
            //BlockBreakable block = new BlockBreakable(x, y, lifepoints);
            //block.attachDeletable(this);
            //getActiveWindow(windows).objects.add(block);
        //}
        for (Window window : windows) {
        	window.setGameObjects(window.objects);
        	window.setInventory(active_player.getInventory());
        	notifyView();
        }
    }


    public void movePlayer(int x, int y) {
        int nextX = active_player.getPosX() + x; //Coordonnées du prochain déplacement (1 case)
        int nextY = active_player.getPosY() + y;
        boolean obstacle = false;
        for (GameObject object : getActiveWindow(windows).objects) {
            if (object.isAtPosition(nextX, nextY)) { //Check si il y a un objet là où il veut se déplacer
                obstacle = object.isObstacle(); //Renvoie true si il y a un objet
            }
            if (obstacle == true) { //On interrompt alors la boucle parce qu'un objet est présent donc on ne peut pas se déplacer là
                break;
            }
        }
        active_player.rotate(x, y); //Fait tourner le perso sur lui meme, on voit la petite barre noire qui tourne
        if (obstacle == false) {
            active_player.move(x, y);
        }
        notifyView();
    }

    public void tirePlayer() {
    	active_player.tire();
    	notifyView();
    }
    public void action() {
        Activable aimedObject = null;
		for(GameObject object : getActiveWindow(windows).objects){
			if(object.isAtPosition(active_player.getFrontX(),active_player.getFrontY())){ //Si l'objet est situé devant le personnage
			    if(object instanceof Activable){
			        aimedObject = (Activable) object;
			    }
			}
		}
		if(aimedObject != null){
		    aimedObject.activate();
            notifyView();
		}
        
    }

    private void notifyView() {
    	getActiveWindow(windows).update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.getActiveWindow(windows).objects;
    }
    
    public ArrayList<GameObject> getGameObjectsNotPlayers() {
    	ArrayList<GameObject> objectsNotPlayers = new ArrayList<GameObject>();
    	for (GameObject o : getActiveWindow(windows).objects) {
    		if (!(o instanceof Player)) {
    			objectsNotPlayers.add(o);
    		}
    	}
    	return objectsNotPlayers;
    }

    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
    	this.getGameObjects().remove(ps); //Permet de supprimer des objets dans la liste d'objets
        if (loot != null) {
        	getActiveWindow(windows).objects.addAll(loot);
        }
        notifyView();
    }


    public void playerPos() {
        System.out.println(active_player.getPosX() + ":" + active_player.getPosY());
        
    }

	public void stop() {
		getActiveWindow(windows).dispatchEvent(new WindowEvent(getActiveWindow(windows), WindowEvent.WINDOW_CLOSING)); //Ferme le jeu
	}


	public void sendPlayer(int x, int y) { 
		boolean move = true;
		boolean change = false;
		Player pl = null;
		for (GameObject o : getActiveWindow(windows).objects) {
			if (x == o.getPosX() && y == o.getPosY()) {
				move = false;
			}
		}
		for (Player p : getActiveWindow(windows).players) {
			if (x == p.getPosX() && y == p.getPosY()) {
				change = true;
				pl = p;
			}
		}
		if (move == true) {
			Thread t = new Thread(new AStarThread(this, active_player, x,  y, getActiveWindow(windows)));
			t.start(); //Active le thread qui va faire aller le joueur jusque la ou on a cliqué
		}
		if (change == true) {
			active_player = pl;
			active_player.setActivePlayer();
			this.notifyObservers();
			getActiveWindow(windows).setInventory(active_player.getInventory());
			Thread t2 = new Thread(new EnergyLoss(active_player,getActiveWindow(windows)));
			t2.start();
		}
	}
	public Window getActiveWindow(ArrayList<Window> windows) {
		Window active_window = null;
		for (Window window : windows) {
			if (window.isActiveWindow()) {
				active_window = window;
			}
		}
		return active_window;
	}
	public Player getActivePlayer() {
		return active_player;
	}
	public void addtoInventory(int x, int y) {
		for(GameObject i : getActiveWindow(windows).objects) {
    			if(i.isAtPosition(x, y) && i.isAddable()) {
    				if(i.isAtPosition(active_player.getFrontX(),active_player.getFrontY())){
    					active_player.addtoinventory(i,getActiveWindow(windows).objects);
    					notifyView();
    					
    					
    				}
    			}
  
		}
		
		
		// cette fonction permet de stocker un objet dans l'inventaire. Lorsqu'on clique a un endroit, elle parcourt l'ensemble des objets de la map pour voir si ils se trouvent
		// a cet endroit, elle verifie par la même occasion si l'objet est ajoutable. Si c'est le cas, la fonction vérifie si l'objet est en face de l'active player
		// si c'est le cas, elle applique addtoinventory, ce qui ajoute l'objet à l'inventaire de notre active player et l'enlève de la liste des objets
		// ainsi en effectuant notifyview, on redescine la map et l'inventaire, l'objet n'apparaitra alors plus sur la map, mais dans l'inventaire.
				
	}


	@Override
	public void notifyObservers() {
		for (GameObject o : getActiveWindow(windows).objects) {
			if (o instanceof Activable) {
				((Activable) o).updateActivePlayer(active_player);
			}
		}
	}
	
	
    /*public void EarnMoney(int x, int y) {
    	for(GameObject i : getActiveWindow(windows).objects) {
    		if(i instanceof Gain)
    			if(i.isAtPosition(x, y)) {
    				if(i.isAtPosition(active_player.getFrontX(),active_player.getFrontY())){
    					Thread t3 = new Thread(new MoneyEvolution(active_player, getActiveWindow(windows),i,getActiveWindow(windows).objects));
    					t3.start();
    					
    					
    				}
    			}
  
    	}
    	
    } */

}