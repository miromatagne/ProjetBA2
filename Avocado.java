package Model;

import java.awt.Image;

public class Avocado extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("Avocado.PNG");
	
	public Avocado(int X, int Y) {
		super(X,Y,hunger,life,image,"Avocado",5);
		// TODO Auto-generated constructor stub
	}
}
