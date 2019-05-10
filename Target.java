package Model;

import java.awt.Image;

import View.Window;

public class Target extends BlockBreakable {
	Window w;
	private boolean add = true;
	public static Image image = getImage("Target.PNG");
	public Target(int x, int y) {
		super(x,y,1,1,1,image);
		Thread t1 = new Thread(new TargetThread(this));
		t1.start();
	}
	
	public void updateActiveWindow(Window w) {
		this.w = w;
	}
}
