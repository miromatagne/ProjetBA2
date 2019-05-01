package Model;

import View.Window;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements DeletableObserver, Observer {
    private Player active_player = null; 
    private static ArrayList<Window> windows;
    private int size;
    int nbBebe;
    int nbAdulte;
    int nbVieux;
    ObjectFactory objectFactory = new ObjectFactory();
    //private int numberOfBreakableBlocks = 40;
    public Game(ArrayList<Window> windows, int nbBebe, int nbAdulte, int nbVieux) {
        Game.windows = windows;
        this.nbBebe = nbBebe;
        this.nbAdulte = nbAdulte;
        this.nbVieux = nbVieux;
        FileReader fileReader;
        String line = null;
    	List<String> ar = new ArrayList<String>();
    	int nbLines = 0;
    	int nbColumns = 0;
		try {
			fileReader = new FileReader("C:\\Users\\Miro-Manuel\\Dropbox\\ULB\\BA2\\Projet Info\\Model\\Etage0.txt");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        //nbColumns = bufferedReader.readLine().split(" ").length;
	        while((line = bufferedReader.readLine()) != null) {
	        	nbLines += 1;
	        	String str[] = line.split(" ");
	        	nbColumns = str.length;
	        	//System.out.println(str[1]);
	        	for(int i = 0; i < str.length; i++) {
	        		ar.add(str[i]);
	        	}
	        }
	        bufferedReader.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ligne = 0;
		int colonne = 0;
		GameObject obj;
		System.out.println(nbColumns);
		System.out.println(nbLines);
		//System.out.println(ar.size());
		for(String o : ar) {
			obj = objectFactory.createObject(o, colonne, ligne);
			System.out.println(obj instanceof Vodka);
			if(obj != null) {
				windows.get(0).objects.add(obj);
			}
			//System.out.println(windows.get(0).objects.size());
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
        //Computer o1 = new Computer(20,20,5);
        //Vodka v = new Vodka(20,23,1);
        //Tree T1 = new Tree(3,13);
        //Tree T2 = new Tree(37,18);
        //T1.attachDeletable(this);
        //T2.attachDeletable(this);
        //Shower sh = new Shower(13,13);
        //Apple po = new Apple(10,14);
        //Apple po2 = new Apple(10,15);
        //Apple po3 = new Apple(10,16);
        //v.attachDeletable(this);
        //po.attachDeletable(this);
        //po2.attachDeletable(this);
        //po3.attachDeletable(this);
        //Thread A1 = new Thread(new AppleAppearance(this,windows,T1));
        //A1.start();
        //Thread A2 = new Thread(new AppleAppearance(this,windows,T2));
        //A2.start();
        Window windowInit = windows.get(0);
        Stairs s = new Stairs(12, 8,windows, 0, 1, this);
        windowInit.objects.add(s);
        for(int i = 1; i < windows.size() - 1; i++) {
        	Stairs s1 = new Stairs(12, 8,windows, i, i-1, this);
        	Stairs s2 = new Stairs(12, 9,windows, i, i+1, this);
        	windows.get(i).objects.add(s1);
        	windows.get(i).objects.add(s2);
        }
        Stairs s3 = new Stairs(12, 8,windows, windows.size()-1, windows.size()-2, this);
        windows.get(windows.size()-1).objects.add(s3);
        //Stairs s1 = new Stairs(12, 8,windows, 1, 0, this);
        //Stairs s2 = new Stairs(20, 4,windows, 1, 2, this);
        //Stairs s3 = new Stairs(20, 4,windows, 2, 1, this);
        Toilet WC = new Toilet(20,25);
        Bed b = new Bed(14,8);
        Pill pill = new Pill(18,8);
        /*windowInit.objects.add(v);
        windowInit.objects.add(b);
        windowInit.objects.add(sh);
        windowInit.objects.add(WC);
        windowInit.objects.add(pill);
        pill.attachDeletable(this);
        windowInit.objects.add(o1);
        windowInit.objects.add(T1);
        windowInit.objects.add(po);
        windowInit.objects.add(po2);
        windowInit.objects.add(po3);
        windowInit.objects.add(s);*/
        //windows.get(1).objects.add(s1);
        //windows.get(1).objects.add(s2);
        //windows.get(2).objects.add(s3);

        size = windows.get(0).getMapSize();
        // Creating one Player at position (10,10)

        
        for (int i = 0; i < nbAdulte; i++) {
        	Adult a = new Adult(10+i, 10);
        	windowInit.objects.add(a);
        	windowInit.players.add(a);
            Thread t1 = new Thread(new EnergyLoss(a));
    		t1.start();
        	//window.setPlayer(p);
        }
        for (int i = 0; i < nbVieux; i++) {
        	Eldery e = new Eldery(10+i, 11);
        	windowInit.objects.add(e);
        	windowInit.players.add(e);
            Thread t1 = new Thread(new EnergyLoss(e));
    		t1.start();
        	//window.setPlayer(p);
        }
        for (int i = 0; i < nbBebe; i++) {
        	Baby baby = new Baby(10+i, 12);
        	windowInit.objects.add(baby);
        	windowInit.players.add(baby);
            Thread t1 = new Thread(new EnergyLoss(baby));
    		t1.start();
        	//window.setPlayer(p);
        }
        
        //active_window = window;
        windowInit.setPlayers(windowInit.players);
        windowInit.setActiveWindow();
        //players.get(0); //obtenir le premier joueur
        
        active_player = windowInit.players.get(0);
        active_player.setActivePlayer();//premier joueur actif par défaut
        this.notifyObservers();
		Thread r1 = new Thread(new Refresh(this));
        r1.start();
        getActiveWindow(windows).setObjects();

        // Map building
       /*for (int i = 0; i < size; i++) { //crée le contour de blocs gris foncé, incassables
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
        	
        }*/
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

    public void notifyView() {
    	getActiveWindow(windows).update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.getActiveWindow(windows).objects;
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
	
	public void changeActiveWindow() {
		if(windows.indexOf(getActiveWindow(windows)) < windows.size()-1){
			int i = windows.indexOf(getActiveWindow(windows));
			getActiveWindow(windows).makeUnactive();
			windows.get(i+1).makeActive();
		}
		
		else if(windows.indexOf(getActiveWindow(windows)) == (windows.size()-1)) {
			getActiveWindow(windows).makeUnactive();
			windows.get(0).makeActive();	
		}
	}
	public ArrayList<Window> getWindows(){
		return this.windows;
	}
	public void removeFromInventory(int x, int y) {
		System.out.println("Je vais retirer mon objet "+ x+"  "+ y);
		ArrayList<GameObject> inventory = active_player.getInventory();
		for(int i =0; i<6; i++) {
			for(int j = 0; j<6; j++){
				if(x == i && y ==25+j) {
					GameObject obj = inventory.get(i+j);
					System.out.println(inventory.get(i+j).getColor());
					inventory.remove(obj);
					obj.setPosition(active_player.getFrontX(),active_player.getFrontY());
					getActiveWindow(windows).objects.add(obj);
					
					
				}
				
			}
		}
		active_player.setInventory(inventory);
		for (Window window : windows) {
			window.setInventory(active_player.getInventory()); // on represente l'inventaire de l'active player dans toutes les windows
			
		}
		notifyView();
	}
	

}