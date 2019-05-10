package Model;

import java.awt.Image;

public class Pill extends BlockBreakable {
	int health = 20;
	public Pill(int X, int Y) {
		super(X, Y, 1, 1, 4);
		}

	Player active_player = null;
	
	public void activate() {
		if (active_player instanceof Eldery) {
			this.crush();
			((Eldery)active_player).gainHealth(health);
		}
	}

	@Override
	public boolean isAddable() {
		return true;
	}
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;	
	}
}

