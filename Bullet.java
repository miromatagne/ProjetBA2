package Model;

import java.awt.Image;

public class Bullet extends BlockBreakable  {
	int direction; 
	static Image image = getImage("Bullet.PNG");
	public Bullet(int X, int Y) {
		super(X, Y, 1, 1, 2, image);
		// TODO Auto-generated constructor stub
	}
	
	public void setDirection(int i) {
		this.direction = i;
	}
	
	public int getDirection() {
		return this.direction;
	}
}
