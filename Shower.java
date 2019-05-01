package Model;

public class Shower extends BlockUnbreakable implements Activable {
	Player active_player;
	public Shower(int x, int y) {
		super(x,y,4);
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		this.active_player.setPosition(this.posX, this.posY);
		Thread t1 = new Thread(new ShowerThread(active_player, this));
		t1.start();
	}

	@Override
	public void updateActivePlayer(Player active_player) {
		// TODO Auto-generated method stub
		this.active_player = active_player;
	}
}