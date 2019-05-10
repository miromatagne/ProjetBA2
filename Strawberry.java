package Model;

import java.awt.Image;

public class Strawberry extends Food {
	private static int hunger = 15;
    private static int life = 10;
    static Image image = getImage("Strawberry.PNG");
	
	public Strawberry(int X, int Y) {
		super(X,Y,hunger,life,image,"Strawberry",5);
		// TODO Auto-generated constructor stub
	}
}
