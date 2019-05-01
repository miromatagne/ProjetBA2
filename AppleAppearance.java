package Model;

import java.util.ArrayList;


import View.Window;
public class AppleAppearance implements Runnable {
	Game g;
	ArrayList<Window> windows;
	Tree tree;
	
	public AppleAppearance (Game g, ArrayList<Window> windows, Tree t) {
		this.g=g;
		this.windows = windows;
		this.tree=t;
		
	}

	@Override
	public void run() {
		int i =0;
		int x = tree.getPosX();
		int y = tree.getPosY();
		while(i==0) {		
			Apple a = new Apple(x,y+1);
			a.attachDeletable(g);
			g.getActiveWindow(windows).objects.add(a);
			
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
		
	}


