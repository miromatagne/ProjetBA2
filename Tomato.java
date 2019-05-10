package Model;

import java.awt.Image;

public class Tomato extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("Tomato.PNG");
	
	public Tomato(int X, int Y) {
		super(X,Y,hunger,life,image,"Tomato",5);
		// TODO Auto-generated constructor stub
	}
}
