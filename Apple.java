package Model;

import java.awt.Image;

public class Apple extends Food {
    private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("Apple.PNG");
	
	public Apple(int X, int Y) {
		super(X,Y,hunger,life,image, "Apple",5);
		// TODO Auto-generated constructor stub
	}
}
