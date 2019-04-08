package Model;

import View.Window;

public class EnergyLoss implements Runnable {
	Player p;
	Window window;
	public EnergyLoss(Player p, Window window) {
		this.p = p;
		this.window = window;
	}
	public void run() {
		while (p.getEnergy() > 0 && p.isActivePlayer() && window.isActiveWindow() && p.getFaim() > 0) {
			p.tire();
			p.starve();
			//System.out.println(p.getColor());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			window.update();
		}
	}
	
}
