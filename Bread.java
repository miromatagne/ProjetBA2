package Model;

import java.awt.Image;

public class Bread extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("Bread.PNG");
	
	public Bread(int X, int Y) {
		super(X,Y,hunger,life,image,"Bread",5);
		// TODO Auto-generated constructor stub
	}
}
