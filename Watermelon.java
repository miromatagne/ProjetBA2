package Model;

import java.awt.Image;

public class Watermelon extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("MelonWater.PNG");
	
	public Watermelon(int X, int Y) {
		super(X,Y,hunger,life,"Watermelon",5);
		// TODO Auto-generated constructor stub
	}
}
