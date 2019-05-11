package Model;
	
	import java.awt.Image;
import java.util.ArrayList;

import View.Window;
	
	public class Player extends GameObject implements Directable {
	
	    int energy = 100;
	    int direction = EAST;
	    int floor = 0;
	    int faim = 100;
	    int a = 0;
	    int b = 25;
	    int money = 1000;
	    int vessie = 0;
	    int hygiene = 100;
	    double alcoolemie = 0;
	    GameObject active_object;
	    ArrayList<GameObject> inventory = new ArrayList<GameObject>();
	    public static Player active_player;
	    boolean inBed = false;
	    boolean inShower = false;
	    public int speed = 0;
	    ModelWindow w;
	    
	   
	
	    public Player(int x, int y) {
	        super(x, y, 1, 1); //Le 2 est la couleur
	    }
	
	    public void move(int X, int Y) {
	    	
	        this.posX = this.posX + X;
	        this.posY = this.posY + Y;
	    }
	    
	
	    public void rotate(int x, int y) {
	        if(x == 0 && y == -1)
	            direction = NORTH;
	        else if(x == 0 && y == 1)
	            direction = SOUTH;
	        else if(x == 1 && y == 0)
	            direction = EAST;
	        else if(x == -1 && y == 0)
	            direction = WEST;
	    }
	
	   // //////////////////////////////////////////////////////////////////////////////////////
	
	
	    @Override
	    public boolean isObstacle() {
	        return true;
	    }
	
	    @Override
	    public int getDirection() {
	    return direction;
	    }
	
	    public int getFrontX() {
	        int delta = 0;
	        if (direction % 2 == 0){
	            delta += 1 - direction;
	        }
	        return this.posX + delta;
	    }
	
	    public int getFrontY() {
	        int delta = 0;
	        if (direction % 2 != 0){
	            delta += direction - 2;
	        }
	        return this.posY + delta;
	    }
	
	    public int getEnergy() {
	    	return energy;
	    }
	    
	    public void NeedToPee(){
	    	if(vessie < 100) {
	    		vessie+=10;
	    		
	    	}else {
	    		vessie=100;
	    		setSpeed(150);
	    	}
	    	
	    	
	    	
	    }
	
		public void tire(int i) {
			if (energy > i) {
				energy -= i;
			}
			else {
				energy = 0;
				this.kill();
			}
		}
		public void starve() {
			if (faim > 1)
				faim -= 1;
		}
		public void setActivePlayer() {
			active_player = this;
			//System.out.println(active_player.getColor());
		}
		public boolean isActivePlayer() {
			return this == active_player;
		}
		public int getFaim() {
			return this.faim;
		}
		
		public int getVessie() {
			return this.vessie;
		}
		
		public void setFaim(int faim) {
			this.faim = faim;
		}
		public void setEnergy(int energy) {
			this.energy = energy;
		}
		public int getMoney() {
			return this.money;
		}
		public void setMoney(int money) {
			this.money += money;
		}
		
		public void addMoney(int money) {
			this.money += money;
		}
		
		public boolean isAddable() {
			return false;
		}
		public void addtoinventory(GameObject obj) {                                                       
	    	this.inventory.add(obj);                                                  
	    }
		public void addtoinventory(GameObject obj, ArrayList<GameObject> objects) {   
	    	objects.add(obj);                                                      
	    	this.inventory.add(obj);                                                  
	    }
		
		public void setInventory(ArrayList<GameObject> inventory) {
			this.inventory = inventory;
			
		}
		
	    public ArrayList<GameObject> getInventory() {
	    	return this.inventory;
	    }
	
	    public void regain(int i, int j) {
	    	this.faim+=i;
	    	if(this.faim>=100) {
	    		this.faim = 100;
	
	    	}
	
	    	this.energy+=j;
	    	if(this.energy>=100) {
	    		this.energy = 100;
	    	}
	
	    }
	    
	    public void sleep() {
	    	energy+=1;
	    	if(energy>=100) {
	    		energy = 100;
	    	}
	   
	    }
	    
	    public void setInBed(boolean TF) {
	    	inBed = TF;
	    }
	    
	    public boolean isInBed() {
	    	return(inBed);
	    }
	    
	    public boolean isInShower() {
	    	return(inShower);
	    }
	    
	    public void setInShower(boolean b) {
	    	inShower = b;
	    }
	    
	    public void pee() {
	    	vessie = 0;	
	    }
	    
	    public void setSpeed(int speed) {
	    	this.speed = speed;
	    }
	    
	    public double getAlcoolemie() {
	    	return this.alcoolemie;
	    }
	    
	    public void clean() {
	    	if(this.hygiene > 99) {
	    		this.hygiene = 100;
	    	}
	    	else {
	    		this.hygiene += 1;
	    	}
	    }
	    
	    public int getHygiene() {
	    	return this.hygiene;
	    }
	    
	    public void lowerHygiene() {
	    	this.hygiene -= 1;
	    }
	    
	    public void drink(double x) {
	    	this.alcoolemie+=x;
	    	if(alcoolemie >= 500) {
	    		alcoolemie = 500;
	    	}
	    }
	    
	    public boolean NotDrunk() {
	    	if(alcoolemie < 50) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    
	    public boolean BitDrunk() {
	    	if(alcoolemie >= 50 && alcoolemie <= 100) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    
	    public boolean Drunk() {
	    	if(alcoolemie > 100 && alcoolemie <= 300) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }

	    public boolean DeadDrunk() {
	    	if(alcoolemie >=300 && alcoolemie <= 500) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    
	    public void getShot() {
	    	if(this.energy > 50) {
	    		this.energy -= 50;
	    	}
	    	else {
	    		this.kill();
	    	}
	    }
	    
	    public void Debourre() {
	    	if(alcoolemie>0) {
	    	alcoolemie-=5;
	    	}	
	    }
	    
	    public void updateActiveWindow(ModelWindow w) {
	    	this.w = w;
	    }
	    
	    public void kill() {
	    	w.getObjects().remove(this);
	    }
	    public void setActiveObject(int a, int b) {
	    	this.a = a;
	    	this.b= b;
	    	active_object = this.getInventory().get(a+5*(b-25));
	    }
	    public int getA() {
	    	return a;
	    }
	    
	    public int getB() {
	    	return b;
	    }
	    
	    public GameObject getActiveObject() {
	    	return this.active_object;
	    }
	    
	}