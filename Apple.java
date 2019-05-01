package Model;

public class Apple extends BlockBreakable {
    int hunger = 15;
    int life = 10;
	
	
	public Apple(int X, int Y) {
		super(X, Y, 1, 1);
		// TODO Auto-generated constructor stub
	}
	
	public void activate() {
		this.crush();
		active_player.regain(hunger,life);		
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
