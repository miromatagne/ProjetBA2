package Model;

import java.awt.Image;

public class Cheese extends Food {
	private static int hunger = 15;
    private static int life = 10;
	
	public Cheese(int X, int Y) {
		super(X,Y,hunger,life,"Cheese",5);
		// TODO Auto-generated constructor stub
	}
}
