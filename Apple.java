package Model;

import java.awt.Image;

public class Apple extends Food {
    private static int hunger = 15;
    private static int life = 10;
	
	public Apple(int X, int Y) {
		super(X,Y,hunger,life, "Apple",5);
		// TODO Auto-generated constructor stub
	}
}
