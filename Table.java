package Model;

import java.awt.Image;

public class Table extends Furniture{
	static Image image = getImage("Bed.JPG");
	public Table(int x, int y) {
		super(x, y, 1, 2, image,"Table",200);
	}

}
