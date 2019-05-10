package Model;

public class Refresh implements Runnable {
	Game game;
	
	public Refresh (Game g) {
		this.game = g;
	}

	@Override
	public void run() {
		int i = 0;
		while (i==0) {
			game.notifyView();
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

// ce thread permet d'effectuer une refresh toute les 100 secondes, il remplace tous les notify viexw présent dans nos autres threads, mais 
// Attention, il ne remplace pas les notifyview arrivant juste après les actions instantanées, cela pourrait induire un décallage.