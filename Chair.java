package Model;

import java.awt.Image;

public class Chair extends Furniture{
	static Image image = getImage("Bed.JPG");
	public Chair(int x, int y) {
		super(x, y, 1, 2, image,"Chair",200);
	}

}
