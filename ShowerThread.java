package Model;

public class ShowerThread implements Runnable {
	Player p;
	Shower s;
	public ShowerThread(Player p, Shower s) {
		this.p = p;
		this.s = s;
	}
	public void run() {
		// TODO Auto-generated method stub
		while(p.isAtPosition(s.getPosX(),s.getPosY())){
			p.setInShower(true);
			p.clean();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		s.notInS();
	}
	
}
