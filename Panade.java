package Model;

import java.awt.Image;

public class Panade extends Food{
	private static int hunger = 20;
	private static int life = 10;
	static Image image = getImage("Panade.PNG");
	public Panade(int x, int y) {
		super(x,y,hunger,life,image,"Panade",5);
	}
	
	public int getHunger() {
		return hunger;
	}
	
	public int getLife() {
		return life;
	}
}
