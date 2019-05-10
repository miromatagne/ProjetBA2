package Model;

import java.awt.Image;

public class Food extends BlockBreakable implements SBableObject {
	int hunger;
	int life;
	String name;
	int price;
	public Food(int x, int y, int hunger, int life, String name, int price) {
		super(x,y,1,1,1);
		this.price = price;
		this.hunger = hunger;
		this.life = life;
		this.name = name;
	}
	public void activate() {
		if(!(active_player instanceof Baby)) {
			this.crush();
			active_player.regain(hunger,life);
		}
	}
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;	
	}

	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}
}
