package Model;

import java.awt.Image;

public class Couch extends Furniture{
	static Image image = getImage("Bed.JPG");
	public Couch(int x, int y) {
		super(x, y, 1, 2, image,"Couch",200);
	}

}
