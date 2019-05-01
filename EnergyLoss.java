package Model;
	
	import View.Window;
	
	public class EnergyLoss implements Runnable {
		Player p;
		Window window;
		public EnergyLoss(Player p) {
			this.p = p;
		}
		public void run() {
			while (p.getEnergy() > 0 && p.getFaim() > 0 ) {
				p.starve();
				p.NeedToPee();
				if(p.isInShower()==false) {
					p.lowerHygiene();
				}
				if(p.isInBed()==false) {
					p.tire();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//		window.update();  ceci n'est plus nécéssaire grâce au thread Refresh
			}
		}
	
	}