package Model;

import java.awt.Image;

public class Adult extends Player implements Worker{
	static Image image = getImage("Adult1.PNG");
	public Adult(int x, int y) {
		super(x,y,image);
	}
}
