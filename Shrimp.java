package Model;

import java.awt.Image;

public class Shrimp extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("Shrimp.PNG");
	
	public Shrimp(int X, int Y) {
		super(X,Y,hunger,life,image,"Shrimp",5);
		// TODO Auto-generated constructor stub
	}
}
