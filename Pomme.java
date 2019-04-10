package Model;

public class Pomme extends BlockBreakable {
    int nourriture = 15;
    int vie = 10;
	
	
	public Pomme(int X, int Y, int color) {
		super(X, Y, color, 1);
		// TODO Auto-generated constructor stub
	}
	
	public void activate() {
		this.crush();
		active_player.regain(nourriture,vie);
		
	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return true;
	}
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;	
	}
}
