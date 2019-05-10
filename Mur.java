package Model;

import java.awt.Image;

public class Mur extends BlockUnbreakable {
	static Image image = getImage("Wall3.JPG");
    public Mur(int X, int Y) {
    	super(X,Y,1,1,image);
    }
}
