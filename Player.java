package Model;

import java.util.ArrayList;

public class Player extends GameObject implements Directable {

    int energy = 100;
    int direction = EAST;
    int floor = 0;
    int faim = 100;
    int money = 1000;
    ArrayList<GameObject> inventory = new ArrayList<GameObject>();
    public static Player active_player;

    public Player(int x, int y, int color) {
        super(x, y, color); //Le 2 est la couleur
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

	public void tire() {
		if (energy > 1)
			energy -= 1;
	}
	public void starve() {
		if (faim > 1)
			faim -= 1;
	}
	public void setActivePlayer() {
		active_player = this;
	}
	public boolean isActivePlayer() {
		return this == active_player;
	}
	public int getFaim() {
		return this.faim;
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
	public void addMoney(int money) {
		this.money += money;
	}
	public boolean isAddable() {
		return false;
	}
	public void addtoinventory(GameObject obj, ArrayList<GameObject> objects) {   
    	objects.remove(obj);                                                      
    	this.inventory.add(obj);                                                  
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
}
