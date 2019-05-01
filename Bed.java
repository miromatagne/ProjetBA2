package Model;

public class Bed extends BlockUnbreakable implements Activable {
	Player active_player = null;
	
	public Bed (int x, int y){
		super(x,y,3);
		
		
	}
	public void activate() {
		this.active_player.setPosition(this.getPosX(),this.getPosY());
		Thread t1 = new Thread(new Sleep(active_player,this));
		t1.start();	
	}

	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void updateActivePlayer(Player active_player) {
		this.active_player= active_player;	
	}


}

