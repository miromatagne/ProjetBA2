package Model;

import java.util.ArrayList;

import View.Window;



public class TimerEnergy extends Thread{
	public Player p;
	public Window window;
	public ArrayList<GameObject> objects;
	
	public TimerEnergy (Player p, Window j, ArrayList<GameObject> objects) {
		this.p=p;
		this.window= j;
		this.objects = objects;
	}

	
	
	public void run() {
		int x = p.getEnergy();
		int y = p.getFaim();
		System.out.println(x + " " + y);
		while( x > 0 && y > 0) {
			x = x - 30;
			y = y - 30;
			p.setEnergy(x);
			p.setFaim(y);
			
			System.out.println("Je perds de la vie et j'ai faim");
			System.out.println(x);
			System.out.println(y);
			if(p.getEnergy()<=0 || p.getFaim()<=0) {
				objects.remove(p);
				
			}
			window.update();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	
	

}
