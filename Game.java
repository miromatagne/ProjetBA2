package Model;

import View.Window;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements DeletableObserver, Observer,Serializable {
    private Player active_player = null; 
    private ArrayList<ModelWindow> windows;
    private int nbBebe;
    private int nbAdulte;
    private int nbVieux;
    ObjectFactory objectFactory = new ObjectFactory();
    //private int numberOfBreakableBlocks = 40;
    public Game(ArrayList<ModelWindow> windows, int nbBebe, int nbAdulte, int nbVieux) {
        this.windows = windows;
        this.nbBebe = nbBebe;
        this.nbAdulte = nbAdulte;
        this.nbVieux = nbVieux;
        ModelWindow windowInit = windows.get(0);
        for (int i = 0; i < nbAdulte; i++) {
        	Adult a = new Adult(10+i, 10);
        	windowInit.getObjects().add(a);
        	windowInit.getPlayers().add(a);
            Thread t1 = new Thread(new EnergyLoss(a));
    		t1.start();
        	//window.setPlayer(p);
        }
        for (int i = 0; i < nbVieux; i++) {
        	Eldery e = new Eldery(10+i, 11);
        	windowInit.getObjects().add(e);
        	windowInit.getPlayers().add(e);
            Thread t1 = new Thread(new EnergyLoss(e));
    		t1.start();
        	//window.setPlayer(p);
        }
        for (int i = 0; i < nbBebe; i++) {
        	Baby baby = new Baby(10+i, 12);
        	windowInit.getObjects().add(baby);
        	windowInit.getPlayers().add(baby);
            Thread t1 = new Thread(new EnergyLoss(baby));
    		t1.start();
        	//window.setPlayer(p);
        }
        
        //active_window = window;
        windowInit.setPlayers(windowInit.getPlayers());
        windowInit.setActiveWindow();
        //players.get(0); //obtenir le premier joueur
        active_player = windowInit.getPlayers().get(0);
        active_player.setActivePlayer();//premier joueur actif par défaut
		Thread r1 = new Thread(new Refresh(this));
        r1.start();
        FileReader fileReader;
        String line = null;
    	for(int k = 0; k < windows.size();k++) {
    		int j = 0;
    		if(k < 2) {
    			j = k;
    		}
    		else {
    			j = 1;
    		}
        	int nbLines = 0;
        	int nbColumns = 0;
        	int nbColumnsMin = 10000;
        	int nbColumnsMax = 0;
    		String fileName = "Etage" + Integer.toString(j);
    		List<String> ar = new ArrayList<String>();
			try {
				fileReader = new FileReader("C:\\Users\\Miro-Manuel\\Dropbox\\ULB\\BA2\\Projet Info\\Model\\"+fileName+".txt");
		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        while((line = bufferedReader.readLine()) != null) {
		        	nbLines += 1;
		        	String str[] = line.split(" ");
		        	nbColumns = str.length;
		        	if (nbColumns < nbColumnsMin) {
		        		nbColumnsMin = nbColumns;
		        	}
		        	else if (nbColumns > nbColumnsMax) {
		        		nbColumnsMax = nbColumns;
		        	}
		        	for(int i = 0; i < str.length; i++) {
		        		ar.add(str[i]);
		        	}
		        }
		        bufferedReader.close();
		        if(nbColumnsMin != nbColumnsMax || nbColumns != windows.get(k).getMapSize() || nbLines != windows.get(k).getMapSize()) {
		        	throw new Exception("Les dimensions du fichier sont incompatibles");
		        }
			} catch (FileNotFoundException e1) {
				System.out.println("Le fichier texte n'existe pas à cette adresse.");
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int ligne = 0;
			int colonne = 0;
			GameObject obj;
			for(String o : ar) {
				obj = objectFactory.createObject(o, colonne, ligne);
				this.notifyObservers();
				if(obj != null) {
					windows.get(k).getObjects().add(obj);
				}
				if(obj instanceof Deletable) {
					((Deletable) obj).attachDeletable(this);
				}
				if(colonne == nbColumns - 1) {
					colonne = 0;
					ligne += 1;
				}
				else {
					colonne += 1;
				}
			}
    	}
        Stairs s = new Stairs(12, 8,windows, 0, 1, this);
        windowInit.getObjects().add(s);
        for(int i = 1; i < windows.size() - 1; i++) {
        	Stairs s1 = new Stairs(12, 8,windows, i, i-1, this);
        	Stairs s2 = new Stairs(12, 9,windows, i, i+1, this);
        	windows.get(i).getObjects().add(s1);
        	windows.get(i).getObjects().add(s2);
        }
        Stairs s3 = new Stairs(12, 8,windows, windows.size()-1, windows.size()-2, this);
        windows.get(windows.size()-1).getObjects().add(s3);

        getActiveWindow().setObjects();

        for (ModelWindow window : windows) {
        	window.setGameObjects(window.getObjects());
        	notifyView();
        }
    }


    public void movePlayer(int x, int y) {
    	Thread r = new Thread (new MovePlayer(active_player,x,y,this));
    	r.start();
        /*int nextX = active_player.getPosX() + x; //Coordonnées du prochain déplacement (1 case)
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
        notifyView();*/
    }

    public void tirePlayer() {
    	active_player.tire(1);
    	notifyView();
    }
    public void action() {
        Activable aimedObject = null;
		for(GameObject object : getActiveWindow().getObjects()){
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
    
    public void feed() {
    	Baby aimedBaby = null;
    	for(GameObject object : getActiveWindow().getObjects()){
			if(object.isAtPosition(active_player.getFrontX(),active_player.getFrontY())){ //Si l'objet est situé devant le personnage
			    if(object instanceof Baby){
			        aimedBaby = (Baby) object;
			    }
			}
		}
		if(aimedBaby != null){
			boolean canFeed = false;
			Panade panade = null;
		    for(GameObject o : active_player.getInventory()) {
		    	if(o instanceof Panade) {
		    		canFeed = true;
		    		panade = (Panade)o;
		    	}
		    if(canFeed) {
		    	aimedBaby.regain(panade.getHunger(), panade.getLife());
		    	active_player.getInventory().remove(panade);
		    }
		}
        
    }
    }
    
    public void notifyView() {
    	getActiveWindow().update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.getActiveWindow().getObjects();
    }
    

    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
    	this.getGameObjects().remove(ps); //Permet de supprimer des objets dans la liste d'objets
        if (loot != null) {
        	getActiveWindow().getObjects().addAll(loot);
        }
        notifyView();
    }


    public void playerPos() {
        System.out.println(active_player.getPosX() + ":" + active_player.getPosY());
        
    }

	public void stop() {
		getActiveWindow().stop();
	}


	public void sendPlayer(int x, int y) { 
		boolean move = true;
		boolean change = false;
		Player pl = null;
		for (GameObject o : getActiveWindow().getObjects()) {
			if (x == o.getPosX() && y == o.getPosY()) {
				move = false;
			}
		}
		for (Player p : getActiveWindow().getPlayers()) {
			if (x == p.getPosX() && y == p.getPosY()) {
				change = true;
				pl = p;
			}
		}
		if (move == true) {
			Thread t = new Thread(new AStarThread(this, active_player, x,  y, getActiveWindow()));
			t.start(); //Active le thread qui va faire aller le joueur jusque la ou on a cliqué
		}
		if (change == true) {
			active_player = pl;
			active_player.setActivePlayer();
			this.notifyObservers();
		}
	}
	public ModelWindow getActiveWindow() {
		ModelWindow active_window = null;
		for (ModelWindow window : windows) {
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
		ArrayList<GameObject> obj = new ArrayList<GameObject>();
		for(GameObject i : getActiveWindow().getObjects()) {
			if(i.isAtPosition(x, y) && i.isAddable()) {
				if(i.isAtPosition(active_player.getFrontX(),active_player.getFrontY())){
					active_player.addtoinventory(i,obj);
					notifyView();	
				}
			}
		}
		for(GameObject j : obj) {
			getActiveWindow().getObjects().remove(j);
		}
		notifyView();
	}
		


	@Override
	public void notifyObservers() {
		for (GameObject o : getActiveWindow().getObjects()) {
			if (o instanceof Activable) {
				((Activable) o).updateActivePlayer(active_player);
			}
			if (o instanceof Gun) {
				((Gun) o).updateActiveWindow(getActiveWindow());
			}
			else if (o instanceof Player) {
				((Player) o).updateActiveWindow(getActiveWindow());
			}
			else if (o instanceof Target) {
				((Target) o).updateActiveWindow(getActiveWindow());
			}
		}
	}
	
	public void changeActiveWindow() {
		if(windows.indexOf(getActiveWindow()) < windows.size()-1){
			int i = windows.indexOf(getActiveWindow());
			getActiveWindow().makeUnactive();
			windows.get(i+1).makeActive();
		}
		
		else if(windows.indexOf(getActiveWindow()) == (windows.size()-1)) {
			getActiveWindow().makeUnactive();
			windows.get(0).makeActive();	
		}
	}
	public ArrayList<ModelWindow> getWindows(){
		return this.windows;
	}
	public void removeFromInventory(int x, int y) {
		boolean alrthere = false;
		ArrayList<GameObject> inventory = active_player.getInventory();
		for(int i =0; i<6; i++) {
			for(int j = 0; j<6; j++){
				if(x == i && y ==25+j) {
					for(GameObject k : getActiveWindow().getObjects()) {
						if(k.isAtPosition(active_player.getFrontX(),active_player.getFrontY())) {
							alrthere = true;
						}
					}
					if(alrthere == false) {
						GameObject obj = inventory.get(i+j);
						inventory.remove(obj);
						if(obj instanceof BlockBreakable) {
							((BlockBreakable) obj).attachDeletable(this);
						}
						obj.setPosition(active_player.getFrontX(),active_player.getFrontY());
						getActiveWindow().getObjects().add(obj);
						setActiveObject(0,25);
					}					
				}				
			}
		}
		active_player.setInventory(inventory);
	}
	
	public void setActivePlayer(Player p) {
		active_player = p;
	}
	
	public void shoot() {
		for (GameObject o : active_player.getInventory()) {
			if(o instanceof Gun) {
				((Gun) o).activate();
			}
		}
	}
	
	public void Save() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Fichier de sauvegarde"));
		oos.writeObject(this);
		oos.flush();
		oos.close();
	}
	
	public void setActiveObject(int x, int y) {
		System.out.println(active_player.getInventory().size());
		if(active_player.getInventory().size() != 0) {
			active_player.setActiveObject(x,y);
		}
	}
}