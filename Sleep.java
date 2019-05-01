package Model;

public class Sleep implements Runnable {
	Player active_player;
	Bed bed;
	
	
	public Sleep (Player p, Bed b) {
		this.active_player=p;
		this.bed=b;
	
	}
	

	@Override
	public void run() {
		active_player.setInBed(true);
		while(active_player.isAtPosition(bed.getPosX(),bed.getPosY())){
			active_player.sleep();
		//	game.notifyView();    ce notifyView n'est plus nécéssaire grace au thread Refresh	
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		active_player.setInBed(false);
		
		
		
	}
	

}
