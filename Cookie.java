package Model;

import java.awt.Image;

public class Cookie extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("Cookie.PNG");
	
	public Cookie(int X, int Y) {
		super(X,Y,hunger,life,image,"Cookie",5);
		// TODO Auto-generated constructor stub
	}
}
