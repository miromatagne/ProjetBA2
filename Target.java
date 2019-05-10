package Model;

import java.awt.Image;

import View.Window;

public class Target extends BlockBreakable {
	ModelWindow w;
	private boolean add = true;
	public Target(int x, int y) {
		super(x,y,1,1,1);
		Thread t1 = new Thread(new TargetThread(this));
		t1.start();
	}
	
	public void updateActiveWindow(ModelWindow w) {
		this.w = w;
	}
}
