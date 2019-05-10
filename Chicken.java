package Model;

import java.awt.Image;

public class Chicken extends Food {
	private static int hunger = 15;
    private static int life = 10;
	
	public Chicken(int X, int Y) {
		super(X,Y,hunger,life,"Chicken",5);
		// TODO Auto-generated constructor stub
	}
}
