package Model;

import java.awt.Image;

public class Baby extends Player{
	static Image image = getImage("Baby1.PNG");
	public Baby(int x, int y) {
		super(x,y,image);
	}
}
