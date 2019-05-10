package Model;

import java.awt.Image;

public class Cherry extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("Cherry.PNG");
	
	public Cherry(int X, int Y) {
		super(X,Y,hunger,life,image,"Cherry",5);
		// TODO Auto-generated constructor stub
	}
}
