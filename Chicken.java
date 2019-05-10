package Model;

import java.awt.Image;

public class Chicken extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("ChickenLeg.PNG");
	
	public Chicken(int X, int Y) {
		super(X,Y,hunger,life,image,"Chicken",5);
		// TODO Auto-generated constructor stub
	}
}
