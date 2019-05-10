package Model;

import java.awt.Image;

public class Toilet extends BlockUnbreakable implements Activable {
	Player active_player;
	static Image image = getImage("Toilet.PNG");
	
	public Toilet(int x, int y) {
		super(x,y,1,1,image);
	}
	
	public void activate() {
		active_player.pee();
		active_player.setSpeed(0);
		
	}

	@Override
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;
		
	}

}
